package com.vipagepharma.farmacia.gestionePrenotazioni.prenotaFarmaci;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.DBMSBoundary;
import com.vipagepharma.farmacia.entity.Utente;
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
    private String nome_farmaco;
    private int id_farmaco;
    private int id_corriere;
    private int id_farmacia;
    private LocalDate data_scadenza_min;
    private String qtyRichiesta;
    private String qtyDisponibile;
    private String qtyMancante;  // serve per mostrare la schermata -> AvvisoMancataDisponibilita
    private int flag_scadenza;
    private LocalDate data_consegna;
    private LocalDate new_data_consegna;
    private ResultSet lotti;

    ArrayList<Integer> idLotti;
    ArrayList<Integer> qtyLotti;
    ArrayList<Integer> new_idLotti;
    ArrayList<Integer> new_qtyLotti;

    public PrenotaFarmaciControl(){
        controlRef = this;
    }
    public static PrenotaFarmaciControl getControl(){
        return controlRef;
    }


    public void start(String id_farmaco, String nome_farmaco) throws IOException {
        this.id_farmaco = Integer.parseInt(id_farmaco);
        this.nome_farmaco = nome_farmaco;
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
        this.lotti = DBMSBoundary.getLotti(String.valueOf(this.id_farmaco));
        ResultSet corrieri = DBMSBoundary.getCorrieri();
        this.scegliCorriere(corrieri);
        this.id_farmacia = Integer.parseInt(Utente.getID());
        checkDisponibilitaEScegliLotti();
        if(Integer.parseInt(this.qtyDisponibile) >= Integer.parseInt(this.qtyRichiesta)){

            App.newWind("gestionePrenotazioni/prenotaFarmaci/AvvisoPrenotazioneDisponibile",event);
        }
        else{
            App.newWind("gestionePrenotazioni/prenotaFarmaci/AvvisoMancataDisponibilita",event);
        }
    }

    public void premutoConferma(MouseEvent event) throws IOException {
        DBMSBoundary.creaPrenotazioneEScarica(this.id_farmacia,this.id_corriere,this.id_farmaco, this.data_consegna,this.idLotti,this.qtyLotti,Integer.parseInt(this.qtyRichiesta));
        App.popup_stage.close();
        App.newWind("gestionePrenotazioni/prenotaFarmaci/AvvisoOperazioneRiuscita",event);

    }

    public void premutoConferma(int opzione,MouseEvent event) throws IOException {
        if(opzione == 1) {
            DBMSBoundary.creaPrenotazioneEScarica(this.id_farmacia, this.id_corriere,this.id_farmaco, this.data_consegna, this.idLotti, this.qtyLotti, Integer.parseInt(this.qtyDisponibile));
        }
        else{
            DBMSBoundary.creaPrenotazioneEScarica(this.id_farmacia, this.id_corriere,this.id_farmaco, this.data_consegna, this.idLotti, this.qtyLotti, Integer.parseInt(this.qtyDisponibile));
            DBMSBoundary.creaPrenotazioneEScarica(this.id_farmacia, this.id_corriere,this.id_farmaco, this.new_data_consegna, this.new_idLotti, this.new_qtyLotti, Integer.parseInt(this.qtyMancante));
        }
        App.popup_stage.close();
        App.newWind("gestionePrenotazioni/prenotaFarmaci/AvvisoOperazioneRiuscita",event);
    }


    private void checkDisponibilitaEScegliLotti() throws SQLException {  //posso calcolare sia la disponibilitá che i lotti con un metodo
        int qtyTotale = Integer.parseInt(this.qtyRichiesta);
        int qtyLottiTot = 0;
        while(lotti.next() && qtyLottiTot < qtyTotale && this.lotti.getDate("data_di_disponibilita").toLocalDate().isBefore(this.data_consegna)){  //esco dal loop appena la data di disp > data consegna richiesta
            if(this.lotti.getDate("data_di_scadenza").toLocalDate().isAfter(this.data_scadenza_min)){
                this.idLotti.add(this.lotti.getInt(1));
                int qtyLotto = this.lotti.getInt(3);
                qtyLottiTot += qtyLotto;
                if(qtyLottiTot > qtyTotale){
                    this.qtyLotti.add(qtyTotale - (qtyLottiTot - qtyLotto));
                }
                else{
                    this.qtyLotti.add(qtyLotto);
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
        this.lotti.close();
    }

    // DA SISTEMARE
    private void calcProxDisponibilitaEScegliLotti() throws SQLException {
        int qtyMancante = Integer.parseInt(this.qtyRichiesta) - Integer.parseInt(this.qtyDisponibile);
        int qtyLottiTot = 0;
        this.new_idLotti.add(this.lotti.getInt("id_l"));
        int qtyLotto1 = this.lotti.getInt("qty");
        qtyLottiTot += qtyLotto1;
        if(qtyLottiTot > qtyMancante){
            this.new_qtyLotti.add(qtyMancante - (qtyLottiTot - qtyLotto1));
        }
        else{
            this.new_qtyLotti.add(qtyLotto1);
        }
        while(this.lotti.next() && qtyLottiTot<qtyMancante){
            this.new_idLotti.add(this.lotti.getInt("id_l"));
            int qtyLotto = this.lotti.getInt("qty");
            qtyLottiTot += qtyLotto;
            if(qtyLottiTot > qtyMancante){
                this.new_qtyLotti.add(qtyMancante - (qtyLottiTot - qtyLotto));
            }
            else{
                this.new_qtyLotti.add(qtyLotto);
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

    private void scegliCorriere(ResultSet corrieri) throws SQLException {
        if(corrieri.last()){
            int len = corrieri.getRow();
            int index = ThreadLocalRandom.current().nextInt(1, len + 1);
            if(corrieri.absolute(index)){
                this.id_corriere = corrieri.getInt("id_ua");
            }
        }
        corrieri.close();
    }

    public void premutoOk() throws IOException {
        App.popup_stage.close();
        App.setRoot("SchermataPrincipale");
    }


    public String getFarmaco(){
        return this.nome_farmaco;
    }
    public String getData(){
        return this.data_consegna.toString();
    }

    public String getQty(){
        return this.qtyDisponibile;
    }

    public String getNewData(){
        return  this.new_data_consegna.toString();
    }

    public String getQtyMancante(){
        return this.qtyMancante;
    }

}
