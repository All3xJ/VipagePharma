package com.vipagepharma.corriere.gestioneConsegne.firmaConsegna;

import com.vipagepharma.corriere.gestioneConsegne.downloadConsegne.DownloadConsegneControl;

import java.io.IOException;

public class AvvisoOperazioneRiuscita {

    public void premeOk(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        FirmaConsegnaControl.firmConsCtrlRef.premutoOk();
    }
}
