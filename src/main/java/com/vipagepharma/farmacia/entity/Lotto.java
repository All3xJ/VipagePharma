package com.vipagepharma.farmacia.entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Lotto {

    private final StringProperty lotto = new SimpleStringProperty();
    private String dataScadenza ;
    private String qty ;

    public Lotto(String lotto,String dataScadenza,String qty){
        this.setLotto(lotto);
        this.dataScadenza = dataScadenza;
        this.qty = qty;
    }

    public final StringProperty lottoProperty() {
        return this.lotto;
    }

    public final String getLotto() {
        return this.lotto.get();
    }

    public final void setLotto(String value) {
        this.lotto.set(value);
    }

    public String getQty(){
        return this.qty;
    }

    public String getDataScadenza(){
        return this.dataScadenza;
    }
}

