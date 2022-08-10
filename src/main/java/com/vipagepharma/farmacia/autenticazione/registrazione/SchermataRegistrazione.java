package com.vipagepharma.farmacia.autenticazione.registrazione;

import java.io.IOException;

import com.vipagepharma.farmacia.autenticazione.logout.LogoutControl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class SchermataRegistrazione{

    @FXML
    private TextField nome;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confermaPassword;
    private final RegistrazioneControl registrazioneControl = RegistrazioneControl.regCtrlRef;
    @FXML
    void premeRegistra(MouseEvent event) throws IOException{
        registrazioneControl.premutoRegistra(this.nome.getText(),this.email.getText(),this.password.getText(),this.confermaPassword.getText());
    }
    @FXML
    void premeOK(MouseEvent event) throws IOException{
        registrazioneControl.premutoOk("autenticazione/registrazione/SchermataRegistrazione");
    }

    @FXML
    void premeIndietro(MouseEvent event) throws IOException{
        RegistrazioneControl.regCtrlRef.premutoIndietro();
    }
}



