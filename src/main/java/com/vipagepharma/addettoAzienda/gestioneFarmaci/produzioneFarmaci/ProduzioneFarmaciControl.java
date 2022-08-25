package com.vipagepharma.addettoAzienda.gestioneFarmaci.produzioneFarmaci;

import com.vipagepharma.addettoAzienda.DBMSBoundary;

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
        if ((data.getDayOfWeek() == DayOfWeek.THURSDAY) && orario.getHour() == 15) {
            ResultSet farmaci = DBMSBoundary.getFarmaci();
            ResultSet contratti = DBMSBoundary.getContratti();
            while (farmaci.next()) {
                int qty = farmaci.getInt("produzione_settimanale");
                if (farmaci.getInt("isBanco") == 1) {
                    int qtyContratti = 0;
                    while (contratti.next()) {
                        if (contratti.getInt("id_farmaco") == farmaci.getInt("id_farmaco")) {
                            qtyContratti += contratti.getInt("qty_settimanale");
                        }
                    }
                    DBMSBoundary.creaLotto(farmaci.getInt("id_farmaco"), LocalDate.now().plusMonths(farmaci.getInt("mesi_scadenza")), LocalDate.now(), qty, qtyContratti);
                    contratti.beforeFirst();
                } else {
                    DBMSBoundary.creaLotto(farmaci.getInt("id_farmaco"), LocalDate.now().plusMonths(farmaci.getInt("mesi_scadenza")), LocalDate.now(), qty, 0);
                }
            }
            farmaci.close();
            contratti.close();
        }
    }
}
