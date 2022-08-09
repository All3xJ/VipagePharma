package com.vipagepharma.farmacia.autenticazione.registrazione;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.DBMSBoundary;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Random;

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

    public void premutoRegistra(String nome,String email,String password,String confermaPassword) throws IOException {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.confermaPassword = confermaPassword;
        if(!checkPass()){
            App.setRoot("autenticazione/registrazione/AvvisoPasswordErrate");
        }
        else{
            boolean mailValida = DBMSBoundary.verificaMail(this.email);
            if(!mailValida){
                App.setRoot("autenticazione/registrazione/AvvisoMailErrata");
            }
            else{
                String key = this.generaKey();
                ResultSet id = DBMSBoundary.registra(this.nome,this.email,this.password,key);
                //this.generaKeyEInvioEmail(id);
                App.setRoot("AvvisoOperazioneRiuscita");
            }
        }
    }

    public void premutoOk(String schermata) throws IOException {
        App.setRoot(schermata);
    }
    private boolean checkPass(){
        if(this.password.equals(this.confermaPassword))
            return true;
        return false;
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
