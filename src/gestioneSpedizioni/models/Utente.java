package gestioneSpedizioni.models;

import gestioneSpedizioni.utils.DataUtility;

/**
 *
 * @author eliatolin
 */

//Classe che definisce un utente
public class Utente {

    private final String nomeUtente;
    private final String password;
    private final String indirizzo;
    public String id;

    //Costruttore dell'oggetto utente
    public Utente(String nomeUtente, String password, String indirizzo) {

        this.nomeUtente = nomeUtente;
        this.password = password;
        this.indirizzo = indirizzo;
    }

    //Ritorna il nome utente
    public String getNomeUtente() {
        return this.nomeUtente;
    }

    //ritorna la password
    public String getPassword() {
        return this.password;
    }

    //Ritorna l'indirizzo
    public String getIndirizzo() {
        return this.indirizzo;
    }

    //Ritorna la lista spedizioni
    public ListaSpedizioni getListaSpedizioni() {
        return DataUtility.getSpedizioniUtente(this.nomeUtente);
    }

    @Override
    public String toString() {
        return nomeUtente + ";" + password + ";" + indirizzo;
    }
}
