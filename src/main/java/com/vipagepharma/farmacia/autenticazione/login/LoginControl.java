package com.vipagepharma.farmacia.autenticazione.login;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.DBMSBoundary;
import com.vipagepharma.farmacia.entity.Utente;
import com.vipagepharma.farmacia.gestioneConsegne.controlloConsegna.ControlloConsegnaControl;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class LoginControl{
    private TextField id;
    private PasswordField pass;

    private Timer timer;
    private TimerTask hourlyTask;

    public static LoginControl logCtrlRef;

    public LoginControl(TextField id,PasswordField pass){
        this.id = id;
        this.pass = pass;
        this.timer = new Timer();
        this.hourlyTask = new TimerTask() {
            @Override
            public void run () {
                ControlloConsegnaControl contrConsCtrl = new ControlloConsegnaControl();
                try {
                    contrConsCtrl.start();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        logCtrlRef = this;
    }

    public void start(ActionEvent actionEvent) throws IOException {
        if (DBMSBoundary.effettuaLogin(this.id.getText(),this.pass.getText())) {
            Utente.creaUtente(this.id.getText());
            App.setRoot("SchermataPrincipale");
            this.timer.schedule(hourlyTask,1,1000*60*60);   // essenzialmente fa check ogni ora
        } else{
            App.newWind("autenticazione/login/AvvisoErroreLogin",actionEvent);
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
