package com.vipagepharma.addettoAzienda.gestioneConsegne.visualizzaStoricoConsegne;

import com.vipagepharma.addettoAzienda.DBMSBoundary;
import com.vipagepharma.addettoAzienda.SchermataPrincipale;
import com.vipagepharma.addettoAzienda.entity.Consegna;
import com.vipagepharma.addettoAzienda.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SchermataStoricoConsegne implements Initializable {

    @FXML
    private TableColumn<Consegna, String> idordine_column;

    @FXML
    private TableColumn<Consegna, String> idfarmacia_column;

    @FXML
    private TableColumn<Consegna, String> dataconsegna_column;

    @FXML
    private TableView<Consegna> storico_table;

    @FXML
    private TableColumn<Consegna, Void> visualizzaricevuta_column;


    @Override
    public void initialize(URL url, ResourceBundle resbound){
        this.idfarmacia_column.setCellValueFactory(new PropertyValueFactory<Consegna,String >("idFarmacia"));
        this.idordine_column.setCellValueFactory(new PropertyValueFactory<Consegna,String >("idOrdine"));
        this.dataconsegna_column.setCellValueFactory(new PropertyValueFactory<Consegna,String >("dataConsegna"));

        this.storico_table.setItems(VisualizzaStoricoConsegneControl.visStoConsCtrlRef.tvObservableList);
        this.addButtonToTable("Visualizza ricevuta",this.visualizzaricevuta_column);
    }


    private void addButtonToTable(String nomeButton,TableColumn colBtn) {
        //TableColumn<Entry, Void> colBtn = new TableColumn("Button Column");

        Callback<TableColumn<Consegna, Void>, TableCell<Consegna, Void>> cellFactory = new Callback<TableColumn<Consegna, Void>, TableCell<Consegna, Void>>() {
            @Override
            public TableCell<Consegna, Void> call(final TableColumn<Consegna, Void> param) {
                final TableCell<Consegna, Void> cell = new TableCell<Consegna, Void>() {

                    private final Button btn = new Button(nomeButton);

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            if (nomeButton.equals("Visualizza ricevuta")){
                                Consegna consegna = getTableView().getItems().get(getIndex());
                                try {
                                    this.premeVisualizzaRicevuta(consegna, event);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        });
                    }

                    private void premeVisualizzaRicevuta(Consegna consegna, ActionEvent event) throws IOException {
                        VisualizzaStoricoConsegneControl.visStoConsCtrlRef.premutoVisualizzaRicevuta(consegna);

                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        this.storico_table.getColumns().add(colBtn);

    }


    public void premeHome(MouseEvent mouseEvent) throws IOException {
        VisualizzaStoricoConsegneControl.visStoConsCtrlRef.premutoHome("gestioneConsegne/visualizzaStoricoConsegne/SchermataStoricoConsegne.java");
    }

    public void premeIndietro(MouseEvent mouseEvent) throws IOException {
        VisualizzaStoricoConsegneControl.visStoConsCtrlRef.premutoIndietro();
    }

    public void premeLogout(MouseEvent mouseEvent) throws IOException {
        VisualizzaStoricoConsegneControl.contatorePagineConsegne=0;
        App.setRoot("autenticazione/login/SchermataLogin");
    }

    public void premeMostraAltro(MouseEvent mouseEvent) throws SQLException, IOException {
        VisualizzaStoricoConsegneControl.visStoConsCtrlRef.premutoMostraAltro();
    }
}
