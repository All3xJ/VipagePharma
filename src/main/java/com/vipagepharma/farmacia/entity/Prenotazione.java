package com.vipagepharma.farmacia.entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Prenotazione {
    private final StringProperty idPrenotazione = new SimpleStringProperty();

    private final StringProperty nomeFarmaco = new SimpleStringProperty();

    private final StringProperty dataConsegna = new SimpleStringProperty();

    private String idFarmaco;
    private String idFarmacia;

    public Prenotazione(String idPrenotazione, String nomeFarmaco, String dataConsegna , String idFarmacia,String idFarmaco){
        this.setId(idPrenotazione);
        this.setNomeFarmaco(nomeFarmaco);
        this.setDataConsegna(dataConsegna);
        this.idFarmacia = idFarmacia;
        this.idFarmaco = idFarmaco;
    }

    public String getIdFarmaco(){
        return this.idFarmaco;
    }

    public String getIdFarmacia(){
        return this.idFarmacia;
    }

    public final StringProperty idPrenotazioneProperty() {
        return this.idPrenotazione;
    }

    public final String getIdPrenotazione() {
        return this.idPrenotazione.get();
    }

    public final void setId(String value) {
        this.idPrenotazione.set(value);
    }

    public final StringProperty dataConsegnaProperty() {
        return this.dataConsegna;
    }

    public final String getDataConsegna() {
        return this.dataConsegna.get();
    }

    public final void setDataConsegna(String value) {
        this.dataConsegna.set(value);
    }

    public final StringProperty nomeFarmacoProperty() {
        return this.nomeFarmaco;
    }

    public final String getNomeFarmaco() {
        return this.nomeFarmaco.get();
    }

    public final void setNomeFarmaco(String value) {
        this.nomeFarmaco.set(value);
    }

}

