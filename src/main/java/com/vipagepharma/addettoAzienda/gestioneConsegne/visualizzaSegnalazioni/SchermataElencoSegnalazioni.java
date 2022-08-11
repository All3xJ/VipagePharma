package com.vipagepharma.addettoAzienda.gestioneConsegne.visualizzaSegnalazioni;

import com.vipagepharma.addettoAzienda.entity.Consegna;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SchermataElencoSegnalazioni implements Initializable {

    @FXML
    private TableColumn<Consegna, String> dataconsegna_column;

    @FXML
    private TableColumn<Consegna, String> idfarmacia_column;

    @FXML
    private TableColumn<Consegna, String> idordine_column;

    @FXML
    private TableView<Consegna> segnalazioni_table;

    @FXML
    private TableColumn<Consegna, Void> visualizzaerrore_column;


    @Override
    public void initialize(URL url, ResourceBundle resbound){
        this.idfarmacia_column.setCellValueFactory(new PropertyValueFactory<Consegna,String >("idFarmacia"));
        this.idordine_column.setCellValueFactory(new PropertyValueFactory<Consegna,String >("idOrdine"));
        this.dataconsegna_column.setCellValueFactory(new PropertyValueFactory<Consegna,String >("dataConsegna"));

        this.segnalazioni_table.setItems(VisualizzaSegnalazioniControl.visConsCtrlRef.tvObservableList);
        this.addButtonToTable("Visualizza errore",this.visualizzaerrore_column);
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
                            if (nomeButton.equals("Visualizza errore")){
                                Consegna consegna = getTableView().getItems().get(getIndex());
                                try {
                                    this.premeVisualizzaErrore(consegna,event);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        });
                    }

                    private void premeVisualizzaErrore(Consegna entry,ActionEvent event) throws IOException {
                        VisualizzaSegnalazioniControl.visConsCtrlRef.premutoVisualizzaErrore("gestioneConsegne/risoluzioneProblemaConsegna/AvvisoProblemaOrdine",event);
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

        this.segnalazioni_table.getColumns().add(colBtn);

    }

}
