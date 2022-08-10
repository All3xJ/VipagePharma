package com.vipagepharma.corriere.autenticazione.reimpostaPassword;

import javafx.fxml.FXML;

import java.io.IOException;

public class AvvisoOperazioneFallita {

    private final ReimpostaPasswordControl reimpostaPasswordControl = ReimpostaPasswordControl.repassCtrlRef;

    @FXML
    public void premeOk() throws IOException {
        reimpostaPasswordControl.premutoOk("autenticazione/login/schermaLogin");
    }
}