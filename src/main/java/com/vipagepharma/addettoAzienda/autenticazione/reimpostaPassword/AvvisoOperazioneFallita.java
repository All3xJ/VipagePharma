package com.vipagepharma.addettoAzienda.autenticazione.reimpostaPassword;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AvvisoOperazioneFallita implements Initializable {
    @FXML
    private Text errore_generico;

    @Override
    public void initialize(URL url, ResourceBundle resbound) {
        errore_generico.setText(ReimpostaPasswordControl.errore);
    }


    @FXML
    public void premeOk(MouseEvent event) throws IOException {
        if(ReimpostaPasswordControl.errore.equals("Le password devono coincidere!")){
            ReimpostaPasswordControl.repassCtrlRef.premutoOk("autenticazione/reimpostaPassword/SchermataNuovaPassword");
        }
        else{
            ReimpostaPasswordControl.repassCtrlRef.premutoOk("autenticazione/reimpostaPassword/SchermataReimpostaPassword");
        }

    }
}