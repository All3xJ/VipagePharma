module com.vipagepharma {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports com.vipagepharma.farmacia;
    opens com.vipagepharma.farmacia to javafx.fxml;
    exports com.vipagepharma.farmacia.autenticazione.login;
    opens com.vipagepharma.farmacia.autenticazione.login to javafx.fxml;
    exports com.vipagepharma.farmacia.autenticazione.registrazione;
    opens com.vipagepharma.farmacia.autenticazione.registrazione to javafx.fxml;
    exports com.vipagepharma.farmacia.autenticazione.reimpostaPassword;
    opens com.vipagepharma.farmacia.autenticazione.reimpostaPassword to javafx.fxml;
    exports com.vipagepharma.farmacia.gestionePrenotazioni.visualizzaPrenotazioni;
    opens com.vipagepharma.farmacia.gestionePrenotazioni.visualizzaPrenotazioni to javafx.fxml;
    exports com.vipagepharma.farmacia.gestioneFarmaci.scaricoFarmaci;
    opens com.vipagepharma.farmacia.gestioneFarmaci.scaricoFarmaci to javafx.fxml;


    exports com.vipagepharma.addettoAzienda;
    opens com.vipagepharma.addettoAzienda to javafx.fxml;
    exports com.vipagepharma.addettoAzienda.autenticazione.login;
    opens com.vipagepharma.addettoAzienda.autenticazione.login to javafx.fxml;
    exports com.vipagepharma.addettoAzienda.autenticazione.registrazione;
    opens com.vipagepharma.addettoAzienda.autenticazione.registrazione to javafx.fxml;
    exports com.vipagepharma.addettoAzienda.gestioneConsegne.visualizzaStoricoConsegne;
    opens com.vipagepharma.addettoAzienda.gestioneConsegne.visualizzaStoricoConsegne to javafx.fxml;

    exports com.vipagepharma.corriere;
    opens com.vipagepharma.corriere to javafx.fxml;
    exports com.vipagepharma.corriere.autenticazione.login;
    opens com.vipagepharma.corriere.autenticazione.login to javafx.fxml;
    exports com.vipagepharma.corriere.autenticazione.registrazione;
    opens com.vipagepharma.corriere.autenticazione.registrazione to javafx.fxml;
    exports com.vipagepharma.corriere.autenticazione.reimpostaPassword;
    opens com.vipagepharma.corriere.autenticazione.reimpostaPassword to javafx.fxml;
    exports com.vipagepharma.corriere.gestioneConsegne.firmaConsegna;
    opens com.vipagepharma.corriere.gestioneConsegne.firmaConsegna to javafx.fxml;
}
