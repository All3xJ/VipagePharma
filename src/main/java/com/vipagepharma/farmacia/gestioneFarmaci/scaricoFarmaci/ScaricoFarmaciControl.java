package com.vipagepharma.farmacia.gestioneFarmaci.scaricoFarmaci;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.DBMSBoundary;
import com.vipagepharma.farmacia.SchermataPrincipale;
import com.vipagepharma.farmacia.entity.Farmaco;
import com.vipagepharma.farmacia.entity.FarmacoScarico;
import com.vipagepharma.farmacia.entity.Prenotazione;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;

public class ScaricoFarmaciControl {

    public static ScaricoFarmaciControl scarFarmCtrl;
    LinkedList<FarmacoScarico> farmaci;

    public ObservableList<String> tvObservableList = FXCollections.observableArrayList();

    public ScaricoFarmaciControl(){
        scarFarmCtrl = this;
    }

    public void start() throws IOException, SQLException {
        farmaci = new LinkedList<>();
        SchermataScarico.schermataPrecedente = "SchermataPrincipale";
        this.riempiObservableList();
        App.setRoot("gestioneFarmaci/scaricoFarmaci/SchermataScarico");
    }

    public void riempiObservableList() throws SQLException {
        ResultSet inventario = DBMSBoundary.getInventario();
        try {
            this.tvObservableList.clear();
            while (true) {
                if (!inventario.next()) break;
                if(!tvObservableList.contains(inventario.getString("nome"))) {
                    this.tvObservableList.add(inventario.getString("nome"));
                    this.farmaci.add(new FarmacoScarico(inventario.getString("ref_id_f"), inventario.getString("ref_id_l"), inventario.getString("nome"), inventario.getString("qty"), inventario.getInt("isBanco")));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        inventario.close();
    }

    public void premutoIndietro() throws IOException {
        App.setRoot(SchermataScarico.schermataPrecedente);
    }

    public void premutoHome(String schermataPrecedente) throws IOException {
        SchermataPrincipale.schermataPrecedente=schermataPrecedente;
        App.setRoot("SchermataPrincipale");
    }

    public void premutoScarica(String nome, String qty, LocalDate data, MouseEvent event) throws IOException {
        FarmacoScarico farmaco = null;
        for (int i = 0; i < farmaci.size(); ++i) {
            if (farmaco == null) {
                farmaco = farmaci.get(i).getFarmacoScarico(nome);
            } else {
                break;
            }
        }
        try {
            if (Integer.parseInt(farmaco.getQty()) < Integer.parseInt(qty)) {
                App.newWind("gestioneFarmaci/scaricoFarmaci/AvvisoErroreDati", event);
            }
            System.out.println(farmaco.getId() + "\n" + qty + "\n" + data);
        } catch (Exception e) {
            App.newWind("gestioneFarmaci/scaricoFarmaci/AvvisoErroreDati", event);
        }

    }
        public void premutoOk() throws IOException {
            App.popup_stage.close();
            App.setRoot("gestioneFarmaci/scaricoFarmaci/SchermataScarico");
        }
}
