package com.vipagepharma.farmacia.entity;


import java.util.ArrayList;

public class FarmacoScarico {
    private String id;
    private ArrayList<String> id_lotti;
    private String qty;
    private int isBanco;
    private String nome ;


    public FarmacoScarico(String id,String id_l,String nome,String qty,int isBanco){
        this.id = id;
        this.id_lotti = new ArrayList<>();
        this.id_lotti.add(id_l);
        this.nome = nome;
        this.isBanco = isBanco;
        this.qty = qty;
    }

    public FarmacoScarico getFarmacoScarico(String nome){
        if(this.nome.equals(nome))
            return this;
        return null;
    }
    public ArrayList<String> getIdLotti(){
        return this.id_lotti;
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

    public void addIdLotto(String id_l) {
        this.addIdLotto(id_l);
    }
}
