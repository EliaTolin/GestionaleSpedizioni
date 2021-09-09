package com.eliatolin.gest_spedizioni.models;

import com.eliatolin.gest_spedizioni.utils.DataUtility;
import com.eliatolin.gest_spedizioni.models.*;
import java.util.Random;

public class Utente {

    private final String nomeUtente;
    private final String password;
    private final String indirizzo;
    private int contatore;
    public String id;

    public Utente(String nomeUtente, String password, String indirizzo) {

        this.nomeUtente = nomeUtente;
        this.password = password;
        this.indirizzo = indirizzo;
    }

    public String getNomeUtente() {
        return this.nomeUtente;
    }

    public String getPassword() {
        return this.password;
    }

    public String getIndirizzo() {
        return this.indirizzo;
    }

    public ListaSpedizioni getListaSpedizioni() {
        return DataUtility.getSpedizioniUtente(this.nomeUtente);
    }

    public int getContatore() {
        return contatore;
    }

    public void inc() {
        contatore++;
    }

    public void dec() {
        if (contatore > 0) {
            contatore--;
        }
    }

    public String id(String id) {
        Random rand = new Random();
        int n = rand.nextInt(1500000);
        return getNomeUtente() + contatore + n;
    }

    @Override
    public String toString() {
        return nomeUtente + ";" + password + ";" + indirizzo;
    }
}
