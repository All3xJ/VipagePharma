package com.vipagepharma.farmacia.gestionePrenotazioni.modificaContratti;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.DBMSBoundary;
import com.vipagepharma.farmacia.SchermataPrincipale;
import com.vipagepharma.farmacia.entity.Contratto;
import com.vipagepharma.farmacia.entity.Prenotazione;
import com.vipagepharma.farmacia.entity.Utente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModificaContrattiControl {

    public static ModificaContrattiControl modifContraCtrl;

    public ObservableList<Contratto> tvObservableList = FXCollections.observableArrayList();

    public ResultSet contratti = null;

    public ModificaContrattiControl(){
        modifContraCtrl = this;
    }

    public void start() throws IOException, SQLException {
        this.riempiObservableList(Utente.getID());
        App.setRoot("gestionePrenotazioni/modificaContratti/SchermataModificaContratti");
    }

    public void riempiObservableList(String IDFarmacia) throws SQLException {
        this.contratti = DBMSBoundary.getContratti(IDFarmacia);
        try {
            this.tvObservableList.clear();
            while (true) {
                if (!contratti.next()) break;
                this.tvObservableList.add(new Contratto(contratti.getString("nome"),contratti.getString("qty_settimanale"),contratti.getString("ref_id_f"),contratti.getString("principio_attivo")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.contratti.close();
    }

    public void premutoConferma(String qty, Contratto contratto) throws IOException {
        DBMSBoundary.aggiornaContratto(contratto.getIDFarmaco(),Utente.getID(),qty);
        App.newWind("gestionePrenotazioni/modificaContratti/AvvisoOperazioneRiuscita");
    }

    public void premutoIndietro(String schermataPrecedente) throws IOException {
        App.setRoot(schermataPrecedente);
    }

    public void premutoHome(String schermataPrecedente) throws IOException {
        SchermataPrincipale.schermataPrecedente=schermataPrecedente;
        App.setRoot("SchermataPrincipale");
    }

    public void premutoOk() throws IOException {
        App.popup_stage.close();
        App.setRoot("SchermataPrincipale");
        SchermataPrincipale.schermataPrecedente="gestionePrenotazioni/modificaContratti/SchermataModificaContratti";
    }
}
