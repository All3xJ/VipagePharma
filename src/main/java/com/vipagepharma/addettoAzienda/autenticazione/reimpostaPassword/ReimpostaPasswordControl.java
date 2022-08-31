package com.vipagepharma.addettoAzienda.autenticazione.reimpostaPassword;

import com.vipagepharma.addettoAzienda.App;
import com.vipagepharma.addettoAzienda.DBMSBoundary;
import javafx.event.ActionEvent;

import java.io.IOException;

public class ReimpostaPasswordControl {

    private String id;
    private String key;
    private String confermaPassword;
    private String password;

    public static String errore;
    public static ReimpostaPasswordControl repassCtrlRef;
    public ReimpostaPasswordControl(){
        repassCtrlRef = this;
    }
    public void start() throws IOException {
        App.setRoot("autenticazione/reimpostaPassword/SchermataReimpostaPassword");
    }

    public void premutoInvia(String id, String key, ActionEvent event) throws IOException {
        boolean esito = DBMSBoundary.verificaKey(id,key);
        if(esito){
            this.id = id;
            this.key = key;
            App.setRoot("autenticazione/reimpostaPassword/SchermataNuovaPassword");
        }
        else{
            errore="ID o Parola chiave errati!";
            App.newWind("autenticazione/reimpostaPassword/AvvisoOperazioneFallita",event);
        }
    }

    public void inviaPassword(String password, String confermaPassword, ActionEvent event) throws IOException {
        this.password = password;
        this.confermaPassword = confermaPassword;
        if(!this.checkPass()){
            errore="Le password devono coincidere!";
            App.newWind("autenticazione/reimpostaPassword/AvvisoOperazioneFallita",event);
        }
        else{
            DBMSBoundary.aggiornaPassword(this.id,this.password);
            App.newWind("autenticazione/reimpostaPassword/AvvisoOperazioneRiuscita",event);
        }
    }

    public void premutoOk(String schermata) throws IOException {
        App.popup_stage.close();
        App.setRoot(schermata);
    }
    private boolean checkPass(){
        if(this.password.equals(this.confermaPassword) && !this.password.equals(""))
            return true;
        return false;
    }

    public void premutoIndietro(String schermataPrecedente) throws IOException {
        App.setRoot(schermataPrecedente);
    }

}
