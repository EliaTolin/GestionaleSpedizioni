package com.eliatolin.gest_spedizioni.screens;

import com.eliatolin.gest_spedizioni.models.ListaUtenti;
import com.eliatolin.gest_spedizioni.models.Utente;
import com.eliatolin.gest_spedizioni.utils.DataUtility;
import java.awt.*;  
import java.awt.event.*;   
import javax.swing.*;
import java.lang.Exception;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eliatolin
 */

public class MenuUtente extends JFrame implements ActionListener{
    JLabel labelWelcome;
    JLabel label;
    JPanel panel;
    JButton jbSpedizione;
    JButton jbSpedAssicurata;
    JButton jbTabella;
    Utente user;
    public MenuUtente(Utente u)
    {
        super("Menu");
        user = u;
        setDefaultCloseOperation(javax.swing.
                WindowConstants.DISPOSE_ON_CLOSE);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setSize(500,300);
        
        panel = new JPanel(new GridLayout(0,1));
        
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
        
        panel.add(labelWelcome);
        panel.add(jbSpedizione);
        panel.add(jbSpedAssicurata);
        panel.add(jbTabella);
        add(panel);
        
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae)
    { 
         if(ae.getSource() == jbSpedizione)
        {
            
        }
        else if (ae.getSource() == jbSpedAssicurata)
        {
            
        }
        else if(ae.getSource() == jbTabella)
        {
            ListaUtenti lstUtenti = DataUtility.getListaUtenti();
            TabellaSpedizioni tb = new TabellaSpedizioni(user,lstUtenti);
            tb.setVisible(true);
            this.dispose();
        }
    }
}
