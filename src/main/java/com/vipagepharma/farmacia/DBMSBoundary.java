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
        boolean esito = true;
        try {
            Connection connection = connectFarmacia();
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
            Connection connection = connectFarmacia();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO vipagepharma_farmacia.utente(nome, password, chiave_recupero, email)" +
                    "VALUES("+"'"+nome+"','" + pass+"','"+ chiave_recupero+"','"+ mail+"'"+")");
            resultSet = statement.executeQuery("SELECT LAST_INSERT_ID()");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public static boolean verificaKey(String id,String key){
        boolean esito = false;
        try {
            Connection connection = connectFarmacia();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from utente where id_uf = " + id + " and chiave_recupero = " + "'" + key + "'");
            esito = resultSet.next();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return esito;
    }

    public static void aggiornaPassword(String id,String password){
        try {
            Connection connection = connectFarmacia();
            Statement statement = connection.createStatement();
            statement.executeUpdate("update utente set password = " + "'" + password + "'" + " where id_uf =" + id);
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
            resultSet = statement .executeQuery("select ref_id_f ,qty ,isBanco  from farmaco");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public static ResultSet getFarmaco(String nome_o_principio_attivo){
        ResultSet resultSet;
        try{
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            resultSet = statement .executeQuery("SELECT * " +
                                                    "FROM farmaco f " +
                                                    "WHERE f.nome LIKE + " + "'%" + nome_o_principio_attivo + "'"+
                                                        "or f.nome LIKE" + "'" + nome_o_principio_attivo + "%'" +
                                                        "or f.nome LIKE" + "'%" + nome_o_principio_attivo + "%'" +
                                                        "or f.nome = "+ "'" + nome_o_principio_attivo + "'" +
                                                        "or f.principio_attivo  LIKE "+ "'%" + nome_o_principio_attivo + "'" +
                                                        "or f.principio_attivo LIKE "+ "'" + nome_o_principio_attivo + "%'" +
                                                        "or f.principio_attivo LIKE"+ "'%" + nome_o_principio_attivo + "%'" +
                                                        "or f.principio_attivo =" + "'" + nome_o_principio_attivo + "'");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public static ResultSet getPrenotazioni(String id_farmacia){
        ResultSet resultSet;
        try{
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            resultSet = statement .executeQuery("SELECT p.* FROM prenotazione p WHERE p.isConsegnato = 0 and p.ref_id_uf =" + id_farmacia );  //prenotazioni per la medesima farmacia e non ancora contrassegnati come consegnati
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }
    // DA COMPLETARE!!!!!!!
    public static void eliminaOrdineERicaricaFarmaci(String id_prenotazione){
        try{
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            statement .executeQuery("DELETE FROM prenotazione p WHERE p.prenotazione =" + id_prenotazione);
            // QUERY RICARICA FARMACI PER ADESSO NON NE HO IDEA
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




} // class ends




