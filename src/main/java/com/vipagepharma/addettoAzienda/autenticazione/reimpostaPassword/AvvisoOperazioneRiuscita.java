package com.vipagepharma.addettoAzienda.autenticazione.reimpostaPassword;

import javafx.fxml.FXML;

import java.io.IOException;

public class AvvisoOperazioneRiuscita {

    private final ReimpostaPasswordControl reimpostaPasswordControl = ReimpostaPasswordControl.repassCtrlRef;

    @FXML
    public void premeOk() throws IOException {
        reimpostaPasswordControl.premutoOk("autenticazione/login/SchermataLogin");
    }
}
