package com.vipagepharma.farmacia;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;


public class DBMSBoundary {

    private static final String url = "jdbc:mysql://vipagesite.duckdns.org:3306/";
    private static final String user = "pi";
    private static final String pass = "BubJbhvbj373838&#@!";
    private static final String dbFarmacia = "vipagepharma_farmacia";
    private static final String dbAzienda = "vipagepharma_azienda";



    public static Connection connectFarmacia(){
        Connection connection = null;
        try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url + dbFarmacia, user, pass);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    } // function ends

    public static Connection connectAzienda(){
        Connection connection = null;
        try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url + dbAzienda, user, pass);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    } // function ends

    public static boolean effettuaLogin(String id,String pass){
        boolean esito = false;
        try {
            Connection connection = connectFarmacia();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select u.* from utente u where u.id_uf = " + id + " and u.password = " + "'" + pass + "'" );
            esito = resultSet.next();
        }
        catch (SQLException e){
            return false;
        }
        return esito;
    }

    public static boolean verificaMail(String mail){
        boolean esito = false;
        try {
            Connection connection = connectFarmacia();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from utente where email = " + "'" + mail + "'");
            esito = resultSet.getBoolean(0);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return esito;
    }

    public static void registra(String nome,String mail, String pass, String chiave_recupero){
        boolean esito = false;
        try {
            Connection connection = connectFarmacia();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("INSERT INTO vipagepharma_farmacia.utente(nome, password, chiave_recupero, email) VALUES("+"'"+nome+"','" + pass+"','"+ chiave_recupero+"','"+ mail+"'");
            //esito = resultSet.getBoolean(0);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static ResultSet getInventario(){
        ResultSet resultSet;
        try{
            Connection connection = connectFarmacia();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("select ref_id_f ,qty ,isBanco  from farmaco");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public static void aggiornaContratto(String id_farmaco, String id_farmacia, String quantita){
        ResultSet resultSet;
        try{
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            statement.executeUpdate("update contratto set qty_settimanale = "+ quantita +" where ref_id_f = " + id_farmaco + "and ref_id_uf = " +  id_farmacia );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet getDati(String id_farmaco){
        ResultSet resultSet;
        try{
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from lotto where ref_id_f = " + id_farmaco);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try{
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("select id_farmaco, produzione_settimanale from farmaco where ref_id_f = " + id_farmaco);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultSet; //???
    }

    public static void creaPrenotazioneEScarica(String id_prenotazione, String id_farmacia, String id_corriere, LocalDate data_consegna, LinkedList <Integer> id_lotti, LinkedList <Integer> quantita){
        ResultSet resultSet;
        try{ //crea prenotazione
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into prenotazione(id_p, ref_id_uf, ref_id_ua, isConsegnato, data_consegna) values ("+"'"+ id_prenotazione+"','" + id_farmacia+"','"+ id_corriere+"','"+ 0+"','"+ data_consegna +"'");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for(int i=0; i<id_lotti.size(); ++i) {
            try { // scarico da tabella lotto
                Connection connection = connectAzienda();
                Statement statement = connection.createStatement();
                statement.executeUpdate("update lotto set qty = qty - " + quantita.removeFirst() + "where id_l = " + id_lotti.removeFirst());

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try { //crea lotto ordinato
                Connection connection = connectAzienda();
                Statement statement = connection.createStatement();
                statement.executeUpdate("insert into lotto_ordinato(ref_id_l, ref_id_p, isCaricato, qty) values(" + id_lotti.removeFirst() + ", " + id_prenotazione + ", " + 0 + ", " + quantita.removeFirst());

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void carica(LinkedList <Integer> quantità, LinkedList <Integer> id_lotti){ //ricarica i lotti non consegnati RISOLUZIONE PROBLEMA CONSEGNA
        ResultSet resultSet;
        for (int i=0; i< id_lotti.size(); ++i){
            try{
                Connection connection = connectAzienda();
                Statement statement = connection.createStatement();
                statement.executeUpdate("Update lotto set qty = qty +" + quantità.removeFirst() + "where id_l = " + id_lotti.removeFirst());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void creaOrdine(String id_prenotazione, String id_farmacista, String id_corriere, LocalDate data_consegna){ //RISOLUZINE PROBLEMA CONSEGNA
        ResultSet resultSet;
        try{
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into prenotazione (id_p, ref_id_uf, ref_id_ua, isConsegnato, data_consegna) values (" + id_prenotazione + "," + id_farmacista + "," + id_corriere +"," + 0 + "," + data_consegna);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



} // class ends




