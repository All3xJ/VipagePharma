package com.vipagepharma.farmacia.gestionePrenotazioni.annullaPrenotazione;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.SchermataPrincipale;
import com.vipagepharma.farmacia.gestionePrenotazioni.visualizzaPrenotazioni.SchermataElencoPrenotazioni;

import java.io.IOException;

public class AnnullaPrenotazioneControl {

    public static AnnullaPrenotazioneControl annPrenCtrl;

    public AnnullaPrenotazioneControl(){
        annPrenCtrl = this;
    }

    public void start() throws IOException {
        App.setRoot("gestionePrenotazioni/annullaPrenotazione/AvvisoAnnullaPrenotazione");
    }

}
