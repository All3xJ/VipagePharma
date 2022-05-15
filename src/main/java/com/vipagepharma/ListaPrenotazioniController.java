package com.vipagepharma;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListaPrenotazioniController implements Initializable{

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
    private TableColumn<?, ?> modifica_column;

	@FXML
    private TableColumn<?, ?> annulla_column;

    @FXML
    private TableColumn<?, ?> carico_column;

	@FXML
    private TableView<Entry> prenotazioni_table;

	private ObservableList<Entry> tvObservableList = FXCollections.observableArrayList(new Entry("1", "app1"),
																						new Entry("2", "app2"), 
																						new Entry("3", "app3"), 
																						new Entry("4", "app4"),
																						new Entry("5", "app5"));


	@Override
	public void initialize(URL url, ResourceBundle resbound){	// FINALMENTE HO SCOPERTO A CHE SERVE. SERVE PER ESSERE DIOCANEMENTE EVOCATA QUANDO FACCIO setRoot. QUESTO METODO VERRÀ EVOCATO. È UNA SORTA DI MAIN DEL CONTROLLERRR
		this.id_column.setCellValueFactory(new PropertyValueFactory<Entry,String >("id"));
		this.dataconsegna_column.setCellValueFactory(new PropertyValueFactory<Entry,String >("data"));

		this.prenotazioni_table.setItems(this.tvObservableList);

		System.out.println("ciavj sv");
	}
}
