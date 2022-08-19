package com.vipagepharma.addettoAzienda.entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;

public class Consegna {

    public StringProperty idFarmacia = new SimpleStringProperty();
    public StringProperty idOrdine = new SimpleStringProperty();
    public StringProperty dataConsegna = new SimpleStringProperty();
    public String idFarmaco;

    public String ricevutaPath;

    public Consegna(String id_farmacia, String id_ordine, String data_consegna, String id_farmaco) {
        this.idFarmacia.set(id_farmacia);
        this.idOrdine.set(id_ordine);
        this.dataConsegna.set(data_consegna);
        this.idFarmaco=id_farmaco;
    }

    public Consegna(String id_farmacia, String id_ordine, String data_consegna, Blob ricevutaPDF) throws IOException, SQLException {
        this.idFarmacia.set(id_farmacia);
        this.idOrdine.set(id_ordine);
        this.dataConsegna.set(data_consegna);
        this.setRicevuta(ricevutaPDF);
    }

    private void setRicevuta(Blob ricevutaPDF) throws IOException, SQLException {
        BufferedInputStream is = new BufferedInputStream(ricevutaPDF.getBinaryStream());
        String path = "/tmp/ricevuta_"+this.idOrdine.get()+".pdf";
        FileOutputStream fos = new FileOutputStream(path);
        // you can set the size of the buffer
        byte[] buffer = new byte[2048];
        int r = 0;
        while((r = is.read(buffer))!=-1) {
            fos.write(buffer, 0, r);
        }
        fos.flush();
        fos.close();
        is.close();
        ricevutaPDF.free();

        this.ricevutaPath = path;
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
