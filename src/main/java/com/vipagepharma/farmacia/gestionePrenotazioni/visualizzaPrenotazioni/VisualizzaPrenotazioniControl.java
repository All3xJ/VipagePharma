package com.vipagepharma.farmacia.gestionePrenotazioni.visualizzaPrenotazioni;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.DBMSBoundary;
import com.vipagepharma.farmacia.SchermataPrincipale;
import com.vipagepharma.farmacia.entity.Utente;
import com.vipagepharma.farmacia.gestionePrenotazioni.annullaPrenotazione.AnnullaPrenotazioneControl;
import com.vipagepharma.farmacia.gestionePrenotazioni.caricoPrenotazione.CaricoPrenotazioneControl;
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

    public void start() throws IOException, SQLException {
        this.riempiObservableList(this.getIDFarmacia());
        App.setRoot("gestionePrenotazioni/visualizzaPrenotazioni/SchermataElencoPrenotazioni");
    }

    private String getIDFarmacia(){
        return Utente.getID();
    }

    private void riempiObservableList(String IDFarmacia) throws SQLException {
        this.prenotazioni = DBMSBoundary.getPrenotazioniEInfoFarmaci(IDFarmacia);
        try {
            this.tvObservableList.clear();
        while (true) {
            if (!prenotazioni.next()) break;
            this.tvObservableList.add(new Prenotazione(prenotazioni.getString("id_p"),prenotazioni.getString("nome"),prenotazioni.getString("data_consegna")));
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.prenotazioni.close();
    }

    public void premutoIndietro() throws IOException {
        App.setRoot("SchermataPrincipale");
    }

    public void premutoHome(String schermataPrecedente) throws IOException {
        SchermataPrincipale.schermataPrecedente=schermataPrecedente;
        App.setRoot("SchermataPrincipale");
    }



    public void premutoAnnulla(String schermataPrecedente) throws IOException {
        AnnullaPrenotazioneControl annPrenCtrl = new AnnullaPrenotazioneControl();
        annPrenCtrl.start();
    }
}
