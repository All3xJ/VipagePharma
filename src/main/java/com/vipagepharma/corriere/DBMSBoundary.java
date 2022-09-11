package com.vipagepharma.corriere;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.vipagepharma.corriere.comunicazioneDBMSFallita.ComunicazioneDBMSFallitaControl;

public class DBMSBoundary {
    private static final String url = "jdbc:mysql://vipagesite.duckdns.org:3306/";
    private static final String user = "pi";
    private static final String pass = "BubJbhvbj373838&#@!";
    private static final String dbAzienda = "vipagepharma_azienda";


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

    public static boolean effettuaLogin(String id,String pass) throws IOException {
        boolean esito = false;
        Connection connection=null;
        try {
             connection = connectAzienda();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select u.* from utente u where u.id_utente_azienda = " + id + " and u.isCorriere = 1 and u.password = " + "'" + pass + "'" );
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
                    "VALUES("+"'"+nome+"','" + pass+"','"+ chiave_recupero+"','"+ mail+"',1"+")");
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
            e.printStackTrace();
            //throw new RuntimeException();
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
            ResultSet resultSet = statement.executeQuery("select * from utente where id_utente_azienda = " + id + " and isCorriere = 1 and chiave_recupero = " + "'" + key + "'");
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
            throw new RuntimeException();
        }
        return esito;
    }




    public static void confermaConsegna(String id_lotto, String id_prenotazione, String isCaricato){
        ResultSet resultSet;
        try{
            Connection connection = connectAzienda();
            Statement statement = connection.createStatement();
            statement.executeUpdate("update lotto_ordinato set isCaricato = " + isCaricato + "where id_lotto = " +  id_lotto +  " and id_prenotazione = " +  id_prenotazione);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet getConsegneOdierne(String id_corriere) throws IOException {
        ResultSet resultSet = null;
        Connection connection=null;
        try {
            connection = connectDBMS();
            Statement statement = connection.createStatement();
            java.util.Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
            String strDataOdierna = formatter.format(date);
            resultSet = statement.executeQuery("select p.id_prenotazione, u.nome, p.data_consegna, p.id_utente_farmacia, sum(l.quantita) as quantita from vipagepharma_azienda.prenotazione p, vipagepharma_farmacia.utente u, vipagepharma_azienda.lotto_ordinato l where p.id_utente_farmacia = u.id_utente_farmacia and p.id_utente_azienda = " + id_corriere + " and p.data_consegna = str_to_date('" + strDataOdierna + "','%d-%m-%Y') and l.id_prenotazione=p.id_prenotazione and p.isConsegnato=0 group by p.id_prenotazione, u.nome, p.data_consegna, p.id_utente_farmacia");
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

    public static void contrassegnaOrdineFirmato(String id_prenotazione) throws IOException {
        ResultSet resultSet;
        Connection connection=null;
        try{
             connection = connectAzienda();
            Statement statement = connection.createStatement();
            statement.executeUpdate("update prenotazione set isConsegnato = 1 where id_prenotazione = " + id_prenotazione);
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

    public static void salvaRicevuta(String id_prenotazione, String ricevutaPath) throws IOException {
        ResultSet resultSet;
        Connection connection=null;

         connection = connectAzienda();
        String sql = "update prenotazione set ricevuta_pdf = ? where id_prenotazione = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
        File fileRicevuta= new File(ricevutaPath);
        FileInputStream inputStreamRicevuta= new FileInputStream(fileRicevuta);
        statement.setBlob(1, inputStreamRicevuta);
        statement.setString(2, id_prenotazione);

        statement.executeUpdate();
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
