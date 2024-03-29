package com.vipagepharma.farmacia.autenticazione.registrazione;

import com.vipagepharma.farmacia.autenticazione.login.SchermataLogin;
import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.DBMSBoundary;
import com.vipagepharma.farmacia.SchermataPrincipale;
import com.vipagepharma.farmacia.autenticazione.reimpostaPassword.SchermataNuovaPassword;
import com.vipagepharma.farmacia.gestionePrenotazioni.visualizzaPrenotazioni.SchermataElencoPrenotazioni;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

public class RegistrazioneControl {

    private String nome;
    private String email;
    private String password;
    private String confermaPassword;
    public static String errore;
    public static RegistrazioneControl regCtrlRef;

    public RegistrazioneControl(){
        regCtrlRef = this;
    }

    public void start() throws IOException {
        App.setRoot("autenticazione/registrazione/SchermataRegistrazione");
    }

    public void premutoRegistra(String nome,String email,String password,String confermaPassword,ActionEvent event) throws IOException, SQLException, MessagingException {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.confermaPassword = confermaPassword;
        if(!this.checkPassword()){
            errore="Le password devono coincidere!";
            App.newWind("autenticazione/registrazione/AvvisoOperazioneFallita",event);
        }
        else{
            if(!checkFormattazioneEmail()){
                errore="Formato dell'email non valido\nInserire formato valido\n(es. nome@example.com)";
                App.newWind("autenticazione/registrazione/AvvisoOperazioneFallita",event);
            }
            else {
                if (!DBMSBoundary.verificaMail(this.email)) {
                    errore="La mail inserita è già registrata nel sistema";
                    App.newWind("autenticazione/registrazione/AvvisoOperazioneFallita",event);
                }
                else {
                    String key = this.generaKey();
                    ResultSet resultSet = DBMSBoundary.registra(this.nome, this.email, this.password, key);
                    if (resultSet.next()) {
                        String id = resultSet.getString("id");
                        this.invioEmail(email,id,key);
                        App.newWind("autenticazione/registrazione/AvvisoOperazioneRiuscita",event);
                    }

                }
            }
        }
    }

    private void invioEmail(String email,String id,String key) throws MessagingException {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("vipagepharma@gmail.com", "erretxvrmmprdgyl");
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("vipagepharma@gmail.com"));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject("Chiave recupero per ViPagePharma");

        String msg = "La registrazione è andata a buon fine.<br/><br/>Il tuo ID per l'accesso è: "+id+" <br/><br/>La tua chiave di recupero è: "+key;

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }

    public void premutoOk(String schermata) throws IOException {
        App.popup_stage.close();
        App.setRoot(schermata);
    }

    private boolean checkPassword(){
        if(this.password.equals(this.confermaPassword)){
            return true;
        }
        return false;
    }
    private boolean checkFormattazioneEmail(){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    private String generaKey(){
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String key = salt.toString();
        return key;
    }

    public void premutoIndietro(String schermataPrecedente) throws IOException {
        App.setRoot(schermataPrecedente);
    }

}
