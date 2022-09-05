package com.vipagepharma.addettoAzienda.gestioneConsegne.risoluzioneProblemaConsegna;

import com.vipagepharma.addettoAzienda.gestioneConsegne.visualizzaSegnalazioni.VisualizzaSegnalazioniControl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SchermataProblemaOrdine implements Initializable {

    @FXML
    private Text testo_problema_ordine;

    @Override
    public void initialize(URL url, ResourceBundle resbound) {
        this.testo_problema_ordine.setText("La consegna n. "+RisoluzioneProblemaConsegnaControl.risProbConsCtrlRef.consegna.idOrdine.get()+ " prevista per la farmacia '"+RisoluzioneProblemaConsegnaControl.risProbConsCtrlRef.consegna.nomeFarmacia.get()+"' ha avuto qualche problema!");

    }

    @FXML
    void premeCompletaOrdine(MouseEvent event) throws SQLException, IOException {
        RisoluzioneProblemaConsegnaControl.risProbConsCtrlRef.premutoCompletaOrdine(event);
    }

    @FXML
    void premeRimborsa(MouseEvent event) throws SQLException, IOException {
        RisoluzioneProblemaConsegnaControl.risProbConsCtrlRef.premutoRimborsa(event);
    }
    public void premeHome(MouseEvent mouseEvent) throws IOException {
        RisoluzioneProblemaConsegnaControl.risProbConsCtrlRef.premutoHome();
    }
    @FXML
    public void premeIndietro(MouseEvent mouseEvent) throws IOException {
        RisoluzioneProblemaConsegnaControl.risProbConsCtrlRef.premutoIndietro("gestioneConsegne/visualizzaSegnalazioni/SchermataElencoSegnalazioni");
    }
    @FXML
    public void premeLogout(MouseEvent mouseEvent) throws IOException {
        RisoluzioneProblemaConsegnaControl.risProbConsCtrlRef.premutoLogout();
    }
}
