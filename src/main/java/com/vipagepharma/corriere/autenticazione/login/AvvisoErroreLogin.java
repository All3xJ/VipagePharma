package com.vipagepharma.corriere.autenticazione.login;

import com.vipagepharma.farmacia.autenticazione.login.LoginControl;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AvvisoErroreLogin {

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

    public void premeOk(MouseEvent mouseEvent) {
    }
}
