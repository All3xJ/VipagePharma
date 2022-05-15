package com.vipagepharma;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController{

	@FXML
	private TextField username;	// il nome della variabile deve essere lo stesso dell'FXXXXXXID di Scene Builder

	@FXML
	private TextField password;

	@FXML
	private Button login;

	@FXML
    void onLoginClicked(MouseEvent event) throws IOException{
		System.out.println(username.getText());
		System.out.println(password.getText());
        App.setRoot("Home");
    }

	@FXML
    void onVisualizzaPrenotazioniClicked(MouseEvent event) throws IOException{
        App.setRoot("ListaPrenotazioni");	// ho messo nel controller di ListaPrenotazioni (quindi ListaPrenotazioniController) un initialize che letteralmente esegue roba che voglio
    }

}
