package com.vipagepharma.addettoAzienda;

import java.sql.*;
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
            ResultSet resultSet = statement.executeQuery("select u.* from utente u where u.id_ua = " + id + " and u.password = " + "'" + pass + "'" );
            esito = resultSet.next();
        }
        catch (SQLException e){
            return false;
        }
        return esito;
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
}
