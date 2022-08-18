package com.vipagepharma.corriere.gestioneConsegne.uploadFirme;

import com.vipagepharma.corriere.gestioneConsegne.downloadConsegne.DownloadConsegneControl;
import javafx.fxml.FXML;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;

public class AvvisoOperazioneRiuscita {

    @FXML
    void premeOk(MouseEvent event) throws IOException {
        UploadFirmeControl.uplFirmCtrlRef.premutoOk();
    }
}
