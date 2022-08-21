package com.vipagepharma.farmacia.gestionePrenotazioni.modificaPrenotazione;

import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;

public class AvvisoConfermaModifica {

    public void premeSi(MouseEvent event) throws IOException, SQLException {
        ModificaPrenotazioneControl.modificaPrenotazioneControl.premutoSi(event);
    }
    public void premeNo(MouseEvent event) throws IOException, SQLException {
        ModificaPrenotazioneControl.modificaPrenotazioneControl.premutoNo(event);
    }
}
