package com.vipagepharma.farmacia.gestionePrenotazioni.modificaContratti;

import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SchermataRiepilogoContratto {

    public static String schermataPrecedente;

    public void premeLogout(MouseEvent mouseEvent) {
    }

    public void premeConferma(MouseEvent mouseEvent) {
    }

    public void premeHome(MouseEvent mouseEvent) throws IOException {
        ModificaContrattiControl.modifContraCtrl.premutoHome("gestionePrenotazioni/modificaContratti/SchermataRiepilogoContratto");
    }

    public void premeIndietro(MouseEvent mouseEvent) throws IOException {
        ModificaContrattiControl.modifContraCtrl.premutoIndietro(schermataPrecedente);
    }
}
