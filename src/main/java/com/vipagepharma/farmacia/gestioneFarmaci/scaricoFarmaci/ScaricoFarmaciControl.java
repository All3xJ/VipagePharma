package com.vipagepharma.farmacia.gestioneFarmaci.scaricoFarmaci;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.DBMSBoundary;
import com.vipagepharma.farmacia.SchermataPrincipale;
import com.vipagepharma.farmacia.entity.Farmaco;
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
    private LinkedList<Farmaco> farmaci;

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
                if((!tvObservableList.contains(inventario.getString("nome")) && (!inventario.getString("quantita").equals("0")))) {
                    this.tvObservableList.add(inventario.getString("nome"));
                    this.farmaci.add(new Farmaco(inventario.getString("id_farmaco"), inventario.getString("id_lotto"), inventario.getString("nome"), inventario.getString("quantita"), inventario.getInt("isBanco")));
                }
                else{
                    if(!inventario.getString("quantita").equals("0")) {
                        Farmaco farmaco = getFarmaco(inventario.getString("nome"));
                        farmaco.addIdLotto(inventario.getString("id_lotto"));
                        farmaco.addQty(inventario.getString("quantita"));
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
        ArrayList<String> id = this.getFarmaco(nome).getIdLotti();
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
        Farmaco farmaco = this.getFarmaco(nome);
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
    public Farmaco getFarmaco(String nome){
        Farmaco farmaco = null;
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
