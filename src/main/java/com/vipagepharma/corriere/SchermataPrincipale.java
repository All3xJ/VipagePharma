package com.vipagepharma.corriere;

import com.vipagepharma.corriere.gestioneConsegne.downloadConsegne.DownloadConsegneControl;
import com.vipagepharma.farmacia.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SchermataPrincipale implements Initializable {

    public static String schermataPrecedente;

    @FXML
    private Button uploadfirme_button;

    @FXML
    private Button visualconsegne_button;

    public static boolean canShowVisualizzaEUpload = false;

    public void initialize(URL url, ResourceBundle resbound) {
        if (canShowVisualizzaEUpload==false){
            uploadfirme_button.setVisible(false);
            visualconsegne_button.setVisible(false);
        }
    }

    public void premeDownloadConsegne(MouseEvent mouseEvent) throws SQLException, IOException {
        DownloadConsegneControl downConsCtrl = new DownloadConsegneControl();
        downConsCtrl.start(mouseEvent);
    }

    public void premeVisualizzaConsegne(MouseEvent mouseEvent) {
    }

    public void premeUploadFirme(MouseEvent mouseEvent) {
    }

    public void premeHome(MouseEvent mouseEvent)  {
    }

    public void premeIndietro(MouseEvent mouseEvent) {

    }

    public void premeLogout(MouseEvent mouseEvent) throws IOException {
        App.setRoot("autenticazione/login/SchermataLogin");
    }
}
