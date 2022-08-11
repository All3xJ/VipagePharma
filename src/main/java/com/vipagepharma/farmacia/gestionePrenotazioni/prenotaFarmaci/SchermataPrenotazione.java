package com.vipagepharma.farmacia.gestionePrenotazioni.prenotaFarmaci;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;

public class SchermataPrenotazione {
    @FXML
    private DatePicker data_consegna;
    @FXML
    private TextField qty;

    private int flag_scadenza = 0;

    @FXML
    public void premeLogout(MouseEvent mouseEvent) {
    }

    public void premeInvia(ActionEvent event) throws SQLException, IOException {
        PrenotaFarmaciControl.getControl().premutoInvia(data_consegna.getValue(),qty.getText(),this.flag_scadenza,event);
    }

    @FXML
    public void premeRadioButton(MouseEvent event){
        if(this.flag_scadenza == 0){
            flag_scadenza = 1;
        }
        else{
            this.flag_scadenza = 0;
        }
    }
}
