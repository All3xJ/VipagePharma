package com.vipagepharma.corriere.autenticazione.login;

import com.vipagepharma.corriere.App;
import com.vipagepharma.corriere.DBMSBoundary;
import com.vipagepharma.corriere.entity.Utente;
import javafx.event.ActionEvent;
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

    public void start(ActionEvent event) throws IOException {
        if (DBMSBoundary.effettuaLogin(this.id.getText(),this.pass.getText())) {
            Utente.creaUtente(this.id.getText());
            App.setRoot("SchermataPrincipale"); // se sono giuste le credenziali mi porta alla home
        } else{
            App.newWind("autenticazione/login/AvvisoErroreLogin",event);
        }
    }

    public void mostra(String schermata) throws IOException {
        App.setRoot(schermata);
    }

    public void premutoOk() throws IOException {
        App.popup_stage.close();
        this.mostra("autenticazione/login/SchermataLogin");
    }
}
