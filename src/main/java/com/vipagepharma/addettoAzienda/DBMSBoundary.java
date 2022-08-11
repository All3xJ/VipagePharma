package com.vipagepharma.addettoAzienda;

import java.sql.*;
import java.util.LinkedList;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBMSBoundary {
    private static final String url = "jdbc:mysql://vipagesite.duckdns.org:3306/";
    private static final String user = "pi";
    private static final String pass = "BubJbhvbj373838&#@!";
    private static final String dbFarmacia = "vipagepharma_farmacia";
    private static final String dbAzienda = "vipagepharma_azienda";

    private static int contatore = 0;

    public static Connection connectFarmacia(){
        Connection connection = null;
        try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
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
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url + dbAzienda, user, pass);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    } // function ends

    public static Connection connectDBMS(){
        Connection connection = null;
        try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    } // function ends


    public static ResultSet getFarmaci(){
        ResultSet resultSet = null;
        try {
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from farmaco");

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }



    public static boolean effettuaLogin(String id,String pass){
        boolean esito = false;
        try {
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select u.* from utente u where u.id_ua = " + id + " and u.isCorriere = 0 and u.password = " + "'" + pass + "'" );
            esito = resultSet.next();
        }
        catch (SQLException e){
            return false;
        }
        return esito;
    }


    public static boolean verificaMail(String mail){
        boolean esito = true;
        try {
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from utente where email = " + "'" + mail + "'");
            esito = !resultSet.next();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return esito;
    }

    public static ResultSet registra(String nome,String mail, String pass, String chiave_recupero){
        ResultSet resultSet = null;
        try {
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO vipagepharma_farmacia.utente(nome, password, chiave_recupero, email,isCorriere)" +
                    "VALUES("+"'"+nome+"','" + pass+"','"+ chiave_recupero+"','"+ mail+"',0"+")");
            resultSet = statement.executeQuery("SELECT LAST_INSERT_ID() as id");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public static void aggiornaPassword(String id,String password){
        try {
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            statement.executeUpdate("update utente set password = " + "'" + password + "'" + " where id_ua =" + id);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static boolean verificaKey(String id,String key){
        boolean esito = false;
        try {
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from utente where id_ua = " + id + " and chiave_recupero = " + "'" + key + "'");
            esito = resultSet.next();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return esito;
    }




    public static void creaLotto(int ID_farmaco, LocalDate dataScadenza, int qty){
        try {
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            LocalDate dataDisponibilita = LocalDate.now().plusMonths(2);
            ResultSet resultSet = statement.executeQuery("INSERT INTO lotto (ref_id_f , qty, data_di_disponibilitá, data_di_scadenza)\n" +
                    "VALUES('"+ID_farmaco+"','"+qty+"','"+dataDisponibilita+"','"+dataScadenza+"');");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static ResultSet getContratti(){  // mettere che lo fa alle 9 e fa produzione alle 8 quindi invertiti.
        ResultSet resultSet = null;
        try {
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from contratto");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet getConsegneOdierneNonCaricate(){
        ResultSet resultSet;
        try{
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from lotto_ordinato where isCaricato = 0");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public static void setFlagProblema(String id_prenotazione){
        ResultSet resultSet;
        try{
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            statement.executeUpdate("Update prenotazione set problema = 1 where id_p = " + id_prenotazione);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void aggiungiEConfermaCarico(LinkedList<Integer> id_lotti, String quantita, String id_farmaco){
        ResultSet resultSet;
        try{
            Connection connection = connectFarmacia();
            Statement statement = connection.createStatement();
            statement.executeUpdate("update farmaci set qty = qty + " + quantita + "where ref_id_f = " + id_farmaco);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for(int i=0; i<id_lotti.size(); ++i) {
            try {
                Connection connection = connectAzienda();
                Statement statement = connection.createStatement();
                statement.executeUpdate("update lotto_ordinato set isCaricato = 1 where ref_id_l = id_lotti.removeFirst()");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static ResultSet getElencoConsegneConSegnalazioni(){
        ResultSet resultSet;
        try{
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("select ref_id_uf, id_p, data_consegna from prenotazione where problema = 1");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public static ResultSet getConsegneRecenti(){
        ResultSet resultSet;
        try{
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("Select id_p, ref_id_uf, data_consegna from prenotazione where isConsegnato = 1 limit 15"); //vedere se funziona sintassi
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public static ResultSet getAltreConsegne(){
        ++contatore;
        int base = contatore * 15;
        ResultSet resultSet;
        try{
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("Select id_p, ref_id_uf, data_consegna from prenotazione where isConsegnato=1 limit " + base + ", 15"); //vedere se funziona sintassi
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

}
