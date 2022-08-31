package com.vipagepharma.addettoAzienda.autenticazione.reimpostaPassword;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SchermataNuovaPassword {

    public static String schermataPrecedente;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confermaPassword;
    private final ReimpostaPasswordControl reimpostaPasswordControl = ReimpostaPasswordControl.repassCtrlRef;
    @FXML
    void premeInvia(ActionEvent event) throws IOException {
        reimpostaPasswordControl.inviaPassword(this.password.getText(),this.confermaPassword.getText(),event);
    }

    @FXML
    void premeIndietro(MouseEvent event) throws IOException{
        ReimpostaPasswordControl.repassCtrlRef.premutoIndietro(schermataPrecedente);
    }
}

