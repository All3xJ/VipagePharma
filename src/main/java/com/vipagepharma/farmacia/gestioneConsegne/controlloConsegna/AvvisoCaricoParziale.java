package com.vipagepharma.farmacia.gestioneConsegne.controlloConsegna;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AvvisoCaricoParziale implements Initializable {

    public static String idprenotazioneProblematica;

    @FXML
    private Text testo_carico_parziale;

    @Override
    public void initialize(URL url, ResourceBundle resbound) {
        this.testo_carico_parziale.setText("La consegna n. "+idprenotazioneProblematica+ " prevista per oggi non è ancora stata caricata");
    }

    public void premeSegnalaUnProblema(MouseEvent mouseEvent) throws IOException {
        ControlloConsegnaControl.contrConsCtrlRef.premutoSegnalaUnProblema(idprenotazioneProblematica);
    }
}
