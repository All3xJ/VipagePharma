package com.vipagepharma.addettoAzienda;

import com.vipagepharma.addettoAzienda.comunicazioneDBMSFallita.ComunicazioneDBMSFallitaControl;
import com.vipagepharma.addettoAzienda.entity.Consegna;
import com.vipagepharma.addettoAzienda.gestioneConsegne.visualizzaStoricoConsegne.VisualizzaStoricoConsegneControl;

import java.io.IOException;
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

    public static Connection connectFarmacia() throws IOException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url + dbFarmacia, user, pass);
        }
        catch (RuntimeException e){
            if (connection==null) {
                ComunicazioneDBMSFallitaControl cdfctrl = new ComunicazioneDBMSFallitaControl();
                ComunicazioneDBMSFallitaControl.comDBFalLCtrl.start();
                System.out.println("ouuuuu");
            }
            throw new RuntimeException();
        } catch (Exception e){
            throw new RuntimeException();
        }
        return connection;
    }

    public static Connection connectAzienda() throws IOException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url + dbAzienda, user, pass);
        }
        catch (RuntimeException e){
            if (connection==null) {
                ComunicazioneDBMSFallitaControl cdfctrl = new ComunicazioneDBMSFallitaControl();
                ComunicazioneDBMSFallitaControl.comDBFalLCtrl.start();
                System.out.println("ouuuuu");
            }
            throw new RuntimeException();
        } catch (Exception e){
            throw new RuntimeException();
        }
        return connection;
    }

    public static Connection connectDBMS() throws IOException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
        }
        catch (RuntimeException e){
            if (connection==null) {
                ComunicazioneDBMSFallitaControl cdfctrl = new ComunicazioneDBMSFallitaControl();
                ComunicazioneDBMSFallitaControl.comDBFalLCtrl.start();
                System.out.println("ouuuuu");
            }
            throw new RuntimeException();
        } catch (Exception e){
            throw new RuntimeException();
        }
        return connection;
    }


    public static ResultSet getFarmaci() throws IOException {
        ResultSet resultSet = null;
        Connection connection=null;
        try {
             connection = connectAzienda();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("select * from farmaco");

        }
        catch (RuntimeException e){
            if (connection==null) {
                ComunicazioneDBMSFallitaControl cdfctrl = new ComunicazioneDBMSFallitaControl();
                ComunicazioneDBMSFallitaControl.comDBFalLCtrl.start();
                System.out.println("ouuuuu");
            }
            throw new RuntimeException();
        } catch (Exception e){
            throw new RuntimeException();
        }
        return resultSet;
    }



    public static boolean effettuaLogin(String id,String pass) throws IOException {
        boolean esito = false;
        Connection connection=null;
        try {
             connection = connectAzienda();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select u.* from utente u where u.id_utente_azienda = " + id + " and u.isCorriere = 0 and u.password = " + "'" + pass + "'" );
            esito = resultSet.next();
        }
        catch (RuntimeException e){
            if (connection==null) {
                ComunicazioneDBMSFallitaControl cdfctrl = new ComunicazioneDBMSFallitaControl();
                ComunicazioneDBMSFallitaControl.comDBFalLCtrl.start();
                System.out.println("ouuuuu");
            }
            throw new RuntimeException();
        } catch (Exception e){
            e.printStackTrace();
            return false;
           // throw new RuntimeException();
        }
        return esito;
    }


    public static boolean verificaMail(String mail) throws IOException {
        boolean esito = true;
        Connection connection=null;
        try {
             connection = connectAzienda();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from utente where email = " + "'" + mail + "'");
            esito = !resultSet.next();
        }
        catch (RuntimeException e){
            if (connection==null) {
                ComunicazioneDBMSFallitaControl cdfctrl = new ComunicazioneDBMSFallitaControl();
                ComunicazioneDBMSFallitaControl.comDBFalLCtrl.start();
                System.out.println("ouuuuu");
            }
            throw new RuntimeException();
        } catch (Exception e){
            e.printStackTrace();
            //throw new RuntimeException();
        }
        return esito;
    }

    public static ResultSet registra(String nome,String mail, String pass, String chiave_recupero) throws IOException {
        ResultSet resultSet = null;
        Connection connection=null;
        try {
             connection = connectAzienda();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO utente(nome, password, chiave_recupero, email,isCorriere)" +
                    "VALUES("+"'"+nome+"','" + pass+"','"+ chiave_recupero+"','"+ mail+"',0"+")");
            resultSet = statement.executeQuery("SELECT LAST_INSERT_ID() as id");
        }
        catch (RuntimeException e){
            if (connection==null) {
                ComunicazioneDBMSFallitaControl cdfctrl = new ComunicazioneDBMSFallitaControl();
                ComunicazioneDBMSFallitaControl.comDBFalLCtrl.start();
                System.out.println("ouuuuu");
            }
            throw new RuntimeException();
        } catch (Exception e){
            throw new RuntimeException();
        }
        return resultSet;
    }

    public static void aggiornaPassword(String id,String password) throws IOException {
        Connection connection=null;
        try {
             connection = connectAzienda();
            Statement statement = connection.createStatement();
            statement.executeUpdate("update utente set password = " + "'" + password + "'" + " where id_utente_azienda =" + id);
        }
        catch (RuntimeException e){
            if (connection==null) {
                ComunicazioneDBMSFallitaControl cdfctrl = new ComunicazioneDBMSFallitaControl();
                ComunicazioneDBMSFallitaControl.comDBFalLCtrl.start();
                System.out.println("ouuuuu");
            }
            throw new RuntimeException();
        } catch (Exception e){
            throw new RuntimeException();
        }
    }

    public static boolean verificaKey(String id,String key) throws IOException {
        boolean esito = false;
        Connection connection=null;
        try {
             connection = connectAzienda();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from utente where id_utente_azienda = " + id + " and isCorriere = 0 and chiave_recupero = " + "'" + key + "'");
            esito = resultSet.next();
        }
        catch (RuntimeException e){
            if (connection==null) {
                ComunicazioneDBMSFallitaControl cdfctrl = new ComunicazioneDBMSFallitaControl();
                ComunicazioneDBMSFallitaControl.comDBFalLCtrl.start();
                System.out.println("ouuuuu");
            }
            throw new RuntimeException();
        } catch (Exception e){
            e.printStackTrace();
            //throw new RuntimeException();
        }
        return esito;
    }


    public static ResultSet getLotti(String id_farmaco) throws IOException {
        ResultSet resultSet;
        Connection connection=null;
        try{ //getLotti
             connection = connectAzienda();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("select * from lotto where id_farmaco = " + id_farmaco);
        } catch (RuntimeException e){
            if (connection==null) {
                ComunicazioneDBMSFallitaControl cdfctrl = new ComunicazioneDBMSFallitaControl();
                ComunicazioneDBMSFallitaControl.comDBFalLCtrl.start();
                System.out.println("ouuuuu");
            }
            throw new RuntimeException();
        } catch (Exception e){
            throw new RuntimeException();
        }
        return resultSet;
    }

    public static ResultSet getCorrieri() throws IOException {
        ResultSet resultSet;
        Connection connection=null;
        try{
             connection = connectAzienda();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("select id_utente_azienda from utente where isCorriere = 1");
        } catch (RuntimeException e){
            if (connection==null) {
                ComunicazioneDBMSFallitaControl cdfctrl = new ComunicazioneDBMSFallitaControl();
                ComunicazioneDBMSFallitaControl.comDBFalLCtrl.start();
                System.out.println("ouuuuu");
            }
            throw new RuntimeException();
        } catch (Exception e){
            throw new RuntimeException();
        }
        return resultSet;
    }



    public static void creaLotto(int id_farmaco, LocalDate dataScadenza, LocalDate dataDisponibilita, int quantita_ordinabile, int qtyContratti) throws IOException {
        Connection connection=null;
        try {
             connection = connectAzienda();
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
        catch (RuntimeException e){
            if (connection==null) {
                ComunicazioneDBMSFallitaControl cdfctrl = new ComunicazioneDBMSFallitaControl();
                ComunicazioneDBMSFallitaControl.comDBFalLCtrl.start();
                System.out.println("ouuuuu");
            }
            throw new RuntimeException();
        } catch (Exception e){
            throw new RuntimeException();
        }
    }

    public static ResultSet getContratti() throws IOException {
        ResultSet resultSet = null;
        Connection connection=null;
        try {
             connection = connectFarmacia();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("select * from contratto");
        }
        catch (RuntimeException e){
            if (connection==null) {
                ComunicazioneDBMSFallitaControl cdfctrl = new ComunicazioneDBMSFallitaControl();
                ComunicazioneDBMSFallitaControl.comDBFalLCtrl.start();
                System.out.println("ouuuuu");
            }
            throw new RuntimeException();
        } catch (Exception e){
            throw new RuntimeException();
        }
        return resultSet;
    }

    public static void setFlagProblema(String id_prenotazione, int boo) throws IOException {
        ResultSet resultSet;
        Connection connection=null;
        try{
             connection = connectAzienda();
            Statement statement = connection.createStatement();
            statement.executeUpdate("Update prenotazione set problema = "+boo+" where id_prenotazione = " + id_prenotazione);
        } catch (RuntimeException e){
            if (connection==null) {
                ComunicazioneDBMSFallitaControl cdfctrl = new ComunicazioneDBMSFallitaControl();
                ComunicazioneDBMSFallitaControl.comDBFalLCtrl.start();
                System.out.println("ouuuuu");
            }
            throw new RuntimeException();
        } catch (Exception e){
            throw new RuntimeException();
        }
    }

    public static void aggiungiEConfermaCarico(LinkedList<Integer> id_lotti, String quantita, String id_farmaco) throws IOException {
        ResultSet resultSet;
        Connection connection=null;
        try{
             connection = connectFarmacia();
            Statement statement = connection.createStatement();
            statement.executeUpdate("update farmaco set quantita = quantita + " + quantita + "where id_farmaco = " + id_farmaco);
        } catch (RuntimeException e){
            if (connection==null) {
                ComunicazioneDBMSFallitaControl cdfctrl = new ComunicazioneDBMSFallitaControl();
                ComunicazioneDBMSFallitaControl.comDBFalLCtrl.start();
                System.out.println("ouuuuu");
            }
            throw new RuntimeException();
        } catch (Exception e){
            throw new RuntimeException();
        }
        for(int i=0; i<id_lotti.size(); ++i) {
            try {
                 connection = connectAzienda();
                Statement statement = connection.createStatement();
                statement.executeUpdate("update lotto_ordinato set isCaricato = 1 where id_lotto = id_lotti.removeFirst()");
            } catch (RuntimeException e){
                if (connection==null) {
                    ComunicazioneDBMSFallitaControl cdfctrl = new ComunicazioneDBMSFallitaControl();
                    ComunicazioneDBMSFallitaControl.comDBFalLCtrl.start();
                    System.out.println("ouuuuu");
                }
                throw new RuntimeException();
            } catch (Exception e){
                throw new RuntimeException();
            }
        }
    }

    public static ResultSet getElencoConsegneConSegnalazioni() throws IOException {
        ResultSet resultSet;
        Connection connection=null;
        try{
             connection = connectDBMS();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("select p.id_utente_farmacia, p.id_prenotazione, p.data_consegna, p.id_farmaco, u.nome from vipagepharma_azienda.prenotazione p, vipagepharma_farmacia.utente u where problema = 1 and p.id_utente_farmacia=u.id_utente_farmacia");
        } catch (RuntimeException e){
            if (connection==null) {
                ComunicazioneDBMSFallitaControl cdfctrl = new ComunicazioneDBMSFallitaControl();
                ComunicazioneDBMSFallitaControl.comDBFalLCtrl.start();
                System.out.println("ouuuuu");
            }
            throw new RuntimeException();
        } catch (Exception e){
            throw new RuntimeException();
        }
        return resultSet;
    }

    public static ResultSet getConsegneRecenti() throws IOException {
        ResultSet resultSet;
        Connection connection=null;
        try{
             connection = connectDBMS();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("Select u.nome, p.id_prenotazione, p.id_utente_farmacia, p.data_consegna, p.ricevuta_pdf from vipagepharma_azienda.prenotazione p, vipagepharma_farmacia.utente u where u.id_utente_farmacia=p.id_utente_farmacia and ricevuta_pdf IS NOT NULL and  isConsegnato = 1 order by data_consegna desc limit 10");
        } catch (RuntimeException e){
            if (connection==null) {
                ComunicazioneDBMSFallitaControl cdfctrl = new ComunicazioneDBMSFallitaControl();
                ComunicazioneDBMSFallitaControl.comDBFalLCtrl.start();
                System.out.println("ouuuuu");
            }
            throw new RuntimeException();
        } catch (Exception e){
            throw new RuntimeException();
        }
        return resultSet;
    }

    public static ResultSet getAltreConsegne() throws IOException {

        int base = VisualizzaStoricoConsegneControl.contatorePagineConsegne * 10;
        ResultSet resultSet;
        Connection connection=null;
        try{
             connection = connectDBMS();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("Select u.nome, p.id_prenotazione, p.id_utente_farmacia, p.data_consegna, p.ricevuta_pdf from vipagepharma_azienda.prenotazione p, vipagepharma_farmacia.utente u where u.id_utente_farmacia=p.id_utente_farmacia and isConsegnato=1 and ricevuta_pdf IS NOT NULL order by data_consegna  desc limit " + base + ", 10");
        } catch (RuntimeException e){
            if (connection==null) {
                ComunicazioneDBMSFallitaControl cdfctrl = new ComunicazioneDBMSFallitaControl();
                ComunicazioneDBMSFallitaControl.comDBFalLCtrl.start();
                System.out.println("ouuuuu");
            }
            throw new RuntimeException();
        } catch (Exception e){
            throw new RuntimeException();
        }
        return resultSet;
    }

    public static void creaPrenotazioneEScarica(int id_farmacia, int id_corriere, int id_farmaco, LocalDate data_consegna, ArrayList<Integer> id_lotti, ArrayList <Integer> qty_lotti) throws IOException {

        Connection connection=null;
        try {
             connection = connectAzienda();
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
        } catch (RuntimeException e){
            if (connection==null) {
                ComunicazioneDBMSFallitaControl cdfctrl = new ComunicazioneDBMSFallitaControl();
                ComunicazioneDBMSFallitaControl.comDBFalLCtrl.start();
                System.out.println("ouuuuu");
            }
            throw new RuntimeException();
        } catch (Exception e){
            throw new RuntimeException();
        }
    }

    public static void creaPrenotazioneDaBancoEScarica(int id_farmacia, int id_corriere, int id_farmaco, LocalDate data_consegna, ArrayList<Integer> id_lotti, ArrayList <Integer> qty_lotti) throws IOException {

        Connection connection=null;
        try {
            int qtyTot=0;
            for (Integer qty_lottO:
                 qty_lotti) {
                qtyTot+=qty_lottO;
                System.out.println(qty_lottO);
            }
             connection = connectAzienda();
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
        } catch (RuntimeException e){
            if (connection==null) {
                ComunicazioneDBMSFallitaControl cdfctrl = new ComunicazioneDBMSFallitaControl();
                ComunicazioneDBMSFallitaControl.comDBFalLCtrl.start();
                System.out.println("ouuuuu");
            }
            throw new RuntimeException();
        } catch (Exception e){
            throw new RuntimeException();
        }
    }

    public static ResultSet getLottiNonConsegnati(Consegna consegna) throws IOException {
        ResultSet resultSet;
        Connection connection=null;
        try{
             connection = connectAzienda();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("select l.quantita,l.id_lotto from lotto_ordinato l, prenotazione p where l.isCaricato=0 and l.id_prenotazione=p.id_prenotazione and p.id_utente_farmacia = " + consegna.idFarmacia.get() + " and p.id_prenotazione="+consegna.idOrdine.get());
        } catch (RuntimeException e){
            if (connection==null) {
                ComunicazioneDBMSFallitaControl cdfctrl = new ComunicazioneDBMSFallitaControl();
                ComunicazioneDBMSFallitaControl.comDBFalLCtrl.start();
                System.out.println("ouuuuu");
            }
            throw new RuntimeException();
        } catch (Exception e){
            throw new RuntimeException();
        }
        return resultSet;
    }

    public static void carica(ResultSet lottiNonConsegnati) throws SQLException, IOException { //ricarica i lotti non consegnati RISOLUZIONE PROBLEMA CONSEGNA
        ResultSet resultSet;
        Connection connection=null;
        int qtyDaRiaggiungere = 0;
        String idLotto = null;
        while (lottiNonConsegnati.next()) {
            try{
                 connection = connectAzienda();
                Statement statement = connection.createStatement();
                qtyDaRiaggiungere = lottiNonConsegnati.getInt("quantita");
                idLotto = lottiNonConsegnati.getString("id_lotto");
                statement.executeUpdate("Update lotto set quantita_ordinabile = quantita_ordinabile +" + qtyDaRiaggiungere + " where id_lotto = " + idLotto);
            } catch (RuntimeException e){
                if (connection==null) {
                    ComunicazioneDBMSFallitaControl cdfctrl = new ComunicazioneDBMSFallitaControl();
                    ComunicazioneDBMSFallitaControl.comDBFalLCtrl.start();
                    System.out.println("ouuuuu");
                }
                throw new RuntimeException();
            } catch (Exception e){
                throw new RuntimeException();
            }
        }
    }

    public static ResultSet creaOrdine(String id_farmacia, int id_corriere, String id_farmaco,ResultSet lottiNonCosegnati) throws IOException { //RISOLUZINE PROBLEMA CONSEGNA
        ResultSet resultSet;
        int qty = 0;
        Connection connection=null;
        try{
        while(lottiNonCosegnati.next()){
            qty += lottiNonCosegnati.getInt("quantita");
            System.out.println(qty);
        }
            connection = connectAzienda();
            Statement statement = connection.createStatement();
            java.util.Date date = new java.util.Date();
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, 1);
            date = c.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
            String strDataGiornoSuccessivo = formatter.format(date);
            //System.out.println("insert into prenotazione (id_utente_farmacia, id_utente_azienda, isConsegnato, data_consegna, id_farmaco) values (" + id_farmacia + "," + id_corriere +"," + 0 + ", str_to_date('"+strDataOdierna+"','%d-%m-%Y')  ,"+ id_farmaco+")");
            statement.executeUpdate("insert into prenotazione (id_utente_farmacia, id_utente_azienda, isConsegnato, data_consegna, id_farmaco,quantita) values (" + id_farmacia + "," + id_corriere + "," + 0 + ", str_to_date('" + strDataGiornoSuccessivo + "','%d-%m-%Y')  ," + id_farmaco + " , " + String.valueOf(qty) + ")");
            resultSet = statement.executeQuery("SELECT LAST_INSERT_ID() as id_prenotazione");
            lottiNonCosegnati.beforeFirst();
        }catch (RuntimeException e){
            if (connection==null) {
                ComunicazioneDBMSFallitaControl cdfctrl = new ComunicazioneDBMSFallitaControl();
                ComunicazioneDBMSFallitaControl.comDBFalLCtrl.start();
                System.out.println("ouuuuu");
            }
            throw new RuntimeException();
        } catch (Exception e){
            throw new RuntimeException();
        }
        return resultSet;
    }

    public static void aggiornaLottiOrdinati(int idprenotazione, ResultSet lottiNonConsegnati, String idoldprenotazione) throws IOException {
        int idLotto = 0;
        Connection connection=null;
        try {
            while (lottiNonConsegnati.next()) {
                 connection = connectAzienda();
                Statement statement = connection.createStatement();
                idLotto = lottiNonConsegnati.getInt("id_lotto");
                System.out.println(idprenotazione + " " + idLotto);
                statement.executeUpdate("Update lotto_ordinato set id_prenotazione = " + idprenotazione + " where id_lotto=" + idLotto + " and id_prenotazione=" + idoldprenotazione);
            }
        }catch (RuntimeException e){
            if (connection==null) {
                ComunicazioneDBMSFallitaControl cdfctrl = new ComunicazioneDBMSFallitaControl();
                ComunicazioneDBMSFallitaControl.comDBFalLCtrl.start();
                System.out.println("ouuuuu");
            }
            throw new RuntimeException();
        } catch (Exception e){
            throw new RuntimeException();
        }
    }


}
