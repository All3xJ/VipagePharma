package com.vipagepharma.corriere.gestioneConsegne.downloadConsegne;

import javafx.fxml.FXML;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;

public class AvvisoOperazioneRiuscita {

    @FXML
    void premeOk(MouseEvent event) throws IOException {
        DownloadConsegneControl.downConsCtrlRef.premutoOk();
    }
}
