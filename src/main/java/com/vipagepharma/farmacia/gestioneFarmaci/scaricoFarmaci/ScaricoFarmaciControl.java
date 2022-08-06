package com.vipagepharma.farmacia.gestioneFarmaci.scaricoFarmaci;

import com.vipagepharma.farmacia.App;

import java.io.IOException;

public class ScaricoFarmaciControl {

    public void start() throws IOException {
        App.setRoot("gestioneFarmaci/scaricoFarmaci/SchermataScarico"); // se sono giuste le credenziali mi porta alla home
        System.out.println("ciao");
    }
}
