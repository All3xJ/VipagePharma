package com.vipagepharma.corriere.autenticazione.logout;

import com.vipagepharma.farmacia.App;

import java.io.IOException;

public class LogoutControl {

    public static void start() throws IOException {
        App.setRoot("autenticazione/login/SchermataLogin");
    }
}
