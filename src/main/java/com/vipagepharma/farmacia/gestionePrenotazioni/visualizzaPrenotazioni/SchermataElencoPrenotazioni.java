package com.vipagepharma.farmacia.gestionePrenotazioni.visualizzaPrenotazioni;

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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class SchermataElencoPrenotazioni implements Initializable{

	@FXML
	private TextField username;	// il nome della variabile deve essere lo stesso dell'FXXXXXXID di Scene Builder

	@FXML
	private TextField password;

	@FXML
	private Button login;

	@FXML
    private TableColumn<Entry, String> id_column;

	@FXML
    private TableColumn<Entry, String> dataconsegna_column;

	@FXML
    private TableColumn<Entry, Void> annulla_column;

    @FXML
    private TableColumn<Entry, Void> carico_column;

	@FXML
    private TableView<Entry> prenotazioni_table;

	private ObservableList<Entry> tvObservableList = FXCollections.observableArrayList(new Entry("1", "data1"),
																						new Entry("2", "data2"), 
																						new Entry("3", "data3"), 
																						new Entry("4", "data4"),
																						new Entry("5", "data5"));


	@Override
	public void initialize(URL url, ResourceBundle resbound){
		this.id_column.setCellValueFactory(new PropertyValueFactory<Entry,String >("id"));
		this.dataconsegna_column.setCellValueFactory(new PropertyValueFactory<Entry,String >("data"));

		this.prenotazioni_table.setItems(this.tvObservableList);
		//this.prenotazioni_table.getColumns().addAll(this.id_column, this.dataconsegna_column); NOOOOOOOOOOOO ALTRIMENTI LI RIAGGIUNGEREBBEEEEEE. GIA LI AGGIUNGE DA SOLO FXMLOADER ECC
		this.addButtonToTable("Annulla",this.annulla_column);
		this.addButtonToTable("Carico",this.carico_column);

		System.out.println("ciavj sv");
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
}