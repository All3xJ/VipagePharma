package com.vipagepharma.corriere;

import com.vipagepharma.farmacia.autenticazione.logout.LogoutControl;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SchermataPrincipale {

    public static String schermataPrecedente;


    public void premeDownloadConsegne(MouseEvent mouseEvent) {
    }

    public void premeVisualizzaConsegne(MouseEvent mouseEvent) {
    }

    public void premeUploadFirme(MouseEvent mouseEvent) {
    }

    public void premeHome(MouseEvent mouseEvent)  {
    }

    public void premeIndietro(MouseEvent mouseEvent) {

    }

    public void premeLogout(MouseEvent mouseEvent) throws IOException {
        LogoutControl.start();
    }
}
