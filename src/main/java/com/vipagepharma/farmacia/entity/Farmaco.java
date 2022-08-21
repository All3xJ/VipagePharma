package com.vipagepharma.farmacia.entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Farmaco {

    private final StringProperty idFarmaco = new SimpleStringProperty();
    private final StringProperty nomeFarmaco = new SimpleStringProperty();

    private final StringProperty principioAttivo = new SimpleStringProperty();

    public boolean isBanco;

    public Farmaco(String idFarmaco, String nomeFarmaco, String principioAttivo){
        this.setId(idFarmaco);
        this.setNomeFarmaco(nomeFarmaco);
        this.setPrincipioAttivo(principioAttivo);
    }

    public Farmaco(String idFarmaco, boolean isBanco){
        this.setId(idFarmaco);
        this.isBanco=isBanco;
    }

    public final StringProperty idFarmacoProperty() {
        return this.idFarmaco;
    }

    public final String getIdFarmaco() {
        return this.idFarmaco.get();
    }

    public final void setId(String value) {
        this.idFarmaco.set(value);
    }

    public final StringProperty principioAttivoProperty() {
        return this.principioAttivo;
    }

    public final String getPrincipioAttivo() {
        return this.principioAttivo.get();
    }

    public final void setPrincipioAttivo(String value) {
        this.principioAttivo.set(value);
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
