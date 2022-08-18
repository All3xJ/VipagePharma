package com.vipagepharma.farmacia.gestionePrenotazioni.caricoPrenotazione;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.DBMSBoundary;
import com.vipagepharma.farmacia.SchermataPrincipale;


import java.io.IOException;
import java.sql.ResultSet;

public class CaricoPrenotazioneControl {

    public static CaricoPrenotazioneControl carPrenCtrl;

    private String id_prenotazione;

    private ResultSet lotti_ordinati;

    public CaricoPrenotazioneControl(String id_prenotazione){
        carPrenCtrl = this;
        this.id_prenotazione = id_prenotazione;
    }

    public void start() throws IOException {
        this.lotti_ordinati = DBMSBoundary.getLottiOrdinati(this.id_prenotazione);
        App.setRoot("gestionePrenotazioni/caricoPrenotazione/SchermataRiepilogoCarico");
    }

    public void premutoIndietro() throws IOException {
        App.setRoot("gestionePrenotazioni/visualizzaPrenotazioni/SchermataElencoPrenotazioni");
    }

    public void premutoHome(String schermataPrecedente) throws IOException {
        SchermataPrincipale.schermataPrecedente=schermataPrecedente;
        App.setRoot("SchermataPrincipale");
    }
}
