package com.vipagepharma.addettoAzienda.gestionePrenotazioni.prenotazioneFarmaciDaBanco;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class PrenotazioneFarmaciDaBancoControl {
    private LocalDate data;
    private LocalTime orario;

    public PrenotazioneFarmaciDaBancoControl(){

    }

    public void start(){
        this.data = LocalDate.now();
        this.orario = LocalTime.now();

    }
}
