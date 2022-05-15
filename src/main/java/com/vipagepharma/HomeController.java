package com.vipagepharma;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class HomeController {

	@FXML
    void onVisualizzaPrenotazioniClicked(MouseEvent event) throws IOException{
        App.setRoot("ListaPrenotazioni");	// ho messo nel controller di ListaPrenotazioni un initialize che letteralmente esegue roba che voglio
    }

}
