package com.vipagepharma.farmacia.gestionePrenotazioni.prenotaFarmaci;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.DBMSBoundary;
import com.vipagepharma.farmacia.entity.Utente;


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
    private String qtyMancante;
    private int flag_scadenza;
    private LocalDate data_consegna;
    private LocalDate new_data_consegna;
    private ResultSet lotti;
    private String id_corriere;
    private String id_farmacia;
    ArrayList<String> idLotti;
    ArrayList<String> qtyLotti;

    public PrenotaFarmaciControl(){
        controlRef = this;
    }

    public void start(String id_farmaco) throws IOException {
        this.id_farmaco = id_farmaco;
        App.setRoot("gestionePrenotazione/prenotaFarmaci/SchermataPrenotazione");
    }

    public void premutoInvio(LocalDate data_consegna, String qtyRichiesta , int flag_scadenza) throws SQLException, IOException {
        this.data_consegna = data_consegna;
        this.qtyRichiesta = qtyRichiesta;
        this.flag_scadenza = flag_scadenza;
        this.data_scadenza_min = calcDataScadenzaMin();
        this.lotti = DBMSBoundary.getLotti(this.id_farmaco);
        ResultSet corrieri = DBMSBoundary.getCorrieri(this.id_farmaco);
        this.id_corriere = this.scegliCorriere(corrieri);
        ResultSet produzione = DBMSBoundary.getProduzione(this.id_farmaco);
        checkDisponibilitaEScegliLotti();
        this.id_farmacia = Utente.getID();
    }

    public void premutoConferma() throws SQLException {
        this.scegliLotti();
        DBMSBoundary.creaPrenotazioneEScarica(this.id_farmacia,this.id_corriere,this.data_consegna,this.idLotti,this.qtyLotti);
    }

    /* Scegli lotti : scorre i lotti finche ci sono tuple and la qty dei farmaci non ha superato la richiesta; per ogni tupla che
                          ha la data di disponibilitá < della data di consegna and la data di scandenza > la data accettata di scadenza
                          aggiunge l'id del lotto alla lista dei lotti e se la qty é abbastanza aggiunge quella che basta, senno tutta
         */
    private void scegliLotti() throws SQLException {
        int qtyTotale = Integer.parseInt(this.qtyRichiesta);
        int qtyLottiTot = 0;
        while(lotti.next() && qtyLottiTot < qtyTotale){
            if(this.lotti.getDate(5).toLocalDate().isAfter(this.data_scadenza_min) && this.lotti.getDate(4).toLocalDate().isBefore(this.data_consegna)){
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

    /* mi servirebbe una tabella con le sole date > tot sarebbe piu semplice qua dovrei comunque riscorrere tutte le tuple */

    private void calcProxDisponibilitaEScegliLotti() throws SQLException {
        int qtyMancante = Integer.parseInt(this.qtyDisponibile) - Integer.parseInt(this.qtyRichiesta);
        int qtyLottiTot = 0;
        while(this.lotti.next() && qtyLottiTot<qtyMancante){
            this.idLotti.add(this.lotti.getString(1));
            int qtyLotto = this.lotti.getInt(3);
            qtyLottiTot += qtyLotto;
            if(qtyLottiTot > qtyMancante){
                this.qtyLotti.add(String.valueOf(qtyMancante - (qtyLottiTot - qtyLotto)));
            }
            else{
                this.qtyLotti.add(String.valueOf(qtyLotto));
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
        if(corrieri.last()){                                        //imposta resultSet sull'ultimo elemento
            int len = corrieri.getRow();                            //prende il numero di quest'ultimo elemento
            int index  = (int) (Math.random() * len);               //sceglie un indice casuale
            if(corrieri.absolute(index)){                           //imposta resultSet sulla riga casuale
                return corrieri.getString("id_ua");      //ritorna l'id di questa riga
            }
        }
        return null;
    }
    public static PrenotaFarmaciControl getControl(){
        return controlRef;
    }
}
