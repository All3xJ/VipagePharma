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
        App.newWind("gestioneConsegne/risoluzioneProblemaConsegna/AvvisoOperazioneRiuscita",event);
        // la flag di fix problema lo fa al click di ok di questo avviso
    }

    public void premutoCompletaOrdine(MouseEvent event) throws SQLException, IOException {
        ResultSet lottiNonConsegnati = DBMSBoundary.getLottiNonConsegnati(this.consegna);
        ResultSet corrieri = DBMSBoundary.getCorrieri();
        int corriere = this.scegliCorriere(corrieri);
        ResultSet newprenotazione = DBMSBoundary.creaOrdine(consegna.getIdFarmacia(),corriere,consegna.idFarmaco,lottiNonConsegnati);
        if (newprenotazione.next()) {
            int idprenotazione = newprenotazione.getInt("id_prenotazione");
            DBMSBoundary.aggiornaLottiOrdinati(idprenotazione,lottiNonConsegnati,this.consegna.idOrdine.get());
            App.newWind("gestioneConsegne/risoluzioneProblemaConsegna/AvvisoOperazioneRiuscita",event);
            // la flag di fix problema lo fa al click di ok di questo avviso
        }
    }

    public void premutoOk() throws IOException {
        DBMSBoundary.setFlagProblema(this.consegna.idOrdine.get(),0);
        SchermataPrincipale.schermataPrecedente="gestioneConsegne/visualizzaSegnalazioni/SchermataElencoSegnalazioni";
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
        corrieri.close();
        return corrieri.getInt("id_utente_azienda");
    }
}
