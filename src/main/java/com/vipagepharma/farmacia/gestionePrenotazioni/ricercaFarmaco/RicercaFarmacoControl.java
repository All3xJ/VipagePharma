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

    private void riempiObservableList(String nome_o_principio_attivo)  {
        this.farmaci = DBMSBoundary.getFarmaco(nome_o_principio_attivo);
        try {
            while (true) {
                if (!farmaci.next()) break;
                this.tvObservableList.clear();
                this.tvObservableList.add(new Farmaco(farmaci.getString("id_f"),farmaci.getString("nome"),farmaci.getString("principio_attivo")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void premutoInvio(String nome_o_principio_attivo) throws IOException {
        riempiObservableList(nome_o_principio_attivo);
        App.setRoot("gestionePrenotazioni/prenotaFarmaci/SchermataListaFarmaci");
    }


    public void premutoIndietro() throws IOException{
        App.setRoot("SchermataPrincipale");
    }

    public void premutoHome(String schermataPrecedente) throws IOException {
        SchermataPrincipale.schermataPrecedente = schermataPrecedente;
        App.setRoot("SchermataPrincipale");
    }

}
