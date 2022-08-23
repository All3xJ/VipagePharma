package com.vipagepharma.corriere.gestioneConsegne.downloadConsegne;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AvvisoOperazioneRiuscita {

    public void premeOk(MouseEvent mouseEvent) throws IOException {
        DownloadConsegneControl.downConsCtrlRef.premutoOk();
    }
}
