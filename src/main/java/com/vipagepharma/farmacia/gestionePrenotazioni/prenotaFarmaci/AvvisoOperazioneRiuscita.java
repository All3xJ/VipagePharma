package com.vipagepharma.farmacia.gestionePrenotazioni.prenotaFarmaci;

import com.vipagepharma.farmacia.autenticazione.registrazione.RegistrazioneControl;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AvvisoOperazioneRiuscita {

    @FXML
    public void premeOk() throws IOException {
        PrenotaFarmaciControl.controlRef.premutoOk();
    }
}
