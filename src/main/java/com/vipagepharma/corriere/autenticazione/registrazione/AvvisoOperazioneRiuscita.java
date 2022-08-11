package com.vipagepharma.corriere.autenticazione.registrazione;

import javafx.fxml.FXML;

import java.io.IOException;

public class AvvisoOperazioneRiuscita {

    private final RegistrazioneControl registrazioneControl = RegistrazioneControl.regCtrlRef;

    @FXML
    public void premeOk() throws IOException {
        registrazioneControl.premutoOk("autenticazione/login/SchermataLogin");
    }
}
