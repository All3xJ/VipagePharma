package com.vipagepharma.farmacia.gestionePrenotazioni.prenotaFarmaci;

import com.vipagepharma.corriere.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AvvisoPrenotazioneDisponibile implements Initializable {

    @FXML
    private Text t;
    private String data;
    private String qty;
    private String farmaco;

    private void init(){
        this.data = PrenotaFarmaciControl.getControl().getData();
        this.qty  = PrenotaFarmaciControl.getControl().getQty();
        this.farmaco = PrenotaFarmaciControl.getControl().getFarmaco();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.init();
        t.setText("La tua prenotazione di " + this.qty + " pezzi di " + this.farmaco + " per il " + this.data + " è disponibile");
    }
    @FXML
    public void premeConferma(MouseEvent mouseEvent) throws IOException {
        PrenotaFarmaciControl.getControl().premutoConferma(mouseEvent);
    }


}
