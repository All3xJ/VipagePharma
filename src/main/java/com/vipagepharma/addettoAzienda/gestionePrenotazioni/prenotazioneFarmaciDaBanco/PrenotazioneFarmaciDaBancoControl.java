package com.vipagepharma.addettoAzienda.gestionePrenotazioni.prenotazioneFarmaciDaBanco;

import com.vipagepharma.addettoAzienda.DBMSBoundary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class PrenotazioneFarmaciDaBancoControl {
    private LocalDate data;
    private LocalTime orario;
    private int id_corriere;
    ArrayList<Integer> idLotti;
    ArrayList<Integer> qtyLotti;

    public PrenotazioneFarmaciDaBancoControl(){
        this.data = LocalDate.now();
        this.orario = LocalTime.now();
        this.idLotti = new ArrayList<>();
        this.qtyLotti = new ArrayList<>();
    }

    public void start() throws SQLException {
        if((data.getDayOfWeek() == DayOfWeek.MONDAY) && orario.getHour() == 9){
            ResultSet contratti = DBMSBoundary.getContratti();
            ResultSet corrieri = DBMSBoundary.getCorrieri();
            while(contratti.next()){
                String id_farmaco = contratti.getString("id_farmaco");
                ResultSet lotti = DBMSBoundary.getLotti(id_farmaco);
                scegliLottiECorriere(lotti,corrieri,contratti);
                DBMSBoundary.creaPrenotazioneDaBancoEScarica(contratti.getInt("id_utente_farmacia"),this.id_corriere,Integer.parseInt(id_farmaco),data.plusWeeks(1),this.idLotti,this.qtyLotti);
                this.idLotti.clear();
                this.qtyLotti.clear();
            }
            //contratti.close();
            //corrieri.close();
        }
    }

    private void scegliLottiECorriere(ResultSet lotti,ResultSet corrieri,ResultSet contratti) throws SQLException {
        int qty = 0;
        int qtyTotale = contratti.getInt("quantita_settimanale");
        while(qty < qtyTotale){
            lotti.next();
            if(lotti.getInt("quantita_contratti")>0) {
                this.idLotti.add(lotti.getInt("id_lotto"));
                int qtyLotto = lotti.getInt("quantita_contratti");
                qty += qtyLotto;
                if (qty > qtyTotale) {
                    qtyLotti.add(qtyTotale - (qty - qtyLotto));
                } else {
                    qtyLotti.add(qtyLotto);
                }
            }
        }
        if(corrieri.last()){
            int len = corrieri.getRow();
            int index = ThreadLocalRandom.current().nextInt(1, len + 1);
            if(corrieri.absolute(index)){
                this.id_corriere = corrieri.getInt("id_utente_azienda");
            }
        }
        corrieri.beforeFirst();
    }
}
