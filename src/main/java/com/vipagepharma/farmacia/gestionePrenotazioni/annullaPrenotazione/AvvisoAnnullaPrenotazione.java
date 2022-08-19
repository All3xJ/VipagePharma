package com.vipagepharma.farmacia.gestionePrenotazioni.annullaPrenotazione;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;

public class AvvisoAnnullaPrenotazione {

    @FXML
    public void premeSi(MouseEvent mouseEvent) throws SQLException, IOException {
        AnnullaPrenotazioneControl.annPrenCtrl.premutoSi(mouseEvent);
    }
    @FXML
    public void premeNo(MouseEvent mouseEvent) {
        AnnullaPrenotazioneControl.annPrenCtrl.premutoNo();
    }
}
