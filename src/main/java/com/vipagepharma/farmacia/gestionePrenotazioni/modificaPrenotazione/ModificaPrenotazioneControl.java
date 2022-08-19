package com.vipagepharma.farmacia.gestionePrenotazioni.modificaPrenotazione;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.DBMSBoundary;
import com.vipagepharma.farmacia.entity.Prenotazione;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ModificaPrenotazioneControl {
    public static ModificaPrenotazioneControl modificaPrenotazioneControl;
    private Prenotazione prenotazione;
    private int flag_scadenza;
    private LocalDate data_consegna;
    private LocalDate data_scadenza_min;
    private int qtyRichiesta;
    private ResultSet lotti;
    ArrayList<Integer> idLotti;
    ArrayList<Integer> qtyLotti;

    public ModificaPrenotazioneControl(Prenotazione prenotazione){
        this.prenotazione = prenotazione;
        modificaPrenotazioneControl = this;
    }

    public void start() throws IOException {
        App.setRoot("gestionePrenotazioni/modificaPrenotazione/SchermataModifica");
    }

    public void premutoInvia(LocalDate data_consegna, String qtyRichiesta , int flag_scadenza, ActionEvent event) throws SQLException, IOException {
        this.data_consegna = data_consegna;
        this.qtyRichiesta = Integer.parseInt(qtyRichiesta) - prenotazione.getQty();
        this.flag_scadenza = flag_scadenza;
        this.data_scadenza_min = calcDataScadenzaMin();
        checkDisponibilita(event);
        this.lotti = DBMSBoundary.getLotti(String.valueOf(prenotazione.getIdFarmaco()));
    }

    private void checkDisponibilita(ActionEvent event) throws SQLException, IOException {  //posso calcolare sia la disponibilitá che i lotti con un metodo
        if(this.qtyRichiesta > 0 && this.data_consegna.isAfter(LocalDate.parse(prenotazione.getDataConsegna()))) {
            int qtyLottiTot = 0;
            while (lotti.next() && qtyLottiTot < this.qtyRichiesta && this.lotti.getDate(4).toLocalDate().isBefore(this.data_consegna)) {  //esco dal loop appena la data di disp > data consegna richiesta
                if (this.lotti.getDate(5).toLocalDate().isAfter(this.data_scadenza_min)) {
                    this.idLotti.add(this.lotti.getInt(1));
                    int qtyLotto = this.lotti.getInt(3);
                    qtyLottiTot += qtyLotto;
                    if (qtyLottiTot > this.qtyRichiesta) {
                        this.qtyLotti.add(this.qtyRichiesta - (qtyLottiTot - qtyLotto));
                    } else {
                        this.qtyLotti.add(qtyLotto);
                    }
                }
            }
            if(qtyLottiTot > this.qtyRichiesta){
                App.newWind("gestionePrenotazioni/modificaPrenotazione/AvvisoConfermaModifica",event);
            }
            else{
                App.newWind("gestionePrenotazioni/modificaPrenotazione/AvvisoModificaErrata",event);
            }
        }
        else if(this.data_consegna.isBefore(LocalDate.parse(prenotazione.getDataConsegna()))) {

        }
    }

    public void premutoSi(MouseEvent event) throws IOException{
        //ESEGUIRE QUERY -> cambiare la vecchia prenotazione nella nuova data , ed aggiungere i nuovi lotti in lotto_ordinato
        App.newWind("gestionePrenotazioni/modificaPrenotazione/AvvisoOperazioneRiuscita",event);
    }
    public void premutoNo(MouseEvent event) throws IOException{
        App.newWind("gestionePrenotazioni/visualizzaPrenotazioni/SchermataElencoPrenotazioni",event);
    }

    public void premutoOk() throws IOException {
        App.popup_stage.close();
        App.setRoot("SchermataPrincipale");
    }
    public void premutoOk(MouseEvent event) throws IOException {
        App.popup_stage.close();
        App.setRoot("gestionePrenotazioni/modificaPrenotazione/SchermataModifica");
    }

    private LocalDate calcDataScadenzaMin(){
        if(this.flag_scadenza == 0){
            return data_consegna.plusMonths(2);
        }
        else{
            return data_consegna;
        }
    }

    public Prenotazione getPrenotazione(){
        return prenotazione;
    }
}
