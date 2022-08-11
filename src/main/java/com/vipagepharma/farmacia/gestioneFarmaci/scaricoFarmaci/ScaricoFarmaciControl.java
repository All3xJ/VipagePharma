package com.vipagepharma.farmacia.gestioneFarmaci.scaricoFarmaci;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.SchermataPrincipale;

import java.io.IOException;

public class ScaricoFarmaciControl {

    public static ScaricoFarmaciControl scarFarmCtrl;

    public ScaricoFarmaciControl(){
        scarFarmCtrl = this;
    }

    public void start() throws IOException {
        SchermataScarico.schermataPrecedente = "SchermataPrincipale";
        App.setRoot("gestioneFarmaci/scaricoFarmaci/SchermataScarico"); // se sono giuste le credenziali mi porta alla home
    }

    public void premutoIndietro() throws IOException {
        App.setRoot(SchermataScarico.schermataPrecedente);
    }

    public void premutoHome(String schermataPrecedente) throws IOException {
        SchermataPrincipale.schermataPrecedente=schermataPrecedente;
        App.setRoot("SchermataPrincipale");
    }
}
