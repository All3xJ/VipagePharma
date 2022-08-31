package com.vipagepharma.farmacia;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.vipagepharma.farmacia.gestionePrenotazioni.visualizzaPrenotazioni.VisualizzaPrenotazioniControl;
import com.vipagepharma.farmacia.gestionePrenotazioni.ricercaFarmaco.RicercaFarmacoControl;
import com.vipagepharma.farmacia.gestionePrenotazioni.modificaContratti.ModificaContrattiControl;
import com.vipagepharma.farmacia.gestioneFarmaci.scaricoFarmaci.ScaricoFarmaciControl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class SchermataPrincipale {

	@FXML
	private Button btnIndietro;

	@FXML
    void premeVisualizzaPrenotazioni(MouseEvent event) throws IOException, SQLException {
		VisualizzaPrenotazioniControl visualprenCtrl = new VisualizzaPrenotazioniControl();
		visualprenCtrl.start();
	}
	public void premePrenotaFarmaci(MouseEvent mouseEvent) throws IOException {
		RicercaFarmacoControl ricfarmCtrl = new RicercaFarmacoControl();
		ricfarmCtrl.start();
	}

	@FXML
	void premeScaricaFarmaci(MouseEvent event) throws IOException, SQLException {
		ScaricoFarmaciControl scarfarmCtrl = new ScaricoFarmaciControl();
		scarfarmCtrl.start();
	}

	@FXML
	void premeModificaContratti(MouseEvent event) throws IOException, SQLException {
		ModificaContrattiControl modcontraCtrl = new ModificaContrattiControl();
		modcontraCtrl.start();
	}
	@FXML
	public void premeLogout(MouseEvent mouseEvent) throws IOException {
		App.setRoot("autenticazione/login/SchermataLogin");
	}
}
