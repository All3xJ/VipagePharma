package com.vipagepharma.farmacia.gestionePrenotazioni.caricoPrenotazione;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.DBMSBoundary;
import com.vipagepharma.farmacia.SchermataPrincipale;
import com.vipagepharma.farmacia.entity.Lotto;
import com.vipagepharma.farmacia.entity.Prenotazione;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class CaricoPrenotazioneControl {

    public static CaricoPrenotazioneControl carPrenCtrl;

    public ObservableList<Lotto> tvObservableList = FXCollections.observableArrayList();

    private Prenotazione prenotazione;
    private String id_prenotazione;
    private String id_farmaco;
    private String id_farmacia;
    public static String nome_farmaco;

    private ResultSet lotti_ordinati;

    public CaricoPrenotazioneControl(Prenotazione prenotazione){
        carPrenCtrl = this;
        this.prenotazione = prenotazione;
    }

    public void start() throws IOException, SQLException {
        this.id_farmacia = prenotazione.getIdFarmacia();
        this.id_farmaco = prenotazione.getIdFarmaco();
        this.id_prenotazione =  prenotazione.getIdPrenotazione();
        nome_farmaco = prenotazione.getNomeFarmaco();
        this.riempiObservableList();
        App.setRoot("gestionePrenotazioni/caricoPrenotazione/SchermataRiepilogoCarico");
    }

    public void premutoConferma(LinkedList<String> lotti_selezionati){
        //DBMSBoundary.confermaConsegna(this.id_prenotazione,lotti_selezionati);
        //DBMSBoundary.aggiungiCarico(this.id_farmacia,this.id_farmaco,this.nome_farmacia,lotti_selezionati,date_scadenza,qty);
    }

    private void riempiObservableList() throws SQLException {
        this.lotti_ordinati = DBMSBoundary.getLottiOrdinati(this.id_prenotazione);
        try {
            this.tvObservableList.clear();
            while (true) {
                if (!lotti_ordinati.next()) break;
                this.tvObservableList.add(new Lotto(lotti_ordinati.getString("ref_id_l"),lotti_ordinati.getString("data_di_scadenza"),lotti_ordinati.getString("qty")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.lotti_ordinati.close();
    }


    public void premutoIndietro() throws IOException {
        App.setRoot("gestionePrenotazioni/visualizzaPrenotazioni/SchermataElencoPrenotazioni");
    }

    public void premutoHome(String schermataPrecedente) throws IOException {
        SchermataPrincipale.schermataPrecedente=schermataPrecedente;
        App.setRoot("SchermataPrincipale");
    }
}
