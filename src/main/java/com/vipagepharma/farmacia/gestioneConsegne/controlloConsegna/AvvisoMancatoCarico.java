package com.vipagepharma.farmacia.gestioneConsegne.controlloConsegna;

import com.vipagepharma.farmacia.entity.Prenotazione;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AvvisoMancatoCarico implements Initializable {

    public static Prenotazione prenotazioneProblematica;

    @FXML
    private Text testo_mancato_carico;

    @Override
    public void initialize(URL url, ResourceBundle resbound) {
        this.testo_mancato_carico.setText("La consegna n. "+prenotazioneProblematica.getIdPrenotazione()+ " prevista per oggi non è ancora stata caricata");
    }

    public void premeSegnalaUnProblema(MouseEvent mouseEvent) throws IOException {
        ControlloConsegnaControl.contrConsCtrlRef.premutoSegnalaUnProblema(prenotazioneProblematica.getIdPrenotazione());
    }

    public void premeConfermaCarico(MouseEvent mouseEvent) throws IOException {
        ControlloConsegnaControl.contrConsCtrlRef.premutoConfermaCarico(prenotazioneProblematica);
    }
}
