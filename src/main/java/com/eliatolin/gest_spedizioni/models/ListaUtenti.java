package com.eliatolin.gest_spedizioni.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaUtenti implements Serializable {

    private List<Utente> Utenti;

    public ListaUtenti() {
        Utenti = new ArrayList<Utente>();
    }

    public void Add(Utente s) {
        Utenti.add(s);
    }

    public void Remove(Utente s) {
        Utenti.remove(s);
    }

    public Utente getUtenteFromIdx(int index) {
        return Utenti.get(index);
    }

    public Utente getUtenteFromName(String nome) {
        for (int i = 0; i < getNumeroUtenti(); i++) {
            Utente tmp = getUtenteFromIdx(i);
            if (tmp.getNomeUtente().equals(nome))
                return tmp;
        }
        return null;
    }

    public int getNumeroUtenti() {
        return Utenti.size();
    }

}
