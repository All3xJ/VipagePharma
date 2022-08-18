package com.vipagepharma.addettoAzienda.entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Consegna {

    public StringProperty idFarmacia = new SimpleStringProperty();
    public StringProperty idOrdine = new SimpleStringProperty();
    public StringProperty dataConsegna = new SimpleStringProperty();

    public Consegna(String id_farmacia, String id_ordine, String data_consegna) {
        this.idFarmacia.set(id_farmacia);
        this.idOrdine.set(id_ordine);
        this.dataConsegna.set(data_consegna);
    }

    public final String getIdFarmacia(){
        return idFarmacia.get();
    }

    public final String getIdOrdine(){
        return idOrdine.get();
    }

    public final String getDataConsegna(){
        return dataConsegna.get();
    }
}
