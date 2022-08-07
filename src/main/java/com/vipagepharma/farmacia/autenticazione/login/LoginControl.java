package com.vipagepharma.farmacia.autenticazione.login;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.DBMSBoundary;
import com.vipagepharma.farmacia.entity.Utente;
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
        if (DBMSBoundary.effettuaLoginFarmacia(this.id.getText(),this.pass.getText())) {
            Utente.creaUtente(this.id.getText());
            App.setRoot("SchermataPrincipale"); // se sono giuste le credenziali mi porta alla home
        } else{
            App.setRoot("autenticazione/login/AvvisoErroreLogin");
            createSolButtonHandler();
        }
    }

    public EventHandler<Event> createSolButtonHandler()
    {
        btnSolHandler = new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                System.out.println("Pressed!");
                biddingHelperFrame.getBtnSag().setVisible(false);
            }
        };
        return btnSolHandler;
    }

}
