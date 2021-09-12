package gestioneSpedizioni.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eliatolin
 */

// Si implementa una classe ListaUtenti per contenere gli utenti
public class ListaUtenti implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Utente> Utenti;

    public ListaUtenti() {
        Utenti = new ArrayList<Utente>();
    }
    
    //Metodo per aggiungere un utente nella lista
    public void Add(Utente s) {
        Utenti.add(s);
    }
    
    //Metodo per rimuovere un utente nella lista
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

    //Ritorna il numero totale di utenti
    public int getNumeroUtenti() {
        return Utenti.size();
    }

}
