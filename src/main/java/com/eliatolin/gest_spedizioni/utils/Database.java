package com.eliatolin.gest_spedizioni.utils;

import com.eliatolin.gest_spedizioni.models.Utente;
import com.eliatolin.gest_spedizioni.models.Spedizione;
import com.eliatolin.gest_spedizioni.models.SpedizioneAssicurata;

import java.io.*;

public abstract class Database {

    static String file_user = "user.txt";
    static String file_ship = "ship.txt";

    
    private static boolean saveInformation(String filename, String value)
    {
        try {
            File out_file = new File(filename);
            if (!out_file.exists()) {
                out_file.createNewFile();
            }
            FileWriter fw = new FileWriter(out_file, true);
            fw.write(value);
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    //
    public static boolean inserisciUtente(Utente user) {
        if(saveInformation(file_user,user.toString()))
            return true;
        System.out.println("Error - salvataggio utente");
        return false;
    }

    public static boolean verificaAccesso(String username, String password) {
        File in_file;
        FileReader f;
        BufferedReader b;

        try {
            in_file = new File(file_user);
            if (!in_file.exists()) {
                return false;
            }

            f = new FileReader(file_user);
            b = new BufferedReader(f);
            while (true) {
                String s_tmp;
                s_tmp = b.readLine();
                if (s_tmp == null) {
                    return false;
                }
                String[] line = s_tmp.split(";");
                if (line[0].equals(username)) {
                    if (line[1].equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error - Verifica accesso");
            e.printStackTrace();
            return false;
        }
    }

    public static boolean utenteRegistrato(String username) {
        File in_file;
        FileReader f;
        BufferedReader b;

        try {
            in_file = new File(file_user);
            if (!in_file.exists()) {
                return false;
            }

            f = new FileReader(file_user);
            b = new BufferedReader(f);
            while (true) {
                String s_tmp;
                s_tmp = b.readLine();
                if (s_tmp == null) {
                    return false;
                }
                String[] line = s_tmp.split(";");
                if (line[0].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error - Verifica accesso");
            e.printStackTrace();
            return false;
        }
    }

    public static boolean salvaSpedizione(Spedizione sp) {
        if(saveInformation(file_ship,sp.toString()))
            return true;
        System.out.println("Error - salvataggio spedizione");
        return false;
    }
    
    public static boolean salvaSpedizione(SpedizioneAssicurata sp) {
        if(saveInformation(file_ship,sp.toString()))
            return true;
        System.out.println("Error - salvataggio spedizione assicurata");
        return false;
    }
}

//
//    /**
//     * Salva le spedizioni all'interno del sistema.
//     *
//     * @param dashboard Dashboard di riferimento da cui estrarre le spedizioni.
//     * @return boolean
//     */
//    public static boolean saveSpedizioni(Dashboard dashboard){
//        File out;
//        FileWriter fw;
//        //Provo a scrivere il file delle spedizioni
//        try {
//            out = new File("com/dariovarriale/data/spedizioni.txt");
//            fw = new FileWriter(out);
//        } catch(IOException e) {
//            System.err.println("Errore nella creazione del file");
//            e.printStackTrace();
//            return false;
//        }
//
//        try {
//            StringBuilder s = new StringBuilder();
//            for(Spedizione sped : dashboard.getSpedizioni()) s.append(sped.toString());
//
//            fw.write(s.toString());
//            fw.close();
//        } catch(IOException e) {
//            System.err.println("Errore nella srittura su file");
//            e.printStackTrace();
//            return false;
//        }
//
//        return true;
//    }
//
//    /**
//     * Controlla se c'è un utente che non ha effettuato il logout, in modo da permettergli di fare un
//     * accesso veloce alla dashboard. In caso positivo ritorna lo username dell'utente.
//     *
//     * @return String
//     */
//    public static String getLog(){
//        InputStreamReader in;
//        BufferedReader b;
//        File f;
//        //Provo a leggere l'ultimo utente loggato
//        try{
//            f = new File("com/dariovarriale/data/lastLogged.txt");
//            if(!f.exists()) return null;
//
//            in = new InputStreamReader(new FileInputStream(f));
//            b = new BufferedReader(in);
//
//            String s = b.readLine();
//
//            if(s != null){
//                return s;
//            }
//
//        } catch(IOException e){
//            System.err.println("Errore nell'apertura del file");
//            e.printStackTrace();
//            return null;
//        }
//        //In caso di errori ritorna null
//        return null;
//    }
//
//    /**
//     * Salva lo username dell'ultimo utente che non ha effettuato il logout.
//     *
//     * @param username Username dell'utente da salvare.
//     * @return boolean
//     */
//    public static boolean saveLog(String username){
//        File out;
//        FileWriter fw;
//        //Provo a scrivere l'utente che non ha effettuato il logout
//        try {
//            out = new File("com/dariovarriale/data/lastLogged.txt");
//            fw = new FileWriter(out);
//        } catch(IOException e) {
//            System.err.println("Errore nella creazione del file");
//            e.printStackTrace();
//            return false;
//        }
//        try {
//            fw.write(username);
//            fw.close();
//        } catch(IOException e) {
//            System.err.println("Errore nella srittura su file");
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//}
