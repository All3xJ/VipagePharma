package com.vipagepharma.farmacia.entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Farmaco {

    private final StringProperty idFarmaco = new SimpleStringProperty();
    private final StringProperty nomeFarmaco = new SimpleStringProperty();

    private final StringProperty principioAttivo = new SimpleStringProperty();
    private final StringProperty qty = new SimpleStringProperty();

    //per lo scarico
    private String id;
    private ArrayList<String> id_lotti;
    private ArrayList<String> qty_lotti;
    private String qtyScarico;
    private int isBanco;
    private String nome ;


    public Farmaco(String id,String id_l,String nome,String qty,int isBanco){
        this.id = id;
        this.qty_lotti = new ArrayList<>();
        this.qty_lotti.add(qty);
        this.id_lotti = new ArrayList<>();
        this.id_lotti.add(id_l);
        this.nome = nome;
        this.isBanco = isBanco;
        this.qtyScarico= qty;
    }
    //per lo scarico

    public Farmaco(String idFarmaco, String nomeFarmaco, String principioAttivo){
        this.setId(idFarmaco);
        this.setNomeFarmaco(nomeFarmaco);
        this.setPrincipioAttivo(principioAttivo);
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
    public final StringProperty qtyProperty() {
        return this.qty;
    }

    public final String getQty() {
        return this.qty.get();
    }

    public final void setQty(String value) {
        this.qty.set(value);
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

    //per lo scarico
    public ArrayList<String> getIdLotti(){
        return this.id_lotti;
    }
    public String getQtyScarico(){
        return this.qtyScarico;
    }
    public int getIsBanco(){
        return this.isBanco;
    }

    public String getId(){
        return this.id;
    }
    public String getNome(){return this.nome;}
    public void addIdLotto(String id_l) {
        this.id_lotti.add(id_l);
    }
    public void addQty(String qty){
        this.qty_lotti.add(qty);
        int neWqty = Integer.parseInt(this.qtyScarico);
        neWqty += Integer.parseInt(qty);
        this.qtyScarico = String.valueOf(neWqty);
        System.out.println(this.qty);
    }

    public int getQtyLotto(String idLotto){
        int index = this.id_lotti.indexOf(idLotto);
        return Integer.parseInt(qty_lotti.get(index));
    }

    public Farmaco getFarmacoScarico(String nome){
        if(this.nome.equals(nome))
            return this;
        return null;
    }
    //per lo scarico
}
