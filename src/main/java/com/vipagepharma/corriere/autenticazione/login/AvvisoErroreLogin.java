package com.vipagepharma.corriere.autenticazione.login;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AvvisoErroreLogin{

    private final LoginControl loginControl = LoginControl.logCtrlRef;
    @FXML
    public void premeOk(MouseEvent event) throws IOException {
        loginControl.premutoOk();
    }
    @FXML
    public void premeIndietro(MouseEvent event) throws IOException{

    }
    @FXML
    public void premeHome(MouseEvent event) throws IOException{

    }
    @FXML
    public void premeLogout(MouseEvent event) throws IOException{

    }
}
