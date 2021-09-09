package com.eliatolin.gest_spedizioni.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eliatolin
 */

// Si implementa una classe ListaUtenti per contenere gli utenti
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
    
    //dato un indice ritorna la utente presente nella posizione index
    //della lista spedizioni.
    public Utente getUtenteFromIdx(int index) {
        return Utenti.get(index);
    }
    
    //dato una stringa ritorna l'utente con il determinato username.
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
