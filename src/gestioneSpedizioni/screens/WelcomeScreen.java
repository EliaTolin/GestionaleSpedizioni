package gestioneSpedizioni.screens;

import java.awt.*;  
import java.awt.event.*;   
import javax.swing.*;

/**
 *
 * @author eliatolin
 */

public class WelcomeScreen extends JFrame implements ActionListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel welcomeLabel;
    JLabel informationLabel;
    JPanel mainPanel;
    JButton jbUser;
    JButton jbAdmin;
    
    //Costruttore
    public WelcomeScreen()
    {
        super("Welcome Board!");
        
        setDefaultCloseOperation(javax.swing.
                WindowConstants.DISPOSE_ON_CLOSE);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setSize(500,300);
        
        mainPanel = new JPanel(new GridLayout(0,1));
        
        welcomeLabel = new JLabel("Gestionale Spedizioni", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        
        informationLabel = new JLabel("Come ti vuoi autenticare?", SwingConstants.CENTER);
        informationLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
        
        Dimension dButton = new Dimension(120,40);
        
        jbUser = new JButton("CLIENTE");
        jbUser.setPreferredSize(dButton);
        jbUser.addActionListener(this);
        
        jbAdmin = new JButton("ADMIN");
        jbAdmin.setPreferredSize(dButton);
        jbAdmin.addActionListener(this);
        
        mainPanel.add(welcomeLabel);
        mainPanel.add(informationLabel);
        mainPanel.add(jbUser);
        mainPanel.add(jbAdmin);
        add(mainPanel);
        
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae)
    { 
         if(ae.getSource() == jbUser)
        {
            LoginForm lf = new LoginForm("user");
            lf.setVisible(true);
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
