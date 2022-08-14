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



    /*BUGGGGGGG
      se viene fatta una ricerca e poi si va indietro e se ne fa un altra le prima ricerca non scompare */

    @Override
    public void initialize(URL url, ResourceBundle resbound){
        this.idFarmaco_column.setCellValueFactory(new PropertyValueFactory<Farmaco,String >("idFarmaco"));
        this.nomeFarmaco_column.setCellValueFactory(new PropertyValueFactory<Farmaco,String >("nomeFarmaco"));
        this.principioAttivo_column.setCellValueFactory(new PropertyValueFactory<Farmaco,String >("principioAttivo"));

        this.farmaci_table.setItems(RicercaFarmacoControl.getControl().tvObservableList);
        //this.farmaci_table.getColumns().addAll(this.idFarmaco_column, this.principioattivo_column); NOOOOOOOOOOOO ALTRIMENTI LI RIAGGIUNGEREBBEEEEEE. GIA LI AGGIUNGE DA SOLO FXMLOADER ECC
        this.addButtonToTable("Prenota",this.prenota_column);
    }

    private void addButtonToTable(String nomeButton,TableColumn colBtn) {
        //TableColumn<Entry, Void> colBtn = new TableColumn("Button Column");

        Callback<TableColumn<Farmaco, Void>, TableCell<Farmaco, Void>> cellFactory = new Callback<TableColumn<Farmaco, Void>, TableCell<Farmaco, Void>>() {
            @Override
            public TableCell<Farmaco, Void> call(final TableColumn<Farmaco, Void> param) {
                final TableCell<Farmaco, Void> cell = new TableCell<Farmaco, Void>() {

                    private final Button btn = new Button(nomeButton);

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
        App.setRoot("autenticazione/login/SchermataLogin");
    }


    public void premeHome(MouseEvent event) throws IOException {
        SchermataPrincipale.schermataPrecedente = "gestionePrenotazioni/prenotaFarmaci/SchermataListaFarmaci";
        App.setRoot("SchermataPrincipale");
    }

    public void premeIndietro(MouseEvent event) throws IOException {
        App.setRoot("gestionePrenotazioni/ricercaFarmaco/SchermataRicercaFarmaco");
    }
}
