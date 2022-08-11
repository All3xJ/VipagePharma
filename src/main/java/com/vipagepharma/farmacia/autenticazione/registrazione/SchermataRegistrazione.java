package com.vipagepharma.farmacia.autenticazione.registrazione;

import java.io.IOException;
import java.sql.SQLException;

import com.vipagepharma.farmacia.autenticazione.logout.LogoutControl;
import javafx.event.ActionEvent;
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
    private PasswordField conferma_password;
    private final RegistrazioneControl registrazioneControl = RegistrazioneControl.regCtrlRef;
    @FXML
    void premeRegistra(ActionEvent event) throws IOException, SQLException {
        registrazioneControl.premutoRegistra(this.nome.getText(),this.email.getText(),this.password.getText(),this.conferma_password.getText(),event);
    }

    @FXML
    void premeIndietro(MouseEvent event) throws IOException{
        RegistrazioneControl.regCtrlRef.premutoIndietro();
    }
}



