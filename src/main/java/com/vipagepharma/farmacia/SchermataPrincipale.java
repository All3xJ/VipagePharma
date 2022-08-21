package com.vipagepharma.farmacia;

import java.io.IOException;
import java.sql.SQLException;

import com.vipagepharma.farmacia.gestionePrenotazioni.visualizzaPrenotazioni.VisualizzaPrenotazioniControl;
import com.vipagepharma.farmacia.gestionePrenotazioni.ricercaFarmaco.RicercaFarmacoControl;
import com.vipagepharma.farmacia.gestionePrenotazioni.modificaContratti.ModificaContrattiControl;
import com.vipagepharma.farmacia.gestioneFarmaci.scaricoFarmaci.ScaricoFarmaciControl;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class SchermataPrincipale {

	public static String schermataPrecedente;

	@FXML
    void premeVisualizzaPrenotazioni(MouseEvent event) throws IOException, SQLException {
		VisualizzaPrenotazioniControl visualprenCtrl = new VisualizzaPrenotazioniControl();
		visualprenCtrl.start();
	}
	@FXML
	void premeRicercaFarmaci(MouseEvent event) throws IOException{
		RicercaFarmacoControl ricfarmCtrl = new RicercaFarmacoControl();
		ricfarmCtrl.start();
	}

	@FXML
	void premeScaricaFarmaci(MouseEvent event) throws IOException{
		ScaricoFarmaciControl scarfarmCtrl = new ScaricoFarmaciControl();
		scarfarmCtrl.start();
	}

	@FXML
	void premeModificaContratti(MouseEvent event) throws IOException, SQLException {
		ModificaContrattiControl modcontraCtrl = new ModificaContrattiControl();
		modcontraCtrl.start();
	}

    public void premeHome(MouseEvent mouseEvent){
    }

	public void premeIndietro(MouseEvent mouseEvent) throws IOException {
		App.setRoot(schermataPrecedente);
	}

	public void premeLogout(MouseEvent mouseEvent) throws IOException {
		App.setRoot("autenticazione/login/SchermataLogin");
	}
}
