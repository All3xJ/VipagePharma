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
                this.tvObservableList.add(new Consegna(consegneConSegnalazione.getString("id_utente_farmacia"),consegneConSegnalazione.getString("id_prenotazione"),consegneConSegnalazione.getString("data_consegna"),consegneConSegnalazione.getString("id_farmaco"),consegneConSegnalazione.getString("nome")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.consegneConSegnalazione.close();
    }
}
