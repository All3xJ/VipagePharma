package com.vipagepharma.addettoAzienda.gestioneFarmaci.produzioneFarmaci;

import com.vipagepharma.addettoAzienda.DBMSBoundary;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class ProduzioneFarmaciControl {

    private LocalDate data;
    private LocalTime orario;

    public ProduzioneFarmaciControl(){
        this.data = LocalDate.now();
        this.orario = LocalTime.now();
    }

    public void start() throws SQLException {
        if((data.getDayOfWeek() == DayOfWeek.WEDNESDAY) && orario.getHour() == 19) {
            System.out.println("Funziona");
            ResultSet farmaci = DBMSBoundary.getFarmaci();
            ResultSet contratti = DBMSBoundary.getContratti();
            while(farmaci.next()){
                if(farmaci.getInt("isBanco") == 1) {
                    int qtyContratti = 0;
                    while(contratti.next()){
                        if(contratti.getInt("ref_id_f") == farmaci.getInt("id_f")){
                            qtyContratti += contratti.getInt("qty_settimanale");
                            break;
                        }
                    }
                    int qty = qtyContratti + farmaci.getInt("produzione_settimanale");
                    DBMSBoundary.creaLotto(farmaci.getInt("id_f"),LocalDate.now().plusMonths(farmaci.getInt("mesi_scadenza")),qty);
                    contratti.beforeFirst();
                }
                else{
                    DBMSBoundary.creaLotto(farmaci.getInt("id_f"),LocalDate.now().plusMonths(farmaci.getInt("mesi_scadenza")),farmaci.getInt("produzione_settimanale"));
                }
            }
        }
    }
}
