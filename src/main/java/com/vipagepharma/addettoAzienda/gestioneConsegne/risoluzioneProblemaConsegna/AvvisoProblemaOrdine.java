package com.vipagepharma.addettoAzienda.gestioneConsegne.risoluzioneProblemaConsegna;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class AvvisoProblemaOrdine {

    @FXML
    private Text testo_problema_ordine;

    @FXML
    void premeCompletaOrdine(MouseEvent event) throws SQLException, IOException {
        RisoluzioneProblemaConsegnaControl.risProbConsCtrlRef.premutoCompletaOrdine(event);
    }

    @FXML
    void premeRimborsa(MouseEvent event) throws SQLException, IOException {
        RisoluzioneProblemaConsegnaControl.risProbConsCtrlRef.premutoRimborsa(event);
    }
}
