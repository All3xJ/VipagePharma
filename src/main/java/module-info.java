module com.vipagepharma {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.vipagepharma.farmacia;
    opens com.vipagepharma.farmacia to javafx.fxml;
    exports com.vipagepharma.farmacia.autenticazione.login;
    opens com.vipagepharma.farmacia.autenticazione.login to javafx.fxml;
    exports com.vipagepharma.farmacia.autenticazione.registrazione;
    opens com.vipagepharma.farmacia.autenticazione.registrazione to javafx.fxml;
    exports com.vipagepharma.farmacia.gestionePrenotazioni.visualizzaPrenotazioni;
    opens com.vipagepharma.farmacia.gestionePrenotazioni.visualizzaPrenotazioni to javafx.fxml;


    exports com.vipagepharma.addettoAzienda;
    opens com.vipagepharma.addettoAzienda to javafx.fxml;
    exports com.vipagepharma.addettoAzienda.autenticazione.login;
    opens com.vipagepharma.addettoAzienda.autenticazione.login to javafx.fxml;
    exports com.vipagepharma.addettoAzienda.autenticazione.registrazione;
    opens com.vipagepharma.addettoAzienda.autenticazione.registrazione to javafx.fxml;
}
