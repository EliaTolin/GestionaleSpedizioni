package com.eliatolin.gest_spedizioni.screens;

//importazione classi e pacchetti richiesti
import com.eliatolin.gest_spedizioni.models.ListaUtenti;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Exception;
import com.eliatolin.gest_spedizioni.models.Utente;
import com.eliatolin.gest_spedizioni.utils.DataUtility;

public class LoginForm extends JFrame implements ActionListener {

    //inizializzo elementi
    JButton btnAccess;
    JButton btnRegistration;
    JButton btnBack;
    JPanel newPanel;
    JLabel userLabel, passLabel;
    final JTextField txtFieldUsername, txtFieldPassword;
    boolean admin = false;

    //costruttore
    public LoginForm(String type) {
        setSize(400, 300);

        userLabel = new JLabel();
        userLabel.setText("Login " + type);

        //creo la label con l'username  
        userLabel = new JLabel();
        userLabel.setText("Username");      //set label value for txtFieldUsername  

        //creo la textfield per l'inserimento del username
        txtFieldUsername = new JTextField(15);    //set length of the text  

        //creo la label per la password
        passLabel = new JLabel();
        passLabel.setText("Password");      //set label value for txtFieldPassword  

        //creo la textfield per l'inserimento della password 
        txtFieldPassword = new JPasswordField(15);    //set length for the password  

        //creo il submit button  
        btnAccess = new JButton("ACCEDI"); //set label to button  
        
        btnBack = new JButton("INDIETRO");
        btnBack.addActionListener(this);
        //creo il pannello e inserisco gli elementi
        newPanel = new JPanel(new GridLayout(0, 1));
        newPanel.setBackground(Color.lightGray);
        newPanel.add(userLabel);
        newPanel.add(txtFieldUsername);
        newPanel.add(passLabel);
        newPanel.add(txtFieldPassword);
        newPanel.add(btnAccess);
        
        if (type != "admin") {
            btnRegistration = new JButton("REGISTRAZIONE");
            newPanel.add(btnRegistration);
            btnRegistration.addActionListener(this);
            this.setTitle("LOGIN UTENTE");
            admin = false;
        } else {
            this.setTitle("LOGIN AMMINISTRATORE");
            admin = true;
        }

        //aggiungo border 
        add(newPanel, BorderLayout.CENTER);
        
        newPanel.add(btnBack);
        //attacco l'evento
        btnAccess.addActionListener(this);
        setTitle("LOGIN FORM");
    }

    private Utente[] getUserFromDatabase() {
        Utente[] user_list = new Utente[1];

        return user_list;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnAccess) {
            String username = txtFieldUsername.getText();
            String password = txtFieldPassword.getText();

            if (username.length() == 0 || password.length() == 0) {
                return;
            }

            if (admin) {
                if (username.equals("admin") && password.equals("admin")) {
                    ListaUtenti lstUtenti = DataUtility.getListaUtenti();
                    TabellaSpedizioni tb = new TabellaSpedizioni(lstUtenti);
                    tb.setVisible(true);
                    this.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,
                            "Credenziali errate",
                            "Errore accesso",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                if (DataUtility.verificaAccesso(username, password)) {
                    Utente u = DataUtility.getUtente(username);
                    MenuUtente menu = new MenuUtente(u);
                    menu.setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Credenziali errate",
                            "Errore accesso",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        } else if (ae.getSource() == btnRegistration) {
            RegistrazioneForm rf = new RegistrazioneForm();
            rf.setVisible(true);
            this.dispose();
        } else if (ae.getSource() == btnBack)
        {
            WelcomeScreen ws = new WelcomeScreen();
            ws.setVisible(true);
            this.dispose();
        }

    }
}
