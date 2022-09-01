package com.vipagepharma.farmacia.gestionePrenotazioni.ricercaFarmaco;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.SchermataPrincipale;
import com.vipagepharma.farmacia.DBMSBoundary;
import com.vipagepharma.farmacia.entity.Farmaco;
import com.vipagepharma.farmacia.entity.Prenotazione;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RicercaFarmacoControl {

    public static RicercaFarmacoControl controlRef;

    public ObservableList<Farmaco> tvObservableList = FXCollections.observableArrayList();

    public ResultSet farmaci = null;

    public RicercaFarmacoControl(){
        controlRef = this;
    }
    public static RicercaFarmacoControl getControl(){
        return controlRef;
    }

    public void start() throws IOException {
        App.setRoot("gestionePrenotazioni/ricercaFarmaco/SchermataRicercaFarmaco");
    }
    //ROBE TABELLA

    private void riempiObservableList(String nome_o_principio_attivo) throws SQLException {
        this.farmaci = DBMSBoundary.getFarmaco(nome_o_principio_attivo);
        try {
            this.tvObservableList.clear();
            while (true) {
                if (!farmaci.next()) break;
                this.tvObservableList.add(new Farmaco(farmaci.getString("id_farmaco"),farmaci.getString("nome"),farmaci.getString("principio_attivo")));
            }
            //this.farmaci.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //this.farmaci.close();
    }

    public void premutoInvio(String nome_o_principio_attivo) throws IOException, SQLException {
        this.riempiObservableList(nome_o_principio_attivo);
        App.setRoot("gestionePrenotazioni/prenotaFarmaci/SchermataListaFarmaci");
    }


    public void premutoIndietro(String schermataPrecedente) throws IOException {
        App.setRoot(schermataPrecedente);
    }
    public void premutoHome() throws IOException {
        App.setRoot("SchermataPrincipale");
    }
    public void premutoLogout() throws IOException {
        App.setRoot("autenticazione/login/SchermataLogin");
    }

}
