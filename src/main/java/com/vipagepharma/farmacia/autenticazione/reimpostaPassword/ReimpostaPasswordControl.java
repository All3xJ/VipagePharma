package com.vipagepharma.farmacia.autenticazione.reimpostaPassword;

import com.vipagepharma.farmacia.App;

import java.io.IOException;

public class ReimpostaPasswordControl {
    public void start() throws IOException {
        App.setRoot("autenticazione/reimpostaPassword/SchermataReimpostaPassword"); // se sono giuste le credenziali mi porta alla home
        System.out.println("ciao");
    }
}
