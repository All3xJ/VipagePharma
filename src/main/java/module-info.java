module com.vipagepharma {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires itextpdf;
    requires java.desktop;
    requires mail;
    requires activation;
    requires mysql.connector.java;

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
    exports com.vipagepharma.farmacia.gestionePrenotazioni.ricercaFarmaco;
    opens com.vipagepharma.farmacia.gestionePrenotazioni.ricercaFarmaco to javafx.fxml;
    exports com.vipagepharma.farmacia.gestionePrenotazioni.modificaContratti;
    opens com.vipagepharma.farmacia.gestionePrenotazioni.modificaContratti to javafx.fxml;
    exports com.vipagepharma.farmacia.gestionePrenotazioni.caricoPrenotazione;
    opens com.vipagepharma.farmacia.gestionePrenotazioni.caricoPrenotazione to javafx.fxml;
    exports com.vipagepharma.farmacia.gestionePrenotazioni.annullaPrenotazione;
    opens com.vipagepharma.farmacia.gestionePrenotazioni.annullaPrenotazione to javafx.fxml;
    exports com.vipagepharma.farmacia.gestionePrenotazioni.prenotaFarmaci;
    opens com.vipagepharma.farmacia.gestionePrenotazioni.prenotaFarmaci to javafx.fxml;
    exports com.vipagepharma.farmacia.gestionePrenotazioni.modificaPrenotazione;
    opens com.vipagepharma.farmacia.gestionePrenotazioni.modificaPrenotazione to javafx.fxml;
    exports com.vipagepharma.farmacia.gestioneFarmaci.controlloScorte;
    opens com.vipagepharma.farmacia.gestioneFarmaci.controlloScorte to javafx.fxml;
    exports com.vipagepharma.farmacia.gestioneConsegne.controlloConsegna;
    opens com.vipagepharma.farmacia.gestioneConsegne.controlloConsegna to javafx.fxml;
    exports com.vipagepharma.farmacia.comunicazioneDBMSFallita;
    opens com.vipagepharma.farmacia.comunicazioneDBMSFallita to javafx.fxml;
    exports com.vipagepharma.farmacia.entity;
    opens com.vipagepharma.farmacia.entity to javafx.base;



    exports com.vipagepharma.addettoAzienda;
    opens com.vipagepharma.addettoAzienda to javafx.fxml;
    exports com.vipagepharma.addettoAzienda.autenticazione.login;
    opens com.vipagepharma.addettoAzienda.autenticazione.login to javafx.fxml;
    exports com.vipagepharma.addettoAzienda.autenticazione.registrazione;
    opens com.vipagepharma.addettoAzienda.autenticazione.registrazione to javafx.fxml;
    exports com.vipagepharma.addettoAzienda.autenticazione.reimpostaPassword;
    opens com.vipagepharma.addettoAzienda.autenticazione.reimpostaPassword to javafx.fxml;
    exports com.vipagepharma.addettoAzienda.gestioneConsegne.visualizzaStoricoConsegne;
    opens com.vipagepharma.addettoAzienda.gestioneConsegne.visualizzaStoricoConsegne to javafx.fxml;
    exports com.vipagepharma.addettoAzienda.gestioneConsegne.visualizzaSegnalazioni;
    opens com.vipagepharma.addettoAzienda.gestioneConsegne.visualizzaSegnalazioni to javafx.fxml;
    exports com.vipagepharma.addettoAzienda.gestioneConsegne.risoluzioneProblemaConsegna;
    opens com.vipagepharma.addettoAzienda.gestioneConsegne.risoluzioneProblemaConsegna to javafx.fxml;
    exports com.vipagepharma.addettoAzienda.comunicazioneDBMSFallita;
    opens com.vipagepharma.addettoAzienda.comunicazioneDBMSFallita to javafx.fxml;
    exports com.vipagepharma.addettoAzienda.entity;
    opens com.vipagepharma.addettoAzienda.entity to javafx.base;



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
    exports com.vipagepharma.corriere.gestioneConsegne.downloadConsegne;
    opens com.vipagepharma.corriere.gestioneConsegne.downloadConsegne to javafx.fxml;
    exports com.vipagepharma.corriere.gestioneConsegne.visualizzaConsegne;
    opens com.vipagepharma.corriere.gestioneConsegne.visualizzaConsegne to javafx.fxml;
    exports com.vipagepharma.corriere.entity;
    opens com.vipagepharma.corriere.entity to javafx.base;
    exports com.vipagepharma.corriere.gestioneConsegne.uploadFirme;
    opens com.vipagepharma.corriere.gestioneConsegne.uploadFirme to javafx.fxml;
    exports com.vipagepharma.corriere.comunicazioneDBMSFallita;
    opens com.vipagepharma.corriere.comunicazioneDBMSFallita to javafx.fxml;

}
