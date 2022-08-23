package com.vipagepharma.farmacia.gestioneFarmaci.scaricoFarmaci;

import com.vipagepharma.farmacia.entity.Farmaco;
import com.vipagepharma.farmacia.gestioneFarmaci.controlloScorte.ControlloScorteControl;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AvvisoScaricoRiuscito {

    public static Farmaco farmaco;
    @FXML
    public void premeOk(MouseEvent mouseEvent) throws IOException {
        ControlloScorteControl conScCtrl = new ControlloScorteControl();
        conScCtrl.start(farmaco,mouseEvent);
    }
}
