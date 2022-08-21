package com.vipagepharma.farmacia.gestionePrenotazioni.modificaPrenotazione;

import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;

public class AvvisoModificaErrata {

    public void premeOk(MouseEvent event) throws IOException, SQLException {
        ModificaPrenotazioneControl.modificaPrenotazioneControl.premutoOk(event);
    }
}
