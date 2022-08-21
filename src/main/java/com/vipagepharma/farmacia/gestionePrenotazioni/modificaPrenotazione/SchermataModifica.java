package com.vipagepharma.farmacia.gestionePrenotazioni.modificaPrenotazione;

import com.vipagepharma.corriere.App;
import com.vipagepharma.farmacia.SchermataPrincipale;
import com.vipagepharma.farmacia.gestionePrenotazioni.prenotaFarmaci.PrenotaFarmaciControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
        testo_nome_farmaco.setText(ModificaPrenotazioneControl.modificaPrenotazioneControl.getPrenotazione().getIdFarmaco());
    }

    public void premeInvia(ActionEvent event) throws SQLException, IOException {
        ModificaPrenotazioneControl.modificaPrenotazioneControl.premutoInvia(data_consegna.getValue(),qty.getText(),this.flag_scadenza,event);
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

    public void premeHome(MouseEvent event) throws IOException {
        SchermataPrincipale.schermataPrecedente = "gestionePrenotazioni/modificaPrenotazione/SchermataModifica";
        App.setRoot("SchermataPrincipale");
    }

    public void premeIndetro(MouseEvent event) {

    }

    public void premeLogout(MouseEvent event) throws IOException {
        App.setRoot("autenticazione/login/SchermataPrincipale");
    }
}
