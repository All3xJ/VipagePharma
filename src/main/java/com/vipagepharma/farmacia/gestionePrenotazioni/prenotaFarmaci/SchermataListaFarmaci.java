package com.vipagepharma.farmacia.gestionePrenotazioni.prenotaFarmaci;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class SchermataListaFarmaci{

    private String id_farmaco;
    @FXML
    public void premeLogout(MouseEvent mouseEvent) {
    }

    public void creaControl(){
        PrenotaFarmaciControl prFarCtrl = new PrenotaFarmaciControl();
        prFarCtrl.start();
    }


}
