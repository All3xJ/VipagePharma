package com.vipagepharma.farmacia.gestionePrenotazioni.ricercaFarmaco;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.SchermataPrincipale;
import com.vipagepharma.farmacia.gestioneFarmaci.scaricoFarmaci.ScaricoFarmaciControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.io.IOException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
        SchermataPrincipale.schermataPrecedente="gestioneFarmaci/ricercaFarmaco/SchermataRicercaFarmaco";
        App.setRoot("SchermataPrincipale");
    }
    @FXML
    public void premeHome(MouseEvent mouseEvent) throws IOException {
        SchermataPrincipale.schermataPrecedente="gestioneFarmaci/ricercaFarmaco/SchermataRicercaFarmaco";
        App.setRoot("SchermataPrincipale");
    }

    @FXML
    public void premeLogout(MouseEvent mouseEvent) throws IOException {
        App.setRoot("autenticazione/login/SchermataLogin");
    }
}
