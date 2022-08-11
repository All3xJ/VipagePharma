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
        App.setRoot("gestionePrenotazione/prenotaFarmaci/SchermataPrenotazione");
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
        checkDisponibilitaEScegliLotti();
        if(Integer.parseInt(this.qtyDisponibile) > Integer.parseInt(this.qtyRichiesta)){
            App.newWind("autenticazione/gestionePrenotazione/AvvisoPrenotazioneDisponibile",event);
        }
        else{
            App.newWind("autenticazione/gestionePrenotazione/AvvisoMancataDisponibilita",event);
        }
    }

    public void premutoConferma(MouseEvent event) throws IOException {
        DBMSBoundary.creaPrenotazioneEScarica(this.id_farmacia,this.id_corriere,this.data_consegna,this.idLotti,this.qtyLotti);
        App.popup_stage.close();
        App.newWind("autenticazione/gestionePrenotazione/OperazioneRiuscita",event);

    }

    public void premutoConferma(int opzione,MouseEvent event) throws IOException {
        App.popup_stage.close();
        if(opzione == 1) {
            DBMSBoundary.creaPrenotazioneEScarica(this.id_farmacia, this.id_corriere, this.data_consegna, this.idLotti, this.qtyLotti);
            DBMSBoundary.creaPrenotazioneEScarica(this.id_farmacia, this.id_corriere, this.new_data_consegna, this.new_idLotti, this.new_qtyLotti);
        }
        else{
            DBMSBoundary.creaPrenotazioneEScarica(this.id_farmacia, this.id_corriere, this.data_consegna, this.idLotti, this.qtyLotti);
        }
        App.popup_stage.close();
        App.newWind("autenticazione/gestionePrenotazione/OperazioneRiuscita",event);
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

    private String scegliCorriere(ResultSet corrieri) throws SQLException {
        String id_corriere = null;
        if(corrieri.last()){                                               //imposta resultSet sull'ultimo elemento
            int len = corrieri.getRow();                                   //prende il numero di quest'ultimo elemento
            int index  = (int) (Math.random() * len);                      //sceglie un indice casuale
            if(corrieri.absolute(index)){                                  //imposta resultSet sulla riga casuale
                id_corriere = corrieri.getString("id_ua");      //ritorna l'id di questa riga
            }
        }
        corrieri.close();
        return id_corriere;
    }
    public static PrenotaFarmaciControl getControl(){
        return controlRef;
    }
}
