package com.eliatolin.gest_spedizioni.screens;

import com.eliatolin.gest_spedizioni.models.ListaUtenti;
import com.eliatolin.gest_spedizioni.models.Utente;
import com.eliatolin.gest_spedizioni.utils.DataUtility;
import java.awt.*;  
import java.awt.event.*;   
import javax.swing.*;


/**
 *
 * @author eliatolin
 */

//Form che permette la scelta delle opeorazioni possibili per l'utente
public class MenuUtenteForm extends JFrame implements ActionListener{
    JLabel labelWelcome;
    JPanel mainPanel;
    JButton jbSpedizione;
    JButton jbSpedAssicurata;
    JButton jbTabella;
    JButton jbBack;
    
    Utente user;
    public MenuUtenteForm(Utente u)
    {
        //definisco le componenti grafiche
        super("Menu");
        
        user = u;
        
        setSize(500,300);
        
        mainPanel = new JPanel(new GridLayout(0,1));
        
        labelWelcome = new JLabel("Gestionale Spedizioni", SwingConstants.CENTER);
        labelWelcome.setFont(new Font("Verdana", Font.BOLD, 18));
        
        Dimension dButton = new Dimension(120,40);
        
        jbSpedizione = new JButton("SPEDIZIONE");
        jbSpedizione.setPreferredSize(dButton);
        jbSpedizione.addActionListener(this);
        
        jbSpedAssicurata = new JButton("SPEDIZIONE ASSICURATA");
        jbSpedAssicurata.setPreferredSize(dButton);
        jbSpedAssicurata.addActionListener(this);
        
        jbTabella = new JButton("TABELLA");
        jbTabella.setPreferredSize(dButton);
        jbTabella.addActionListener(this);
        
        jbBack = new JButton("INDIETRO");
        jbBack.setPreferredSize(dButton);
        jbBack.addActionListener(this);
        
        mainPanel.add(labelWelcome);
        mainPanel.add(jbSpedizione);
        mainPanel.add(jbSpedAssicurata);
        mainPanel.add(jbTabella);
        mainPanel.add(jbBack);
        add(mainPanel);
        
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae)
    { 
         if(ae.getSource() == jbSpedizione)
        {
            AggiungiSpedizioneForm asForm = new AggiungiSpedizioneForm(user,false);
            asForm.setVisible(true);
            this.dispose();
        }
        else if (ae.getSource() == jbSpedAssicurata)
        {
            AggiungiSpedizioneForm asForm = new AggiungiSpedizioneForm(user,true);
            asForm.setVisible(true);
            this.dispose();
        }
        else if(ae.getSource() == jbTabella)
        {
            ListaUtenti lstUtenti = DataUtility.getListaUtenti();
            TabellaSpedizioniForm tb = new TabellaSpedizioniForm(user,lstUtenti);
            tb.setVisible(true);
            this.dispose();
        }
        else if(ae.getSource() == jbBack)
        {
            LoginForm lf = new LoginForm("user");
            lf.setVisible(true);
            this.dispose();
        }
    }
}
