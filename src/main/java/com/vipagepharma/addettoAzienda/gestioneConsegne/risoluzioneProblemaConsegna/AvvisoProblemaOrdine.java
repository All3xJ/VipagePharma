package com.vipagepharma.addettoAzienda.gestioneConsegne.risoluzioneProblemaConsegna;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AvvisoProblemaOrdine implements Initializable {

    @FXML
    private Text testo_problema_ordine;

    @Override
    public void initialize(URL url, ResourceBundle resbound) {
        this.testo_problema_ordine.setText("La consegna n. "+RisoluzioneProblemaConsegnaControl.risProbConsCtrlRef.consegna.idOrdine.get()+ " prevista per la farmacia "+RisoluzioneProblemaConsegnaControl.risProbConsCtrlRef.consegna.nomeFarmacia.get()+" ha avuto qualche problema!");

    }

    @FXML
    void premeCompletaOrdine(MouseEvent event) throws SQLException, IOException {
        RisoluzioneProblemaConsegnaControl.risProbConsCtrlRef.premutoCompletaOrdine(event);
    }

    @FXML
    void premeRimborsa(MouseEvent event) throws SQLException, IOException {
        RisoluzioneProblemaConsegnaControl.risProbConsCtrlRef.premutoRimborsa(event);
    }
}
