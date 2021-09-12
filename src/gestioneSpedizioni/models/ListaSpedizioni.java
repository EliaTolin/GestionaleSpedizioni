package gestioneSpedizioni.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eliatolin
 */

// Si implementa una classe ListaSpedizioni per contenere le spedizioni sia normale 
// che assicurate tramite l'utilizzo dei generics. 
public class ListaSpedizioni implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final List<Spedizione> Spedizioni;

	//Costruttore ListaSpedizioni
    public ListaSpedizioni() {
        Spedizioni = new ArrayList<Spedizione>();
    }
    
    //Metodo per aggiungere una spedizione
    public void Add(Spedizione s) {
        Spedizioni.add(s);
    }
    
    //Metodo per rimuovere una spedizione
    public void Remove(Spedizione s) {
        Spedizioni.remove(s);
    }
    
    //dato un indice ritorna la spedizione presente nella posizione index
    //della lista spedizioni.
    public Spedizione getSpedizione(int index) {
        return Spedizioni.get(index);
    }

    //Ritorna il numero di spedizioni nella lista
    public int getNumeroSpedizioni() {
        return Spedizioni.size();
    }

}
