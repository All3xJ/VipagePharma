package com.vipagepharma.farmacia.entity;


public class FarmacoScarico {
    private String id;
    private String id_l;
    private String qty;
    private int isBanco;
    private String nome ;


    public FarmacoScarico(String id,String id_l,String nome,String qty,int isBanco){
        this.id = id;
        this.id_l = id_l;
        this.nome = nome;
        this.isBanco = isBanco;
        this.qty = qty;
    }

    public FarmacoScarico getFarmacoScarico(String nome){
        if(this.nome.equals(nome))
            return this;
        return null;
    }
    public String getId_l(){
        return this.id_l;
    }
    public String getQty(){
        return this.qty;
    }
    public int getIsBanco(){
        return this.isBanco;
    }

    public String getId(){
        return id;
    }
}
