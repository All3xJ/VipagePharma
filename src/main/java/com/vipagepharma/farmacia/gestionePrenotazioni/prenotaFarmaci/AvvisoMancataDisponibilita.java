package com.vipagepharma.farmacia.gestionePrenotazioni.prenotaFarmaci;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AvvisoMancataDisponibilita {

    @FXML
    private RadioButton opzione1;

    @FXML
    private RadioButton opzione2;

    private int opzione = 0;

    @FXML
    void selezionaOpzione1(MouseEvent mouseEvent){
        opzione2.setDisable(true);
        this.opzione = 1;
    }

    @FXML
    void selezionaOpzione2(MouseEvent mouseEvent){
        opzione1.setDisable(true);
        this.opzione = 2;
    }
    @FXML
    public void premeConferma(MouseEvent event) throws IOException {
        if(this.opzione != 0)
            PrenotaFarmaciControl.getControl().premutoConferma(this.opzione,event);
    }
}
