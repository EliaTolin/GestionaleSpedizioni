package gestioneSpedizioni.screens;

//importazione classi e pacchetti richiesti
import gestioneSpedizioni.models.ListaUtenti;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import gestioneSpedizioni.models.Utente;
import gestioneSpedizioni.utils.DataUtility;

/**
 *
 * @author eliatolin
 */

//Form che permette il Login utente
public class LoginForm extends JFrame implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//inizializzo elementi
    JButton btnAccess;
    JButton btnRegistration;
    JButton btnBack;
    JPanel mainPanel;
    JLabel userLabel, passLabel;
    final JTextField txtFieldUsername, txtFieldPassword;
    
    //utilizzo la variabile per vedere se sono admin
    boolean admin = false;

    //costruttore della classe LoginForm
    public LoginForm(String type) {
        setSize(400, 300);

        userLabel = new JLabel();
        userLabel.setText("Login " + type);

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

        //creo il submit button  
        btnAccess = new JButton("ACCEDI");  
        
        btnBack = new JButton("INDIETRO");
        btnBack.addActionListener(this);
        
        //creo il pannello e inserisco gli elementi
        mainPanel = new JPanel(new GridLayout(0, 1));
        mainPanel.setBackground(Color.lightGray);
        mainPanel.add(userLabel);
        mainPanel.add(txtFieldUsername);
        mainPanel.add(passLabel);
        mainPanel.add(txtFieldPassword);
        mainPanel.add(btnAccess);
        
        if (type != "admin") {
            btnRegistration = new JButton("REGISTRAZIONE");
            mainPanel.add(btnRegistration);
            btnRegistration.addActionListener(this);
            this.setTitle("LOGIN UTENTE");
            admin = false;
        } else {
            this.setTitle("LOGIN AMMINISTRATORE");
            admin = true;
        }

        //aggiungo border 
        add(mainPanel, BorderLayout.CENTER);
        
        mainPanel.add(btnBack);
        //attacco l'evento
        btnAccess.addActionListener(this);
        setTitle("LOGIN FORM");
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
                    TabellaSpedizioniForm tb = new TabellaSpedizioniForm(lstUtenti);
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
                    MenuUtenteForm menu = new MenuUtenteForm(u);
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
