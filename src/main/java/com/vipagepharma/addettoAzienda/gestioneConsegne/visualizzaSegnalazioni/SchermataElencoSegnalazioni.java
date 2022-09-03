package com.vipagepharma.addettoAzienda.gestioneConsegne.visualizzaSegnalazioni;

import com.vipagepharma.addettoAzienda.entity.Consegna;
import com.vipagepharma.addettoAzienda.gestioneConsegne.risoluzioneProblemaConsegna.RisoluzioneProblemaConsegnaControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SchermataElencoSegnalazioni implements Initializable {

    @FXML
    private TableColumn<Consegna, String> dataconsegna_column;

    @FXML
    private TableColumn<Consegna, String> nomefarmacia_column;

    @FXML
    private TableColumn<Consegna, String> idordine_column;

    @FXML
    private TableView<Consegna> segnalazioni_table;

    @FXML
    private TableColumn<Consegna, Void> visualizzaerrore_column;



    @Override
    public void initialize(URL url, ResourceBundle resbound){
        this.nomefarmacia_column.setCellValueFactory(new PropertyValueFactory<Consegna,String >("nomeFarmacia"));
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
                        btn.setStyle("-fx-background-radius: 5; -fx-border-radius: 5; -fx-background-color: cff3f2; -fx-border-color: b9b9b9;");
                    }

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            if (nomeButton.equals("Visualizza errore")){
                                Consegna consegna = getTableView().getItems().get(getIndex());
                                try {
                                    this.premeVisualizzaErrore(consegna);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        });
                    }

                    private void premeVisualizzaErrore(Consegna entry) throws IOException {
                        RisoluzioneProblemaConsegnaControl risProbConCtrl = new RisoluzioneProblemaConsegnaControl(entry);
                        risProbConCtrl.start();
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

    public void premeHome(MouseEvent mouseEvent) throws IOException {
        VisualizzaSegnalazioniControl.visConsCtrlRef.premutoHome();
    }
    @FXML
    public void premeIndietro(MouseEvent mouseEvent) throws IOException {
        VisualizzaSegnalazioniControl.visConsCtrlRef.premutoIndietro("SchermataPrincipale");
    }
    @FXML
    public void premeLogout(MouseEvent mouseEvent) throws IOException {
        VisualizzaSegnalazioniControl.visConsCtrlRef.premutoLogout();
    }

}
