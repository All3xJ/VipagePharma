package com.vipagepharma.farmacia.gestionePrenotazioni.visualizzaPrenotazioni;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class SchermataElencoPrenotazioni implements Initializable{

	public static String schermataPrecedente;

	@FXML
    private TableColumn<Entry, String> idprenotazione_column;

	@FXML
	private TableColumn<Entry, String> nomefarmaco_column;

	@FXML
    private TableColumn<Entry, String> dataconsegna_column;

	@FXML
    private TableColumn<Entry, Void> annulla_column;

    @FXML
    private TableColumn<Entry, Void> carico_column;

	@FXML
	private TableColumn<Entry, Void> modifica_column;

	@FXML
    private TableView<Entry> prenotazioni_table;

	@Override
	public void initialize(URL url, ResourceBundle resbound){
		this.idprenotazione_column.setCellValueFactory(new PropertyValueFactory<Entry,String >("idPrenotazione"));
		this.nomefarmaco_column.setCellValueFactory(new PropertyValueFactory<Entry,String >("nomeFarmaco"));
		this.dataconsegna_column.setCellValueFactory(new PropertyValueFactory<Entry,String >("dataConsegna"));

		this.prenotazioni_table.setItems(VisualizzaPrenotazioniControl.visualPrenCtrlRef.tvObservableList);
		//this.prenotazioni_table.getColumns().addAll(this.idprenotazione_column, this.dataconsegna_column); NOOOOOOOOOOOO ALTRIMENTI LI RIAGGIUNGEREBBEEEEEE. GIA LI AGGIUNGE DA SOLO FXMLOADER ECC
		this.addButtonToTable("Annulla",this.annulla_column);
		this.addButtonToTable("Carico",this.carico_column);
		this.addButtonToTable("Modifica",this.modifica_column);
	}

	private void addButtonToTable(String nomeButton,TableColumn colBtn) {
        //TableColumn<Entry, Void> colBtn = new TableColumn("Button Column");

        Callback<TableColumn<Entry, Void>, TableCell<Entry, Void>> cellFactory = new Callback<TableColumn<Entry, Void>, TableCell<Entry, Void>>() {
            @Override
            public TableCell<Entry, Void> call(final TableColumn<Entry, Void> param) {
                final TableCell<Entry, Void> cell = new TableCell<Entry, Void>() {

                    private final Button btn = new Button(nomeButton);

                    {
                        btn.setOnAction((ActionEvent event) -> {
							if (nomeButton.equals("Annulla")){
								Entry entry = getTableView().getItems().get(getIndex());
								System.out.println("Annulla-> selectedEntry: " + entry);
								showPopup();
							} else if (nomeButton.equals("Carico")){
								Entry entry = getTableView().getItems().get(getIndex());
								System.out.println("Carico-> selectedEntry: " + entry);
							}else if (nomeButton.equals("Modifica")){
								Entry entry = getTableView().getItems().get(getIndex());
								System.out.println("Carico-> selectedEntry: " + entry);
							}
                        });
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

        this.prenotazioni_table.getColumns().add(colBtn);

    }

	public static void showPopup(){
		Stage newStage = new Stage();
		VBox comp = new VBox();
		TextField nameField = new TextField("Name");
		TextField phoneNumber = new TextField("Phone Number");
		comp.getChildren().add(nameField);
		comp.getChildren().add(phoneNumber);
		
		Scene stageScene = new Scene(comp, 300, 300);
		stageScene.getRoot().setStyle("-fx-font-family: 'Arial'");
		newStage.setScene(stageScene);
		newStage.show();
		}

    public void premeHome(MouseEvent mouseEvent) throws IOException {
		VisualizzaPrenotazioniControl.visualPrenCtrlRef.premutoHome();
    }

	public void premeIndietro(MouseEvent mouseEvent) throws IOException {
		VisualizzaPrenotazioniControl.visualPrenCtrlRef.premutoIndietro(schermataPrecedente);
	}

	public void premeLogout(MouseEvent mouseEvent) {

	}
}