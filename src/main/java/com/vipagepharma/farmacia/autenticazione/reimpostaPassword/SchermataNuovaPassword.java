package com.vipagepharma.farmacia.autenticazione.reimpostaPassword;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SchermataNuovaPassword {
    @FXML
    private String password;
    @FXML
    private String confermaPassword;
    private final ReimpostaPasswordControl reimpostaPasswordControl = ReimpostaPasswordControl.repassCtrlRef;
    @FXML
    void premeInvia(MouseEvent event) throws IOException {
        reimpostaPasswordControl.inviaPassword(this.password,this.confermaPassword);
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

