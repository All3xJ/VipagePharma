package com.vipagepharma.farmacia.gestionePrenotazioni.prenotaFarmaci;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.DBMSBoundary;
import com.vipagepharma.farmacia.entity.Utente;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.Array;
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
    private int flag_scadenza;
    private LocalDate data_consegna;
    private LocalDate new_data_consegna;
    private ResultSet lotti;
    private String id_corriere;
    private String id_farmacia;
    ArrayList<Integer> idLotti;
    ArrayList<Integer> qtyLotti;

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
        //DA FARE CHECK DISPONIBILITÁ che torna qtyDisponibile
        this.id_farmacia = Utente.getID();

        if(Integer.parseInt(this.qtyDisponibile) > Integer.parseInt(this.qtyRichiesta)){
            App.setRoot("gestionePrenotazione/prenotaFarmaci/AvvisoPrenotazioneDisponibile");
        }
        else{
            calcProxDisponibilitaEScegliLotti();
        }


    }

    public void premutoConferma(){
        scegliLotti(this.lotti,this.qtyRichiesta);
        DBMSBoundary.creaPrenotazioneEScarica(this.id_farmacia,this.id_corriere,this.data_consegna,this.idLotti,this.qtyLotti);
    }

    public void scegliLotti(ResultSet lotti,String qty) throws SQLException {
        int qtyTotale = Integer.parseInt(qty);
        int qtyLottiTot = 0;
        while(lotti.next() && qtyLottiTot < qtyTotale){
            if(lotti.getDate(5).toLocalDate().isAfter(this.data_scadenza_min) && lotti.getDate(4).toLocalDate().isAfter(this.data_consegna)){
                this.idLotti.add(lotti.getInt(1));
                int qtyLotto = lotti.getInt(3);
                qtyLottiTot += qtyLotto;
                if(qtyLottiTot > qtyTotale){
                    this.qtyLotti.add(qtyTotale - (qtyLottiTot - qtyLotto));
                }
                else{
                    this.qtyLotti.add(qtyLotto);
                }
            }
        }
    }

    public void calcProxDisponibilitaEScegliLotti(){
        int qtyMancante = Integer.parseInt(this.qtyDisponibile) - Integer.parseInt(this.qtyRichiesta);
        //DA FARE
    }

    public LocalDate calcDataScadenzaMin(){
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
