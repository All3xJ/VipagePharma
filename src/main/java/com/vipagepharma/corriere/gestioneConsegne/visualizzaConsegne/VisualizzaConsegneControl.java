package com.vipagepharma.corriere.gestioneConsegne.visualizzaConsegne;

import com.vipagepharma.corriere.SchermataPrincipale;
import com.vipagepharma.corriere.entity.Ordine;
import com.vipagepharma.corriere.gestioneConsegne.firmaConsegna.FirmaConsegnaControl;
import com.vipagepharma.corriere.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VisualizzaConsegneControl {

    public static VisualizzaConsegneControl visualConCtrlRef;

    public static ResultSet ordini;

    public ObservableList<Ordine> tvObservableList = FXCollections.observableArrayList();

    public VisualizzaConsegneControl(){
        visualConCtrlRef=this;
    }

    public void start() throws IOException, SQLException {
        this.riempiObservableList(ordini);
        App.setRoot("gestioneConsegne/visualizzaConsegne/SchermataConsegneOdierne");
    }

    private void riempiObservableList(ResultSet ordini) throws SQLException {
        try {
            this.tvObservableList.clear();
            while (true) {
                if (!ordini.next()) break;
                this.tvObservableList.add(new Ordine(ordini.getString("id_p"),ordini.getString("nome"),ordini.getString("ref_id_uf"),ordini.getString("qty"),ordini.getString("data_consegna")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ordini.close();
    }

    public void premutoHome(String schermataPrecedente) throws IOException {
        SchermataPrincipale.schermataPrecedente = schermataPrecedente;
        App.setRoot("SchermataPrincipale");
    }

    public void premutoIndietro() throws IOException {
        App.setRoot("SchermataPrincipale");
    }
}
