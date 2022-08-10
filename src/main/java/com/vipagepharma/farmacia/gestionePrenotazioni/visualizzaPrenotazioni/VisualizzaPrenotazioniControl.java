package com.vipagepharma.farmacia.gestionePrenotazioni.visualizzaPrenotazioni;

import com.vipagepharma.farmacia.App;

import java.io.IOException;

public class VisualizzaPrenotazioniControl {


    public void start() throws IOException {
        App.setRoot("gestionePrenotazioni/visualizzaPrenotazioni/SchermataElencoPrenotazioni");
    }

    public static String getIDFarmacia(){
        return com.vipagepharma.farmacia.entity.Utente.getID();
    }
}
