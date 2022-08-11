package com.vipagepharma.farmacia.autenticazione.reimpostaPassword;

import com.vipagepharma.farmacia.autenticazione.reimpostaPassword.ReimpostaPasswordControl;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AvvisoOperazioneFallita {

    private final ReimpostaPasswordControl reimpostaPasswordControl = ReimpostaPasswordControl.repassCtrlRef;

    @FXML
    public void premeOk(MouseEvent event) throws IOException {
        reimpostaPasswordControl.premutoOk("autenticazione/reimpostaPassword/SchermataReimpostaPassword");
    }
}