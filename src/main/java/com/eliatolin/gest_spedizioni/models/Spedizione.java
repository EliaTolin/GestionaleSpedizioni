package com.eliatolin.gest_spedizioni.models;

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

    public Spedizione(String user, float peso, Date data, String destinazione) {
        this.user = user;
        this.id = getCasualId(user);
        this.peso = peso;
        this.date = data;
        this.destinazione = destinazione;
        this.stato = "IN_PREPARAZIONE";
    }
    
    public Spedizione(String user,String id, float peso, Date data, String destinazione) {
        this.user = user;
        this.id = id;
        this.peso = peso;
        this.date = data;
        this.destinazione = destinazione;
        this.stato = "IN_PREPARAZIONE";
    }
    
    public Spedizione(String user, float peso, Date data, String destinazione,
            String stato) {
        this.user = user;
        this.id = getCasualId(user);
        this.peso = peso;
        this.date = data;
        this.destinazione = destinazione;
        this.stato = stato;
    }
    
    public Spedizione(String user,String id,float peso, Date data, String destinazione,
            String stato) {
        this.user = user;
        this.id = id;
        this.peso = peso;
        this.date = data;
        this.destinazione = destinazione;
        this.stato = stato;
    }

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

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getStato() {
        return this.stato;
    }

    public String getId() {
        return id;
    }

    public String getDestinazione() {
        return destinazione;
    }

    public float getPeso() {
        return peso;
    }

    public Date getDate() {
        return date;
    }

    public String getDateToString() {
        return new SimpleDateFormat("dd/MM/yyyy").format(getDate());
    }

    public String getUtente() {
        return user;
    }

    public float getValoreAssicurato() {
        return 0;
    }

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
