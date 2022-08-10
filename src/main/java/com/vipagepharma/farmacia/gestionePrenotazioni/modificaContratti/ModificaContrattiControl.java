package com.vipagepharma.farmacia.gestionePrenotazioni.modificaContratti;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.SchermataPrincipale;

import java.io.IOException;

public class ModificaContrattiControl {

    public static ModificaContrattiControl modifContraCtrl;

    public ModificaContrattiControl(){
        modifContraCtrl = this;
    }

    public void start() throws IOException {
        SchermataModificaContratti.schermataPrecedente="SchermataPrincipale";
        App.setRoot("gestionePrenotazioni/modificaContratti/SchermataModificaContratti");
    }

    public void premutoIndietro(String schermataPrecedente) throws IOException {
        App.setRoot(schermataPrecedente);
    }

    public void premutoHome(String schermataPrecedente) throws IOException {
        SchermataPrincipale.schermataPrecedente=schermataPrecedente;
        App.setRoot("SchermataPrincipale");
    }
}
