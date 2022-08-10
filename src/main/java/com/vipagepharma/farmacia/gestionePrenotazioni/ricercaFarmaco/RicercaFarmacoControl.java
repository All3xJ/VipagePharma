package com.vipagepharma.farmacia.gestionePrenotazioni.ricercaFarmaco;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.SchermataPrincipale;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class RicercaFarmacoControl {

    public static RicercaFarmacoControl ricercFarmCtrlRef;

    public RicercaFarmacoControl(){
        ricercFarmCtrlRef = this;
    }

    public void start() throws IOException {
        SchermataRicercaFarmaco.schermataPrecedente = "SchermataPrincipale";
        App.setRoot("gestionePrenotazioni/ricercaFarmaco/SchermataRicercaFarmaco"); // se sono giuste le credenziali mi porta alla home
    }

    public void premutoIndietro(String schermataPrecedente) throws IOException{
        App.setRoot(schermataPrecedente);
    }

    public void premutoHome(String schermataPrecedente) throws IOException {
        SchermataPrincipale.schermataPrecedente=schermataPrecedente;
        App.setRoot("SchermataPrincipale");
    }
}
