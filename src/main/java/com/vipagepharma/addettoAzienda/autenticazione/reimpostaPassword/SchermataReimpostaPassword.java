package com.vipagepharma.addettoAzienda.autenticazione.reimpostaPassword;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SchermataReimpostaPassword {
    @FXML
    private TextField id;
    @FXML
    private TextField key;
    private final ReimpostaPasswordControl reimpostaPasswordControl = ReimpostaPasswordControl.repassCtrlRef;
    @FXML
    void premeInvia(MouseEvent event) throws IOException {
        reimpostaPasswordControl.premutoInvia(id.getText(),key.getText());

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
