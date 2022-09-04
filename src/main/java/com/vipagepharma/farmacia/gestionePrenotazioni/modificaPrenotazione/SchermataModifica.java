package com.vipagepharma.farmacia.gestionePrenotazioni.modificaPrenotazione;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SchermataModifica implements Initializable {

    @FXML
    private DatePicker data_consegna;
    @FXML
    private TextField qty;
    @FXML
    private Text testo_nome_farmaco;
    private int flag_scadenza = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.qty.setText(String.valueOf(ModificaPrenotazioneControl.modificaPrenotazioneControl.getPrenotazione().getQty()));
        this.data_consegna.setValue(LocalDate.parse(ModificaPrenotazioneControl.modificaPrenotazioneControl.getPrenotazione().getDataConsegna()));
        this.testo_nome_farmaco.setText(ModificaPrenotazioneControl.modificaPrenotazioneControl.getPrenotazione().getNomeFarmaco());
        this.data_consegna.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0 );
            }
        });
    }

    public void premeInvia(ActionEvent event) throws SQLException, IOException {
        ModificaPrenotazioneControl.modificaPrenotazioneControl.premutoInvia(data_consegna.getValue(),qty.getText(),this.flag_scadenza,event);
    }

    @FXML
    public void premeRadioButton(MouseEvent event){
        if(this.flag_scadenza == 0){
            this.flag_scadenza = 1;
        }
        else{
            this.flag_scadenza = 0;
        }
    }
    @FXML
    public void premeHome(MouseEvent event) throws IOException {
        ModificaPrenotazioneControl.modificaPrenotazioneControl.premutoHome();
    }
    @FXML
    public void premeIndetro(MouseEvent event) throws IOException {
        ModificaPrenotazioneControl.modificaPrenotazioneControl.premutoIndietro("gestionePrenotazioni/visualizzaPrenotazioni/SchermataElencoPrenotazioni");
    }
    @FXML
    public void premeLogout(MouseEvent event) throws IOException {
        ModificaPrenotazioneControl.modificaPrenotazioneControl.premutoLogout();
    }
}
