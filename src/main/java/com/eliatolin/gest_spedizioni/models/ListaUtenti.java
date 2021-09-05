package com.eliatolin.gest_spedizioni.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaUtenti implements Serializable {

    private List<Utente> Utenti;

    public ListaUtenti(){
        Utenti = new ArrayList<Utente>();
    }
    
    public void Add(Utente s) {
        Utenti.add(s);
    }

    public void Remove(Utente s) {
        Utenti.remove(s);
    }

    public Utente getUtente(int index) {
        return Utenti.get(index);
    }

    public int getNumeroUtenti() {
        return Utenti.size();
    }

}
