package com.vipagepharma;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SchermataPrincipale {

	@FXML
    void onVisualizzaPrenotazioniClicked(MouseEvent event) throws IOException{
        App.setRoot("SchermataElencoPrenotazioni");	// ho messo nel controller di ListaPrenotazioni (quindi ListaPrenotazioniController) un initialize che letteralmente esegue roba che voglio. questo perchè inizialmente volevo fare tutto in questo controller una volta che si fa setRoot si SMINCHIANO TUTTE LE VARIBAILI DI QUESTO OGGETTO.... È COME SE SI RICARICASSE TUTTO AHAHAHAHAHAH QUINDI È TUTTO NULL E QUINDI NULLPOINTEREXCEPTION DIOCRISTO. DEVO SEPARARE I CONTROLLER DIOBONO DI DIO STANTO.
		System.out.println("test per vedere se dopo che si va setRoot effettivamente continua a fare robe oppure proprio flusso è cambiato");	// risposta: SI LO PRINTA SUBITO, QUINDI È COME SE FACESSE THREAD
	}

}
