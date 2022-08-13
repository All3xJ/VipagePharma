package com.vipagepharma.corriere.gestioneConsegne.downloadConsegne;

import com.vipagepharma.corriere.App;
import com.vipagepharma.corriere.DBMSBoundary;
import com.vipagepharma.corriere.SchermataPrincipale;
import com.vipagepharma.corriere.entity.Utente;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DownloadConsegneControl {

    public static DownloadConsegneControl downConsCtrlRef;

    public DownloadConsegneControl(){
        downConsCtrlRef = this;
    }

    public void start(MouseEvent event) throws SQLException, IOException {
        String id = Utente.getID();
        ResultSet consegneOdierne = DBMSBoundary.getConsegneOdierne(id);
        consegneOdierne.close();
        App.newWind("gestioneConsegne/downloadConsegne/AvvisoOperazioneRiuscita",event);
    }

    public void premutoOk() throws IOException {
        SchermataPrincipale.canShowVisualizzaEUpload=true;
        App.setRoot("SchermataPrincipale");
    }
}
