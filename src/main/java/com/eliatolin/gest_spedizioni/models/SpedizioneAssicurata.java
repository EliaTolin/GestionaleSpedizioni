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
    
    private final float valoreAssicurato;
    
    public SpedizioneAssicurata(String user, float peso, Date data, String destinazione, 
            String stato, float valoreAssicurato){
        super(user,peso,data,destinazione);
        setStato(stato);
        this.valoreAssicurato = valoreAssicurato;
    }
    
    public SpedizioneAssicurata(String user, float peso, Date data, String destinazione, 
            float valoreAssicurato){
        super(user,peso,data,destinazione);
        this.valoreAssicurato = valoreAssicurato;
    }
    
    @Override
    public float getValoreAssicurato()
    {
        return valoreAssicurato;
    }
    
    @Override
    public void aggiornaStato()
    {
        super.aggiornaStato();
        if("RIMBORSO_RICHIESTO".equals(stato))
            setStato("RIMBORSO_EROGATO");
    }
    
    @Override
    public boolean CheckRimborso()
    {
        return this.getStato().equals("FALLITA");
    }
    
    @Override
    public String toString() {
        return getUtente()+";"+getId()+";"+getDestinazione()+";"+getPeso()+";"
                +getDateToString()+";"+getStato()+";"+getValoreAssicurato()+";";
    }
}
