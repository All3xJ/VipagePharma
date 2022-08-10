package com.vipagepharma.corriere;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class DBMSBoundary {
    private static final String url = "jdbc:mysql://vipagesite.duckdns.org:3306/";
    private static final String user = "pi";
    private static final String pass = "BubJbhvbj373838&#@!";
    private static final String dbAzienda = "vipagepharma_azienda";


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

    public static boolean effettuaLogin(String id,String pass){
        boolean esito = false;
        try {
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select u.* from utente u where u.id_ua = " + id + " and u.password = " + "'" + pass + "'" );
            esito = resultSet.next();
        }
        catch (SQLException e){
            return false;
        }
        return esito;
    }

    public static void confermaConsegna(String id_lotto, String id_prenotazione, String isCaricato){
        ResultSet resultSet;
        try{
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            statement.executeUpdate("update lotto_ordinato set isCaricato = " + isCaricato + "where ref_id_l = " +  id_lotto +  " and ref_id_p = " +  id_prenotazione);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet getConsegneOdierne(String id_corriere){
        ResultSet resultSet;
        try{
            Connection connection = connectDBMS();
            Statement statement = connection.createStatement();
            java.util.Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
            String str = formatter.format(date);
            resultSet = statement.executeQuery("Select p.id_p, u.indirizzo from vipagepharma_azienda.prenotazione p join vipagepharma_farmacia.utente u where p.ref_id_uf = u.id_uf and p.ref_id_ua = " + id_corriere + " and p.data_consegna = data_odierna");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public static void contrassegnaOrdineFirmato(LinkedList <String> id_prenotazioni){
        ResultSet resultSet;
        for(int i=0; i<id_prenotazioni.size(); ++i){
            try{
                Connection connection = connectAzienda();
                Statement statement = connection.createStatement();
                statement.executeUpdate("update prenotazione set isConsegnato = 1 where id_p = " + id_prenotazioni.removeFirst());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void salvaRicevuta(LinkedList <String> id_prenotazioni, LinkedList <Blob> ricevute){
        ResultSet resultSet;
        for(int i=0; i<id_prenotazioni.size(); ++i){
            try{
                Connection connection = connectAzienda();
                Statement statement = connection.createStatement();
                statement.executeUpdate("update prenotazione set ricevuta_pdf = " + ricevute.removeFirst() + " where id_p = " + id_prenotazioni.removeFirst());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }



}
