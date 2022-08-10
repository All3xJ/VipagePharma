package com.vipagepharma.farmacia.gestionePrenotazioni.modificaContratti;

import com.vipagepharma.farmacia.autenticazione.logout.LogoutControl;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SchermataModificaContratti {

    public static String schermataPrecedente;

    public void premeLogout(MouseEvent mouseEvent) throws IOException {
        LogoutControl.start();
    }

    public void premeIndietro(MouseEvent mouseEvent) throws IOException {
        ModificaContrattiControl.modifContraCtrl.premutoIndietro(schermataPrecedente);
    }

    public void premeHome(MouseEvent mouseEvent) throws IOException {
        ModificaContrattiControl.modifContraCtrl.premutoHome("gestionePrenotazioni/modificaContratti/SchermataModificaContratti");
    }
}
