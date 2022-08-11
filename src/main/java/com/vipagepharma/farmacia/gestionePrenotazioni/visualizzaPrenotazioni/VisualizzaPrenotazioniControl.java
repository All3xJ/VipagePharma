package com.vipagepharma.farmacia.gestionePrenotazioni.visualizzaPrenotazioni;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.DBMSBoundary;
import com.vipagepharma.farmacia.SchermataPrincipale;
import com.vipagepharma.farmacia.autenticazione.logout.LogoutControl;
import com.vipagepharma.farmacia.entity.Utente;
import com.vipagepharma.farmacia.gestionePrenotazioni.annullaPrenotazione.AnnullaPrenotazioneControl;
import com.vipagepharma.farmacia.gestionePrenotazioni.caricoPrenotazione.CaricoPrenotazioneControl;
import com.vipagepharma.farmacia.gestionePrenotazioni.caricoPrenotazione.SchermataRiepilogoCarico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.vipagepharma.farmacia.entity.Prenotazione;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VisualizzaPrenotazioniControl {

    public static VisualizzaPrenotazioniControl visualPrenCtrlRef;

    public ObservableList<Prenotazione> tvObservableList = FXCollections.observableArrayList();

    public ResultSet prenotazioni = null;

    public VisualizzaPrenotazioniControl(){
        visualPrenCtrlRef = this;
    }

    public void start() throws IOException {
        this.riempiObservableList(this.getIDFarmacia());
        App.setRoot("gestionePrenotazioni/visualizzaPrenotazioni/SchermataElencoPrenotazioni");
    }

    private String getIDFarmacia(){
        return Utente.getID();
    }

    private void riempiObservableList(String IDFarmacia)  {
        this.prenotazioni = DBMSBoundary.getPrenotazioniEInfoFarmaci(IDFarmacia);
        try {
        while (true) {
            if (!prenotazioni.next()) break;
            this.tvObservableList.add(new Prenotazione(prenotazioni.getString("id_p"),prenotazioni.getString("nome"),prenotazioni.getString("data_consegna")));
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void premutoIndietro() throws IOException {
        App.setRoot("SchermataPrincipale");
    }

    public void premutoHome(String schermataPrecedente) throws IOException {
        SchermataPrincipale.schermataPrecedente=schermataPrecedente;
        App.setRoot("SchermataPrincipale");
    }

    public void premutoLogout() throws IOException {
        LogoutControl.start();
    }

    public void premutoCarico(String schermataPrecedente) throws IOException {
        CaricoPrenotazioneControl carPrenCtrl = new CaricoPrenotazioneControl();
        carPrenCtrl.start();
    }

    public void premutoAnnulla(String schermataPrecedente) throws IOException {
        AnnullaPrenotazioneControl annPrenCtrl = new AnnullaPrenotazioneControl();
        annPrenCtrl.start();
    }
}
