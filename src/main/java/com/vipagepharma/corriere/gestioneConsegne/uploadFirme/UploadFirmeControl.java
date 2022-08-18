package com.vipagepharma.corriere.gestioneConsegne.uploadFirme;

import com.vipagepharma.corriere.App;
import com.vipagepharma.corriere.DBMSBoundary;
import com.vipagepharma.corriere.SchermataPrincipale;
import com.vipagepharma.corriere.entity.Ordine;
import com.vipagepharma.corriere.gestioneConsegne.visualizzaConsegne.VisualizzaConsegneControl;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.Blob;

public class UploadFirmeControl {

    public static UploadFirmeControl uplFirmCtrlRef;

    public UploadFirmeControl(){
        uplFirmCtrlRef = this;
    }

    public void start(MouseEvent mouseEvent) throws IOException {
        for (Ordine ordine: VisualizzaConsegneControl.visualConCtrlRef.tvObservableList) {
            if (ordine.filePDF!=null){
                DBMSBoundary.contrassegnaOrdineFirmato(ordine.idPrenotazione.toString());
                DBMSBoundary.salvaRicevuta(ordine.idPrenotazione.toString(), (Blob) ordine.filePDF);
            }
        }
        App.newWind("gestioneConsegne/uploadFirme/AvvisoOperazioneRiuscita",mouseEvent);
    }

    public void premutoOk() throws IOException {
        SchermataPrincipale.canShowVisualizzaEUpload=false;
        App.setRoot("SchermataPrincipale");
        App.popup_stage.close();
    }
}
