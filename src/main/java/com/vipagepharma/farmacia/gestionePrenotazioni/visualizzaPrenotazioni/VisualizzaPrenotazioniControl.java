package com.vipagepharma.farmacia.gestionePrenotazioni.visualizzaPrenotazioni;

import com.vipagepharma.farmacia.App;

import java.io.IOException;

public class VisualizzaPrenotazioniControl {


    public void start() throws IOException {
        // deve fare roba col db
        App.setRoot("gestionePrenotazioni/visualizzaPrenotazioni/SchermataElencoPrenotazioni"); // se sono giuste le credenziali mi porta alla home
        System.out.println("ciao");
    }
}
