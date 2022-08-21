package com.vipagepharma.farmacia.gestionePrenotazioni.modificaPrenotazione;

import com.vipagepharma.farmacia.gestionePrenotazioni.prenotaFarmaci.PrenotaFarmaciControl;
import javafx.fxml.FXML;

import java.io.IOException;

public class AvvisoOperazioneRiuscita {

    @FXML
    public void premeOk() throws IOException {
        ModificaPrenotazioneControl.modificaPrenotazioneControl.premutoOk();
    }
}
