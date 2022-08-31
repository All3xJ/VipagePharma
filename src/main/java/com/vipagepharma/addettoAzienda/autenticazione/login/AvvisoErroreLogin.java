package com.vipagepharma.addettoAzienda.autenticazione.login;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AvvisoErroreLogin{

    private final LoginControl loginControl = LoginControl.logCtrlRef;
    @FXML
    public void premeOk(MouseEvent event) throws IOException {
        loginControl.premutoOk();
    }
}
