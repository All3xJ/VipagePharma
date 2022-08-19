package com.vipagepharma.farmacia.gestionePrenotazioni.annullaPrenotazione;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.DBMSBoundary;
import com.vipagepharma.farmacia.entity.Prenotazione;
import com.vipagepharma.farmacia.gestionePrenotazioni.visualizzaPrenotazioni.VisualizzaPrenotazioniControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnnullaPrenotazioneControl {

    public static AnnullaPrenotazioneControl annPrenCtrl;
    private Prenotazione prenotazione;

    public AnnullaPrenotazioneControl(Prenotazione prenotazione){
        this.prenotazione = prenotazione;
        annPrenCtrl = this;
    }

    public void start(ActionEvent event) throws IOException {
        App.newWind("gestionePrenotazioni/annullaPrenotazione/AvvisoAnnullaPrenotazione",event);
    }
    @FXML
    public void premutoSi(MouseEvent event) throws SQLException, IOException {
        String idPrenotazione = prenotazione.getIdPrenotazione();
        ResultSet lottiOrdinati = DBMSBoundary.getLottiOrdinati(idPrenotazione);
        ArrayList<String> qtyLotti = new ArrayList<>();
        ArrayList<String> idLotti = new ArrayList<>();
        while(lottiOrdinati.next()){
            qtyLotti.add(lottiOrdinati.getString("qty"));
            idLotti.add(lottiOrdinati.getString("ref_id_l"));
        }
        System.out.println(qtyLotti);
        System.out.println(idLotti);
        DBMSBoundary.eliminaOrdineERicaricaFarmaci(idPrenotazione,idLotti,qtyLotti);
        lottiOrdinati.close();
        App.popup_stage.close();
        App.newWind("gestionePrenotazioni/annullaPrenotazione/AvvisoOperazioneRiuscita",event);

    }
    @FXML
    public void premutoNo(){
        App.popup_stage.close();
    }

    public void premutoOk() throws SQLException, IOException {
        VisualizzaPrenotazioniControl.visualPrenCtrlRef.riempiObservableList(prenotazione.getIdFarmacia());
        App.popup_stage.close();
        App.setRoot("gestionePrenotazioni/visualizzaPrenotazioni/SchermataElencoPrenotazioni");
    }

}
