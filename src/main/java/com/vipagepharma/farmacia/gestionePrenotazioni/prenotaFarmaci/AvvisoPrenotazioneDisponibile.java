package com.vipagepharma.farmacia.gestionePrenotazioni.prenotaFarmaci;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;

public class AvvisoPrenotazioneDisponibile {
    @FXML
    public void premeLogout(MouseEvent mouseEvent) {
    }
    @FXML
    public void premeConferma(MouseEvent mouseEvent) throws IOException {
        PrenotaFarmaciControl.getControl().premutoConferma(mouseEvent);
    }
}
