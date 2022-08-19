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
        String idFarmaco = prenotazione.getIdFarmaco();
        ResultSet lotti = DBMSBoundary.getLotti(idFarmaco);
        ArrayList<String> qtyLotti = new ArrayList<>();
        ArrayList<String> idLotti = new ArrayList<>();
        while(lotti.next()){
            qtyLotti.add(lotti.getString("qty"));
            idLotti.add(lotti.getString("id_l"));
        }
        lotti.close();
        DBMSBoundary.eliminaOrdineERicaricaFarmaci(idPrenotazione,idLotti,qtyLotti);
        App.newWind("gestionePrenotazioni/annullaPrenotazioni/AvvisoAnnullaPrenotazione",event);

    }
    @FXML
    public void premutoNo(){
        App.popup_stage.close();
    }

    public void premutoOk() throws SQLException, IOException {
        App.popup_stage.close();
        VisualizzaPrenotazioniControl.visualPrenCtrlRef.riempiObservableList(prenotazione.getIdFarmacia());
        App.setRoot("gestionePrenotazioni/visualizzaPrenotazioni/SchermataElencoPrenotazioni");
    }

}
