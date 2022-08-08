package com.vipagepharma.farmacia.autenticazione.registrazione;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.w3c.dom.Text;

public class SchermataRegistrazione{
    @FXML
    private TextField nome;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confermaPassword;

    @FXML
    void premutoRegistra(MouseEvent event) throws IOException{
        RegistrazioneControl registrazioneControl = RegistrazioneControl.regCtrlRef;
        registrazioneControl.setDati(nome.getText(),email.getText(),password.getText(),confermaPassword.getText());
        registrazioneControl.checkPass();
    }
    @FXML
    void premutoHome(MouseEvent event) throws IOException{

    }
    @FXML
    void premutoLogout(MouseEvent event) throws IOException{

    }
    @FXML
    void premutoIndietro(MouseEvent event) throws IOException{

    }
}



