package com.vipagepharma.addettoAzienda.gestioneConsegne.risoluzioneProblemaConsegna;

import com.vipagepharma.addettoAzienda.App;
import com.vipagepharma.addettoAzienda.DBMSBoundary;
import com.vipagepharma.addettoAzienda.SchermataPrincipale;
import com.vipagepharma.addettoAzienda.entity.Consegna;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;


public class RisoluzioneProblemaConsegnaControl {

    public static RisoluzioneProblemaConsegnaControl risProbConsCtrlRef;

    public Consegna consegna;

    public RisoluzioneProblemaConsegnaControl(Consegna consegna){
        this.consegna=consegna;
        risProbConsCtrlRef=this;
    }

    public void start() throws IOException {
        App.setRoot("gestioneConsegne/risoluzioneProblemaConsegna/SchermataProblemaOrdine");
    }

    public void premutoRimborsa(MouseEvent event) throws SQLException, IOException {
        ResultSet lottiNonConsegnati = DBMSBoundary.getLottiNonConsegnati(this.consegna);
        DBMSBoundary.carica(lottiNonConsegnati);
        DBMSBoundary.setFlagProblema(this.consegna.idOrdine.get(),0);
        App.newWind("gestioneConsegne/risoluzioneProblemaConsegna/AvvisoOperazioneRiuscita",event);
    }

    public void premutoCompletaOrdine(MouseEvent event) throws SQLException, IOException {
        ResultSet lottiNonConsegnati = DBMSBoundary.getLottiNonConsegnati(this.consegna);
        ResultSet corrieri = DBMSBoundary.getCorrieri();
        int corriere = this.scegliCorriere(corrieri);
        ResultSet newprenotazione = DBMSBoundary.creaOrdine(consegna.getIdFarmacia(),corriere,consegna.idFarmaco,lottiNonConsegnati);
        if (newprenotazione.next()) {
            int idprenotazione = newprenotazione.getInt("id_prenotazione");
            DBMSBoundary.aggiornaLottiOrdinati(idprenotazione,lottiNonConsegnati,this.consegna.idOrdine.get());
            DBMSBoundary.setFlagProblema(this.consegna.idOrdine.get(),0);
            App.newWind("gestioneConsegne/risoluzioneProblemaConsegna/AvvisoOperazioneRiuscita",event);
        }
    }

    public void premutoOk() throws IOException {
        App.setRoot("SchermataPrincipale");
        App.popup_stage.close();
    }

    private int scegliCorriere(ResultSet corrieri) throws SQLException {
        if(corrieri.last()){
            int len = corrieri.getRow();
            int index = ThreadLocalRandom.current().nextInt(1, len + 1);
            if(corrieri.absolute(index)){
                return corrieri.getInt("id_utente_azienda");
            }
        }
        return corrieri.getInt("id_utente_azienda");
    }
    public void premutoHome() throws IOException {
        App.setRoot("SchermataPrincipale");
    }

    public void premutoIndietro(String schermataPrecedente) throws IOException {
        App.setRoot(schermataPrecedente);
    }
    public void premutoLogout() throws IOException {
        App.setRoot("autenticazione/login/SchermataLogin");
    }
}
