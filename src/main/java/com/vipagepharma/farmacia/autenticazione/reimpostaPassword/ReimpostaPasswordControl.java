package com.vipagepharma.farmacia.autenticazione.reimpostaPassword;

import com.vipagepharma.farmacia.App;
import com.vipagepharma.farmacia.DBMSBoundary;
import com.vipagepharma.farmacia.SchermataPrincipale;

import java.io.IOException;

public class ReimpostaPasswordControl {

    private String id;
    private String key;
    private String confermaPassword;
    private String password;
    public static ReimpostaPasswordControl repassCtrlRef;
    public ReimpostaPasswordControl(){
        repassCtrlRef = this;
    }
    public void start() throws IOException {
        SchermataReimpostaPassword.schermataPrecedente = "autenticazione/login/SchermataLogin";
        App.setRoot("autenticazione/reimpostaPassword/SchermataReimpostaPassword");
    }

    public void premutoInvia(String id,String key) throws IOException {
        boolean esito = DBMSBoundary.verificaKey(id,key);
        if(esito){
            this.id = id;
            this.key = key;
            SchermataNuovaPassword.schermataPrecedente = "autenticazione/registrazione/SchermataReimpostaPassword";
            App.setRoot("autenticazione/reimpostaPassword/SchermataNuovaPassword");
        }
        else{
            App.setRoot("autenticazione/reimpostaPassword/AvvisoOperazioneFallita");
        }
    }

    public void inviaPassword(String password,String confermaPassword) throws IOException {
        this.password = password;
        this.confermaPassword = confermaPassword;
        if(!this.checkPass()){
            App.setRoot("autenticazione/reimpostaPassword/AvvisoPasswordErrate");
        }
        else{
            DBMSBoundary.aggiornaPassword(this.id,this.password);
            App.setRoot("autenticazione/reimpostaPassword/AvvisoOperazioneRiuscita");
        }
    }

    public void premutoOk(String schermata) throws IOException {
        App.setRoot(schermata);
    }
    private boolean checkPass(){
        if(this.password.equals(this.confermaPassword))
            return true;
        return false;
    }

    public void premutoIndietro(String schermataPrecedente) throws IOException {
        App.setRoot(schermataPrecedente);
    }

}
