package com.vipagepharma.addettoAzienda;

import com.vipagepharma.addettoAzienda.gestioneConsegne.visualizzaSegnalazioni.VisualizzaSegnalazioniControl;
import com.vipagepharma.addettoAzienda.autenticazione.logout.LogoutControl;
import com.vipagepharma.addettoAzienda.gestioneConsegne.visualizzaStoricoConsegne.VisualizzaStoricoConsegneControl;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SchermataPrincipale {

    public static String schermataPrecedente;

	public void premeVisualizzaSegnalazioni(MouseEvent mouseEvent) throws IOException {
        VisualizzaSegnalazioniControl visConseCtrl = new VisualizzaSegnalazioniControl();
        visConseCtrl.start();
    }

    public void premeVisualizzaStorico(MouseEvent mouseEvent) throws IOException {
        VisualizzaStoricoConsegneControl visStoConsCtrl = new VisualizzaStoricoConsegneControl();
        visStoConsCtrl.start();
    }

    public void premeHome(MouseEvent mouseEvent)  {
    }

    public void premeIndietro(MouseEvent mouseEvent) {

    }

    public void premeLogout(MouseEvent mouseEvent) throws IOException {
        LogoutControl.start();
    }
}
