package com.vipagepharma.farmacia.gestionePrenotazioni.modificaContratti;

import com.vipagepharma.farmacia.entity.Contratto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SchermataRiepilogoContratto implements Initializable {


    @FXML
    private TextField qty_text;

    @FXML
    private Text testo_riepilogo_contratto;

    public static Contratto contratto;

    @Override
    public void initialize(URL url, ResourceBundle resbound) {
        this.testo_riepilogo_contratto.setText("Il contratto attualmente selezionato è del farmaco "+contratto.nomeFarmaco.get() + " con principio attivo "+contratto.prinicipioAttivo+" con quantità settimanale di "+contratto.qtySettimanale.get());
    }

    @FXML
    void premeConferma(MouseEvent event) throws IOException {
        ModificaContrattiControl.modifContraCtrl.premutoConferma(this.qty_text.getText(),contratto);
    }

    public void premeLogout(MouseEvent mouseEvent) {
    }

    public void premeHome(MouseEvent mouseEvent) throws IOException {
        ModificaContrattiControl.modifContraCtrl.premutoHome("gestionePrenotazioni/modificaContratti/SchermataRiepilogoContratto");
    }

    public void premeIndietro(MouseEvent mouseEvent) throws IOException {
        ModificaContrattiControl.modifContraCtrl.premutoIndietro("gestionePrenotazioni/modificaContratti/SchermataModificaContratti");
    }
}
