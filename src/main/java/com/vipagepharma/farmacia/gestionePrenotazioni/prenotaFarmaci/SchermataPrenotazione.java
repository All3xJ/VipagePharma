package com.vipagepharma.farmacia.gestionePrenotazioni.prenotaFarmaci;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.SchermataPrincipale;
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

public class SchermataPrenotazione implements Initializable {
    @FXML
    private DatePicker data_consegna;
    @FXML
    private TextField qty;
    @FXML
    private Text t;


    private int flag_scadenza = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        t.setText(PrenotaFarmaciControl.getControl().getFarmaco());
    }

    @FXML
    public void premeLogout(MouseEvent mouseEvent) throws IOException {
        App.setRoot("autenticazione/login/SchermataLogin");
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

    public void premeHome(MouseEvent event) throws IOException {
        SchermataPrincipale.schermataPrecedente = "gestionePrenotazioni/prenotaFarmaci/SchermataPrenotazione";
        App.setRoot("SchermataPrincipale");
    }

    public void premeIndetro(MouseEvent event) {

    }


}
