package com.vipagepharma.corriere.autenticazione.login;

import com.vipagepharma.corriere.autenticazione.registrazione.RegistrazioneControl;
import com.vipagepharma.corriere.autenticazione.reimpostaPassword.ReimpostaPasswordControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SchermataLogin implements Initializable {

	@FXML
	private TextField username;  // il nome della variabile deve essere lo stesso dell'FXXXXXXID di Scene Builder

	@FXML
	private PasswordField password;

	@FXML
	private Button login;

	@Override
	public void initialize(URL url, ResourceBundle resbound){
		username.setStyle("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);");
		password.setStyle("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);");
	}

	@FXML
    void premeLogin(ActionEvent event) throws IOException{
		System.out.println(username.getText());
		System.out.println(password.getText());
		LoginControl logCtrl = new LoginControl(username,password);
		logCtrl.start(event);
    }

	@FXML
	void premeRegistrati(MouseEvent event) throws IOException{
		RegistrazioneControl regCtrl = new RegistrazioneControl();
		regCtrl.start();
	}

	@FXML
	void premeReimpostaPassword(MouseEvent event) throws IOException{
		ReimpostaPasswordControl repassCtrl = new ReimpostaPasswordControl();
		repassCtrl.start();
	}
}
