package com.vipagepharma.farmacia.gestioneFarmaci.scaricoFarmaci;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.gestionePrenotazioni.visualizzaPrenotazioni.VisualizzaPrenotazioniControl;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SchermataScarico {

    public static String schermataPrecedente;
    public void premeHome(MouseEvent mouseEvent) throws IOException {
        ScaricoFarmaciControl.scarFarmCtrl.premutoHome("gestioneFarmaci/scaricoFarmaci/SchermataScarico");
    }

    public void premeIndietro(MouseEvent mouseEvent) throws IOException {
        ScaricoFarmaciControl.scarFarmCtrl.premutoIndietro();
    }

    public void premeLogout(MouseEvent mouseEvent) throws IOException {
        App.setRoot("autenticazione/login/SchermataLogin");
    }

    public void premeScarica(MouseEvent mouseEvent) {
    }
}
