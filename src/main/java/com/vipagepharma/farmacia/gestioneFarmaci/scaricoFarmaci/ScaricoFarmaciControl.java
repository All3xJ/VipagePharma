package com.vipagepharma.farmacia.gestioneFarmaci.scaricoFarmaci;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.DBMSBoundary;
import com.vipagepharma.farmacia.SchermataPrincipale;
import com.vipagepharma.farmacia.entity.FarmacoScarico;
import com.vipagepharma.farmacia.entity.Utente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

public class ScaricoFarmaciControl {

    public static ScaricoFarmaciControl scarFarmCtrl;
    private LinkedList<FarmacoScarico> farmaci;

    public ObservableList<String> tvObservableList = FXCollections.observableArrayList();
    public ObservableList<String> tvObservableList2 = FXCollections.observableArrayList();

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
        ResultSet inventario = DBMSBoundary.getInventario(Utente.getID());
        try {
            this.tvObservableList.clear();
            while (true) {
                if (!inventario.next()) break;
                if((!tvObservableList.contains(inventario.getString("nome")) && (!inventario.getString("qty").equals("0")))) {
                    this.tvObservableList.add(inventario.getString("nome"));
                    this.farmaci.add(new FarmacoScarico(inventario.getString("ref_id_f"), inventario.getString("ref_id_l"), inventario.getString("nome"), inventario.getString("qty"), inventario.getInt("isBanco")));
                }
                else{
                    if(!inventario.getString("qty").equals("0")) {
                        FarmacoScarico farmaco = getFarmacoScarico(inventario.getString("nome"));
                        farmaco.addIdLotto(inventario.getString("ref_id_l"));
                        farmaco.addQty(inventario.getString("qty"));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        inventario.close();
    }

    public void riempiObservableList2(String nome){
        this.tvObservableList2.clear();
        ArrayList<String> id = this.getFarmacoScarico(nome).getIdLotti();
        for(int i = 0; i < id.size();++i){
            this.tvObservableList2.add(id.get(i));
        }
    }

    public void premutoIndietro() throws IOException {
        App.setRoot(SchermataScarico.schermataPrecedente);
    }

    public void premutoHome(String schermataPrecedente) throws IOException {
        SchermataPrincipale.schermataPrecedente=schermataPrecedente;
        App.setRoot("SchermataPrincipale");
    }

    public void premutoScarica(String nome, String idLotto, String qty, MouseEvent event) throws IOException {
        FarmacoScarico farmaco = this.getFarmacoScarico(nome);
        try {
            if (farmaco.getQtyLotto(idLotto) < Integer.parseInt(qty)) {
                App.newWind("gestioneFarmaci/scaricoFarmaci/AvvisoErroreDati", event);
                return;
            }
        } catch (Exception e) {
            App.newWind("gestioneFarmaci/scaricoFarmaci/AvvisoErroreDati", event);
            return;
        }
        DBMSBoundary.scaricaFarmaci(Utente.getID(),farmaco.getId(),idLotto,qty);
        farmaco.aggiornaQtyRimanente(Integer.parseInt(qty));
        AvvisoScaricoRiuscito.farmaco = farmaco;
        App.newWind("gestioneFarmaci/scaricoFarmaci/AvvisoScaricoRiuscito",event);
    }
    public void premutoOk() throws IOException {
        App.popup_stage.close();
        App.setRoot("gestioneFarmaci/scaricoFarmaci/SchermataScarico");
    }
    public FarmacoScarico getFarmacoScarico(String nome){
        FarmacoScarico farmaco = null;
        for (int i = 0; i < this.farmaci.size(); ++i) {
            if (farmaco == null) {
                farmaco = this.farmaci.get(i).getFarmacoScarico(nome);
            } else {
                break;
            }
        }
        return farmaco;
    }
}
