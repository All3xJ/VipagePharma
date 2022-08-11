package com.vipagepharma.farmacia.autenticazione.registrazione;

import com.vipagepharma.farmacia.App;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AvvisoMailNonDisponibile {

    @FXML
    public void premeOk(MouseEvent event) throws IOException {
        RegistrazioneControl.regCtrlRef.premutoOk("autenticazione/registrazione/SchermataRegistrazione");
    }
}
