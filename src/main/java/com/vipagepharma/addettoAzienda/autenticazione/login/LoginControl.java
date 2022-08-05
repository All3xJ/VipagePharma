package com.vipagepharma.addettoAzienda.autenticazione.login;

import com.vipagepharma.farmacia.App;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginControl {
    private TextField id;
    private TextField pass;

    public LoginControl(TextField id,TextField pass){
        this.id = id;
        this.pass = pass;
    }

    public void start() throws IOException {
        App.setRoot("SchermataPrincipaleFarmacista"); // se sono giuste le credenziali mi porta alla home
        System.out.println("ciao");
    }
}
