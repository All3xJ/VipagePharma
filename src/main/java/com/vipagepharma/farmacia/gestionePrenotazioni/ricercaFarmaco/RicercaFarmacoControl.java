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

    public void premutoIndietro(String schermataPrecedente) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        App a = new App();
        Object schermata = Class.forName(a.getPackageFromFile(schermataPrecedente)).getConstructor().newInstance();
        if (schermata instanceof SchermataPrincipale) {
            SchermataPrincipale schermataVera = (SchermataPrincipale) schermata;
            schermataVera.schermataPrecedente = "c";
            System.out.println(a.getPackageFromFile(schermataPrecedente));
        }
        App.setRoot(schermataPrecedente);
    }
}
