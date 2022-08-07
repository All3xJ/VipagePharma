package com.vipagepharma.corriere;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SchermataPrincipale {

	@FXML
    void premutoVisualizzaPrenotazioni(MouseEvent event) throws IOException{
        App.setRoot("SchermataElencoPrenotazioni");
	}

}
