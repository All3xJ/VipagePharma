package com.vipagepharma.farmacia.gestionePrenotazioni.ricercaFarmaco;

import com.vipagepharma.farmacia.autenticazione.logout.LogoutControl;
import com.vipagepharma.farmacia.gestioneFarmaci.scaricoFarmaci.ScaricoFarmaciControl;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.io.IOException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class SchermataRicercaFarmaco {

    @FXML
    TextField nome_o_principio_attivo;

    @FXML
    public void premeInvio(MouseEvent mouseEvent) throws IOException {
        RicercaFarmacoControl.getControl().premutoInvio(this.nome_o_principio_attivo.getText());
    }

    public void premeIndietro(MouseEvent mouseEvent) throws IOException{
        RicercaFarmacoControl.ricercFarmCtrlRef.premutoIndietro();
    }

    public void premeHome(MouseEvent mouseEvent) throws IOException {
        RicercaFarmacoControl.ricercFarmCtrlRef.premutoHome("gestionePrenotazioni/ricercaFarmaco/SchermataRicercaFarmaco");
    }


    @FXML
    public void premeLogout(MouseEvent mouseEvent) throws IOException {
        LogoutControl.start();
    }
}
