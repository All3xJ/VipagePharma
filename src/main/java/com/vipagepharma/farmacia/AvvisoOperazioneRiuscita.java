package com.vipagepharma.farmacia;

import com.vipagepharma.farmacia.autenticazione.registrazione.RegistrazioneControl;
import com.vipagepharma.farmacia.autenticazione.reimpostaPassword.ReimpostaPasswordControl;
import javafx.fxml.FXML;

import java.io.IOException;

public class AvvisoOperazioneRiuscita {

    private final RegistrazioneControl registrazioneControl = RegistrazioneControl.regCtrlRef;

    @FXML
    public void premeOk() throws IOException {
        registrazioneControl.premutoOk("autenticazione/login/SchermataLogin");
    }
}