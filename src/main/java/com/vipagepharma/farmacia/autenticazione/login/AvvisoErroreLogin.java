package com.vipagepharma.farmacia.autenticazione.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.vipagepharma.farmacia.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class AvvisoErroreLogin{

    private final LoginControl loginControl = LoginControl.logCtrlRef;
    @FXML
    public void premeOk() throws IOException {
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
