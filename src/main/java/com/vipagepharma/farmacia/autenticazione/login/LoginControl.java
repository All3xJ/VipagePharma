package com.vipagepharma.farmacia.autenticazione.login;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.DBMSBoundary;
import com.vipagepharma.farmacia.entity.Utente;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.io.IOException;

public class LoginControl{
    private TextField id;
    private PasswordField pass;

    public static LoginControl logCtrlRef;

    public LoginControl(TextField id,PasswordField pass){
        this.id = id;
        this.pass = pass;
        logCtrlRef = this;
    }

    public void start() throws IOException {
        if (DBMSBoundary.effettuaLogin(Integer.parseInt(this.id.getText()),this.pass.getText())) {
            Utente.creaUtente(this.id.getText());
            App.setRoot("SchermataPrincipale"); // se sono giuste le credenziali mi porta alla home
        } else{
            App.setRoot("autenticazione/login/AvvisoErroreLogin");
        }
    }

    public void premutoOk() throws IOException {
        this.mostra("autenticazione/login/SchermataLogin");
    }
    public void mostra(String schermata) throws IOException {
        App.setRoot(schermata);
    }


}
