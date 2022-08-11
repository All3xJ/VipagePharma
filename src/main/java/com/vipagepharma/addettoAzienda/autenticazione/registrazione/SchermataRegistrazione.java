package com.vipagepharma.addettoAzienda.autenticazione.registrazione;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;


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
    void premeRegistra(MouseEvent event) throws Exception{
        registrazioneControl.premutoRegistra(this.nome.getText(),this.email.getText(),this.password.getText(),this.confermaPassword.getText());
    }

    @FXML
    void premeHome(MouseEvent event) throws IOException{

    }
    @FXML
    void premeLogout(MouseEvent event) throws IOException{

    }
    @FXML
    void premeIndietro(MouseEvent event) throws IOException{

    }
}



