package com.vipagepharma.farmacia.autenticazione.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.vipagepharma.farmacia.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class AvvisoErroreLogin implements Initializable{

    public static AvvisoErroreLogin av;
    public static boolean premuto = false;

    @Override
    public void initialize(URL url, ResourceBundle resbound){	// FINALMENTE HO SCOPERTO A CHE SERVE. SERVE PER ESSERE DIOCANEMENTE EVOCATA QUANDO FACCIO setRoot. QUESTO METODO VERRÀ EVOCATO. È UNA SORTA DI MAIN DEL CONTROLLERRR
        av = this;
    }

    synchronized public void premeOk(MouseEvent event) throws IOException {
        premuto = true;
        notifyAll();
    }

    synchronized public void checkpremutoOk(){
        while (premuto==false){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }




    public void premeIndietro(MouseEvent event) throws IOException{

    }

    public void premeHome(MouseEvent event) throws IOException{

    }

    public void premeLogout(MouseEvent event) throws IOException{

    }
}
