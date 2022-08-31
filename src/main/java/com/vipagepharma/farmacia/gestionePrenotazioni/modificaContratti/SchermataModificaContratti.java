package com.vipagepharma.farmacia.gestionePrenotazioni.modificaContratti;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.entity.Contratto;
import com.vipagepharma.farmacia.entity.Contratto;
import com.vipagepharma.farmacia.gestionePrenotazioni.visualizzaPrenotazioni.VisualizzaPrenotazioniControl;
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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SchermataModificaContratti implements Initializable {

    @FXML
    private TableView<Contratto> contratti_table;

    @FXML
    private TableColumn<Contratto, String> nomefarmaco_column;

    @FXML
    private TableColumn<Contratto, String> qty_column;

    @FXML
    private TableColumn<Contratto, Void> modifica_column;

    @Override
    public void initialize(URL url, ResourceBundle resbound) {
        this.nomefarmaco_column.setCellValueFactory(new PropertyValueFactory<>("nomeFarmaco"));
        this.qty_column.setCellValueFactory(new PropertyValueFactory<>("qtySettimanale"));

        this.contratti_table.setItems(ModificaContrattiControl.modifContraCtrl.tvObservableList);
        this.addButtonToTable("Modifica",this.modifica_column);
    }

    private void addButtonToTable(String nomeButton, TableColumn colBtn) {
        //TableColumn<Entry, Void> colBtn = new TableColumn("Button Column");

        Callback<TableColumn<Contratto, Void>, TableCell<Contratto, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Contratto, Void> call(final TableColumn<Contratto, Void> param) {
                final TableCell<Contratto, Void> cell = new TableCell<>() {

                    private final Button btn = new Button(nomeButton);

                    {
                        btn.setStyle("-fx-background-radius: 5; -fx-border-radius: 5; -fx-background-color: cff3f2; -fx-border-color: b9b9b9;");


                        //NON FUNZIONA GetTableView


                    }
                    {

                        btn.setOnAction((ActionEvent event) -> {
                            if (nomeButton.equals("Modifica")){
                                Contratto Contratto = getTableView().getItems().get(getIndex());
                                try {
                                    this.premeModifica(Contratto);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        });
                    }

                    private void premeModifica(Contratto entry) throws IOException {
                        ModificaContrattiControl.modifContraCtrl.premutoModifica(entry);
                        App.setRoot("gestionePrenotazioni/modificaContratti/SchermataRiepilogoContratto");
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

        this.contratti_table.getColumns().add(colBtn);

    }

    @FXML
    public void premeLogout(MouseEvent mouseEvent) throws IOException {
        ModificaContrattiControl.modifContraCtrl.premutoLogout();
    }
    @FXML
    public void premeIndietro(MouseEvent mouseEvent) throws IOException {
        ModificaContrattiControl.modifContraCtrl.premutoIndietro("SchermataPrincipale");
    }
    @FXML
    public void premeHome(MouseEvent mouseEvent) throws IOException {
        ModificaContrattiControl.modifContraCtrl.premutoHome();
    }
}
