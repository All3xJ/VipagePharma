package com.vipagepharma.farmacia.gestionePrenotazioni.caricoPrenotazione;

import com.vipagepharma.corriere.App;
import com.vipagepharma.farmacia.SchermataPrincipale;
import com.vipagepharma.farmacia.autenticazione.logout.LogoutControl;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class CaricoPrenotazioneControl {

    public static CaricoPrenotazioneControl carPrenCtrl;

    public CaricoPrenotazioneControl(){
        carPrenCtrl = this;
    }

    public void start() throws IOException {
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
