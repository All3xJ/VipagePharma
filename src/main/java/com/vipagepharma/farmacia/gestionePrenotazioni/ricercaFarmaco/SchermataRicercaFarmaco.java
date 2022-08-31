package com.vipagepharma.farmacia.gestionePrenotazioni.ricercaFarmaco;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;

public class SchermataRicercaFarmaco {

    @FXML
    TextField nome_o_principio_attivo;

    @FXML
    public void premeInvio(ActionEvent event) throws IOException, SQLException {
        RicercaFarmacoControl.getControl().premutoInvio(this.nome_o_principio_attivo.getText());
    }
    @FXML
    public void premeIndietro(MouseEvent mouseEvent) throws IOException{
        RicercaFarmacoControl.getControl().premutoIndietro("SchermataPrincipale");
    }
    @FXML
    public void premeHome(MouseEvent mouseEvent) throws IOException {
        RicercaFarmacoControl.getControl().premutoHome();
    }

    @FXML
    public void premeLogout(MouseEvent mouseEvent) throws IOException {
        RicercaFarmacoControl.getControl().premutoLogout();
    }
}
