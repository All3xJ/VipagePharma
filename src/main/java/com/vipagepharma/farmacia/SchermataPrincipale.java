package com.vipagepharma.farmacia;

import java.io.IOException;

import com.vipagepharma.farmacia.autenticazione.login.LoginControl;
import com.vipagepharma.farmacia.gestionePrenotazioni.visualizzaPrenotazioni.VisualizzaPrenotazioniControl;
import com.vipagepharma.farmacia.gestionePrenotazioni.ricercaFarmaco.RicercaFarmacoControl;
import com.vipagepharma.farmacia.gestionePrenotazioni.modificaContratti.ModificaContrattiControl;
import com.vipagepharma.farmacia.gestioneFarmaci.scaricoFarmaci.ScaricoFarmaciControl;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class SchermataPrincipale {

	@FXML
    void premeVisualizzaPrenotazioni(MouseEvent event) throws IOException{

		VisualizzaPrenotazioniControl visualprenCtrl = new VisualizzaPrenotazioniControl();
		visualprenCtrl.start();
		System.out.println("test per vedere se dopo che si va setRoot effettivamente continua a fare robe oppure proprio flusso è cambiato");	// risposta: SI LO PRINTA SUBITO, QUINDI È COME SE FACESSE THREAD
	}
	@FXML
	void premeRicercaFarmaci(MouseEvent event) throws IOException{
		RicercaFarmacoControl ricfarmCtrl = new RicercaFarmacoControl();
		ricfarmCtrl.start();
		System.out.println("test per vedere se dopo che si va setRoot effettivamente continua a fare robe oppure proprio flusso è cambiato");	// risposta: SI LO PRINTA SUBITO, QUINDI È COME SE FACESSE THREAD
	}

	@FXML
	void premeScaricaFarmaci(MouseEvent event) throws IOException{
		ScaricoFarmaciControl scarfarmCtrl = new ScaricoFarmaciControl();
		scarfarmCtrl.start();
		System.out.println("test per vedere se dopo che si va setRoot effettivamente continua a fare robe oppure proprio flusso è cambiato");	// risposta: SI LO PRINTA SUBITO, QUINDI È COME SE FACESSE THREAD
	}

	@FXML
	void premeModificaContratti(MouseEvent event) throws IOException{
		ModificaContrattiControl modcontraCtrl = new ModificaContrattiControl();
		modcontraCtrl.start();
		System.out.println("test per vedere se dopo che si va setRoot effettivamente continua a fare robe oppure proprio flusso è cambiato");	// risposta: SI LO PRINTA SUBITO, QUINDI È COME SE FACESSE THREAD
	}

}
