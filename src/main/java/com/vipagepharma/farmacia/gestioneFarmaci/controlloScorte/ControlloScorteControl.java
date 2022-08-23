package com.vipagepharma.farmacia.gestioneFarmaci.controlloScorte;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.entity.Farmaco;
import com.vipagepharma.farmacia.gestionePrenotazioni.prenotaFarmaci.PrenotaFarmaciControl;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ControlloScorteControl {
    public static ControlloScorteControl conScCtrl;
    private Farmaco farmaco;
    public ControlloScorteControl(){
        conScCtrl = this;
    }
    public void start(Farmaco farmaco, MouseEvent event) throws IOException {
        // id = farmaco.getId();
        this.farmaco = farmaco;
        String qtyRimanente = farmaco.getQtyScarico();
        System.out.print(qtyRimanente);
        int tipo = farmaco.getIsBanco();
        App.popup_stage.close();
        if((tipo == 1 && Integer.parseInt(qtyRimanente) < 50) || (tipo == 0 && Integer.parseInt(qtyRimanente) < 20)) {
            App.newWind("gestioneFarmaci/controlloScorte/AvvisoEsaurimentoScorte", event);
        }
        else{
            App.setRoot("SchermataPrincipale");
        }
    }

    public String getText(){
        return "Le tue scorte di "+ this.farmaco.getNome() + " stanno per terminare.\nVuoi effettuare una nuova prenotazione?";
    }

    public void premutoPrenota() throws IOException {
        PrenotaFarmaciControl prenotaFarmaciControl = new PrenotaFarmaciControl();
        prenotaFarmaciControl.start(this.farmaco.getId(),this.farmaco.getNome());
        App.popup_stage.close();
    }
}
