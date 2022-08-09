package com.vipagepharma.addettoAzienda;

import java.sql.*;
import java.time.LocalDate;

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

    public static boolean effettuaLogin(int id,String pass){
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

}
