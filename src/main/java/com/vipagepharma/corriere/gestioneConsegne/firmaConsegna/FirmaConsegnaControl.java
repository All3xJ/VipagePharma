package com.vipagepharma.corriere.gestioneConsegne.firmaConsegna;

import com.vipagepharma.corriere.App;
import com.vipagepharma.corriere.entity.Ordine;

import java.io.IOException;

public class FirmaConsegnaControl {

    public static FirmaConsegnaControl firmConsCtrlRef;

    public FirmaConsegnaControl(){
        firmConsCtrlRef = this;
    }

    public void start(Ordine ordine) throws IOException {
        SchermataRiepilogoOrdine.ordine=ordine;
        App.setRoot("gestioneConsegne/firmaConsegna/SchermataRiepilogoOrdine");
    }

    public void premutoFirma(Ordine ordine){

        //ordine.setFilePDF(file);
    }


}
