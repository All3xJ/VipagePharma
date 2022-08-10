package com.vipagepharma.farmacia.gestionePrenotazioni.ricercaFarmaco;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class SchermataRicercaFarmaco {

    public static String schermataPrecedente;
    @FXML
    void premeLogout(MouseEvent mouseEvent){

    }
    @FXML
    void premeInvio(MouseEvent mouseEvent){

    }

    public void premeIndietro(MouseEvent mouseEvent) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        RicercaFarmacoControl.ricercFarmCtrlRef.premutoIndietro(schermataPrecedente);
    }
}
