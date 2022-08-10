package com.vipagepharma.farmacia.gestionePrenotazioni.ricercaFarmaco;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.io.IOException;

public class SchermataRicercaFarmaco {
    @FXML
    TextField nome_o_principio_attivo;

    @FXML
    public void premeLogout(MouseEvent mouseEvent) {
    }
    @FXML
    public void premeInvio(MouseEvent mouseEvent) throws IOException {
        RicercaFarmacoControl.getControl().premutoInvio(this.nome_o_principio_attivo.getText());
    }
}
