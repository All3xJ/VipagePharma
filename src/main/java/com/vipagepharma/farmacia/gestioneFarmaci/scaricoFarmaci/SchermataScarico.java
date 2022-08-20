package com.vipagepharma.farmacia.gestioneFarmaci.scaricoFarmaci;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.DBMSBoundary;
import com.vipagepharma.farmacia.entity.Farmaco;
import com.vipagepharma.farmacia.entity.FarmacoScarico;
import com.vipagepharma.farmacia.gestionePrenotazioni.visualizzaPrenotazioni.VisualizzaPrenotazioniControl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class SchermataScarico implements Initializable {

    public static String schermataPrecedente;

    @FXML
    private ComboBox<String> tendina;
    @FXML
    private TextField qty;
    @FXML
    private DatePicker data;

    @Override
    public void initialize(URL url, ResourceBundle resbound){
        this.tendina.setItems(ScaricoFarmaciControl.scarFarmCtrl.tvObservableList);
    }



    public void premeHome(MouseEvent mouseEvent) throws IOException {
        ScaricoFarmaciControl.scarFarmCtrl.premutoHome("gestioneFarmaci/scaricoFarmaci/SchermataScarico");
    }

    public void premeIndietro(MouseEvent mouseEvent) throws IOException {
        ScaricoFarmaciControl.scarFarmCtrl.premutoIndietro();
    }

    public void premeLogout(MouseEvent mouseEvent) throws IOException {
        App.setRoot("autenticazione/login/SchermataLogin");
    }

    public void premeScarica(MouseEvent mouseEvent) throws IOException {
        ScaricoFarmaciControl.scarFarmCtrl.premutoScarica(tendina.getValue(),qty.getText(),data.getValue(),mouseEvent);
    }
}
