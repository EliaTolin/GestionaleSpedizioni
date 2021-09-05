package com.eliatolin.gest_spedizioni.models;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Date;
/**
 *
 * @author eliatolin
 */
/**
 *
 * @author eliatolin
 */

public class Spedizione{
    protected final String id;
    protected final String destinazione;
    protected final int peso;
    protected String stato;
    protected final Date date;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
    
    public Spedizione(String id, int peso, Date data, String destinazione){
        this.id = id;
        this.peso = peso;
        this.date = data;
        this.destinazione = destinazione;
        this.stato = "IN_PREPARAZIONE";
    }
    
    public Spedizione(String id, int peso, Date data, String destinazione, 
            String stato){
        this.id = id;
        this.peso = peso;
        this.date = data;
        this.destinazione = destinazione;
        this.stato = stato;
    }
    
    public void aggiornaStato(){
        switch(this.stato){
            case "IN_PREPARAZIONE":
                this.stato = "IN_TRANSITO";
                break;
            case "IN_TRANSITO":
                Random rand = new Random();
                if(1 == rand.nextInt(3)) {
                    this.stato = "FALLITA";
                    break;
                }
                this.stato = "CONSEGNATA";
                break;
            default:
                break;
        }
    }
    
    public void setStato(String stato){
        this.stato = stato;
    }

    
    public String getStato(){
        return this.stato;
    }

    public String getId() {
        return id;
    }

    public String getDestinazione() {
        return destinazione;
    }

    public int getPeso() {
        return peso;
    }

    public Date getDate() {
        return date;
    }
    
    public String getDateToString()
    {
        return sdf.format(date);
    }

    @Override
    public String toString() {
        return getId() + ";"+ getDestinazione() + ";" + getPeso() + ";" +
                getDateToString() +";"+getStato()+";";
    }
}

