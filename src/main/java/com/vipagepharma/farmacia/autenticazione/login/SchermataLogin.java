package com.vipagepharma.farmacia.autenticazione.login;

import java.io.IOException;
import com.vipagepharma.farmacia.autenticazione.registrazione.RegistrazioneControl;
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
		LoginControl logCtrl = new LoginControl(username,password);
		logCtrl.start();
	}

	@FXML
	void onRegistrazioneClicked(MouseEvent event) throws IOException{
		RegistrazioneControl regCtrl = new RegistrazioneControl();
		regCtrl.start();
	}
}
