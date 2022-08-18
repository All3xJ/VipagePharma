package com.vipagepharma.addettoAzienda.gestioneConsegne.visualizzaStoricoConsegne;

import com.vipagepharma.addettoAzienda.App;
import com.vipagepharma.addettoAzienda.DBMSBoundary;
import com.vipagepharma.addettoAzienda.entity.Consegna;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VisualizzaStoricoConsegneControl {

    public static VisualizzaStoricoConsegneControl visStoConsCtrlRef;

    public VisualizzaStoricoConsegneControl(){
        visStoConsCtrlRef = this;
    }

    public ObservableList<Consegna> tvObservableList = FXCollections.observableArrayList();

    public ResultSet consegne = null;

    public void start() throws IOException, SQLException {
        this.riempiObservableList();
        App.setRoot("gestioneConsegne/visualizzaStoricoConsegne/SchermataStoricoConsegne");
    }

    private void riempiObservableList() throws SQLException {
        this.consegne = DBMSBoundary.getConsegneRecenti();
        try {
            this.tvObservableList.clear();
            while (true) {
                if (!consegne.next()) break;
                this.tvObservableList.add(new Consegna(consegne.getString("ref_id_uf"),consegne.getString("id_p"),consegne.getString("data_consegna")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.consegne.close();
    }

    public void premutoVisualizzaErrore(String schermata, ActionEvent event) throws IOException {
        App.newWind(schermata,event);
    }
}
