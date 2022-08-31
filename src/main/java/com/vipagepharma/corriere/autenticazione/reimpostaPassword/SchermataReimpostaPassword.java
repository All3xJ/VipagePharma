package com.vipagepharma.corriere.autenticazione.reimpostaPassword;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SchermataReimpostaPassword {

    public static String schermataPrecedente;
    @FXML
    private TextField id;
    @FXML
    private TextField key;
    private final ReimpostaPasswordControl reimpostaPasswordControl = ReimpostaPasswordControl.repassCtrlRef;
    @FXML
    void premeInvia(ActionEvent event) throws IOException {
        reimpostaPasswordControl.premutoInvia(id.getText(),key.getText(),event);

    }

    @FXML
    void premeIndietro(MouseEvent event) throws IOException {
        ReimpostaPasswordControl.repassCtrlRef.premutoIndietro(schermataPrecedente);
    }
}
