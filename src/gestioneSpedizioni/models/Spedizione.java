package gestioneSpedizioni.models;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Date;

/**
 *
 * @author eliatolin
 */

//Si implementa una classe Spedizione che rappresenta una spedizione classica
//non assicurata.
public class Spedizione {

    protected final String user;
    protected final String id;
    protected final String destinazione;
    protected final float peso;
    protected String stato;
    protected final Date date;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    //Costruttore Spedizione con id generato casualmente e stato direttamente 
    //in preparazione
    public Spedizione(String user, float peso, Date data, String destinazione) {
        this.user = user;
        this.id = getCasualId(user);
        this.peso = peso;
        this.date = data;
        this.destinazione = destinazione;
        this.stato = "IN_PREPARAZIONE";
    }
    //Costruttore spedizione con id passato per paramentro e stato in preparazione
    public Spedizione(String user,String id, float peso, Date data, String destinazione) {
        this.user = user;
        this.id = id;
        this.peso = peso;
        this.date = data;
        this.destinazione = destinazione;
        this.stato = "IN_PREPARAZIONE";
    }
    
    //Costruttore con id casuale e stato passato per parametro
    public Spedizione(String user, float peso, Date data, String destinazione,
            String stato) {
        this.user = user;
        this.id = getCasualId(user);
        this.peso = peso;
        this.date = data;
        this.destinazione = destinazione;
        this.stato = stato;
    }
    
    //Costruttore spedizione con tutti i parametri
    public Spedizione(String user,String id,float peso, Date data, String destinazione,
            String stato) {
        this.user = user;
        this.id = id;
        this.peso = peso;
        this.date = data;
        this.destinazione = destinazione;
        this.stato = stato;
    }

    //Metodo che permette l'aggiornamento di uno stato
    public void aggiornaStato() {
        switch (this.stato) {
            case "IN_PREPARAZIONE":
                this.stato = "IN_TRANSITO";
                break;
            case "IN_TRANSITO":
                Random rand = new Random();
                if (1 == rand.nextInt(3)) {
                    this.stato = "FALLITA";
                    break;
                }
                this.stato = "RICEVUTA";
                break;
            default:
                break;
        }
    }

    //metodo che permette di settare lo stato
    public void setStato(String stato) {
        this.stato = stato;
    }

    //metodo che ritorna lo stato
    public String getStato() {
        return this.stato;
    }

    //Metodo che ritorna l'id
    public String getId() {
        return id;
    }
    
    //Metodo che ritorna la destinazione
    public String getDestinazione() {
        return destinazione;
    }

    //Metodo che ritorna il peso
    public float getPeso() {
        return peso;
    }

    //Metodo che ritorna la data
    public Date getDate() {
        return date;
    }

    //Metodo che ritorna la data sotto forma di stringa formattata
    public String getDateToString() {
        return new SimpleDateFormat("dd/MM/yyyy").format(getDate());
    }
    
    //Ritorna l'utente che ha effettuato la spedizione
    public String getUtente() {
        return user;
    }
    
    //Ritorna il valore assicurato
    public float getValoreAssicurato() {
        return 0;
    }
    
    //Metodo che controlla se il rimborso è possibile effettuarlo
    public boolean CheckRimborso() {
        return false;
    }

    public boolean SpedizioneTerminata() {
        return getStato().equals("FALLITA") || getStato().equals("RICEVUTA");
    }

    public final String getCasualId(String value) {
        long unixTime = System.currentTimeMillis() / 1000L;
        String s =  "IT" + value.toUpperCase()
                + String.valueOf(unixTime);
        return s; 
    }

    @Override
    public String toString() {
        return getUtente() + ";" + getId() + ";" + getDestinazione() + ";" + getPeso() + ";"
                + getDateToString() + ";" + getStato() + ";";
    }
}
