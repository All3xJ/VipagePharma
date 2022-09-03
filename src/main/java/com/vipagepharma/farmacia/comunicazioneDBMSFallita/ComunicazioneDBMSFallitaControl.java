package com.vipagepharma.farmacia.comunicazioneDBMSFallita;

import com.vipagepharma.farmacia.App;

import java.io.IOException;

public class ComunicazioneDBMSFallitaControl {

    public static ComunicazioneDBMSFallitaControl comDBFalLCtrl;

    public ComunicazioneDBMSFallitaControl(){
        comDBFalLCtrl=this;
    }

    public void start() throws IOException {
        App.newWind("comunicazioneDBMSFallita/AvvisoComunicazioneFallita");
    }

    public void premutoOk(){
        App.popup_stage.close();
    }
}
