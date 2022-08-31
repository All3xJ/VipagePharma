package com.vipagepharma.farmacia.gestionePrenotazioni.caricoPrenotazione;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.DBMSBoundary;
import com.vipagepharma.farmacia.SchermataPrincipale;
import com.vipagepharma.farmacia.entity.Lotto;
import com.vipagepharma.farmacia.entity.Prenotazione;
import com.vipagepharma.farmacia.gestionePrenotazioni.visualizzaPrenotazioni.VisualizzaPrenotazioniControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;


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

    public void premutoConferma(LinkedList<String> lotti_selezionati, LinkedList<String> qty, LinkedList<String> date_scadenza, MouseEvent event) throws IOException {
        DBMSBoundary.confermaCarico(this.id_prenotazione,lotti_selezionati);
        DBMSBoundary.aggiungiCarico(this.id_farmacia,this.id_farmaco,nome_farmaco,lotti_selezionati,date_scadenza,qty,prenotazione.getIsBanco());
        App.newWind("gestionePrenotazioni/caricoPrenotazione/AvvisoOperazioneRiuscita",event);
    }

    public void premutoOk() throws IOException, SQLException {
        VisualizzaPrenotazioniControl.visualPrenCtrlRef.riempiObservableList(this.id_farmacia);
        App.setRoot("gestionePrenotazioni/visualizzaPrenotazioni/SchermataElencoPrenotazioni");
        App.popup_stage.close();

    }

    private void riempiObservableList() throws SQLException {
        this.lotti_ordinati = DBMSBoundary.getLottiOrdinati(this.id_prenotazione);
        try {
            this.tvObservableList.clear();
            SchermataRiepilogoCarico.qty_prevista=0;
            while (true) {
                if (!lotti_ordinati.next()) break;
                SchermataRiepilogoCarico.qty_prevista += lotti_ordinati.getInt("quantita");
                System.out.println(lotti_ordinati.getString("data_scadenza"));
                this.tvObservableList.add(new Lotto(lotti_ordinati.getString("id_lotto"),lotti_ordinati.getString("data_scadenza"),lotti_ordinati.getString("quantita")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //this.lotti_ordinati.close();
    }


    public void premutoIndietro() throws IOException {
        App.setRoot("gestionePrenotazioni/visualizzaPrenotazioni/SchermataElencoPrenotazioni");
    }

    public void premutoHome(String schermataPrecedente) throws IOException {
        SchermataPrincipale.schermataPrecedente=schermataPrecedente;
        App.setRoot("SchermataPrincipale");
    }
}
