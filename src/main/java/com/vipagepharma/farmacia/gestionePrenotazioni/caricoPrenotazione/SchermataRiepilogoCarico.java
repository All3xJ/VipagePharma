package com.vipagepharma.farmacia.gestionePrenotazioni.caricoPrenotazione;

import com.vipagepharma.farmacia.App;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SchermataRiepilogoCarico {

    public void premeLogout(MouseEvent mouseEvent) throws IOException {
        App.setRoot("autenticazione/login/SchermataLogin");
    }

    public void premeIndietro(MouseEvent mouseEvent) throws IOException {
        CaricoPrenotazioneControl.carPrenCtrl.premutoIndietro();
    }

    public void premeHome(MouseEvent mouseEvent) throws IOException {
        CaricoPrenotazioneControl.carPrenCtrl.premutoHome("gestionePrenotazioni/caricoPrenotazione/SchermataRiepilogoCarico");
    }

}
