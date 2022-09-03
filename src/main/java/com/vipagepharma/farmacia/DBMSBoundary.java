package com.vipagepharma.farmacia;

import com.vipagepharma.farmacia.entity.Lotto;

import java.sql.*;
import java.text.SimpleDateFormat;
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

    public static Connection connectDBMS(){
        Connection connection = null;
        try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
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
            ResultSet resultSet = statement.executeQuery("select u.* from utente u where u.id_utente_farmacia = " + id + " and u.password = " + "'" + pass + "'" );
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
            statement.executeUpdate("update utente set password = " + "'" + password + "'" + " where id_utente_farmacia =" + id);
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
            ResultSet resultSet = statement.executeQuery("select * from utente where id_utente_farmacia = " + id + " and chiave_recupero = " + "'" + key + "'");
            esito = resultSet.next();
        }
        catch (SQLException e){
            return false;
        }
        return esito;
    }


    public static ResultSet getInventario(String id_farmacia){
        ResultSet resultSet;
        try{
            Connection connection = connectFarmacia();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from farmaco where id_utente_farmacia =" + id_farmacia);
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
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            java.util.Date date = new java.util.Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
            String strDataOdierna = formatter.format(date);
            resultSet = statement.executeQuery("SELECT p.id_utente_azienda,p.id_prenotazione, p.id_utente_farmacia,f.id_farmaco, f.nome, p.data_consegna ,p.isConsegnato,p.quantita,f.isBanco FROM prenotazione p, farmaco f WHERE p.id_utente_farmacia =" + id_farmacia +" and p.id_farmaco=f.id_farmaco" + " and p.isCaricato = 0 and p.data_consegna >=  str_to_date('"+strDataOdierna+"','%d-%m-%Y') order by data_consegna asc");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public static Connection annullaConRollback(String id_prenotazione,ArrayList<String> id_l,ArrayList<String> qty){
        Connection connection = connectAzienda();
        try{
            Statement statement = connection.createStatement();
            connection.setAutoCommit(false);
            statement.executeUpdate("DELETE FROM lotto_ordinato WHERE id_prenotazione = "+ id_prenotazione);
            for(int i = 0 ; i< id_l.size();++i) {
                statement.executeUpdate("UPDATE lotto set quantita_ordinabile = quantita_ordinabile + " + qty.get(i)+" WHERE id_lotto =" + id_l.get(i));
            }
            statement.executeUpdate("DELETE FROM prenotazione  WHERE id_prenotazione = " + id_prenotazione);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void eliminaOrdineERicaricaFarmaci(String id_prenotazione,ArrayList<String> id_lotto,ArrayList<String> qty){

        try{
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM lotto_ordinato WHERE id_prenotazione = "+ id_prenotazione);
            for(int i = 0 ; i< id_lotto.size();++i) {
                statement.executeUpdate("UPDATE lotto set quantita_ordinabile = quantita_ordinabile + " + qty.get(i)+" WHERE id_lotto =" + id_lotto.get(i));
            }
            statement.executeUpdate("DELETE FROM prenotazione  WHERE id_prenotazione = " + id_prenotazione);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void creaPrenotazioneEScarica(int id_farmacia, int id_corriere,int id_farmaco,LocalDate data_consegna, ArrayList<Integer> id_lotti, ArrayList <Integer> qty_lotti,int qty){
        try{ //crea prenotazione
            Connection connection = connectAzienda();
            PreparedStatement statement1 = connection.prepareStatement("insert into prenotazione(id_utente_farmacia, id_utente_azienda,id_farmaco, isConsegnato, data_consegna,quantita) values (?,?,?,?,?,?)");
            statement1.setInt(1,id_farmacia);
            statement1.setInt(2,id_corriere);
            statement1.setInt(3,id_farmaco);
            statement1.setInt(4, 0);
            statement1.setDate(5,Date.valueOf(data_consegna));
            statement1.setInt(6,qty);
            statement1.executeUpdate();
            Statement statement2 = connection.createStatement() ;
            ResultSet rsId = statement2.executeQuery("SELECT LAST_INSERT_ID() as id");
            rsId.next();
            int id_prenotazione = rsId.getInt("id");
            for(int i=0; i<id_lotti.size(); ++i) {
                statement2.executeUpdate("update lotto set quantita_ordinabile = quantita_ordinabile - " + String.valueOf(qty_lotti.get(i)) + " where id_lotto = " + id_lotti.get(i));
                PreparedStatement statement3 = connection.prepareStatement("insert into lotto_ordinato(id_lotto, id_prenotazione, isCaricato, quantita) values(?,?,?,?)");
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

    public static void modificaPrenotazioneEAggiornaLotti(int id_prenotazione,int id_farmacia, int id_corriere,int id_farmaco,LocalDate data_consegna, ArrayList<Integer> id_lotti, ArrayList <Integer> qty_lotti,int qty){
        try{ //crea prenotazione
            Connection connection = connectAzienda();
            PreparedStatement statement1 = connection.prepareStatement("insert into prenotazione(id_prenotazione,id_utente_farmacia, id_utente_azienda,id_farmaco, isConsegnato, data_consegna,quantita) values (?,?,?,?,?,?,?)");
            statement1.setInt(1,id_prenotazione);
            statement1.setInt(2,id_farmacia);
            statement1.setInt(3,id_corriere);
            statement1.setInt(4,id_farmaco);
            statement1.setInt(5, 0);
            statement1.setDate(6,Date.valueOf(data_consegna));
            statement1.setInt(7,qty);
            statement1.executeUpdate();
            Statement statement2 = connection.createStatement() ;
            for(int i=0; i<id_lotti.size(); ++i) {
                statement2.executeUpdate("update lotto set quantita_ordinabile = quantita_ordinabile - " + String.valueOf(qty_lotti.get(i)) + " where id_lotto = " + id_lotti.get(i));
                PreparedStatement statement3 = connection.prepareStatement("insert into lotto_ordinato(id_lotto, id_prenotazione, isCaricato, quantita) values(?,?,?,?)");
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

    public static ResultSet getLotti(String id_farmaco){
        ResultSet resultSet;
        try{ //getLotti
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("select * from lotto where id_farmaco = " + id_farmaco);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public static LinkedList<Object> getLotti(String id_prenotazione,ArrayList<String> id_l,ArrayList<String> qty,String id_farmaco){
        LinkedList<Object> lotti = new LinkedList<>();
        ResultSet resultSet;
        try{ //getLotti
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            connection.setAutoCommit(false);
            statement.executeUpdate("DELETE FROM lotto_ordinato WHERE id_prenotazione = "+ id_prenotazione);
            for(int i = 0 ; i< id_l.size();++i) {
                statement.executeUpdate("UPDATE lotto set quantita_ordinabile = quantita_ordinabile + " + qty.get(i)+" WHERE id_lotto =" + id_l.get(i));
            }
            statement.executeUpdate("DELETE FROM prenotazione  WHERE id_prenotazione = " + id_prenotazione);
            resultSet = statement.executeQuery("select * from lotto where id_farmaco = " + id_farmaco);
            lotti.add(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        lotti.add(resultSet);
        return lotti;
    }

    public static ResultSet getLottiOrdinati(String id_prenotazione){
        ResultSet resultSet;
        try{ //getLotti
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("select lo.id_lotto ,l.data_scadenza,l.data_disponibilita,lo.quantita from lotto_ordinato lo,lotto l where lo.id_lotto = l.id_lotto and lo.id_prenotazione = " + id_prenotazione);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public static ResultSet getCorrieri(){
        ResultSet resultSet;
        try{
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
            resultSet = statement.executeQuery("select id_utente_azienda from utente where isCorriere = 1");
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
            resultSet = statement.executeQuery("select id_farmaco, produzione_settimanale from farmaco where id_farmaco= " + id_farmaco);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public static void aggiungiCarico(String id_farmacia, String id_farmaco, String nome_farmaco,LinkedList<String> id_lotti, LinkedList<String>date_scadenza, LinkedList<String> quantita,boolean isBanco){
        for (int i=0; i< id_lotti.size(); ++i){
            try{
                Connection connection = connectFarmacia();
                PreparedStatement statement = connection.prepareStatement("insert into farmaco (id_farmaco, id_lotto, id_utente_farmacia, nome, data_scadenza, quantita,isBanco) values (?,?,?,?,?,?,?)");
                statement.setInt(1,Integer.parseInt(id_farmaco));
                statement.setInt(2,Integer.parseInt(id_lotti.get(i)));
                statement.setInt(3,Integer.parseInt(id_farmacia));
                statement.setString(4,nome_farmaco);
                statement.setDate(5,Date.valueOf(date_scadenza.get(i)));
                statement.setInt(6,Integer.parseInt(quantita.get(i)));
                statement.setBoolean(7,isBanco);
                try {
                    statement.executeUpdate();
                } catch(Exception e){
                    Statement statement2 = connection.createStatement();
                    statement2.executeUpdate("update farmaco set quantita=quantita+"+quantita.get(i)+" where  id_farmaco="+id_farmaco+" and id_lotto="+id_lotti.get(i)+" and id_utente_farmacia="+id_farmacia);
                }
            } catch (Exception e) {
                //throw new RuntimeException(e);
            }
        }
    }

    public static void aggiungiCarico(String id_farmaco, String nome_farmaco, String id_farmacia, boolean isBanco, LinkedList<Lotto> lotti){

        System.out.println("size: "+lotti.size());
        for (int i=0; i< lotti.size(); ++i){
            try{
                Connection connection = connectFarmacia();
                PreparedStatement statement = connection.prepareStatement("insert into farmaco (id_farmaco, id_lotto, id_utente_farmacia, nome, data_scadenza, quantita, isBanco) values (?,?,?,?,?,?,?)");
                statement.setInt(1,Integer.parseInt(id_farmaco));
                statement.setInt(2,Integer.parseInt(lotti.get(i).getLotto()));
                statement.setInt(3,Integer.parseInt(id_farmacia));
                statement.setString(4,nome_farmaco);
                statement.setDate(5,Date.valueOf(lotti.get(i).getDataScadenza()));
                statement.setInt(6,Integer.parseInt(lotti.get(i).getQty()));
                statement.setBoolean(7,isBanco);
                try {
                    statement.executeUpdate();
                } catch(Exception e){
                    Statement statement2 = connection.createStatement();
                    System.out.println("Sto aggiungendo: "+lotti.get(i).getQty());
                    statement2.executeUpdate("update farmaco set quantita=quantita+"+lotti.get(i).getQty()+" where  id_farmaco="+id_farmaco+" and id_lotto="+lotti.get(i).getLotto()+" and id_utente_farmacia="+id_farmacia);
                }
            } catch (Exception e) {
                //throw new RuntimeException(e);
            }
        }
    }

    public static void confermaCaricoLotti(LinkedList <Lotto> lotti, String id_prenotazione){
            try{
                Connection connection = connectAzienda();
                Statement statement = connection.createStatement();
                for (int i=0; i< lotti.size(); ++i) {
                    statement.executeUpdate("Update lotto_ordinato set isCaricato = 1 where id_prenotazione = " + id_prenotazione + " and id_lotto = " + lotti.get(i).getLotto());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }

    public static ResultSet getContratti(String id_farmacia){ //diversa da quella dell'addetto
        ResultSet resultSet;
        try{
            Connection connection = connectDBMS();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("select * from vipagepharma_farmacia.contratto c, vipagepharma_azienda.farmaco f  where c.id_farmaco=f.id_farmaco and c.id_utente_farmacia = " +id_farmacia);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public static void aggiornaContratto(String id_farmaco, String id_farmacia, String quantita){
        try{
            Connection connection = connectFarmacia();
            Statement statement = connection.createStatement();
            statement.executeUpdate("update contratto set quantita_settimanale = "+ quantita +" where id_farmaco = " + id_farmaco + " and id_utente_farmacia = " +  id_farmacia );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet getConsegneOdierneNonCaricate(String IDFarmacia){// faccio PRIMA check prenotazione per vedere se è stato caricato...
        ResultSet resultSet;
        try{
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            java.util.Date date = new java.util.Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
            String strDataOdierna = formatter.format(date);
            resultSet = statement.executeQuery("select * from lotto_ordinato lo, prenotazione p, lotto l, farmaco f where p.id_prenotazione=lo.id_prenotazione and isConsegnato=1 and f.id_farmaco=l.id_farmaco and p.data_consegna = str_to_date('"+strDataOdierna+"','%d-%m-%Y') and lo.id_lotto=l.id_lotto and p.id_utente_farmacia="+IDFarmacia +" and p.id_prenotazione IN (select p.id_prenotazione from lotto_ordinato lo, prenotazione p, lotto l, farmaco f where p.id_prenotazione=lo.id_prenotazione and f.id_farmaco=l.id_farmaco and p.data_consegna = str_to_date('"+strDataOdierna+"','%d-%m-%Y') and lo.id_lotto=l.id_lotto and isConsegnato=1 and p.id_utente_farmacia="+IDFarmacia+" group by lo.id_prenotazione having count(*)!=sum(lo.isCaricato))");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public static void setFlagProblema(String id_prenotazione, int boo){
        try{
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            statement.executeUpdate("Update prenotazione set problema = "+boo+" where id_prenotazione = " + id_prenotazione);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void scaricaFarmaci(String id_farmacia,String id_farmaco,String id_lotto,String qty){
        try{
            Connection connection = connectFarmacia();
            Statement statement = connection.createStatement();
            statement.executeUpdate("update farmaco set quantita = quantita -" + qty + " where id_utente_farmacia ="+ id_farmacia + " and id_farmaco =" + id_farmaco +" and id_lotto ="+ id_lotto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void confermaCarico(String id_prenotazione,LinkedList<String> id_lotti){
            try{
                Connection connection = connectAzienda();
                Statement statement = connection.createStatement();
                for (int i=0; i< id_lotti.size(); ++i) {
                    statement.executeUpdate("Update lotto_ordinato set isCaricato = 1 where id_prenotazione = " +id_prenotazione + " and id_lotto = " + id_lotti.get(i));
                }
                statement.executeUpdate("update prenotazione set isCaricato = 1  where id_prenotazione = "+ id_prenotazione);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }


} // class ends




