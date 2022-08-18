package com.vipagepharma.corriere.gestioneConsegne.firmaConsegna;

import com.vipagepharma.corriere.entity.Ordine;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SchermataRiepilogoOrdine implements Initializable {

    @FXML
    private TableColumn<Ordine, String> dataconsegna_column;

    @FXML
    private TableColumn<Ordine, String> idconsegna_column;

    @FXML
    private TableColumn<Ordine, String> idfarmacia_column;

    @FXML
    private TableColumn<Ordine, String> qty_column;

    @FXML
    private TableView<Ordine> riepilogoordine_table;

    public static Ordine ordine;

    @Override
    public void initialize(URL url, ResourceBundle resbound) {
        this.idconsegna_column.setCellValueFactory(new PropertyValueFactory<Ordine,String >("idPrenotazione"));
        this.idfarmacia_column.setCellValueFactory(new PropertyValueFactory<Ordine,String >("idFarmacia"));
        this.dataconsegna_column.setCellValueFactory(new PropertyValueFactory<Ordine,String >("dataConsegna"));
        this.qty_column.setCellValueFactory(new PropertyValueFactory<Ordine,String >("qty"));
        this.riepilogoordine_table.getItems().add(ordine);
    }

    public void premeHome(MouseEvent mouseEvent) {
    }

    public void premeIndietro(MouseEvent mouseEvent) {
    }

    public void premeLogout(MouseEvent mouseEvent) {
    }

    public void premeFirma(MouseEvent mouseEvent) {
    }
}
