package com.vipagepharma.farmacia.autenticazione.reimpostaPassword;

import com.vipagepharma.farmacia.autenticazione.reimpostaPassword.ReimpostaPasswordControl;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AvvisoPasswordErrate {
    private final ReimpostaPasswordControl reimpostaPasswordControl = ReimpostaPasswordControl.repassCtrlRef;

    @FXML
    public void premeOk() throws IOException {
        reimpostaPasswordControl.premutoOk("autenticazione/reimpostaPassword/SchermataNuovaPassword");

    }

    @FXML
    void premeHome(MouseEvent event) throws IOException{

    }
    @FXML
    void premeLogout(MouseEvent event) throws IOException{

    }
    @FXML
    void premeIndietro(MouseEvent event) throws IOException{

    }
}
