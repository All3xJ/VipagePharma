package com.vipagepharma.corriere.gestioneConsegne.firmaConsegna;

import com.itextpdf.text.DocumentException;
import com.vipagepharma.corriere.entity.Ordine;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SchermataRiepilogoOrdine implements Initializable {

    public static Ordine ordine;

    @FXML
    private Text testo_riepilogo_ordine;

    @Override
    public void initialize(URL url, ResourceBundle resbound) {
        this.testo_riepilogo_ordine.setText("Firmando si dichiara l'avvenuta consegna dell'ordine \nn. "+ordine.getIdPrenotazione()+ " destinato alla farmacia '"+ordine.getNomeFarmaciaConsegna()+"'\nin data "+ordine.getDataConsegna());

    }
    @FXML
    public void premeHome(MouseEvent mouseEvent) throws IOException {
        FirmaConsegnaControl.firmConsCtrlRef.premutoHome();
    }
    @FXML
    public void premeIndietro(MouseEvent mouseEvent) throws IOException {
        FirmaConsegnaControl.firmConsCtrlRef.premutoIndietro("gestioneConsegne/visualizzaConsegne/SchermataConsegneOdierne");
    }
    @FXML
    public void premeLogout(MouseEvent mouseEvent) throws IOException {
        FirmaConsegnaControl.firmConsCtrlRef.premutoLogout();
    }

    @FXML
    public void premeFirma(MouseEvent mouseEvent) throws DocumentException, IOException {
        FirmaConsegnaControl.firmConsCtrlRef.premutoFirma(ordine);
    }
}
