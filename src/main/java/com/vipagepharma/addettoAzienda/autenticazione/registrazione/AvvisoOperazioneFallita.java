package com.vipagepharma.addettoAzienda.autenticazione.registrazione;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AvvisoOperazioneFallita implements Initializable {
    @FXML
    private Text errore_generico;

    @Override
    public void initialize(URL url, ResourceBundle resbound) {
        errore_generico.setText(RegistrazioneControl.errore);
    }


    @FXML
    public void premeOk(MouseEvent event) throws IOException {
        RegistrazioneControl.regCtrlRef.premutoOk("autenticazione/registrazione/SchermataRegistrazione");
    }
}