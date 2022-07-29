package com.vipagepharma.farmacia.autenticazione.login;

import com.vipagepharma.farmacia.App;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class LoginControl {
    private TextField id;
    private TextField pass;

    public LoginControl(TextField id,TextField pass){
        this.id = id;
        this.pass = pass;
    }

    public void start() throws IOException {
        App.setRoot("SchermataPrincipale");
        System.out.println("ciao");
    }
}
