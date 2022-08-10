package com.vipagepharma.farmacia.autenticazione.registrazione;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.DBMSBoundary;

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
    public static RegistrazioneControl regCtrlRef;

    public RegistrazioneControl(){
        regCtrlRef = this;
    }

    public void start() throws IOException {
        App.setRoot("autenticazione/registrazione/SchermataRegistrazione");
    }

    public void premutoRegistra(String nome,String email,String password,String confermaPassword) throws IOException, SQLException {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.confermaPassword = confermaPassword;
        if(!this.checkPassword()){
            App.setRoot("autenticazione/registrazione/AvvisoPasswordErrate");
        }
        else{
            if(!checkFormattazioneEmail()){
                App.setRoot("autenticazione/registrazione/AvvisoMailErrata");
            }
            else {
                if (!DBMSBoundary.verificaMail(this.email)) {
                    App.setRoot("autenticazione/registrazione/AvvisoMailNonDisponibile");
                }
                else {
                    String key = this.generaKey();
                    ResultSet resultSet = DBMSBoundary.registra(this.nome, this.email, this.password, key);
                    if (resultSet.next()) {
                        String id = resultSet.getString("id");
                    }
                    //this.invioEmail(id,key);
                    App.setRoot("AvvisoOperazioneRiuscita");
                }
            }
        }
    }

    public void premutoOk(String schermata) throws IOException {
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
        while (salt.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String key = salt.toString();
        return key;
    }
}
