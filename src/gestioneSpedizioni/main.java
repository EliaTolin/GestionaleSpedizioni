package gestioneSpedizioni;
import javax.swing.*;

import gestioneSpedizioni.screens.*;

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
            WelcomeScreen ws = new WelcomeScreen();
            ws.setVisible(true);
        }  
        catch(Exception e)  
        {     
            //handle exception   
            JOptionPane.showMessageDialog(null, e.getMessage());  
        }  
    }  
}  
