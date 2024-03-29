package com.vipagepharma.farmacia.gestionePrenotazioni.prenotaFarmaci;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.SchermataPrincipale;
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

public class SchermataPrenotazione implements Initializable {
    @FXML
    private DatePicker data_consegna;
    @FXML
    private TextField qty;
    @FXML
    private Text testo_nome_farmaco;


    private int flag_scadenza = 1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.testo_nome_farmaco.setText(PrenotaFarmaciControl.controlRef.getFarmaco());
        this.data_consegna.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0 );
            }
        });
    }



    public void premeInvia(ActionEvent event) throws SQLException, IOException {
        PrenotaFarmaciControl.controlRef.premutoInvia(data_consegna.getValue(),qty.getText(),this.flag_scadenza);
    }

    @FXML
    public void premeCheckBox(MouseEvent event){
        if(this.flag_scadenza == 0){
            flag_scadenza = 1;
        }
        else{
            this.flag_scadenza = 0;
        }
    }
    @FXML
    public void premeLogout(MouseEvent mouseEvent) throws IOException {
        PrenotaFarmaciControl.controlRef.premutoLogout();
    }
    @FXML
    public void premeHome(MouseEvent event) throws IOException {
        PrenotaFarmaciControl.controlRef.premutoHome();
    }
    @FXML
    public void premeIndetro(MouseEvent event) throws IOException {
        PrenotaFarmaciControl.controlRef.premutoIndietro("gestionePrenotazioni/prenotaFarmaci/SchermataListaFarmaci");
    }
}
