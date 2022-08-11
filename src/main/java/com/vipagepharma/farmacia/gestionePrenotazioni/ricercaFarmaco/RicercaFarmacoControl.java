package com.vipagepharma.farmacia.gestionePrenotazioni.ricercaFarmaco;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.SchermataPrincipale;
import com.vipagepharma.farmacia.DBMSBoundary;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class RicercaFarmacoControl {

    public static RicercaFarmacoControl controlRef;

    public RicercaFarmacoControl(){
        controlRef = this;
    }

    public void start() throws IOException {
        App.setRoot("gestionePrenotazioni/ricercaFarmaco/SchermataRicercaFarmaco");
    }

    public void premutoInvio(String nome_o_principio_attivo) throws IOException {
        ResultSet risultatoRicerca = DBMSBoundary.getFarmaco(nome_o_principio_attivo);
        // fare magie con il risultato per creare le tabelle
        App.setRoot("gestionePrenotazione/prenotaFarmaci/SchermataListaFarmaci");
    }

    public static RicercaFarmacoControl getControl(){
        return controlRef;
    }

    public void premutoIndietro() throws IOException{
        App.setRoot("SchermataPrincipale");
    }

    public void premutoHome(String schermataPrecedente) throws IOException {
        SchermataPrincipale.schermataPrecedente=schermataPrecedente;
        App.setRoot("SchermataPrincipale");
    }

}
