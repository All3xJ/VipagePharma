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

    public void premutoOk(MouseEvent event) throws IOException {
        LoginControl loginControl = LoginControl.logCtrlRef;
        loginControl.mostra("autenticazione/login/SchermataLogin");
    }

    public void premutoIndietro(MouseEvent event) throws IOException{

    }

    public void premutoHome(MouseEvent event) throws IOException{

    }

    public void premutoLogout(MouseEvent event) throws IOException{

    }
}
