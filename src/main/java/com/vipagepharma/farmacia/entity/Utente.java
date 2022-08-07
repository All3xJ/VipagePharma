package com.vipagepharma.farmacia.entity;

public class Utente {
    private static String id;

    public static void creaUtente(String idd){
        id = idd;
    }

    public static String getID(){
        return id;
    }
}

