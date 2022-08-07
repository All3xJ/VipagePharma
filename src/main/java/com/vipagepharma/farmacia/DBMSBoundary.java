package com.vipagepharma.farmacia;

import java.sql.*;


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
            ResultSet resultSet = statement.executeQuery("INSERT INTO vipagepharma_farmacia.utente(nome, password, chiave_recupero, email) VALUES("+"'"+nome+"','" + pass+"','"+ chiave_recupero+"',''"+ mail+"'");
            //sito = resultSet.getBoolean(0);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }



} // class ends




