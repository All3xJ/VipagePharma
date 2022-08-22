package com.vipagepharma.farmacia.entity;


import java.util.ArrayList;

public class FarmacoScarico {
    private String id;
    private ArrayList<String> id_lotti;
    private ArrayList<String> qty_lotti;
    private String qty;
    private int isBanco;
    private String nome ;


    public FarmacoScarico(String id,String id_l,String nome,String qty,int isBanco){
        this.id = id;
        this.qty_lotti = new ArrayList<>();
        this.qty_lotti.add(qty);
        this.id_lotti = new ArrayList<>();
        this.id_lotti.add(id_l);
        this.nome = nome;
        this.isBanco = isBanco;
        this.qty = qty;
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
        return this.id;
    }
    public String getNome(){return this.nome;}
    public void addIdLotto(String id_l) {
        this.id_lotti.add(id_l);
    }
    public void addQty(String qty){
        this.qty_lotti.add(qty);
        int neWqty = Integer.parseInt(this.qty);
        neWqty += Integer.parseInt(qty);
        this.qty = String.valueOf(neWqty);
        System.out.println(this.qty);
    }

    public void aggiornaQtyRimanente(int qty){
        int qtyRimanente = Integer.parseInt(this.qty);
        qtyRimanente -= qty;
        this.qty = String.valueOf(qtyRimanente);
    }

    public int getQtyLotto(String idLotto){
        int index = this.id_lotti.indexOf(idLotto);
        return Integer.parseInt(qty_lotti.get(index));
    }

    public FarmacoScarico getFarmacoScarico(String nome){
        if(this.nome.equals(nome))
            return this;
        return null;
    }
}
