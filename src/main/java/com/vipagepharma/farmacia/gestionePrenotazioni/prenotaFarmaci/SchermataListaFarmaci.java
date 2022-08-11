package com.vipagepharma.farmacia.gestionePrenotazioni.prenotaFarmaci;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SchermataListaFarmaci{

    private String id_farmaco;
    @FXML

    public void premeLogout(MouseEvent mouseEvent) {
    }

    public void premePrenota() throws IOException {
        PrenotaFarmaciControl prFarCtrl = new PrenotaFarmaciControl();
        prFarCtrl.start(this.id_farmaco);
    }
}
