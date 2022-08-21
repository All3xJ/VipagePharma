package com.vipagepharma.farmacia.entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contratto {

    public StringProperty nomeFarmaco = new SimpleStringProperty();

    public StringProperty qtySettimanale = new SimpleStringProperty();

    public String IDFarmaco;

    public String prinicipioAttivo;

    public Contratto(String nomeFarmaco, String qty_settimanale, String IDFarmaco, String prinicipioAttivo){
        this.nomeFarmaco.set(nomeFarmaco);
        this.qtySettimanale.set(qty_settimanale);
        this.IDFarmaco = IDFarmaco;
        this.prinicipioAttivo=prinicipioAttivo;
    }

    public final String getIDFarmaco(){
        return this.IDFarmaco;
    }

    public final String getNomeFarmaco(){
        return this.nomeFarmaco.get();
    }

    public final String getQtySettimanale(){
        return this.qtySettimanale.get();
    }
}
