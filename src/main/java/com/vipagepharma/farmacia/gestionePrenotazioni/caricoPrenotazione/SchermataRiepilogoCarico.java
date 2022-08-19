package com.vipagepharma.farmacia.gestionePrenotazioni.caricoPrenotazione;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.entity.Lotto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Callback;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class SchermataRiepilogoCarico implements Initializable {

    @FXML
    private Text testo_nome_farmaco;
    @FXML
    private Text testo_qty_prevista;
    @FXML
    private Text testo_qty_consegnata;

    public static int qty_prevista;
    private int qty_consegnata = 0;



    @FXML
    private TableColumn<Lotto, String> id_lotti_column;
    @FXML
    private TableColumn<Lotto, String> rdButton_column;
    @FXML
    private TableView<Lotto> lotti_table;

    private static LinkedList<String> lotti_selezionati;
    private static LinkedList<String> qty_selezionati;
    private static LinkedList<String> date_scadenza_selezionate;
    @Override
    public void initialize(URL url, ResourceBundle resbound){
        lotti_selezionati = new LinkedList<>();
        qty_selezionati = new LinkedList<>();
        date_scadenza_selezionate = new LinkedList<>();
        testo_qty_prevista.setText("Quantità prevista: "+ qty_prevista);
        testo_qty_consegnata.setText("Quantità consegnata: "+ this.qty_consegnata);
        testo_nome_farmaco.setText(CaricoPrenotazioneControl.nome_farmaco);
        this.id_lotti_column.setCellValueFactory(new PropertyValueFactory<>("lotto"));
        this.lotti_table.setItems(CaricoPrenotazioneControl.carPrenCtrl.tvObservableList);
        this.addRadioButtonToTable(this.rdButton_column);
    }

    private void addRadioButtonToTable(TableColumn colRadioBtn) {
        //TableColumn<Entry, Void> colRadioBtn = new TableColumn("RadioButton Column");

        Callback<TableColumn<Lotto, Void>, TableCell<Lotto, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Lotto, Void> call(final TableColumn<Lotto, Void> param) {
                final TableCell<Lotto, Void> cell = new TableCell<>() {

                    private final RadioButton RadioBtn = new RadioButton();
                    private boolean flag = false;

                    {
                        RadioBtn.setOnMouseClicked((MouseEvent event) -> {
                            Lotto lotto = getTableView().getItems().get(getIndex());
                            try {
                                this.selezionaRadioButton(lotto);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    }

                    private void selezionaRadioButton(Lotto lotto) throws IOException{
                        if(!flag) {
                            lotti_selezionati.add(lotto.getLotto());
                            qty_selezionati.add(lotto.getQty());
                            date_scadenza_selezionate.add(lotto.getDataScadenza());
                            qty_consegnata = qty_consegnata + Integer.parseInt(lotto.getQty());
                            flag = true;
                        }
                        else{
                            lotti_selezionati.remove(lotto.getLotto());
                            qty_selezionati.remove(lotto.getQty());
                            date_scadenza_selezionate.remove(lotto.getDataScadenza());
                            qty_consegnata = qty_consegnata - Integer.parseInt(lotto.getQty());
                            flag = false;
                        }
                        testo_qty_consegnata.setText("Quantità consegnata: "+ qty_consegnata);
                        System.out.println(lotti_selezionati);
                        System.out.println(qty_selezionati);
                        System.out.println(date_scadenza_selezionate);
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(RadioBtn);
                        }
                    }
                };
                return cell;
            }
        };

        colRadioBtn.setCellFactory(cellFactory);

        this.lotti_table.getColumns().add(colRadioBtn);

    }

    @FXML
    public void premeConferma(MouseEvent event) throws IOException {
        CaricoPrenotazioneControl.carPrenCtrl.premutoConferma(lotti_selezionati,qty_selezionati,date_scadenza_selezionate,event);
    }
    public void premeLogout(MouseEvent mouseEvent) throws IOException {
        App.setRoot("autenticazione/login/SchermataLogin");
    }

    public void premeIndietro(MouseEvent mouseEvent) throws IOException {
        CaricoPrenotazioneControl.carPrenCtrl.premutoIndietro();
    }

    public void premeHome(MouseEvent mouseEvent) throws IOException {
        CaricoPrenotazioneControl.carPrenCtrl.premutoHome("gestionePrenotazioni/caricoPrenotazione/SchermataRiepilogoCarico");
    }
}
