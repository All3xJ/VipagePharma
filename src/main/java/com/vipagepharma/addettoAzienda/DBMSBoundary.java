package com.vipagepharma.addettoAzienda;

import com.vipagepharma.addettoAzienda.entity.Consegna;
import com.vipagepharma.addettoAzienda.gestioneConsegne.visualizzaStoricoConsegne.VisualizzaStoricoConsegneControl;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.time.LocalDate;
import java.text.SimpleDateFormat;

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


    public static ResultSet getFarmaci(){
        ResultSet resultSet = null;
        try {
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
            ResultSet resultSet = statement.executeQuery("select u.* from utente u where u.id_utente_azienda = " + id + " and u.isCorriere = 0 and u.password = " + "'" + pass + "'" );
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
            statement.executeUpdate("update utente set password = " + "'" + password + "'" + " where id_utente_azienda =" + id);
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
            ResultSet resultSet = statement.executeQuery("select * from utente where id_utente_azienda = " + id + " and chiave_recupero = " + "'" + key + "'");
            esito = resultSet.next();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return esito;
    }


    public static ResultSet getLotti(String id_farmaco){
        ResultSet resultSet;
        try{ //getLotti
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("select * from lotto where id_farmaco = " + id_farmaco);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public static ResultSet getCorrieri(){
        ResultSet resultSet;
        try{
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("select id_utente_azienda from utente where isCorriere = 1");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }



    public static void creaLotto(int id_farmaco, LocalDate dataScadenza, LocalDate dataDisponibilita, int quantita_ordinabile, int qtyContratti){
        try {
            Connection connection = connectAzienda();
            //dataDisponibilita = dataDisponibilita.plusMonths(2);
            //dataScadenza = dataScadenza.plusMonths(2);
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO lotto (id_farmaco , quantita_ordinabile,quantita_contratti, data_disponibilita, data_scadenza) VALUES(?,?,?,?,?)",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setInt(1,id_farmaco);
            stmt.setInt(2,quantita_ordinabile);
            stmt.setInt(3,qtyContratti);
            stmt.setDate(4, Date.valueOf(dataDisponibilita));
            stmt.setDate(5, Date.valueOf(dataScadenza));
            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static ResultSet getContratti(){  // mettere che lo fa alle 9 e fa produzione alle 8 quindi invertiti.
        ResultSet resultSet = null;
        try {
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("select * from contratto");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public static void setFlagProblema(String id_prenotazione, int boo){
        ResultSet resultSet;
        try{
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            statement.executeUpdate("Update prenotazione set problema = "+boo+" where id_prenotazione = " + id_prenotazione);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void aggiungiEConfermaCarico(LinkedList<Integer> id_lotti, String quantita, String id_farmaco){
        ResultSet resultSet;
        try{
            Connection connection = connectFarmacia();
            Statement statement = connection.createStatement();
            statement.executeUpdate("update farmaco set quantita = quantita + " + quantita + "where id_farmaco = " + id_farmaco);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for(int i=0; i<id_lotti.size(); ++i) {
            try {
                Connection connection = connectAzienda();
                Statement statement = connection.createStatement();
                statement.executeUpdate("update lotto_ordinato set isCaricato = 1 where id_lotto = id_lotti.removeFirst()");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static ResultSet getElencoConsegneConSegnalazioni(){
        ResultSet resultSet;
        try{
            Connection connection = connectDBMS();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("select p.id_utente_farmacia, p.id_prenotazione, p.data_consegna, p.id_farmaco, u.nome from vipagepharma_azienda.prenotazione p, vipagepharma_farmacia.utente u where problema = 1 and p.id_utente_farmacia=u.id_utente_farmacia");
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
            resultSet = statement.executeQuery("Select id_prenotazione, id_utente_farmacia, data_consegna, ricevuta_pdf from prenotazione where isConsegnato = 1 order by data_consegna desc limit 2"); //vedere se funziona sintassi
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public static ResultSet getAltreConsegne(){

        int base = VisualizzaStoricoConsegneControl.contatorePagineConsegne * 10;
        ResultSet resultSet;
        try{
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("Select id_prenotazione, id_utente_farmacia, data_consegna, ricevuta_pdf from prenotazione where isConsegnato=1 order by data_consegna  desc limit " + base + ", 10"); //vedere se funziona sintassi
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public static void creaPrenotazioneEScarica(int id_farmacia, int id_corriere, int id_farmaco, LocalDate data_consegna, ArrayList<Integer> id_lotti, ArrayList <Integer> qty_lotti) {
        try {
            Connection connection = connectAzienda();
            PreparedStatement statement1 = connection.prepareStatement("insert into prenotazione(id_utente_farmacia, id_utente_azienda,id_farmaco, isConsegnato, data_consegna) values (?,?,?,?,?)");
            statement1.setInt(1, id_farmacia);
            statement1.setInt(2, id_corriere);
            statement1.setInt(3, id_farmaco);
            statement1.setInt(4, 0);
            statement1.setDate(5, Date.valueOf(data_consegna));
            statement1.executeUpdate();
            Statement statement2 = connection.createStatement();
            ResultSet rsId = statement2.executeQuery("SELECT LAST_INSERT_ID() as id");
            rsId.next();
            int id_prenotazione = rsId.getInt("id");
            for (int i = 0; i < id_lotti.size(); ++i) {
                statement2.executeUpdate("update lotto set quantita_ordinabile = quantita_ordinabile - " + String.valueOf(qty_lotti.get(i)) + " where id_lotto = " + id_lotti.get(i));
                PreparedStatement statement3 = connection.prepareStatement("insert into lotto_ordinato(id_lotto, id_prenotazione, isCaricato, quantita) values(?,?,?,?)");
                statement3.setInt(1, id_lotti.get(i));
                statement3.setInt(2, id_prenotazione);
                statement3.setInt(3, 0);
                statement3.setInt(4, qty_lotti.get(i));
                statement3.executeUpdate();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void creaPrenotazioneDaBancoEScarica(int id_farmacia, int id_corriere, int id_farmaco, LocalDate data_consegna, ArrayList<Integer> id_lotti, ArrayList <Integer> qty_lotti){
        try {
            int qtyTot=0;
            for (Integer qty_lottO:
                 qty_lotti) {
                qtyTot+=qty_lottO;
                System.out.println(qty_lottO);
            }
            Connection connection = connectAzienda();
            PreparedStatement statement1 = connection.prepareStatement("insert into prenotazione(id_utente_farmacia, id_utente_azienda,id_farmaco, isConsegnato, data_consegna, quantita) values (?,?,?,?,?,?)");
            statement1.setInt(1, id_farmacia);
            statement1.setInt(2, id_corriere);
            statement1.setInt(3, id_farmaco);
            statement1.setInt(4, 0);
            statement1.setDate(5, Date.valueOf(data_consegna));
            statement1.setInt(6, qtyTot);
            statement1.executeUpdate();
            Statement statement2 = connection.createStatement();
            ResultSet rsId = statement2.executeQuery("SELECT LAST_INSERT_ID() as id");
            rsId.next();
            int id_prenotazione = rsId.getInt("id");
            for (int i = 0; i < id_lotti.size(); ++i) {
                statement2.executeUpdate("update lotto set quantita_contratti = quantita_contratti - " + String.valueOf(qty_lotti.get(i)) + " where id_lotto = " + id_lotti.get(i));
                PreparedStatement statement3 = connection.prepareStatement("insert into lotto_ordinato(id_lotto, id_prenotazione, isCaricato, quantita) values(?,?,?,?)");
                statement3.setInt(1, id_lotti.get(i));
                statement3.setInt(2, id_prenotazione);
                statement3.setInt(3, 0);
                statement3.setInt(4, qty_lotti.get(i));
                statement3.executeUpdate();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet getLottiNonConsegnati(Consegna consegna){
        ResultSet resultSet;
        try{
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("select l.quantita,l.id_lotto from lotto_ordinato l, prenotazione p where l.isCaricato=0 and l.id_prenotazione=p.id_prenotazione and p.id_utente_farmacia = " + consegna.idFarmacia.get() + " and p.id_prenotazione="+consegna.idOrdine.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public static void carica(ResultSet lottiNonConsegnati) throws SQLException { //ricarica i lotti non consegnati RISOLUZIONE PROBLEMA CONSEGNA
        ResultSet resultSet;
        int qtyDaRiaggiungere = 0;
        String idLotto = null;
        while (lottiNonConsegnati.next()) {
            try{
                Connection connection = connectAzienda();
                Statement statement = connection.createStatement();
                qtyDaRiaggiungere = lottiNonConsegnati.getInt("quantita");
                idLotto = lottiNonConsegnati.getString("id_lotto");
                statement.executeUpdate("Update lotto set quantita_ordinabile = quantita_ordinabile +" + qtyDaRiaggiungere + " where id_lotto = " + idLotto);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static ResultSet creaOrdine(String id_farmacia, int id_corriere, String id_farmaco,ResultSet lottiNonCosegnati) throws SQLException { //RISOLUZINE PROBLEMA CONSEGNA
        ResultSet resultSet;
        int qty = 0;
        System.out.println("suca");
        while(lottiNonCosegnati.next()){
            qty += lottiNonCosegnati.getInt("quantita");
            System.out.println(qty);
        }
        Connection connection = connectAzienda();
        Statement statement = connection.createStatement();
        java.util.Date date = new java.util.Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        date = c.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
        String strDataGiornoSuccessivo = formatter.format(date);
        //System.out.println("insert into prenotazione (id_utente_farmacia, id_utente_azienda, isConsegnato, data_consegna, id_farmaco) values (" + id_farmacia + "," + id_corriere +"," + 0 + ", str_to_date('"+strDataOdierna+"','%d-%m-%Y')  ,"+ id_farmaco+")");
        statement.executeUpdate("insert into prenotazione (id_utente_farmacia, id_utente_azienda, isConsegnato, data_consegna, id_farmaco,quantita) values (" + id_farmacia + "," + id_corriere +"," + 0 + ", str_to_date('"+strDataGiornoSuccessivo+"','%d-%m-%Y')  ,"+ id_farmaco+" , " + String.valueOf(qty)+")");
        resultSet = statement.executeQuery("SELECT LAST_INSERT_ID() as id_prenotazione");
        lottiNonCosegnati.beforeFirst();
        return resultSet;
    }

    public static void aggiornaLottiOrdinati(int idprenotazione, ResultSet lottiNonConsegnati, String idoldprenotazione) throws SQLException {
        int idLotto = 0;
        while (lottiNonConsegnati.next()) {
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            idLotto = lottiNonConsegnati.getInt("id_lotto");
            System.out.println(idprenotazione+" "+idLotto);
            statement.executeUpdate("Update lotto_ordinato set id_prenotazione = " + idprenotazione + " where id_lotto="+idLotto+" and id_prenotazione="+idoldprenotazione);
        }
    }


}
