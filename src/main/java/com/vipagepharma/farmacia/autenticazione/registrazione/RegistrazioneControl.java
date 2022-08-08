package com.vipagepharma.farmacia.autenticazione.registrazione;

import com.vipagepharma.farmacia.App;

import java.io.IOException;

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
    
    public void setDati(String nome,String email,String password,String confermaPassword){
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.confermaPassword = confermaPassword;
    }
    public void checkPass(){

    }
}
