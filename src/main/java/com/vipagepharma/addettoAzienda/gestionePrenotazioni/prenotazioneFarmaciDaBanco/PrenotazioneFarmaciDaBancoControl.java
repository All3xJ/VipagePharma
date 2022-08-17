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
    }

    public void start() throws SQLException {
        if((data.getDayOfWeek() == DayOfWeek.MONDAY) && orario.getHour() == 9){
            ResultSet contratti = DBMSBoundary.getContratti();
            ResultSet corrieri = DBMSBoundary.getCorrieri();
            while(contratti.next()){
                String id_farmaco = contratti.getString("ref_id_f");
                ResultSet lotti = DBMSBoundary.getLotti(id_farmaco);
                scegliLottiECorriere(lotti,corrieri,contratti);
                DBMSBoundary.creaPrenotazioneEScarica(contratti.getInt("ref_id_uf"),this.id_corriere,Integer.parseInt(id_farmaco),data.plusWeeks(1),idLotti,qtyLotti);
                lotti.close();
            }
            contratti.close();
            corrieri.close();
        }
    }

    private void scegliLottiECorriere(ResultSet lotti,ResultSet corrieri,ResultSet contratti) throws SQLException {
        int qty = 0;
        int qtyTotale = contratti.getInt("qty_settimanale");
        while(qty < qtyTotale){
            lotti.next();
            this.idLotti.add(lotti.getInt("id_l"));
            int qtyLotto = lotti.getInt("qty");
            qty += qtyLotto;
            if(qty > qtyTotale){
                qtyLotti.add(qtyTotale - (qty - qtyLotto));
            }
            else{
                qtyLotti.add(qtyLotto);
            }
        }
        if(corrieri.last()){
            int len = corrieri.getRow();
            int index = ThreadLocalRandom.current().nextInt(1, len + 1);
            if(corrieri.absolute(index)){
                this.id_corriere = corrieri.getInt("id_ua");
            }
        }
        corrieri.close();
    }
}
