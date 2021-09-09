package com.eliatolin.gest_spedizioni.utils;

import com.eliatolin.gest_spedizioni.models.ListaSpedizioni;
import com.eliatolin.gest_spedizioni.models.Spedizione;
import com.eliatolin.gest_spedizioni.models.SpedizioniTableModel;
import java.util.Random;

/**
 *
 * @author eliatolin
 */

public class ThreadSpedizioniCasuali extends Thread {
    
    private int idxSped;
    private int csl;
    private ListaSpedizioni lstSped;
    private SpedizioniTableModel tmSped;
    private Spedizione sped;
    private String statoSped;
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

        csl = rnd.nextInt(100);

        if (csl < 35) {
            if (statoSped.equals("IN_PREPARAZIONE")) {
                sped.setStato("IN_TRANSITO");
                tmSped.fireTableDataChanged();
            }
        }

        if (csl > 35 && csl < 80) {
            if (statoSped.equals("IN_TRANSITO")) {
                sped.setStato("RICEVUTA");
                tmSped.fireTableDataChanged();
            }
        }

        if (csl > 80) {
            if (statoSped.equals("IN_TRANSITO") || statoSped.equals("IN_PREPARAZIONE")) {
                sped.setStato("FALLITA");
                tmSped.fireTableDataChanged();
            }
        }

        if (csl < 70) {
            if (statoSped.equals("RIMBORSO_RICHIESTO")) {
                sped.setStato("RIMBORSO_EROGATO");
                tmSped.fireTableDataChanged();
            }
        }
    }
}
