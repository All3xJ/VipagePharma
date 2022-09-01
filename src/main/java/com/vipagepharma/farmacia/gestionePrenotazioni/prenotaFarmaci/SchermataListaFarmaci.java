package com.vipagepharma.farmacia.gestionePrenotazioni.prenotaFarmaci;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.SchermataPrincipale;
import com.vipagepharma.farmacia.entity.Farmaco;
import com.vipagepharma.farmacia.gestionePrenotazioni.ricercaFarmaco.RicercaFarmacoControl;
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

public class SchermataListaFarmaci implements Initializable{


    @FXML
    private TableColumn<Farmaco, String> idFarmaco_column;

    @FXML
    private TableColumn<Farmaco, String> nomeFarmaco_column;

    @FXML
    private TableColumn<Farmaco, String> principioAttivo_column;

    @FXML
    private TableColumn<Farmaco, Void> prenota_column;

    @FXML
    private TableView<Farmaco> farmaci_table;



    @Override
    public void initialize(URL url, ResourceBundle resbound){
/*
        this.idFarmaco_column.setCellValueFactory(new PropertyValueFactory<>("idFarmaco"));
*/
        this.nomeFarmaco_column.setCellValueFactory(new PropertyValueFactory<>("nomeFarmaco"));
        this.principioAttivo_column.setCellValueFactory(new PropertyValueFactory<>("principioAttivo"));

        this.farmaci_table.setItems(RicercaFarmacoControl.getControl().tvObservableList);
        //this.farmaci_table.getColumns().addAll(this.idFarmaco_column, this.principioattivo_column); NOOOOOOOOOOOO ALTRIMENTI LI RIAGGIUNGEREBBEEEEEE. GIA LI AGGIUNGE DA SOLO FXMLOADER ECC
        this.addButtonToTable("Prenota",this.prenota_column);
    }

    private void addButtonToTable(String nomeButton,TableColumn colBtn) {
        //TableColumn<Entry, Void> colBtn = new TableColumn("Button Column");

        Callback<TableColumn<Farmaco, Void>, TableCell<Farmaco, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Farmaco, Void> call(final TableColumn<Farmaco, Void> param) {
                final TableCell<Farmaco, Void> cell = new TableCell<>() {

                    private final Button btn = new Button(nomeButton);
                    {
                        btn.setStyle("-fx-background-radius: 5; -fx-border-radius: 5; -fx-background-color: cff3f2; -fx-border-color: b9b9b9;");
                    }

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Farmaco Farmaco = getTableView().getItems().get(getIndex());
                            try {
                                this.premePrenota(Farmaco.getIdFarmaco(),Farmaco.getNomeFarmaco());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    }

                    private void premePrenota(String id_farmaco,String nome_farmaco) throws IOException {
                        PrenotaFarmaciControl prFarCtrl = new PrenotaFarmaciControl();
                        prFarCtrl.start(id_farmaco,nome_farmaco);
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

        this.farmaci_table.getColumns().add(colBtn);

    }


    @FXML
    public void premeLogout(MouseEvent mouseEvent) throws IOException {
        RicercaFarmacoControl.getControl().premutoLogout();
    }

    @FXML
    public void premeHome(MouseEvent event) throws IOException {
        RicercaFarmacoControl.getControl().premutoHome();
    }
    @FXML
    public void premeIndietro(MouseEvent event) throws IOException {
        RicercaFarmacoControl.getControl().premutoIndietro("gestionePrenotazioni/ricercaFarmaco/SchermataRicercaFarmaco");
    }
}
