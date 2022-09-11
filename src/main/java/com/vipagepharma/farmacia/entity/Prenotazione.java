package com.vipagepharma.farmacia.entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.LinkedList;

public class Prenotazione {
    private final StringProperty idPrenotazione = new SimpleStringProperty();

    private final StringProperty nomeFarmaco = new SimpleStringProperty();

    private final StringProperty dataConsegna = new SimpleStringProperty();
    private boolean isConsegnato;
    private String idFarmaco;
    private String idFarmacia;
    private String idCorriere;
    private int qty;
    private boolean isBanco;
    public LinkedList<Lotto> lotti = new LinkedList<>();

    public Prenotazione(String idCorriere ,String idPrenotazione, String nomeFarmaco, String dataConsegna , String idFarmacia,String idFarmaco,int isConsegnato,int qty,boolean isBanco){
        this.setId(idPrenotazione);
        this.setNomeFarmaco(nomeFarmaco);
        this.setDataConsegna(dataConsegna);
        this.idFarmacia = idFarmacia;
        this.idFarmaco = idFarmaco;
        this.idCorriere= idCorriere;
        this.qty = qty;
        this.isBanco=isBanco;
        if(isConsegnato == 1)
            this.isConsegnato = true;
        else
            this.isConsegnato = false;
    }

    public Prenotazione(String idPrenotazione, String nomeFarmaco, String dataConsegna , String idFarmacia,String idFarmaco,boolean isBanco,int isConsegnato){
        this.setId(idPrenotazione);
        this.setNomeFarmaco(nomeFarmaco);
        this.setDataConsegna(dataConsegna);
        this.idFarmacia = idFarmacia;
        this.idFarmaco = idFarmaco;
        this.isBanco = isBanco;
        if(isConsegnato == 1)
            this.isConsegnato = true;
        else
            this.isConsegnato = false;
    }


    @Override
    public boolean equals(Object o) {
        Prenotazione p = null;
        if (o instanceof Prenotazione)
            p = (Prenotazione) o;

        if (this.idPrenotazione.get().equals(p.idPrenotazione.get()))
            return true;
        else
            return false;
    }



    public boolean getIsConsegnato(){
        return this.isConsegnato;
    }
    public String getIdFarmaco(){
        return this.idFarmaco;
    }
    public boolean getIsBanco(){
        return this.isBanco;
    }
    public String getIdCorriere(){
        return this.idCorriere;
    }

    public String getIdFarmacia(){
        return this.idFarmacia;
    }
    public int getQty(){
        return this.qty;
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

