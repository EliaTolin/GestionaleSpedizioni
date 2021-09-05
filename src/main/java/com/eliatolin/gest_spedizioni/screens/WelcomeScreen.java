package com.eliatolin.gest_spedizioni.screens;

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

public class WelcomeScreen extends JFrame implements ActionListener{
    JLabel labelWelcome;
    JLabel label;
    JPanel panel;
    JButton jbUser;
    JButton jbAdmin;
    
    public WelcomeScreen()
    {
        super("Welcome Board!");
        
        setDefaultCloseOperation(javax.swing.
                WindowConstants.DISPOSE_ON_CLOSE);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setSize(500,300);
        
        panel = new JPanel(new GridLayout(0,1));
        
        labelWelcome = new JLabel("Gestionale Spedizioni", SwingConstants.CENTER);
        labelWelcome.setFont(new Font("Verdana", Font.BOLD, 18));
        
        label = new JLabel("Come ti vuoi autenticare?", SwingConstants.CENTER);
        label.setFont(new Font("Verdana", Font.PLAIN, 12));
        
        Dimension dButton = new Dimension(120,40);
        
        jbUser = new JButton("CLIENTE");
        jbUser.setPreferredSize(dButton);
        jbUser.addActionListener(this);
        
        jbAdmin = new JButton("ADMIN");
        jbAdmin.setPreferredSize(dButton);
        jbAdmin.addActionListener(this);
        
        panel.add(labelWelcome);
        panel.add(label);
        panel.add(jbUser);
        panel.add(jbAdmin);
        add(panel);
        
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae)
    { 
         if(ae.getSource() == jbUser)
        {
            LoginForm lf = new LoginForm("user");
            lf.setVisible(true);
            //this.setVisible?????
            this.dispose();
        }
        else if (ae.getSource() == jbAdmin)
        {
            LoginForm lf = new LoginForm("admin");
            lf.setVisible(true);
            this.dispose();
        }
    }
}
