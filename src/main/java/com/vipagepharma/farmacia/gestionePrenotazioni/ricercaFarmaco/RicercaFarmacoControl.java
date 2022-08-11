package com.vipagepharma.farmacia.gestionePrenotazioni.ricercaFarmaco;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.DBMSBoundary;

import java.io.IOException;
import java.sql.ResultSet;

public class RicercaFarmacoControl {

    public static RicercaFarmacoControl controlRef;
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

}
