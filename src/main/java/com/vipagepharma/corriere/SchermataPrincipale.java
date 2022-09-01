package com.vipagepharma.corriere;

import com.vipagepharma.corriere.gestioneConsegne.downloadConsegne.DownloadConsegneControl;
import com.vipagepharma.corriere.gestioneConsegne.uploadFirme.UploadFirmeControl;
import com.vipagepharma.corriere.gestioneConsegne.visualizzaConsegne.VisualizzaConsegneControl;
import com.vipagepharma.corriere.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SchermataPrincipale implements Initializable {

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

    public void premeVisualizzaConsegne(MouseEvent mouseEvent) throws IOException, SQLException {
        VisualizzaConsegneControl visConCtr = new VisualizzaConsegneControl();
        visConCtr.start();
    }

    public void premeUploadFirme(MouseEvent mouseEvent) throws IOException {
        UploadFirmeControl upFirCtrl = new UploadFirmeControl();
        upFirCtrl.start(mouseEvent);
    }
    @FXML
    public void premeLogout(MouseEvent mouseEvent) throws IOException {
        App.setRoot("autenticazione/login/SchermataLogin");
    }
}
