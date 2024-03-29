package com.vipagepharma.farmacia.gestionePrenotazioni.modificaPrenotazione;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.DBMSBoundary;
import com.vipagepharma.farmacia.entity.Prenotazione;
import com.vipagepharma.farmacia.gestionePrenotazioni.prenotaFarmaci.PrenotaFarmaciControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

public class ModificaPrenotazioneControl {
    public static ModificaPrenotazioneControl modificaPrenotazioneControl;
    private Prenotazione prenotazione;
    private int flag_scadenza;
    private LocalDate data_consegna;
    private LocalDate data_scadenza_min;
    private int qtyRichiesta;
    Connection con;
    ResultSet lotti;
    ArrayList<Integer> idLotti;
    ArrayList<Integer> qtyLotti;

    public ModificaPrenotazioneControl(Prenotazione prenotazione){
        this.prenotazione = prenotazione;
        this.idLotti = new ArrayList<>();
        this.qtyLotti = new ArrayList<>();
        modificaPrenotazioneControl = this;
    }

    public void start() throws IOException, SQLException {
        App.setRoot("gestionePrenotazioni/modificaPrenotazione/SchermataModifica");
    }

    public void premutoInvia(LocalDate data_consegna, String qtyRichiesta , int flag_scadenza, ActionEvent event) throws SQLException, IOException {
        this.data_consegna = data_consegna;
        this.qtyRichiesta = Integer.parseInt(qtyRichiesta);
        this.flag_scadenza = flag_scadenza;
        this.data_scadenza_min = calcDataScadenzaMin();
        this.checkDisponibilita(event);
    }

    private void checkDisponibilita(ActionEvent event) throws SQLException, IOException {
        String idPrenotazione = prenotazione.getIdPrenotazione();
        ResultSet lottiOrdinati = DBMSBoundary.getLottiOrdinati(idPrenotazione);
        ArrayList<String> qtyLotti = new ArrayList<>();
        ArrayList<String> idLotti = new ArrayList<>();
        while(lottiOrdinati.next()){
            qtyLotti.add(lottiOrdinati.getString("quantita"));
            idLotti.add(lottiOrdinati.getString("id_lotto"));
        }
        LinkedList<Object> risultato = DBMSBoundary.getLotti(idPrenotazione,idLotti,qtyLotti,prenotazione.getIdFarmaco());
        this.lotti = (ResultSet) risultato.get(1);
        this.con = (Connection) risultato.get(0);
        if(this.controllaEScegliNuoviLotti()){
            App.newWind("gestionePrenotazioni/modificaPrenotazione/AvvisoConfermaModifica",event);
        }
        else{
            App.newWind("gestionePrenotazioni/modificaPrenotazione/AvvisoModificaErrata",event);
        }
    }


    private boolean controllaEScegliNuoviLotti() throws SQLException {
        int qtyLottiTot = 0;
        while (this.lotti.next() && qtyLottiTot < this.qtyRichiesta && (this.lotti.getDate("data_disponibilita").toLocalDate().isBefore(this.data_consegna) || this.lotti.getDate("data_disponibilita").toLocalDate().isEqual(this.data_consegna))) {  //esco dal loop appena la data di disp > data consegna richiesta
            if (this.lotti.getDate("data_scadenza").toLocalDate().isAfter(this.data_scadenza_min)) {
                this.idLotti.add(this.lotti.getInt("id_lotto"));
                int qtyLotto = this.lotti.getInt("quantita_ordinabile");
                qtyLottiTot += qtyLotto;
                System.out.println("qtyLottiTot: "+qtyLottiTot+" ; qtyLotto: "+qtyLotto);
                if (qtyLottiTot > this.qtyRichiesta) {
                    this.qtyLotti.add(this.qtyRichiesta - (qtyLottiTot - qtyLotto));
                    System.out.println(this.qtyRichiesta-(qtyLottiTot-qtyLotto));
                } else {
                    this.qtyLotti.add(qtyLotto);
                }
            }
        }
        if(qtyLottiTot >= this.qtyRichiesta){
            return true;
        }
        return false;
    }

    public void premutoSi(MouseEvent event) throws IOException, SQLException {
        this.con.commit();
        System.out.println(idLotti);
        System.out.println(qtyLotti);
        DBMSBoundary.modificaPrenotazioneEAggiornaLotti(Integer.parseInt(prenotazione.getIdPrenotazione()),Integer.parseInt(prenotazione.getIdFarmacia()),Integer.parseInt(prenotazione.getIdCorriere()),Integer.parseInt(prenotazione.getIdFarmaco()),this.data_consegna,idLotti,qtyLotti,this.qtyRichiesta);
        App.popup_stage.close();
        App.newWind("gestionePrenotazioni/modificaPrenotazione/AvvisoOperazioneRiuscita",event);
    }
    public void premutoNo(MouseEvent event) throws IOException, SQLException {
        this.con.rollback();
        App.setRoot("gestionePrenotazioni/modificaPrenotazione/SchermataModifica");
        App.popup_stage.close();
    }

    public void premutoOk() throws IOException {
        App.setRoot("SchermataPrincipale");
        App.popup_stage.close();
    }
    public void premutoOk(MouseEvent event) throws IOException, SQLException {
        this.con.rollback();
        App.setRoot("gestionePrenotazioni/modificaPrenotazione/SchermataModifica");
        App.popup_stage.close();
    }

    private LocalDate calcDataScadenzaMin(){
        if(this.flag_scadenza == 0){
            return data_consegna.plusMonths(2);
        }
        else{
            return data_consegna;
        }
    }

    public void premutoIndietro(String schermataPrecedente) throws IOException {
        App.setRoot(schermataPrecedente);
    }
    public void premutoHome() throws IOException {
        App.setRoot("SchermataPrincipale");
    }
    public void premutoLogout() throws IOException {
        App.setRoot("autenticazione/login/SchermataLogin");
    }

    public Prenotazione getPrenotazione(){
        return prenotazione;
    }





}
