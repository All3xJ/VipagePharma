package com.vipagepharma.corriere.autenticazione.registrazione;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AvvisoOperazioneRiuscita {

    private final RegistrazioneControl registrazioneControl = RegistrazioneControl.regCtrlRef;

    @FXML
    public void premeOk(MouseEvent event) throws IOException {
        registrazioneControl.premutoOk("autenticazione/login/SchermataLogin");
    }
}
