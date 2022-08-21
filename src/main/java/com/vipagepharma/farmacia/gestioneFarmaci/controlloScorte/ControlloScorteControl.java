package com.vipagepharma.farmacia.gestioneFarmaci.controlloScorte;

import com.vipagepharma.corriere.App;
import com.vipagepharma.farmacia.entity.FarmacoScarico;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ControlloScorteControl {
    public static ControlloScorteControl conScCtrl;
    public ControlloScorteControl(){
        conScCtrl = this;
    }
    public void start(FarmacoScarico farmaco, MouseEvent event) throws IOException {
        String id = farmaco.getId();
        String qtyRimanente = farmaco.getQty();
        int tipo = farmaco.getIsBanco();
        if((tipo == 1 && Integer.parseInt(qtyRimanente) < 50) || (tipo == 0 && Integer.parseInt(qtyRimanente) < 20)) {
            App.newWind("gestioneFarmaci/controlloScorte/AvvisoEsaurimentoScorte", event);
        }
    }
}
