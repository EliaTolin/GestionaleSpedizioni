/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eliatolin.gest_spedizioni.screens;

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
import java.lang.Exception;  
import com.eliatolin.gest_spedizioni.models.Utente;
import com.eliatolin.gest_spedizioni.utils.Database;

/**
 *
 * @author eliatolin
 */
public class RegistrazioneForm extends JFrame implements ActionListener  {
    
     //inizializzo elementi
    JButton btnRegistration,btnHome;
    JPanel newPanel;  
    JLabel userLabel, passLabel, addressLabel;  
    final JTextField  txtFieldUsername, txtFieldPassword, txtFieldAddress;  

    public RegistrazioneForm()
    {
        setSize(200,300);
        
        JLabel information = new JLabel("Registrazione utente");
        
        //creo la label con l'username  
        userLabel = new JLabel();  
        userLabel.setText("Username");      //set label value for txtFieldUsername  
          
        //creo la textfield per l'inserimento del username
        txtFieldUsername = new JTextField(15);    //set length of the text  
  
        //creo la label per la password
        passLabel = new JLabel();  
        passLabel.setText("Password");      //set label value for txtFieldPassword  
          
        //creo la textfield per l'inserimento della password 
        txtFieldPassword = new JPasswordField(15);
        
        //creo la label per la password
        addressLabel = new JLabel();  
        addressLabel.setText("Password");      //set label value for txtFieldPassword  
          
        //creo la textfield per l'inserimento della password 
        txtFieldAddress = new JTextField(20);
        
        btnRegistration = new JButton("Registrati");
        btnHome = new JButton("Indietro");
        
        //creo il pannello e inserisco gli elementi
        newPanel = new JPanel(new GridLayout(0, 1));  
        newPanel.setBackground(Color.lightGray);
        newPanel.add(userLabel);   
        newPanel.add(txtFieldUsername); 
        newPanel.add(passLabel); 
        newPanel.add(txtFieldPassword);  
        newPanel.add(addressLabel);
        newPanel.add(txtFieldAddress);
        newPanel.add(btnRegistration);
        newPanel.add(btnHome);
        
        //aggiungo border 
        add(newPanel, BorderLayout.CENTER);  
        //attacco l'evento
        btnRegistration.addActionListener(this);
        btnHome.addActionListener(this);
        
        setTitle("REGISTRAZIONE FORM");
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getSource() == btnRegistration)
        {
            String username = txtFieldUsername.getText();
            String password = txtFieldPassword.getText();
            String address = txtFieldAddress.getText();
            
            if(username.length()==0 || password.length()==0 ||
                    address.length() == 0)
            {
                 JOptionPane.showMessageDialog(null,
                    "Inserisci tutte le informazioni",
                    "Errore registrazione",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(Database.utenteRegistrato(username))
            {
                JOptionPane.showMessageDialog(null,
                    "Errore utente già registrato",
                    "Errore registrazione",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(!Database.inserisciUtente(new Utente(username,password,address)))
            {
                JOptionPane.showMessageDialog(null,
                    "Errore inserimento utente",
                    "Errore registrazione",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            JOptionPane.showMessageDialog(null,
                    "Registrazione dell'utente "
                        +username+" avvenuta con successo",
                    "Conferma registrazione",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        
        LoginForm lf = new LoginForm("user");
        lf.setVisible(true);
        this.dispose();
    }
    
}
