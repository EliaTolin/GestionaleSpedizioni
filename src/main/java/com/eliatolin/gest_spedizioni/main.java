package com.eliatolin.gest_spedizioni;
import com.eliatolin.gest_spedizioni.screens.*;

import javax.swing.*;

//DEBUG
import com.eliatolin.gest_spedizioni.utils.DataUtility;
import com.eliatolin.gest_spedizioni.models.Utente;
import com.eliatolin.gest_spedizioni.models.ListaUtenti;
import java.awt.*;  
import java.awt.event.*; 
import java.io.*;
/**
 *
 * @author eliatolin
 */

class Gestionale  
{  
    //main() method start  
    public static void main(String arg[])  
    {  
        try  
        {  
            /*LoginForm form = new LoginForm("admin");  
            form.setSize(300,100);
            form.setVisible(true);
            System.out.println("Ciao");*/
            //JFrame frame = new JFrame("Test")
            /*JFrame f = new JFrame("Test");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setSize(800,600);
            JPanel panel = new JPanel();
            JButton b1;
            b1 = new JButton("PROVA");
            panel.add(b1);
            f.add(panel);
            f.setVisible(true);
            */
            // returns pathnames for files and directory
//            Utente us = new Utente("pro","dw","via pawicc");
//            DataUtility.inserisciUtente(us);
//            if(DataUtility.verificaAccesso("prwo", "adw"))
//                System.out.println("Accss");
//            else
//                System.out.println("Not accss");
            Utente us = new Utente("pro","dw","via pawicc");
            ListaUtenti ls = DataUtility.getListaUtenti();
            TabellaSpedizioni ts = new TabellaSpedizioni(ls);
            ts.setVisible(true);
            //WelcomeScreen ws = new WelcomeScreen();
            //ws.setVisible(true);
            
        }  
        catch(Exception e)  
        {     
            //handle exception   
            JOptionPane.showMessageDialog(null, e.getMessage());  
        }  
    }  
}  
