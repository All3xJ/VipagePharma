package com.vipagepharma.addettoAzienda.gestioneConsegne.visualizzaStoricoConsegne;

import com.vipagepharma.addettoAzienda.App;
import com.vipagepharma.addettoAzienda.DBMSBoundary;
import com.vipagepharma.addettoAzienda.SchermataPrincipale;
import com.vipagepharma.addettoAzienda.entity.Consegna;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VisualizzaStoricoConsegneControl {

    public static VisualizzaStoricoConsegneControl visStoConsCtrlRef;

    public VisualizzaStoricoConsegneControl(){
        visStoConsCtrlRef = this;
    }

    public ObservableList<Consegna> tvObservableList = FXCollections.observableArrayList();

    public ResultSet consegne = null;

    public static int contatorePagineConsegne = 0;


    public void start() throws IOException, SQLException {
        this.riempiObservableList();
        App.setRoot("gestioneConsegne/visualizzaStoricoConsegne/SchermataStoricoConsegne");
    }

    private void riempiObservableList() throws SQLException {
        if (contatorePagineConsegne==0) {
            this.tvObservableList.clear();
            this.consegne = DBMSBoundary.getConsegneRecenti();
        }else {
           // this.tvObservableList.clear();
            this.consegne = DBMSBoundary.getAltreConsegne();
            if(this.consegne.next()==false){
                this.tvObservableList.clear();
            }
            this.consegne.beforeFirst();
        }try {
            while (true) {
                if (!this.consegne.next()) break;

                this.tvObservableList.add(new Consegna(this.consegne.getString("id_utente_farmacia"),this.consegne.getString("id_prenotazione"),this.consegne.getString("data_consegna"),this.consegne.getBlob("ricevuta_pdf")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.consegne.close();
    }

    public void premutoVisualizzaRicevuta(Consegna consegna) throws IOException {
        try
        {
            Runtime.getRuntime().exec("evince "+consegna.ricevutaPath);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            try{
                Runtime.getRuntime().exec("okular "+consegna.ricevutaPath);
            } catch (Exception e2){
                e2.printStackTrace();
            }
        }
        contatorePagineConsegne=0;
    }

    public void premutoMostraAltro() throws SQLException, IOException {
        ++contatorePagineConsegne;
        this.start();
    }

    public void premutoHome(String schermataPrecedente) throws IOException {
        contatorePagineConsegne=0;
        SchermataPrincipale.schermataPrecedente = schermataPrecedente;
        App.setRoot("SchermataPrincipale");
    }


    public void premutoIndietro() throws IOException {
        contatorePagineConsegne=0;
        App.setRoot("SchermataPrincipale");
    }
}
