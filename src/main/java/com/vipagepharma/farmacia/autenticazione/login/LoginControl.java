package com.vipagepharma.farmacia.autenticazione.login;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.DBMSBoundary;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginControl {
    private TextField id;
    private PasswordField pass;

    public LoginControl(TextField id,PasswordField pass){
        this.id = id;
        this.pass = pass;
    }

    public void start() throws IOException {
        if (this.effettuaLogin(this.id,this.pass)==true) {
            App.setRoot("SchermataPrincipale"); // se sono giuste le credenziali mi porta alla home
            System.out.println("ciao");
        } else{
            System.out.println("suca");
        }
    }

    private boolean effettuaLogin(TextField id, PasswordField pass){
        DBMSBoundary.start();
        return true;
    }
}
