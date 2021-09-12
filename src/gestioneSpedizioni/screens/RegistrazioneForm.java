package gestioneSpedizioni.screens;

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
import gestioneSpedizioni.models.Utente;
import gestioneSpedizioni.utils.DataUtility;

/**
 *
 * @author eliatolin
 */

//Form che permette la registrazione di un utente
public class RegistrazioneForm extends JFrame implements ActionListener  {
    
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//inizializzo elementi
    JButton btnRegistration,btnHome;
    JPanel mainPanel;  
    JLabel userLabel, passLabel, addressLabel;  
    final JTextField  txtFieldUsername, txtFieldPassword, txtFieldAddress;  

    //Costruttore senza parametri
    public RegistrazioneForm()
    {
        setSize(200,300);
        
        //creo la label con l'username  
        userLabel = new JLabel();  
        userLabel.setText("Username");
          
        //creo la textfield per l'inserimento del username
        txtFieldUsername = new JTextField(15);
  
        //creo la label per la password
        passLabel = new JLabel();  
        passLabel.setText("Password");
          
        //creo la textfield per l'inserimento della password 
        txtFieldPassword = new JPasswordField(15);
        
        //creo la label per la password
        addressLabel = new JLabel();  
        addressLabel.setText("Indirizzo");
          
        //creo la textfield per l'inserimento della password 
        txtFieldAddress = new JTextField(20);
        
        btnRegistration = new JButton("Registrati");
        btnHome = new JButton("Indietro");
        
        //creo il pannello e inserisco gli elementi
        mainPanel = new JPanel(new GridLayout(0, 1));  
        mainPanel.setBackground(Color.lightGray);
        mainPanel.add(userLabel);   
        mainPanel.add(txtFieldUsername); 
        mainPanel.add(passLabel); 
        mainPanel.add(txtFieldPassword);  
        mainPanel.add(addressLabel);
        mainPanel.add(txtFieldAddress);
        mainPanel.add(btnRegistration);
        mainPanel.add(btnHome);
        
        //aggiungo border 
        add(mainPanel, BorderLayout.CENTER);  
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
            if(DataUtility.utenteRegistrato(username))
            {
                JOptionPane.showMessageDialog(null,
                    "Errore utente già registrato",
                    "Errore registrazione",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(!DataUtility.inserisciUtente(new Utente(username,password,address)))
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
