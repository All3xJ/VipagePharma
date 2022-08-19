package com.vipagepharma.farmacia.gestionePrenotazioni.modificaPrenotazione;

import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AvvisoConfermaModifica {

    public void premeSi(MouseEvent event) throws IOException {
        ModificaPrenotazioneControl.modificaPrenotazioneControl.premutoSi(event);
    }
    public void premeNo(MouseEvent event) throws IOException {
        ModificaPrenotazioneControl.modificaPrenotazioneControl.premutoNo(event);
    }
}
