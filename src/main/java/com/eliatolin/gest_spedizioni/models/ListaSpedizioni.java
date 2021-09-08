package com.eliatolin.gest_spedizioni.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaSpedizioni implements Serializable {

    private final List<Spedizione> Spedizioni;

    public ListaSpedizioni() {
        Spedizioni = new ArrayList<Spedizione>();
    }
    
    public void Add(Spedizione s) {
        Spedizioni.add(s);
    }

    public void Remove(Spedizione s) {
        Spedizioni.remove(s);
    }

    public Spedizione getSpedizione(int index) {
        return Spedizioni.get(index);
    }

    public int getNumeroSpedizioni() {
        return Spedizioni.size();
    }

}
