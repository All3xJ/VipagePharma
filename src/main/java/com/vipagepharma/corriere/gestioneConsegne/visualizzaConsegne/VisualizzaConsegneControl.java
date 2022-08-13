package com.vipagepharma.corriere.gestioneConsegne.visualizzaConsegne;

import com.vipagepharma.corriere.entity.Ordine;

import java.sql.ResultSet;

public class VisualizzaConsegneControl {
    public void start(){
        ResultSet ordini = Ordine.consegneOdierne;
        // fare elenco
    }
}
