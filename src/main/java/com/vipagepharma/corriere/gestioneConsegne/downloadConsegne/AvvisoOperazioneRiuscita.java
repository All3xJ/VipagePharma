package com.vipagepharma.corriere.gestioneConsegne.downloadConsegne;

import javafx.fxml.FXML;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;

public class AvvisoOperazioneRiuscita {

    public void premeOk(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        DownloadConsegneControl.downConsCtrlRef.premutoOk();
    }
}
