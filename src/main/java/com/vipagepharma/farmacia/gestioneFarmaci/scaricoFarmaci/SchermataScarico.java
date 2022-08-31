package com.vipagepharma.farmacia.gestioneFarmaci.scaricoFarmaci;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.DBMSBoundary;
import com.vipagepharma.farmacia.SchermataPrincipale;
import com.vipagepharma.farmacia.entity.Farmaco;
import com.vipagepharma.farmacia.entity.Farmaco;
import com.vipagepharma.farmacia.entity.Prenotazione;
import com.vipagepharma.farmacia.gestionePrenotazioni.visualizzaPrenotazioni.VisualizzaPrenotazioniControl;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.*;

public class SchermataScarico implements Initializable {

    @FXML
    private ComboBox<String> tendinaFarmaci;

    @FXML
    private ComboBox<String> tendinaLotti;

    @FXML
    private TextField qty;

    @Override
    public void initialize(URL url, ResourceBundle resbound){
        this.tendinaFarmaci.setItems(ScaricoFarmaciControl.scarFarmCtrl.tvObservableList);
        this.tendinaLotti.setDisable(true);

        this.tendinaFarmaci.setOnMouseClicked((MouseEvent event) -> {
            this.tendinaLotti.valueProperty().set(null);
        });

        this.tendinaFarmaci.setOnAction((ActionEvent event) -> {
            this.tendinaLotti.setDisable(false);
            ScaricoFarmaciControl.scarFarmCtrl.riempiObservableList2(this.tendinaFarmaci.getValue());
            this.tendinaLotti.setItems(ScaricoFarmaciControl.scarFarmCtrl.tvObservableList2);
        });
    }



    @FXML
    public void premeHome(MouseEvent mouseEvent) throws IOException {
        ScaricoFarmaciControl.scarFarmCtrl.premutoHome();
    }
    @FXML
    public void premeIndietro(MouseEvent mouseEvent) throws IOException {
        ScaricoFarmaciControl.scarFarmCtrl.premutoIndietro("SchermataPrincipale");
    }
    @FXML
    public void premeLogout(MouseEvent mouseEvent) throws IOException {
        ScaricoFarmaciControl.scarFarmCtrl.premutoLogout();
    }

    public void premeScarica(MouseEvent mouseEvent) throws IOException {
        ScaricoFarmaciControl.scarFarmCtrl.premutoScarica(tendinaFarmaci.getValue(),tendinaLotti.getValue(),qty.getText(),mouseEvent);
    }
}
