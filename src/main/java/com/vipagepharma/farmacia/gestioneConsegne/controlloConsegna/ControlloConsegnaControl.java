package com.vipagepharma.farmacia.gestioneConsegne.controlloConsegna;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.DBMSBoundary;
import com.vipagepharma.farmacia.entity.Lotto;
import com.vipagepharma.farmacia.entity.Prenotazione;
import com.vipagepharma.farmacia.entity.Utente;
import javafx.application.Platform;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

public class ControlloConsegnaControl {

    public static ControlloConsegnaControl contrConsCtrlRef;

    LinkedList<Prenotazione> prenotazioniMancatoCarico;
    LinkedList<String> idprenotazioniCaricoParziale;

    private LocalTime orario;

    public ControlloConsegnaControl(){
        this.orario = LocalTime.now();
        contrConsCtrlRef = this;
    }

    public void start() throws SQLException, IOException {
        if (orario.getHour() == 20) {
            String idFarmacia = Utente.getID();
            ResultSet consegneNonCaricate = DBMSBoundary.getConsegneOdierneNonCaricate(idFarmacia);
            this.prenotazioniMancatoCarico = new LinkedList<>();
            this.idprenotazioniCaricoParziale = new LinkedList<>();
            this.checkCaricoParziale(consegneNonCaricate);
        }
    }

    public void checkCaricoParziale(ResultSet consegneNonCaricate) throws SQLException, IOException {
            while(consegneNonCaricate.next()) {
                System.out.println(consegneNonCaricate.getString("id_prenotazione")+" "+consegneNonCaricate.getString("id_lotto"));
                if (consegneNonCaricate.getInt("isCaricato") == 1){

                    if (!idprenotazioniCaricoParziale.contains(consegneNonCaricate.getString("id_prenotazione")))  // se non c'è già la stessa prenotazione nella lista
                        idprenotazioniCaricoParziale.add(consegneNonCaricate.getString("id_prenotazione"));
                } else{
                    Lotto lot = new Lotto(consegneNonCaricate.getString("id_lotto"),consegneNonCaricate.getString("data_scadenza"),consegneNonCaricate.getString("lo.quantita"));
                    Prenotazione pren = new Prenotazione(consegneNonCaricate.getString("id_prenotazione"),consegneNonCaricate.getString("nome"),consegneNonCaricate.getString("data_consegna"),consegneNonCaricate.getString("p.id_utente_farmacia"),consegneNonCaricate.getString("id_farmaco"),consegneNonCaricate.getBoolean("isBanco"),consegneNonCaricate.getInt("isConsegnato"));
                    pren.lotti.add(lot);

                    if (!prenotazioniMancatoCarico.contains(pren)) { // dovrebbe funzionare il contains visto che ho fatto override di equals
                      prenotazioniMancatoCarico.add(pren);  // se entra qua quindi se non c'è gia la stessa prenotaz nella lista allora la aggiunge
                    System.out.println("aaggiunto lotto num: "+lot.getLotto());
                    }else {
                        int index = prenotazioniMancatoCarico.indexOf(pren);    // dovrebbe funzionare indexOf, visto che lavora con equals e ho fatto override di equals dentro Prenotazione
                        prenotazioniMancatoCarico.get(index).lotti.add(lot);    // se invece la prenotaz gia c'era, aggiunge solo il lotto
                        System.out.println("aggiunto lotto num: "+lot.getLotto());
                    }
                }
            }

            System.out.println("prenotaz mancato carico: "+prenotazioniMancatoCarico.size());

            for (String idpren: idprenotazioniCaricoParziale){
                for (Prenotazione pren: prenotazioniMancatoCarico ) {
                    if (pren.getIdPrenotazione().equals(idpren)) { // se c'è una prenotazione che è in carico parziale, bisogna levarla dalla lista mancato carico
                        prenotazioniMancatoCarico.remove(pren);
                        System.out.println("mcnia");
                    }
                }
            }


            this.faiListe();


    }


    private void faiListe(){
        if (!this.idprenotazioniCaricoParziale.isEmpty()){
            String idpren = this.idprenotazioniCaricoParziale.remove();
            AvvisoCaricoParziale.idprenotazioneProblematica=idpren;
            System.out.println("su0: "+idpren);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        App.newWind("gestioneConsegne/controlloConsegna/AvvisoCaricoParziale");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }else
            if (!this.prenotazioniMancatoCarico.isEmpty()){
                Prenotazione pren = this.prenotazioniMancatoCarico.remove();
                AvvisoMancatoCarico.prenotazioneProblematica = pren;
                System.out.println("su2: "+pren.getIdPrenotazione());

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            App.newWind("gestioneConsegne/controlloConsegna/AvvisoMancatoCarico");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }

    }

    public void premutoSegnalaUnProblema(String idPrenotazioneProbl) throws IOException {
        App.popup_stage.close();
        DBMSBoundary.setFlagProblema(idPrenotazioneProbl,1);
        System.out.println(idPrenotazioneProbl+"ci");
        this.faiListe();
    }

    public void premutoConfermaCarico(Prenotazione prenotazioneProbl) throws IOException {
        App.popup_stage.close();
        System.out.println("sizeee: "+prenotazioneProbl.lotti.size());
        DBMSBoundary.aggiungiCarico(prenotazioneProbl.getIdFarmaco(),prenotazioneProbl.getNomeFarmaco(),prenotazioneProbl.getIdFarmacia(),prenotazioneProbl.getIsBanco(),prenotazioneProbl.lotti);
        DBMSBoundary.confermaCaricoLotti(prenotazioneProbl.lotti,prenotazioneProbl.getIdPrenotazione());
        System.out.println(prenotazioneProbl.getIdPrenotazione()+"bi");
        this.faiListe();
    }
}
