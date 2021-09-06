package com.eliatolin.gest_spedizioni.utils;

import com.eliatolin.gest_spedizioni.models.ListaSpedizioni;
import com.eliatolin.gest_spedizioni.models.Spedizione;
import com.eliatolin.gest_spedizioni.models.SpedizioniTableModel;
import java.util.Random;

public class ThreadSpedizioniCasuali extends Thread {

    private ListaSpedizioni lstSped;
    private SpedizioniTableModel tmSped;
    private Spedizione sped;
    private int idxSped;
    private String statoSped;
    private int csl;
    private Random rnd;

    public ThreadSpedizioniCasuali(SpedizioniTableModel t) {
        lstSped = t.getLista();
        tmSped = t;
    }

    @Override
    public void run() {
        rnd = new Random();
        idxSped = rnd.nextInt(lstSped.getNumeroSpedizioni());
        
        sped = lstSped.getSpedizione(idxSped);
        statoSped = sped.getStato();

        csl = rnd.nextInt(1000);

        if (csl < 350) {
            if (statoSped.equals("IN_PREPARAZIONE")) {
                sped.setStato("IN_TRANSITO");
                tmSped.fireTableDataChanged();
            }
        }

        if (csl > 350 && csl < 800) {
            if (statoSped.equals("IN_TRANSITO")) {
                sped.setStato("RICEVUTA");
                tmSped.fireTableDataChanged();
            }
        }

        if (csl > 800) {
            if (statoSped.equals("IN_TRANSITO") || statoSped.equals("IN_PREPARAZIONE")) {
                sped.setStato("FALLITA");
                tmSped.fireTableDataChanged();
            }
        }

        if (csl < 700) {
            if (statoSped.equals("RIMBORSO_RICHIESTO")) {
                sped.setStato("RIMBORSO_EROGATO");
                tmSped.fireTableDataChanged();
            }
        }
    }
}