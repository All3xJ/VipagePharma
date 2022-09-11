package com.vipagepharma.farmacia;

import com.vipagepharma.farmacia.comunicazioneDBMSFallita.ComunicazioneDBMSFallitaControl;
import com.vipagepharma.farmacia.entity.Lotto;

import java.io.IOException;
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



    public static Connection connectFarmacia() throws IOException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url + dbFarmacia, user, pass);
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

    public static boolean effettuaLogin(String id,String pass) throws IOException {
        boolean esito = false;
        Connection connection=null;
        try {
             connection = connectFarmacia();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select u.* from utente u where u.id_utente_farmacia = " + id + " and u.password = " + "'" + pass + "'" );
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
            //throw new RuntimeException();
        }
        return esito;
    }

    public static boolean verificaMail(String mail) throws IOException {
        boolean esito = true;
        Connection connection=null;
        try {
             connection = connectFarmacia();
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
             connection = connectFarmacia();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO vipagepharma_farmacia.utente(nome, password, chiave_recupero, email)" +
                    "VALUES("+"'"+nome+"','" + pass+"','"+ chiave_recupero+"','"+ mail+"'"+")");
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
             connection = connectFarmacia();
            Statement statement = connection.createStatement();
            statement.executeUpdate("update utente set password = " + "'" + password + "'" + " where id_utente_farmacia =" + id);
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
             connection = connectFarmacia();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from utente where id_utente_farmacia = " + id + " and chiave_recupero = " + "'" + key + "'");
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


    public static ResultSet getInventario(String id_farmacia) throws IOException {
        ResultSet resultSet;
        Connection connection=null;
        try{
             connection = connectFarmacia();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from farmaco where id_utente_farmacia =" + id_farmacia);
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

    public static ResultSet getFarmaco(String nome_o_principio_attivo) throws IOException {
        ResultSet resultSet;
        Connection connection = null;
        try{
            connection = connectAzienda();
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

    public static ResultSet getPrenotazioni(String id_farmacia) throws IOException {
        ResultSet resultSet = null;
        Connection connection = null;
        try{
            connection = connectAzienda();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            java.util.Date date = new java.util.Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
            String strDataOdierna = formatter.format(date);
            resultSet = statement.executeQuery("SELECT p.id_utente_azienda,p.id_prenotazione, p.id_utente_farmacia,f.id_farmaco, f.nome, p.data_consegna ,p.isConsegnato,p.quantita,f.isBanco FROM prenotazione p, farmaco f WHERE p.id_utente_farmacia =" + id_farmacia +" and p.id_farmaco=f.id_farmaco" + " and p.isCaricato = 0 and p.data_consegna >=  str_to_date('"+strDataOdierna+"','%d-%m-%Y') order  by p.data_consegna asc");
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

    public static Connection annullaConRollback(String id_prenotazione,ArrayList<String> id_l,ArrayList<String> qty) throws IOException {
        Connection connection = connectAzienda();
        try{
            Statement statement = connection.createStatement();
            connection.setAutoCommit(false);
            statement.executeUpdate("DELETE FROM lotto_ordinato WHERE id_prenotazione = "+ id_prenotazione);
            for(int i = 0 ; i< id_l.size();++i) {
                statement.executeUpdate("UPDATE lotto set quantita_ordinabile = quantita_ordinabile + " + qty.get(i)+" WHERE id_lotto =" + id_l.get(i));
            }
            statement.executeUpdate("DELETE FROM prenotazione  WHERE id_prenotazione = " + id_prenotazione);
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
        return connection;
    }

    public static void eliminaOrdineERicaricaFarmaci(String id_prenotazione,ArrayList<String> id_lotto,ArrayList<String> qty) throws IOException {
        Connection connection=null;
        try{
             connection = connectAzienda();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM lotto_ordinato WHERE id_prenotazione = "+ id_prenotazione);
            for(int i = 0 ; i< id_lotto.size();++i) {
                statement.executeUpdate("UPDATE lotto set quantita_ordinabile = quantita_ordinabile + " + qty.get(i)+" WHERE id_lotto =" + id_lotto.get(i));
            }
            statement.executeUpdate("DELETE FROM prenotazione  WHERE id_prenotazione = " + id_prenotazione);
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


    public static void creaPrenotazioneEScarica(int id_farmacia, int id_corriere,int id_farmaco,LocalDate data_consegna, ArrayList<Integer> id_lotti, ArrayList <Integer> qty_lotti,int qty) throws IOException {
        Connection connection=null;
        try{ //crea prenotazione
             connection = connectAzienda();
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

    public static void modificaPrenotazioneEAggiornaLotti(int id_prenotazione,int id_farmacia, int id_corriere,int id_farmaco,LocalDate data_consegna, ArrayList<Integer> id_lotti, ArrayList <Integer> qty_lotti,int qty) throws IOException {
        Connection connection=null;
        try{ //crea prenotazione
            connection = connectAzienda();
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
                System.out.println("id_lotto: " + id_lotti.get(i)+ " ; qty da togliere: " +qty_lotti.get(i) );
                statement2.executeUpdate("update lotto set quantita_ordinabile = quantita_ordinabile - " + qty_lotti.get(i) + " where id_lotto = " + id_lotti.get(i));
                PreparedStatement statement3 = connection.prepareStatement("insert into lotto_ordinato(id_lotto, id_prenotazione, isCaricato, quantita) values(?,?,?,?)");
                statement3.setInt(1,id_lotti.get(i));
                statement3.setInt(2,id_prenotazione);
                statement3.setInt(3,0);
                statement3.setInt(4,qty_lotti.get(i));
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
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static ResultSet getLotti(String id_farmaco) throws IOException {
        ResultSet resultSet;
        Connection connection=null;
        try{ //getLotti
            connection = connectAzienda();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("select * from lotto where quantita_ordinabile > 0 and id_farmaco = " + id_farmaco);
        } catch (RuntimeException e){
            if (connection==null) {
                ComunicazioneDBMSFallitaControl cdfctrl = new ComunicazioneDBMSFallitaControl();
                ComunicazioneDBMSFallitaControl.comDBFalLCtrl.start();
                System.out.println("ouuuuu");
            }
            throw new RuntimeException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        return resultSet;
    }

    public static LinkedList<Object> getLotti(String id_prenotazione,ArrayList<String> id_l,ArrayList<String> qty,String id_farmaco) throws IOException {
        LinkedList<Object> lotti = new LinkedList<>();
        ResultSet resultSet;
        Connection connection=null;
        try{ //getLotti
            connection = connectAzienda();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            connection.setAutoCommit(false);
            statement.executeUpdate("DELETE FROM lotto_ordinato WHERE id_prenotazione = "+ id_prenotazione);
            for(int i = 0 ; i< id_l.size();++i) {
                statement.executeUpdate("UPDATE lotto set quantita_ordinabile = quantita_ordinabile + " + qty.get(i)+" WHERE id_lotto =" + id_l.get(i));
            }
            statement.executeUpdate("DELETE FROM prenotazione  WHERE id_prenotazione = " + id_prenotazione);
            resultSet = statement.executeQuery("select * from lotto where quantita_ordinabile > 0 and id_farmaco = " + id_farmaco);
            lotti.add(connection);
            lotti.add(resultSet);
            return lotti;

        } catch (RuntimeException e){
            if (connection==null) {
                ComunicazioneDBMSFallitaControl cdfctrl = new ComunicazioneDBMSFallitaControl();
                ComunicazioneDBMSFallitaControl.comDBFalLCtrl.start();
                System.out.println("ouuuuu");
            }
            throw new RuntimeException();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static ResultSet getLottiOrdinati(String id_prenotazione) throws IOException {
        ResultSet resultSet;
        Connection connection=null;
        try{ //getLotti
            connection = connectAzienda();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("select lo.id_lotto ,l.data_scadenza,l.data_disponibilita,lo.quantita from lotto_ordinato lo,lotto l where lo.quantita>0 and lo.id_lotto = l.id_lotto and lo.id_prenotazione = " + id_prenotazione);
            return resultSet;
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

    public static ResultSet getCorrieri() throws IOException {
        ResultSet resultSet;
        Connection connection=null;
        try{
             connection = connectAzienda();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
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

    public static ResultSet getProduzione(String id_farmaco) throws IOException {
        ResultSet resultSet;
        Connection connection=null;
        try{
             connection = connectAzienda();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("select id_farmaco, produzione_settimanale from farmaco where id_farmaco= " + id_farmaco);
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

    public static void aggiungiCarico(String id_farmacia, String id_farmaco, String nome_farmaco,LinkedList<String> id_lotti, LinkedList<String>date_scadenza, LinkedList<String> quantita,boolean isBanco) throws IOException {
        Connection connection=null;
        for (int i=0; i< id_lotti.size(); ++i){
            try{
                 connection = connectFarmacia();
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
                }
                catch(Exception e){
                    Statement statement2 = connection.createStatement();
                    statement2.executeUpdate("update farmaco set quantita=quantita+"+quantita.get(i)+" where  id_farmaco="+id_farmaco+" and id_lotto="+id_lotti.get(i)+" and id_utente_farmacia="+id_farmacia);
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
    }

    public static void aggiungiCarico(String id_farmaco, String nome_farmaco, String id_farmacia, boolean isBanco, LinkedList<Lotto> lotti) throws IOException {
        Connection connection=null;
        System.out.println("size: "+lotti.size());
        for (int i=0; i< lotti.size(); ++i){
            try{
                 connection = connectFarmacia();
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

    public static void confermaCaricoLotti(LinkedList <Lotto> lotti, String id_prenotazione) throws IOException {
            Connection connection=null;
            try{
                 connection = connectAzienda();
                Statement statement = connection.createStatement();
                for (int i=0; i< lotti.size(); ++i) {
                    statement.executeUpdate("Update lotto_ordinato set isCaricato = 1 where id_prenotazione = " + id_prenotazione + " and id_lotto = " + lotti.get(i).getLotto());
                }
                statement.executeUpdate("update prenotazione set isCaricato = 1  where id_prenotazione = "+ id_prenotazione);
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

    public static ResultSet getContratti(String id_farmacia) throws IOException { //diversa da quella dell'addetto
        ResultSet resultSet;
        Connection connection=null;
        try{
             connection = connectDBMS();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("select * from vipagepharma_farmacia.contratto c, vipagepharma_azienda.farmaco f  where c.id_farmaco=f.id_farmaco and c.id_utente_farmacia = " +id_farmacia);
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

    public static void aggiornaContratto(String id_farmaco, String id_farmacia, String quantita) throws IOException {
        Connection connection=null;
        try{
             connection = connectFarmacia();
            Statement statement = connection.createStatement();
            statement.executeUpdate("update contratto set quantita_settimanale = "+ quantita +" where id_farmaco = " + id_farmaco + " and id_utente_farmacia = " +  id_farmacia );
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

    public static ResultSet getConsegneOdierneNonCaricate(String IDFarmacia) throws IOException {
        ResultSet resultSet;
        Connection connection=null;
        try{
             connection = connectAzienda();
            Statement statement = connection.createStatement();
            java.util.Date date = new java.util.Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
            String strDataOdierna = formatter.format(date);
            resultSet = statement.executeQuery("select * from lotto_ordinato lo, prenotazione p, lotto l, farmaco f where p.id_prenotazione=lo.id_prenotazione and isConsegnato=1 and f.id_farmaco=l.id_farmaco and p.data_consegna = str_to_date('"+strDataOdierna+"','%d-%m-%Y') and lo.id_lotto=l.id_lotto and p.id_utente_farmacia="+IDFarmacia +" and p.id_prenotazione IN (select p.id_prenotazione from lotto_ordinato lo, prenotazione p, lotto l, farmaco f where p.id_prenotazione=lo.id_prenotazione and f.id_farmaco=l.id_farmaco and p.data_consegna = str_to_date('"+strDataOdierna+"','%d-%m-%Y') and lo.id_lotto=l.id_lotto and isConsegnato=1 and p.id_utente_farmacia="+IDFarmacia+" group by lo.id_prenotazione having count(*)!=sum(lo.isCaricato))");
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

    public static void setFlagProblema(String id_prenotazione, int boo) throws IOException {
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

    public static void scaricaFarmaci(String id_farmacia,String id_farmaco,String id_lotto,String qty) throws IOException {
        Connection connection=null;
        try{
             connection = connectFarmacia();
            Statement statement = connection.createStatement();
            statement.executeUpdate("update farmaco set quantita = quantita -" + qty + " where id_utente_farmacia ="+ id_farmacia + " and id_farmaco =" + id_farmaco +" and id_lotto ="+ id_lotto);
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

    public static void confermaCarico(String id_prenotazione,LinkedList<String> id_lotti) throws IOException {
            Connection connection=null;
            try{
                 connection = connectAzienda();
                Statement statement = connection.createStatement();
                for (int i=0; i< id_lotti.size(); ++i) {
                    statement.executeUpdate("Update lotto_ordinato set isCaricato = 1 where id_prenotazione = " +id_prenotazione + " and id_lotto = " + id_lotti.get(i));
                }
                statement.executeUpdate("update prenotazione set isCaricato = 1  where id_prenotazione = "+ id_prenotazione);
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




