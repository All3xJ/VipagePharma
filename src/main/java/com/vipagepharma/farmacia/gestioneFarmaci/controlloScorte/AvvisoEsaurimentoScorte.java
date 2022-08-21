package com.vipagepharma.farmacia.gestioneFarmaci.controlloScorte;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AvvisoEsaurimentoScorte implements Initializable {
    @FXML
    private Text testo_esaurimento_scorte;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        testo_esaurimento_scorte.setText(ControlloScorteControl.conScCtrl.getText());
    }
    @FXML
    public void premePrenota(MouseEvent mouseEvent) throws IOException {
        ControlloScorteControl.conScCtrl.premutoPrenota();
    }
}
