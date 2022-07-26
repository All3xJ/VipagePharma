package com.vipagepharma.farmacia;

import java.io.IOException;

import com.vipagepharma.farmacia.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SchermataLogin{

	@FXML
	private TextField username;	// il nome della variabile deve essere lo stesso dell'FXXXXXXID di Scene Builder

	@FXML
	private TextField password;

	@FXML
	private Button login;

	@FXML
    void onLoginClicked(MouseEvent event) throws IOException{	// sono nella schermata di login. se preme pulsante "login" allora:
		System.out.println(username.getText());
		System.out.println(password.getText());
        App.setRoot("SchermataPrincipale"); // se sono giuste le credenziali mi porta alla home
    }

	

}
