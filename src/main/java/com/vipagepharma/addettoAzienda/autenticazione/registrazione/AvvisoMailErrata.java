package com.vipagepharma.addettoAzienda.autenticazione.registrazione;


import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AvvisoMailErrata {
    private final RegistrazioneControl registrazioneControl = RegistrazioneControl.regCtrlRef;

    @FXML
    public void premeOk() throws IOException {
        registrazioneControl.premutoOk("autenticazione/login/SchermataLogin");
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
