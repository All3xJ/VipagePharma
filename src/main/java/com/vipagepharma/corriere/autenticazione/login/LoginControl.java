package com.vipagepharma.corriere.autenticazione.login;

import com.vipagepharma.corriere.App;
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
        App.setRoot("SchermataPrincipale"); // se sono giuste le credenziali mi porta alla home
        System.out.println("ciao");
    }
}
