package com.eliatolin.gest_spedizioni.utils;

import com.eliatolin.gest_spedizioni.models.Utente;
import com.eliatolin.gest_spedizioni.models.Spedizione;
import com.eliatolin.gest_spedizioni.models.SpedizioneAssicurata;
import com.eliatolin.gest_spedizioni.models.ListaSpedizioni;
import com.eliatolin.gest_spedizioni.models.ListaUtenti;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DataUtility {

    static String file_user = "user.txt";
    static String file_ship = "ship.txt";

    private static boolean saveInformation(String filename, String value) {
        try {
            File out_file = new File(filename);
            if (!out_file.exists()) {
                out_file.createNewFile();
            }
            try (FileWriter fw = new FileWriter(out_file, true)) {
                fw.write(value);
                fw.write("\n");
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static boolean inserisciUtente(Utente user) {
        if (saveInformation(file_user, user.toString())) {
            return true;
        }
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
            System.err.println("Error - Utente registrato");
            return false;
        }
    }

    public static boolean salvaSpedizione(Spedizione sp) {
        
        if (saveInformation(file_ship, sp.toString())) {
            return true;
        }
        System.out.println("Error - salvataggio spedizione");
        return false;
    }

    public static boolean salvaSpedizione(SpedizioneAssicurata sp) {
       
        if (saveInformation(file_ship, sp.toString())) {
            return true;
        }
        System.out.println("Error - salvataggio spedizione assicurata");
        return false;
    }

    public static Utente getUtente(String username) {
        File in_file;
        FileReader f;
        BufferedReader b;

        try {
            in_file = new File(file_user);
            if (!in_file.exists()) {
                return null;
            }

            f = new FileReader(file_user);
            b = new BufferedReader(f);
            while (true) {
                String s_tmp;
                s_tmp = b.readLine();
                if (s_tmp == null) {
                    return null;
                }
                String[] line = s_tmp.split(";");
                if (line[0].equals(username)) {
                    Utente u = new Utente(line[0],line[1],line[2]);
                    return u;
                }
            }
        } catch (IOException e) {
            System.err.println("Error - Utente registrato");
            return null;
        }
    }
    
    public static ListaSpedizioni getSpedizioniUtente(String username) {
        File in_file;
        FileReader f;
        BufferedReader b;
        ListaSpedizioni lst = new ListaSpedizioni();
        try {
            in_file = new File(file_ship);
            if (!in_file.exists()) {
                return new ListaSpedizioni();
            }
            f = new FileReader(file_ship);
            b = new BufferedReader(f);

            while (true) {
                String s_tmp;
                s_tmp = b.readLine();
                if (s_tmp == null) {
                    break;
                }
                String[] line = s_tmp.split(";");
                if (line[0].equals(username)) {
                    String user, id, dest, strDate, v_ass, stato;
                    Float peso;
                    user = line[0];
                    id = line[1];
                    dest = line[2];
                    peso = Float.parseFloat(line[3]);
                    strDate = line[4];
                    stato = line[5];
                    Date date = new SimpleDateFormat("dd-mm-yyyy").parse(strDate);
                    Spedizione s;
                    if (line.length != 6) {
                        v_ass = line[6];
                        s = new SpedizioneAssicurata(user,peso, date, dest,
                                stato, Float.parseFloat(v_ass));
                    } else {
                        s = new Spedizione(user, peso, date, dest,
                                stato);
                    }
                    lst.Add(s);
                }
            }
            return lst;
        } catch (Exception e) {
            System.err.println("Error - Get Spedizioni utente");
            e.printStackTrace();
            return new ListaSpedizioni();
        }
    }

    public static ListaSpedizioni getSpedizioni() {
        File in_file;
        FileReader f;
        BufferedReader b;
        try {
            ListaSpedizioni lst = new ListaSpedizioni();
            in_file = new File(file_ship);
            if (!in_file.exists()) {
                return new ListaSpedizioni();
            }
            f = new FileReader(file_ship);
            b = new BufferedReader(f);

            while (true) {
                String s_tmp;
                s_tmp = b.readLine();
                if (s_tmp == null) {
                    break;
                }
                String[] line = s_tmp.split(";");
                String user, id, dest, peso, strDate, v_ass, stato;
                user = line[0];
                id = line[1];
                dest = line[2];
                peso = line[3];
                strDate = line[4];
                stato = line[5];
                Date date = new SimpleDateFormat("dd-mm-yyyy").parse(strDate);
                Spedizione s;
                if (line.length != 6) {
                    v_ass = line[6];
                    s = new SpedizioneAssicurata(user, Float.parseFloat(peso), date, dest,
                            stato, Float.parseFloat(v_ass));
                } else {
                    s = new Spedizione(user, Float.parseFloat(peso), date, dest,
                            stato);
                }
                lst.Add(s);
            }
            return lst;
        } catch (IOException | NumberFormatException | ParseException e) {
            System.err.println("Error - Get spedizioni");
            return new ListaSpedizioni();
        }
    }

    public static ListaUtenti getListaUtenti() {
        File in_file;
        FileReader f;
        BufferedReader b;
        ListaUtenti lst = new ListaUtenti();
        try {
            in_file = new File(file_user);
            if (!in_file.exists()) {
                return new ListaUtenti();
            }
            f = new FileReader(file_user);
            b = new BufferedReader(f);

            while (true) {
                String s_tmp;
                s_tmp = b.readLine();
                if (s_tmp == null) {
                    break;
                }
                String[] line = s_tmp.split(";");

                String user, password, indirizzo;
                user = line[0];
                password = line[1];
                indirizzo = line[2];

                Utente u = new Utente(user, password, indirizzo);
                lst.Add(u);
            }
            return lst;
        } catch (IOException e) {
            System.err.println("Error - Get Lista Utenti");
            return new ListaUtenti();
        }
    }
    
    private static void clearFile(String file) throws FileNotFoundException
    {
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.print("");
        }
    }
    
    public static void salvaInfoUtenti(ListaUtenti lista)
    {
        for(int i = 0; i < lista.getNumeroUtenti();i++ )
        {
            Utente us = lista.getUtenteFromIdx(i);
            DataUtility.inserisciUtente(us);
            ListaSpedizioni ls = us.getListaSpedizioni();
            for(int y = 0; y < ls.getNumeroSpedizioni(); y++)
            {
                DataUtility.salvaSpedizione(ls.getSpedizione(i));
            }
        }
    }
}