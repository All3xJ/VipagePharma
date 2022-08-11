package com.vipagepharma.farmacia.gestionePrenotazioni.caricoPrenotazione;

import com.vipagepharma.farmacia.autenticazione.logout.LogoutControl;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SchermataRiepilogoCarico {

    public void premeLogout(MouseEvent mouseEvent) throws IOException {
        LogoutControl.start();
    }

    public void premeIndietro(MouseEvent mouseEvent) throws IOException {
        CaricoPrenotazioneControl.carPrenCtrl.premutoIndietro();
    }

    public void premeHome(MouseEvent mouseEvent) throws IOException {
        CaricoPrenotazioneControl.carPrenCtrl.premutoHome("gestionePrenotazioni/caricoPrenotazione/SchermataRiepilogoCarico");
    }

}
