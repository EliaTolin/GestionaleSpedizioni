package gestioneSpedizioni.screens;

import gestioneSpedizioni.models.SpedizioneAssicurata;
import gestioneSpedizioni.models.Spedizione;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import gestioneSpedizioni.models.Utente;
import gestioneSpedizioni.utils.DataUtility;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author eliatolin
 */


//Form che permette l'inserimento di una nuova spedizione
public class AggiungiSpedizioneForm extends JFrame implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final int MAX_LEN = 25;
    //inizializzo elementi
    JButton btnAddSpedizione, btnBack;
    JPanel newPanel;
    JLabel pesoLabel,addressLabel, valoreLabel;
    JTextField txtFieldPeso,txtFieldAddress, txtFieldValore;
    Boolean assicurata;
    Utente user;

    //Costruttore che accetta come parametro un utente e un valore booleano
    //che rappresenta se la spedizione è assicurata
    public AggiungiSpedizioneForm(Utente u, Boolean assicurata) {
        
        Dimension dButton = new Dimension(120,40);
        //Definisco le componenti grafiche
        setSize(300, 300);
        user = u;
        this.assicurata = assicurata;

        //creo la label per la password
        pesoLabel = new JLabel();
        pesoLabel.setText("Peso");  

        //creo la textfield per l'inserimento della password 
        txtFieldPeso = new JTextField(MAX_LEN);

        //creo la label per la password
        addressLabel = new JLabel();
        addressLabel.setText("Indirizzo");
        txtFieldAddress = new JTextField(MAX_LEN);

        btnAddSpedizione = new JButton("Aggiungi spedizione");
        btnBack = new JButton("Indietro");
        btnAddSpedizione.setPreferredSize(dButton);
        btnBack.setPreferredSize(dButton);
        
        //creo il pannello e inserisco gli elementi
        newPanel = new JPanel(new GridLayout(0, 1));
        newPanel.setBackground(Color.lightGray);
        
        //peso
        newPanel.add(pesoLabel);
        newPanel.add(txtFieldPeso);
        
        //indirizzo
        newPanel.add(addressLabel);
        newPanel.add(txtFieldAddress);

        //valore
        if (assicurata) {
            valoreLabel = new JLabel();
            valoreLabel.setText("Valore assicurato");
            txtFieldValore = new JTextField(MAX_LEN);
            newPanel.add(valoreLabel);
            newPanel.add(txtFieldValore);
        }
        //bottoni
        newPanel.add(btnAddSpedizione);
        newPanel.add(btnBack);

        //aggiungo border 
        add(newPanel, BorderLayout.CENTER);

        //attacco l'evento
        btnAddSpedizione.addActionListener(this);
        btnBack.addActionListener(this);

        setTitle("AGGIUNTA SPEDIZIONI");
    }
    
    //Catturo gli eventi
    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == btnAddSpedizione) {
            String address = txtFieldAddress.getText();
            Float peso;
            try {
                String pesoTmp = txtFieldPeso.getText();
                if (pesoTmp.length() == 0) {
                    JOptionPane.showMessageDialog(null,
                            "Inserire peso",
                            "Errore inserimento spedizione",
                            JOptionPane.ERROR_MESSAGE);
                    return;

                }
                peso = Float.parseFloat(txtFieldPeso.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Errore conversione peso",
                        "Errore inserimento spedizione",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (address.length() == 0) {
                JOptionPane.showMessageDialog(null,
                        "Inserisci tutte le informazioni",
                        "Errore inserimento spedizione",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (peso <= 0) {
                JOptionPane.showMessageDialog(null,
                        "Errore inserimento peso",
                        "Errore inserimento spedizione",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date date1;
                String dateTmp = sdf.format(new Date());
                date1 = sdf.parse(dateTmp);
                Spedizione s;
                if (assicurata) {
                    Float valore;
                    try {
                        String valAssTmp = txtFieldValore.getText();
                        if (valAssTmp.length() == 0) {
                            JOptionPane.showMessageDialog(null,
                                    "Inserire valore assicurato",
                                    "Errore inserimento spedizione",
                                    JOptionPane.ERROR_MESSAGE);
                            return;

                        }
                        valore = Float.parseFloat(valAssTmp);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null,
                                "Errore conversione valore assicurato",
                                "Errore inserimento spedizione",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    s = new SpedizioneAssicurata(user.getNomeUtente(), peso, date1, address, valore);
                } else {
                    s = new Spedizione(user.getNomeUtente(), peso, date1, address);
                }

                if (!DataUtility.salvaSpedizione(s)) {
                    JOptionPane.showMessageDialog(null,
                            "Errore salvataggio spedizione",
                            "Errore registrazione",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null,
                        "Errore conversione data",
                        "Errore registrazione",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(null,
                    "Registrazione della spedizione "
                    + " avvenuta con successo",
                    "Conferma registrazione",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (ae.getSource() == btnBack) {
            MenuUtenteForm menu = new MenuUtenteForm(user);
            menu.setVisible(true);
            this.dispose();
        }

        MenuUtenteForm menu = new MenuUtenteForm(user);
        menu.setVisible(true);
        this.dispose();

    }

}
