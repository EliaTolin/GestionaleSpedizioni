/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eliatolin.gest_spedizioni.models;
import java.util.Date;
/**
 *
 * @author eliatolin
 */


public class SpedizioneAssicurata extends Spedizione{
    
    private float valoreAssicurato = 0;
    private boolean assicurata = false;
    protected String stato;
    
    public SpedizioneAssicurata(String id, int peso, Date data, String destinazione, 
            String stato, boolean assicurata, float valoreAssicurato){
        super(id,peso,data,destinazione);
        setStato(stato);
        setValoreAssicurato(valoreAssicurato);
        setAssicurata(assicurata);
    }
    
    public void setValoreAssicurato(float valoreAssicurato) {
        this.valoreAssicurato = valoreAssicurato;
    }
    
    public void setAssicurata(boolean assicurata) {
        this.assicurata = assicurata;
    }

    public boolean getAssicurata() {
        return assicurata;
    }
    
    public float getValoreAssicurato()
    {
        return valoreAssicurato;
    }
    
    public void aggiornaStato()
    {
        super.aggiornaStato();
        if(stato=="RIMBORSO_RICHIESTO")
            setStato("RIMBORSO_EROGATO");
    }
    
    @Override
    public String toString() {
        return getId()+";"+getDestinazione()+";"+getPeso()+";"
                +getDateToString()+";"+getValoreAssicurato()+";"+getStato()+";";
    }
}
