package com.vipagepharma.addettoAzienda.autenticazione.logout;

import com.vipagepharma.addettoAzienda.App;

import java.io.IOException;

public class LogoutControl {

    public static void start() throws IOException {
        App.setRoot("autenticazione/login/SchermataLogin");
    }
}
