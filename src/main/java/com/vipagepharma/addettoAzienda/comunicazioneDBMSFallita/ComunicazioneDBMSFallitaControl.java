package com.vipagepharma.addettoAzienda.comunicazioneDBMSFallita;

import com.vipagepharma.addettoAzienda.App;

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
