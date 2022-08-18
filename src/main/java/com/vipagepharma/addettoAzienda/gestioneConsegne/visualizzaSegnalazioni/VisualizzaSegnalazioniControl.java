package com.vipagepharma.addettoAzienda.gestioneConsegne.visualizzaSegnalazioni;

import com.vipagepharma.addettoAzienda.DBMSBoundary;
import com.vipagepharma.addettoAzienda.entity.Consegna;
import com.vipagepharma.addettoAzienda.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VisualizzaSegnalazioniControl {

    public static VisualizzaSegnalazioniControl visConsCtrlRef;

    public ObservableList<Consegna> tvObservableList = FXCollections.observableArrayList();

    public ResultSet consegneConSegnalazione = null;

    public VisualizzaSegnalazioniControl(){
        visConsCtrlRef = this;
    }

    public void start() throws IOException, SQLException {
        this.riempiObservableList();
        App.setRoot("gestioneConsegne/visualizzaSegnalazioni/SchermataElencoSegnalazioni");
    }

    private void riempiObservableList() throws SQLException {
        this.consegneConSegnalazione = DBMSBoundary.getElencoConsegneConSegnalazioni();
        try {
            this.tvObservableList.clear();
            while (true) {
                if (!consegneConSegnalazione.next()) break;
                this.tvObservableList.add(new Consegna(consegneConSegnalazione.getString("ref_id_uf"),consegneConSegnalazione.getString("id_p"),consegneConSegnalazione.getString("data_consegna")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.consegneConSegnalazione.close();
    }

    public void premutoVisualizzaErrore(String schermata, ActionEvent event) throws IOException {
        App.newWind(schermata,event);
    }
}
