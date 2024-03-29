package com.vipagepharma.farmacia.autenticazione.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.autenticazione.registrazione.RegistrazioneControl;
import com.vipagepharma.farmacia.autenticazione.reimpostaPassword.ReimpostaPasswordControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SchermataLogin implements Initializable {

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;


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
