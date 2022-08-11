package com.vipagepharma.farmacia.autenticazione.registrazione;

import com.vipagepharma.farmacia.App;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AvvisoPasswordErrate {
    private final RegistrazioneControl registrazioneControl = RegistrazioneControl.regCtrlRef;

    @FXML
    public void premeOk(MouseEvent event) throws IOException {
        registrazioneControl.premutoOk("autenticazione/registrazione/SchermataRegistrazione");
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
