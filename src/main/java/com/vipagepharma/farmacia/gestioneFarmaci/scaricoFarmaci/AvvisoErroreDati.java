package com.vipagepharma.farmacia.gestioneFarmaci.scaricoFarmaci;

import com.vipagepharma.farmacia.autenticazione.login.LoginControl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AvvisoErroreDati implements Initializable {

    @FXML
    private Text testoErrore;

    @Override
    public void initialize(URL url, ResourceBundle resbound){
        this.testoErrore.setText("Quantità dei farmaci da scaricare invalida");
    }


    @FXML
    public void premeOk() throws IOException {
        ScaricoFarmaciControl.scarFarmCtrl.premutoOk();
    }
}
