package com.vipagepharma.farmacia.entity;

public class FarmacoScarico {
    private static String id;
    private static int tipo;
    private static int qtyRimanente;

    public static void creaFarmacoScarico(String idd,int tipoo,int qtyRimanentee){
        id = idd;
        tipo = tipoo;
        qtyRimanente = qtyRimanentee;
    }

    public static String getId(){
        return id;
    }
    public static int getTipo(){
        return tipo;
    }
    public static int getQtyRimanente(){
        return qtyRimanente;
    }

}
