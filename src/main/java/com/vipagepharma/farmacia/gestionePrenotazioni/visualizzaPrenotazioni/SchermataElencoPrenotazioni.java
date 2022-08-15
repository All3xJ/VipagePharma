package com.vipagepharma.farmacia.gestionePrenotazioni.visualizzaPrenotazioni;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.entity.Prenotazione;
import com.vipagepharma.farmacia.autenticazione.logout.LogoutControl;
import com.vipagepharma.farmacia.gestionePrenotazioni.caricoPrenotazione.CaricoPrenotazioneControl;
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

public class SchermataElencoPrenotazioni implements Initializable{

	@FXML
    private TableColumn<Prenotazione, String> idprenotazione_column;

	@FXML
	private TableColumn<Prenotazione, String> nomefarmaco_column;

	@FXML
    private TableColumn<Prenotazione, String> dataconsegna_column;

	@FXML
    private TableColumn<Prenotazione, Void> annulla_column;

    @FXML
    private TableColumn<Prenotazione, Void> carico_column;

	@FXML
	private TableColumn<Prenotazione, Void> modifica_column;

	@FXML
    private TableView<Prenotazione> prenotazioni_table;

	@Override
	public void initialize(URL url, ResourceBundle resbound){
		this.idprenotazione_column.setCellValueFactory(new PropertyValueFactory<>("idPrenotazione"));
		this.nomefarmaco_column.setCellValueFactory(new PropertyValueFactory<>("nomeFarmaco"));
		this.dataconsegna_column.setCellValueFactory(new PropertyValueFactory<>("dataConsegna"));

		this.prenotazioni_table.setItems(VisualizzaPrenotazioniControl.visualPrenCtrlRef.tvObservableList);
		//this.prenotazioni_table.getColumns().addAll(this.idprenotazione_column, this.dataconsegna_column); NOOOOOOOOOOOO ALTRIMENTI LI RIAGGIUNGEREBBEEEEEE. GIA LI AGGIUNGE DA SOLO FXMLOADER ECC
		this.addButtonToTable("Annulla",this.annulla_column);
		this.addButtonToTable("Carico",this.carico_column);
		this.addButtonToTable("Modifica",this.modifica_column);
	}

	private void addButtonToTable(String nomeButton,TableColumn colBtn) {
        //TableColumn<Entry, Void> colBtn = new TableColumn("Button Column");

        Callback<TableColumn<Prenotazione, Void>, TableCell<Prenotazione, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Prenotazione, Void> call(final TableColumn<Prenotazione, Void> param) {
                final TableCell<Prenotazione, Void> cell = new TableCell<>() {

                    private final Button btn = new Button(nomeButton);

                    {
                        btn.setOnAction((ActionEvent event) -> {
							if (nomeButton.equals("Annulla")){
								Prenotazione prenotazione = getTableView().getItems().get(getIndex());
								try {
									this.premeAnnulla(prenotazione);
								} catch (IOException e) {
									throw new RuntimeException(e);
								}
								//System.out.println("Annulla-> selectedEntry: " + entry);
							} else if (nomeButton.equals("Carico")){
								Prenotazione prenotazione = getTableView().getItems().get(getIndex());
								try {
									this.premeCarico(prenotazione.getIdPrenotazione());
								} catch (IOException e) {
									throw new RuntimeException(e);
								}
								//System.out.println("Carico-> selectedEntry: " + entry);
							}else if (nomeButton.equals("Modifica")){
								Prenotazione prenotazione = getTableView().getItems().get(getIndex());
								this.premeModifica(prenotazione);
								//System.out.println("Carico-> selectedEntry: " + entry);
							}
                        });
                    }

					private void premeAnnulla(Prenotazione entry) throws IOException {
						VisualizzaPrenotazioniControl.visualPrenCtrlRef.premutoAnnulla("gestionePrenotazioni/visualizzaPrenotazioni/AvvisoAnnullaPrenotazione");
					}

					private void premeCarico(String id_prenotazione) throws IOException {
						CaricoPrenotazioneControl carPreCtrl = new CaricoPrenotazioneControl(id_prenotazione);
						carPreCtrl.start();
					}

					private void premeModifica(Prenotazione entry) {

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

    public void premeHome(MouseEvent mouseEvent) throws IOException {
		VisualizzaPrenotazioniControl.visualPrenCtrlRef.premutoHome("gestionePrenotazioni/visualizzaPrenotazioni/SchermataElencoPrenotazioni");
    }

	public void premeIndietro(MouseEvent mouseEvent) throws IOException {
		VisualizzaPrenotazioniControl.visualPrenCtrlRef.premutoIndietro();
	}

	public void premeLogout(MouseEvent mouseEvent) throws IOException {
		LogoutControl.start();
	}
}