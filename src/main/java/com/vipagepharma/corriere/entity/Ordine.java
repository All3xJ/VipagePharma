package com.vipagepharma.corriere.entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.FileOutputStream;
import java.sql.ResultSet;

public class Ordine {

    public StringProperty idPrenotazione = new SimpleStringProperty();
    public StringProperty nomeFarmaciaConsegna = new SimpleStringProperty();

    public StringProperty idFarmacia = new SimpleStringProperty();

    public StringProperty qty = new SimpleStringProperty();

    public StringProperty dataConsegna = new SimpleStringProperty();

    public FileOutputStream filePDF = null;

    public Ordine(String idPrenotazione, String nomeFarmaciaConsegna, String idFarmacia, String qty, String dataConsegna){
        this.idPrenotazione.set(idPrenotazione);
        this.nomeFarmaciaConsegna.set(nomeFarmaciaConsegna);
        this.idFarmacia.set(idFarmacia);
        this.qty.set(qty);
        this.dataConsegna.set(dataConsegna);
    }

    public final String getIdPrenotazione(){
        return idPrenotazione.get();
    }

    public final String getNomeFarmaciaConsegna(){
        return nomeFarmaciaConsegna.get();
    }

    public final String getIdFarmacia(){
        return idFarmacia.get();
    }

    public final String getQty(){
        return qty.get();
    }

    public final String getDataConsegna(){
        return dataConsegna.get();
    }

    public void setFilePDF(FileOutputStream filePDF){
        this.filePDF = filePDF;
    }

}
