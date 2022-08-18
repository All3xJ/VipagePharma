package com.vipagepharma.addettoAzienda;

import com.vipagepharma.addettoAzienda.gestioneConsegne.visualizzaSegnalazioni.VisualizzaSegnalazioniControl;
import com.vipagepharma.addettoAzienda.gestioneConsegne.visualizzaStoricoConsegne.VisualizzaStoricoConsegneControl;
import com.vipagepharma.addettoAzienda.App;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;

public class SchermataPrincipale {

    public static String schermataPrecedente;

	public void premeVisualizzaSegnalazioni(MouseEvent mouseEvent) throws IOException, SQLException {
        VisualizzaSegnalazioniControl visConseCtrl = new VisualizzaSegnalazioniControl();
        visConseCtrl.start();
    }

    public void premeVisualizzaStorico(MouseEvent mouseEvent) throws IOException, SQLException {
        VisualizzaStoricoConsegneControl visStoConsCtrl = new VisualizzaStoricoConsegneControl();
        visStoConsCtrl.start();
    }

    public void premeHome(MouseEvent mouseEvent)  {
    }

    public void premeIndietro(MouseEvent mouseEvent) {

    }

    public void premeLogout(MouseEvent mouseEvent) throws IOException {
        App.setRoot("autenticazione/login/SchermataLogin");
    }
}
