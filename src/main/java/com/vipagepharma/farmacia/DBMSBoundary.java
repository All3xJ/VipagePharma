package com.vipagepharma.farmacia;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
            resultSet = statement.executeQuery("SELECT LAST_INSERT_ID() as id");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
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

    public static boolean verificaKey(String id,String key){
        boolean esito = false;
        try {
            Connection connection = connectFarmacia();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from utente where id_uf = " + id + " and chiave_recupero = " + "'" + key + "'");
            esito = resultSet.next();
        }
        catch (SQLException e){
            return false;
        }
        return esito;
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

    public static ResultSet getPrenotazioniEInfoFarmaci(String id_farmacia){
        ResultSet resultSet;
        try{
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //resultSet = statement .executeQuery("SELECT p.id_p, f.nome, p.data_consegna FROM prenotazione p, farmaco f, lotto l, lotto_ordinato lo WHERE p.isConsegnato = 0 and p.ref_id_uf =" + id_farmacia +" and p.id_p=lo.ref_id_p and lo.ref_id_l=l.id_l and l.ref_id_f=f.id_f");  //prenotazioni per la medesima farmacia e non ancora contrassegnati come consegnati
            resultSet = statement.executeQuery("SELECT p.id_p, p.ref_id_uf,f.id_f, f.nome, p.data_consegna ,p.isConsegnato FROM prenotazione p, farmaco f WHERE p.ref_id_uf =" + id_farmacia +" and p.ref_id_f=f.id_f");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public static void eliminaOrdineERicaricaFarmaci(String id_prenotazione,ArrayList<String> id_l,ArrayList<String> qty){
        try{
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM prenotazione p WHERE p.prenotazione =" + id_prenotazione);
            for(int i = 0 ; i< id_l.size();++i) {
                statement.executeUpdate("UPDATE FROM lotto l set qty = qty + " + qty.get(i)+" WHERE l.id_l =" + id_l.get(i));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void aggiornaContratto(String id_farmaco, String id_farmacia, String quantita){
        ResultSet resultSet;
        try{
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            statement.executeUpdate("update contratto set qty_settimanale = "+ quantita +" where ref_id_f = " + id_farmaco + " and ref_id_uf = " +  id_farmacia );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void creaPrenotazioneEScarica(int id_farmacia, int id_corriere,int id_farmaco,LocalDate data_consegna, ArrayList<Integer> id_lotti, ArrayList <Integer> qty_lotti){
        try{ //crea prenotazione
            Connection connection = connectAzienda();
            PreparedStatement statement1 = connection.prepareStatement("insert into prenotazione(ref_id_uf, ref_id_ua,ref_id_f, isConsegnato, data_consegna) values (?,?,?,?,?)");
            statement1.setInt(1,id_farmacia);
            statement1.setInt(2,id_corriere);
            statement1.setInt(3,id_farmaco);
            statement1.setInt(4, 0);
            statement1.setDate(5,Date.valueOf(data_consegna));
            statement1.executeUpdate();
            Statement statement2 = connection.createStatement() ;
            ResultSet rsId = statement2.executeQuery("SELECT LAST_INSERT_ID() as id");
            rsId.next();
            int id_prenotazione = rsId.getInt("id");
            for(int i=0; i<id_lotti.size(); ++i) {
                statement2.executeUpdate("update lotto set qty = qty - " + String.valueOf(qty_lotti.get(i)) + " where id_l = " + id_lotti.get(i));
                PreparedStatement statement3 = connection.prepareStatement("insert into lotto_ordinato(ref_id_l, ref_id_p, isCaricato, qty) values(?,?,?,?)");
                statement3.setInt(1,id_lotti.get(i));
                statement3.setInt(2,id_prenotazione);
                statement3.setInt(3,0);
                statement3.setInt(4,qty_lotti.get(i));
                statement3.executeUpdate();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void carica(LinkedList <String> quantità, LinkedList <String> id_lotti){ //ricarica i lotti non consegnati RISOLUZIONE PROBLEMA CONSEGNA
        ResultSet resultSet;
        for (int i=0; i< id_lotti.size(); ++i){
            try{
                Connection connection = connectAzienda();
                Statement statement = connection.createStatement();
                statement.executeUpdate("Update lotto set qty = qty +" + quantità.removeFirst() + " where id_l = " + id_lotti.removeFirst());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void creaOrdine(String id_farmacista, String id_corriere, LocalDate data_consegna){ //RISOLUZINE PROBLEMA CONSEGNA
        ResultSet resultSet;
        try{
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into prenotazione (ref_id_uf, ref_id_ua, isConsegnato, data_consegna) values (" + id_farmacista + ", " + id_corriere +", " + 0 + ", " + data_consegna);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet getLotti(String id_farmaco){
        ResultSet resultSet;
        try{ //getLotti
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("select * from lotto where ref_id_f = " + id_farmaco);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public static ResultSet getLottiOrdinati(String id_prenotazione){
        ResultSet resultSet;
        try{ //getLotti
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("select lo.ref_id_l ,l.data_di_scadenza,lo.qty from lotto_ordinato lo,lotto l where lo.ref_id_l = l.id_l and lo.ref_id_p = " + id_prenotazione);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public static ResultSet getCorrieri(String id_farmaco){
        ResultSet resultSet;
        try{
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("select id_ua from utente where isCorriere = 1");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public static ResultSet getProduzione(String id_farmaco){
        ResultSet resultSet;
        try{
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("select id_f, produzione_settimanale from farmaco where id_f= " + id_farmaco);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public static void confermaConsegna(String id_prenotazione, LinkedList <String> id_lotti){
        ResultSet resultSet;
        for (int i=0; i< id_lotti.size(); ++i){
            try{
                Connection connection = connectAzienda();
                Statement statement = connection.createStatement();
                statement.executeUpdate("Update lotto_ordinato set isCaricato = 1 where ref_id_p = " +id_prenotazione + " and ref_id_l = " + id_lotti.removeFirst());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void aggiungiCarico(String id_farmacia, String id_farmaco, String nome_farmaco, Date data_scadenza, LinkedList<String> id_lotti, LinkedList<Date>date_scadenza, LinkedList<Integer> quantita){
        ResultSet resultSet;
        for (int i=0; i< id_lotti.size(); ++i){
            try{
                Connection connection = connectFarmacia();
                Statement statement = connection.createStatement();
                statement.executeUpdate("insert into farmaco (ref_id_f, ref_id_l, ref_id_uf, nome, data_di_scadenza, quantita) values (" + id_farmaco + ", " + id_lotti.removeFirst() +", " + id_farmacia + ", " + nome_farmaco + ", " + date_scadenza.removeFirst() + ", " + quantita.removeFirst());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static ResultSet getContratti(String id_farmacia){ //diversa da quella dell'addetto
        ResultSet resultSet;
        try{
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("select * from contratto where ref_id_uf = " +id_farmacia);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }



} // class ends




