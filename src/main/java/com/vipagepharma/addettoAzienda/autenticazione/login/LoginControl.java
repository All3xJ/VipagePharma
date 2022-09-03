package com.vipagepharma.addettoAzienda.autenticazione.login;

import com.vipagepharma.addettoAzienda.App;
import com.vipagepharma.addettoAzienda.DBMSBoundary;
import com.vipagepharma.addettoAzienda.entity.Utente;
import com.vipagepharma.addettoAzienda.gestioneFarmaci.produzioneFarmaci.ProduzioneFarmaciControl;
import com.vipagepharma.addettoAzienda.gestionePrenotazioni.prenotazioneFarmaciDaBanco.PrenotazioneFarmaciDaBancoControl;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
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
        this.hourlyTask = new TimerTask () {
            @Override
            public void run () {
                PrenotazioneFarmaciDaBancoControl controlPre = new PrenotazioneFarmaciDaBancoControl();
                ProduzioneFarmaciControl controlProd = new ProduzioneFarmaciControl();
                try {
                    controlPre.start();
                    controlProd.start();
                } catch (SQLException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        logCtrlRef = this;
    }

    public void start(ActionEvent event) throws IOException {
        if (DBMSBoundary.effettuaLogin(this.id.getText(),this.pass.getText())) {
            Utente.creaUtente(this.id.getText());
            this.timer.schedule(hourlyTask,1,1000*60*60);   // essenzialmente fa check ogni ora
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
