package com.vipagepharma.farmacia.gestionePrenotazioni.prenotaFarmaci;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AvvisoMancataDisponibilita implements Initializable {

    @FXML
    private Text testo_mancata_disponibilita;
    @FXML
    private RadioButton opzione1;

    @FXML
    private RadioButton opzione2;

    private String data;
    private String qty;
    private String farmaco;
    private String newData;
    private int opzione = 1;
    private String qtyMancante;


    private void init(){
        this.data = PrenotaFarmaciControl.getControl().getData();
        this.qty  = PrenotaFarmaciControl.getControl().getQty();
        this.newData = PrenotaFarmaciControl.getControl().getNewData();
        this.qtyMancante  = PrenotaFarmaciControl.getControl().getQtyMancante();
        this.farmaco = PrenotaFarmaciControl.getControl().getFarmaco();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.init();
        opzione1.setSelected(true);
        testo_mancata_disponibilita.setText("La quantità inserita di " + this.farmaco +  " non è totalmente disponibile per il " + this.data);
        opzione1.setText("Accetto solo " + this.qty + " pezzi per il " + this.data);
        opzione2.setText("Accetto " + this.qty + " pezzi per il " + this.data + " e "+ this.qtyMancante + " pezzi per il " + this.newData);
    }

    @FXML
    void selezionaOpzione1(MouseEvent mouseEvent){
        this.opzione2.setSelected(false);
        this.opzione = 1;
    }

    @FXML
    void selezionaOpzione2(MouseEvent mouseEvent){
        this.opzione1.setSelected(false);
        this.opzione = 2;
    }
    @FXML
    public void premeConferma(MouseEvent event) throws IOException {
        PrenotaFarmaciControl.getControl().premutoConferma(this.opzione,event);
    }
}
