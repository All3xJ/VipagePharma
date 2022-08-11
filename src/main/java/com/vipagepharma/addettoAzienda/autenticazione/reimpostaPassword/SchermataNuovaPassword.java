package com.vipagepharma.addettoAzienda.autenticazione.reimpostaPassword;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SchermataNuovaPassword {
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confermaPassword;
    private final ReimpostaPasswordControl reimpostaPasswordControl = ReimpostaPasswordControl.repassCtrlRef;
    @FXML
    void premeInvia(MouseEvent event) throws IOException {
        reimpostaPasswordControl.inviaPassword(this.password.getText(),this.confermaPassword.getText());
    }

    @FXML
    void premeHome(MouseEvent event) throws IOException {

    }
    @FXML
    void premeLogout(MouseEvent event) throws IOException{

    }
    @FXML
    void premeIndietro(MouseEvent event) throws IOException{

    }
}

