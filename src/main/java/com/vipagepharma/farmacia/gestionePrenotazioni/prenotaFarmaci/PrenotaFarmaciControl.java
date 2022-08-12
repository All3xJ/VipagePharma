package com.vipagepharma.farmacia.gestionePrenotazioni.prenotaFarmaci;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.DBMSBoundary;
import com.vipagepharma.farmacia.entity.Farmaco;
import com.vipagepharma.farmacia.entity.Prenotazione;
import com.vipagepharma.farmacia.entity.Utente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class PrenotaFarmaciControl {

    private static PrenotaFarmaciControl controlRef;
    private String id_farmaco;
    private LocalDate data_scadenza_min;
    private String qtyRichiesta;
    private String qtyDisponibile;
    private String qtyMancante;  // serve per mostrare la schermata -> AvvisoMancataDisponibilita
    private int flag_scadenza;
    private LocalDate data_consegna;
    private LocalDate new_data_consegna;
    private ResultSet lotti;
    private String id_corriere;
    private String id_farmacia;
    ArrayList<String> idLotti;
    ArrayList<String> qtyLotti;
    ArrayList<String> new_idLotti;
    ArrayList<String> new_qtyLotti;

    public PrenotaFarmaciControl(){
        controlRef = this;
    }


    public void start(String id_farmaco) throws IOException {
        this.id_farmaco = id_farmaco;
        App.setRoot("gestionePrenotazioni/prenotaFarmaci/SchermataPrenotazione");
    }


    public void premutoInvia(LocalDate data_consegna, String qtyRichiesta , int flag_scadenza, ActionEvent event) throws SQLException, IOException {
        this.data_consegna = data_consegna;
        this.qtyRichiesta = qtyRichiesta;
        this.flag_scadenza = flag_scadenza;
        this.data_scadenza_min = calcDataScadenzaMin();
        this.idLotti = new ArrayList<>();
        this.new_idLotti = new ArrayList<>();
        this.qtyLotti = new ArrayList<>();
        this.new_qtyLotti = new ArrayList<>();
        this.lotti = DBMSBoundary.getLotti(this.id_farmaco);
        ResultSet corrieri = DBMSBoundary.getCorrieri(this.id_farmaco);
        this.id_corriere = this.scegliCorriere(corrieri);
        this.id_farmacia = Utente.getID();
        System.out.println("Farmacia:" + this.id_farmacia +"\nQty richiesta:" + this.qtyRichiesta + "\nData consegna :" + this.data_consegna + "\nCorriere selezionato: " + this.id_corriere + "\nFlag Scadenza:"+ this.flag_scadenza);
        checkDisponibilitaEScegliLotti();
        if(Integer.parseInt(this.qtyDisponibile) >= Integer.parseInt(this.qtyRichiesta)){
            App.newWind("gestionePrenotazioni/prenotaFarmaci/AvvisoPrenotazioneDisponibile",event);
        }
        else{
            App.newWind("gestionePrenotazioni/prenotaFarmaci/AvvisoMancataDisponibilita",event);
        }
    }

    public void premutoConferma(MouseEvent event) throws IOException {
        DBMSBoundary.creaPrenotazioneEScarica(this.id_farmacia,this.id_corriere,this.id_farmaco, this.data_consegna,this.idLotti,this.qtyLotti);
        App.popup_stage.close();
        App.newWind("gestionePrenotazioni/prenotaFarmaci/OperazioneRiuscita",event);

    }

    public void premutoConferma(int opzione,MouseEvent event) throws IOException {
        if(opzione == 1) {
            DBMSBoundary.creaPrenotazioneEScarica(this.id_farmacia, this.id_corriere,this.id_farmaco, this.data_consegna, this.idLotti, this.qtyLotti);
            DBMSBoundary.creaPrenotazioneEScarica(this.id_farmacia, this.id_corriere,this.id_farmaco, this.new_data_consegna, this.new_idLotti, this.new_qtyLotti);
        }
        else{
            DBMSBoundary.creaPrenotazioneEScarica(this.id_farmacia, this.id_corriere,this.id_farmaco, this.data_consegna, this.idLotti, this.qtyLotti);
        }
        App.popup_stage.close();
        App.newWind("gestionePrenotazioni/prenotaFarmaci/OperazioneRiuscita",event);
    }


    private void checkDisponibilitaEScegliLotti() throws SQLException {  //posso calcolare sia la disponibilitá che i lotti con un metodo
        int qtyTotale = Integer.parseInt(this.qtyRichiesta);
        int qtyLottiTot = 0;
        while(lotti.next() && qtyLottiTot < qtyTotale && this.lotti.getDate(4).toLocalDate().isBefore(this.data_consegna)){  //esco dal loop appena la data di disp > data consegna richiesta
            if(this.lotti.getDate(5).toLocalDate().isAfter(this.data_scadenza_min)){
                this.idLotti.add(this.lotti.getString(1));
                int qtyLotto = this.lotti.getInt(3);
                qtyLottiTot += qtyLotto;
                if(qtyLottiTot > qtyTotale){
                    this.qtyLotti.add(String.valueOf(qtyTotale - (qtyLottiTot - qtyLotto)));
                }
                else{
                    this.qtyLotti.add(String.valueOf(qtyLotto));
                }
            }
        }
        if(qtyLottiTot <= qtyTotale){  //se dopo il loop non mi sono bastati i farmaci
            this.qtyDisponibile = String.valueOf(qtyLottiTot);
            calcProxDisponibilitaEScegliLotti();
        }
        else{
            this.qtyDisponibile = qtyRichiesta;
        }
    }

    private void calcProxDisponibilitaEScegliLotti() throws SQLException {
        int qtyMancante = Integer.parseInt(this.qtyDisponibile) - Integer.parseInt(this.qtyRichiesta);
        int qtyLottiTot = 0;
        this.new_idLotti.add(this.lotti.getString(1));
        int qtyLotto1 = this.lotti.getInt(3);
        qtyLottiTot += qtyLotto1;
        if(qtyLottiTot > qtyMancante){
            this.new_qtyLotti.add(String.valueOf(qtyMancante - (qtyLottiTot - qtyLotto1)));
        }
        else{
            this.new_qtyLotti.add(String.valueOf(qtyLotto1));
        }
        while(this.lotti.next() && qtyLottiTot<qtyMancante){
            this.new_idLotti.add(this.lotti.getString(1));
            int qtyLotto = this.lotti.getInt(3);
            qtyLottiTot += qtyLotto;
            if(qtyLottiTot > qtyMancante){
                this.new_qtyLotti.add(String.valueOf(qtyMancante - (qtyLottiTot - qtyLotto)));
            }
            else{
                this.new_qtyLotti.add(String.valueOf(qtyLotto));
            }
        }
        this.qtyMancante = String.valueOf(qtyMancante);
        this.lotti.previous();
        this.new_data_consegna = this.lotti.getDate(4).toLocalDate();
    }

    private LocalDate calcDataScadenzaMin(){
        if(this.flag_scadenza == 0){
            return data_consegna.plusMonths(2);
        }
        else{
            return data_consegna;
        }
    }


    /*imposta resultSet sull'ultimo elemento
    prende il numero di quest'ultimo elemento
    sceglie un indice casuale
    imposta resultSet sulla riga casuale
    ritorna l'id di questa riga*/

    private String scegliCorriere(ResultSet corrieri) throws SQLException {
        String id_corriere = "";
        if(corrieri.last()){
            int len = corrieri.getRow();
            int index = ThreadLocalRandom.current().nextInt(1, len + 1);
            if(corrieri.absolute(index)){
                id_corriere = id_corriere.concat(corrieri.getString("id_ua"));
            }
        }
        corrieri.close();
        return id_corriere;
    }
    public static PrenotaFarmaciControl getControl(){
        return controlRef;
    }
}
