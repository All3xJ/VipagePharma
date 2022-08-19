package com.vipagepharma.farmacia.gestionePrenotazioni.annullaPrenotazione;

import javafx.fxml.FXML;

import java.io.IOException;
import java.sql.SQLException;

public class AvvisoOperazioneRiuscita {

    @FXML
    public void premeOk() throws IOException, SQLException {
        AnnullaPrenotazioneControl.annPrenCtrl.premutoOk();
    }
}
