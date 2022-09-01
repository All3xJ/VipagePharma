package com.vipagepharma.corriere.gestioneConsegne.firmaConsegna;

import com.itextpdf.text.DocumentException;
import com.vipagepharma.corriere.entity.Ordine;
import com.vipagepharma.corriere.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
        this.testo_riepilogo_ordine.setText("Consegna n. "+ordine.getIdPrenotazione()+ " da consegnare alla farmacia '"+ordine.getNomeFarmaciaConsegna()+"' in data "+ordine.getDataConsegna());

    }

    public void premeHome(MouseEvent mouseEvent) throws IOException {
    }

    public void premeIndietro(MouseEvent mouseEvent) {
    }

    public void premeLogout(MouseEvent mouseEvent) {
    }

    public void premeFirma(MouseEvent mouseEvent) throws DocumentException, IOException {
        FirmaConsegnaControl.firmConsCtrlRef.premutoFirma(ordine);
    }
}
