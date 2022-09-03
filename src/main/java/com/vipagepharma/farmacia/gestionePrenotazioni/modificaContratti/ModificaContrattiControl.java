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

    public void riempiObservableList(String IDFarmacia) throws SQLException, IOException {
        this.contratti = DBMSBoundary.getContratti(IDFarmacia);
        try {
            this.tvObservableList.clear();
            while (true) {
                if (!contratti.next()) break;
                this.tvObservableList.add(new Contratto(contratti.getString("nome"),contratti.getString("quantita_settimanale"),contratti.getString("id_farmaco"),contratti.getString("principio_attivo")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //this.contratti.close();
    }

    public void premutoConferma(String qty, Contratto contratto) throws IOException {
        DBMSBoundary.aggiornaContratto(contratto.getIDFarmaco(),Utente.getID(),qty);
        App.newWind("gestionePrenotazioni/modificaContratti/AvvisoOperazioneRiuscita");
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

    public void premutoOk() throws IOException {
        App.popup_stage.close();
        App.setRoot("SchermataPrincipale");
    }

    public void premutoModifica(Contratto contr){
        SchermataRiepilogoContratto.contratto = contr;
    }
}
