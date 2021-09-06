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
    
    public SpedizioneAssicurata(String user,String id, int peso, Date data, String destinazione, 
            String stato, float valoreAssicurato){
        super(user,id,peso,data,destinazione);
        setStato(stato);
        setValoreAssicurato(valoreAssicurato);
    }
    
    public void setValoreAssicurato(float valoreAssicurato) {
        this.valoreAssicurato = valoreAssicurato;
    }
    
    public void aggiornaStato()
    {
        super.aggiornaStato();
        if(stato=="RIMBORSO_RICHIESTO")
            setStato("RIMBORSO_EROGATO");
    }
    
    public boolean CheckRimborso()
    {
        if(this.getStato().equals("FALLITA"))
            return true;
        return false;
    }
    
    @Override
    public String toString() {
        return getUtente()+";"+getId()+";"+getDestinazione()+";"+getPeso()+";"
                +getDateToString()+";"+getStato()+";"+getValoreAssicurato()+";";
    }
}
