package com.vipagepharma.farmacia.gestionePrenotazioni.prenotaFarmaci;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AvvisoPrenotazioneDisponibile implements Initializable {

    @FXML
    private Text testo_prenotazione_disponibile;
    private String data;
    private String qty;
    private String farmaco;

    private void init(){
        this.data = PrenotaFarmaciControl.controlRef.getData();
        this.qty  = PrenotaFarmaciControl.controlRef.getQty();
        this.farmaco = PrenotaFarmaciControl.controlRef.getFarmaco();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.init();
        testo_prenotazione_disponibile.setText("La tua prenotazione di " + this.qty + " pezzi di " + this.farmaco + " per il " + this.data + " è disponibile");
    }
    @FXML
    public void premeConferma(MouseEvent mouseEvent) throws IOException {
        PrenotaFarmaciControl.controlRef.premutoConferma(mouseEvent);
    }


}
