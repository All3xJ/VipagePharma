package com.vipagepharma.addettoAzienda.entity;

public class Consegna {

    public String idFarmacia;
    public String idOrdine;
    public String dataConsegna;

    public Consegna(String id_farmacia, String id_ordine, String data_consegna) {
        this.idFarmacia = id_farmacia;
        this.idOrdine = id_ordine;
        this.dataConsegna = data_consegna;
    }
}
