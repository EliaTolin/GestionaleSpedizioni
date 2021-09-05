package com.eliatolin.gest_spedizioni.models;
import com.eliatolin.gest_spedizioni.utils.DataUtility;

public class Utente {
	
	private final String nomeUtente;
	private final String password;
	private final String indirizzo;
	
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
        
        public String getIndirizzo()
        {
            return this.indirizzo;
        }
        
        public ListaSpedizioni getListaSpedizioni()
        {
            return DataUtility.getSpedizioniUtente(this.nomeUtente);
        }
	
	@Override
	public String toString() {
		return nomeUtente + ";" + password + ";" + indirizzo;
	}
}
