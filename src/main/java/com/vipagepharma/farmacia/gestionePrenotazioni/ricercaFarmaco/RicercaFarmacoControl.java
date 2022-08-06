package com.vipagepharma.farmacia.gestionePrenotazioni.ricercaFarmaco;

import com.vipagepharma.farmacia.App;

import java.io.IOException;

public class RicercaFarmacoControl {

    public void start() throws IOException {
        App.setRoot("gestionePrenotazioni/ricercaFarmaco/SchermataRicercaFarmaco"); // se sono giuste le credenziali mi porta alla home
        System.out.println("ciao");
    }
}
