package com.vipagepharma.farmacia.gestionePrenotazioni.modificaContratti;

import com.vipagepharma.farmacia.App;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SchermataModificaContratti {

    public void premeLogout(MouseEvent mouseEvent) throws IOException {
        App.setRoot("autenticazione/login/SchermataLogin");
    }

    public void premeIndietro(MouseEvent mouseEvent) throws IOException {
        ModificaContrattiControl.modifContraCtrl.premutoIndietro("SchermataPrincipale");
    }

    public void premeHome(MouseEvent mouseEvent) throws IOException {
        ModificaContrattiControl.modifContraCtrl.premutoHome("gestionePrenotazioni/modificaContratti/SchermataModificaContratti");
    }
}
