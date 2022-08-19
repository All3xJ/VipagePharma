package com.vipagepharma.corriere.gestioneConsegne.visualizzaConsegne;

import com.vipagepharma.corriere.App;
import com.vipagepharma.corriere.SchermataPrincipale;
import com.vipagepharma.corriere.entity.Ordine;
import com.vipagepharma.corriere.gestioneConsegne.firmaConsegna.FirmaConsegnaControl;
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
import java.util.ResourceBundle;

public class SchermataConsegneOdierne implements Initializable {

    public static String schermataPrecedente;

    @FXML
    private TableColumn<Ordine, String> nomeFarmacia_column;

    @FXML
    private TableColumn<Ordine, Void> firma_column;

    @FXML
    private TableColumn<Ordine, String> idprenotazione_column;

    @FXML
    private TableView<Ordine> ordini_table;

    @Override
    public void initialize(URL url, ResourceBundle resbound){
        this.idprenotazione_column.setCellValueFactory(new PropertyValueFactory<Ordine,String >("idPrenotazione"));
        this.nomeFarmacia_column.setCellValueFactory(new PropertyValueFactory<Ordine,String >("nomeFarmaciaConsegna"));

        this.ordini_table.setItems(VisualizzaConsegneControl.visualConCtrlRef.tvObservableList);
        //this.prenotazioni_table.getColumns().addAll(this.idprenotazione_column, this.dataconsegna_column); NOOOOOOOOOOOO ALTRIMENTI LI RIAGGIUNGEREBBEEEEEE. GIA LI AGGIUNGE DA SOLO FXMLOADER ECC
        this.addButtonToTable("Firma",this.firma_column);
    }

    private void addButtonToTable(String nomeButton,TableColumn colBtn) {
        //TableColumn<Entry, Void> colBtn = new TableColumn("Button Column");

        Callback<TableColumn<Ordine, Void>, TableCell<Ordine, Void>> cellFactory = new Callback<TableColumn<Ordine, Void>, TableCell<Ordine, Void>>() {
            @Override
            public TableCell<Ordine, Void> call(final TableColumn<Ordine, Void> param) {
                final TableCell<Ordine, Void> cell = new TableCell<Ordine, Void>() {

                    private final Button btn = new Button(nomeButton);

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            if (nomeButton.equals("Firma")){
                                Ordine ordine = getTableView().getItems().get(getIndex());
                                try {
                                    this.premeFirma(ordine);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        });
                    }

                    private void premeFirma(Ordine entry) throws IOException {
                        FirmaConsegnaControl firmaconsCtrl = new FirmaConsegnaControl();
                        firmaconsCtrl.start(entry);
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

        this.ordini_table.getColumns().add(colBtn);

    }


    public void premeHome(MouseEvent mouseEvent) throws IOException {
        VisualizzaConsegneControl.visualConCtrlRef.premutoHome("gestioneConsegne/visualizzaConsegne/SchermataConsegneOdierne");
    }

    public void premeIndietro(MouseEvent mouseEvent) throws IOException {
        VisualizzaConsegneControl.visualConCtrlRef.premutoIndietro(schermataPrecedente);
    }

    public void premeLogout(MouseEvent mouseEvent) throws IOException {
        App.setRoot("autenticazione/login/SchermataLogin");
    }
}
