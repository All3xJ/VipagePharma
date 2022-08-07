package com.vipagepharma.farmacia.autenticazione.login;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.DBMSBoundary;
import com.vipagepharma.farmacia.entity.Utente;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.io.IOException;

public class LoginControl extends Thread{
    private TextField id;
    private PasswordField pass;

    public LoginControl(TextField id,PasswordField pass){
        this.id = id;
        this.pass = pass;
    }

    public void run() {
        try {
            if (DBMSBoundary.effettuaLoginFarmacia(this.id.getText(),this.pass.getText())) {
                Utente.creaUtente(this.id.getText());
                App.setRoot("SchermataPrincipale"); // se sono giuste le credenziali mi porta alla home
            } else{
                App.setRoot("autenticazione/login/AvvisoErroreLogin");
                AvvisoErroreLogin avv = AvvisoErroreLogin.av;
                avv.checkpremutoOk();
                App.setRoot("autenticazione/login/SchermataLogin");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
