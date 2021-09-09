package com.eliatolin.gest_spedizioni.utils;

import com.eliatolin.gest_spedizioni.models.Utente;
import com.eliatolin.gest_spedizioni.models.Spedizione;
import com.eliatolin.gest_spedizioni.models.SpedizioneAssicurata;
import com.eliatolin.gest_spedizioni.models.ListaSpedizioni;
import com.eliatolin.gest_spedizioni.models.ListaUtenti;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author eliatolin
 */
//Classe che permette l'iterazione con la base di dati, per salvataggio o lettura.
public abstract class DataUtility {

    //File dove vengono salvati gli utenti.
    static String file_user = "user.txt";
    //File dove vengono salvate le spedizioni.
    static String file_ship = "ship.txt";

    //Metodo che dato un valore lo salva nel file passato per parametro.
    private static boolean saveInformation(String filename, String value) {
        try {
            File out_file = new File(filename);
            if (!out_file.exists()) {
                out_file.createNewFile();
            }
            try ( FileWriter fw = new FileWriter(out_file, true)) {
                fw.write(value);
                fw.write("\n");
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    //Metodo che permette il salvataggio di un'utente.
    public static boolean inserisciUtente(Utente user) {
        if (saveInformation(file_user, user.toString())) {
            return true;
        }
        System.out.println("Error - salvataggio utente");
        return false;
    }

    //Metodo che verifica la possibilità di accesso di un'utente.
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

    //Metodo che verifica se un'utente è registrato.
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

    //Metodo che permette il salvataggio di una spedizione normale.
    public static boolean salvaSpedizione(Spedizione sp) {
        if (CheckSpedizioneLine(sp.toString())) {
            return true;
        }
        if (saveInformation(file_ship, sp.toString())) {
            return true;
        }
        System.out.println("Error - salvataggio spedizione");
        return false;
    }

    //Metodo che permette il salvataggio di una spedizione assicurata.
    public static boolean salvaSpedizione(SpedizioneAssicurata sp) {

        if (CheckSpedizioneLine(sp.toString())) {
            return true;
        }
        if (saveInformation(file_ship, sp.toString())) {
            return true;
        }
        System.out.println("Error - salvataggio spedizione assicurata");
        return false;
    }

    //Metodo che ritorna un utente dato un'username.
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
                    Utente u = new Utente(line[0], line[1], line[2]);
                    return u;
                }
            }
        } catch (IOException e) {
            System.err.println("Error - Utente registrato");
            return null;
        }
    }

    //Metodo che ritorna una lista spedizioni dato un utente.
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
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse(strDate);
                    Spedizione s;
                    if (line.length != 6) {
                        v_ass = line[6];
                        s = new SpedizioneAssicurata(user, id, peso, date, dest,
                                stato, Float.parseFloat(v_ass));
                    } else {
                        s = new Spedizione(user, id, peso, date, dest,
                                stato);
                    }
                    lst.Add(s);
                }
            }
            return lst;
        } catch (IOException | NumberFormatException | ParseException e) {
            System.err.println("Error - Get Spedizioni utente");
            return new ListaSpedizioni();
        }
    }

    //Metodo che controlla l'esistenza di una spedizione all'interno del file 
    //spedizioni.
    public static Boolean CheckSpedizioneLine(String lineSped) {
        File in_file;
        FileReader f;
        BufferedReader b;
        try {
            in_file = new File(file_ship);
            if (!in_file.exists()) {
                return false;
            }
            f = new FileReader(file_ship);
            b = new BufferedReader(f);

            while (true) {
                String s_tmp;
                s_tmp = b.readLine();
                if (s_tmp == null) {
                    break;
                }
                if (s_tmp.equals(lineSped)) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            System.err.println("Error - CheckID");
            return false;
        }
    }

    //Metodo che ritorna la lista utenti.
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

    //Metodo che permette la pulizia di un file.
    private static void clearFile(String file) throws FileNotFoundException {
        File in_file = new File(file_user);
        if (!in_file.exists()) {
            return;
        }
        try ( PrintWriter writer = new PrintWriter(file)) {
            writer.print("");
        }
    }

    //Metodo che salva tutte le info degli utenti.
    public static void salvaInfo(ListaUtenti lista) {
        List<ListaSpedizioni> lstList = new ArrayList<>();

        for (int i = 0; i < lista.getNumeroUtenti(); i++) {
            Utente us = lista.getUtenteFromIdx(i);
            ListaSpedizioni ls = us.getListaSpedizioni();
            lstList.add(ls);
        }

        try {
            clearFile(file_ship);
        } catch (FileNotFoundException ex) {
            System.out.println("Error - Clear file");
        }

        for (int i = 0; i < lista.getNumeroUtenti(); i++) {
            Utente us = lista.getUtenteFromIdx(i);
            if (!DataUtility.utenteRegistrato(us.getNomeUtente())) {
                DataUtility.inserisciUtente(us);
            }
            ListaSpedizioni ls = lstList.get(i);
            for (int y = 0; y < ls.getNumeroSpedizioni(); y++) {
                DataUtility.salvaSpedizione(ls.getSpedizione(y));
            }
        }
    }

    //Salvataggio ListaSpedizioni
    public static void SalvaListaSpedizione(ListaSpedizioni ls) {
        try {
            clearFile(file_ship);
        } catch (FileNotFoundException ex) {
            System.out.println("Error - Clear file");
        }
        for (int y = 0; y < ls.getNumeroSpedizioni(); y++) {
            DataUtility.salvaSpedizione(ls.getSpedizione(y));
        }
    }

    //Salvataggio ListaSpedizioni
    public static void SalvaListaSpedizione(ListaSpedizioni ls, String user) {
        if(ls == null)
            return;
        //Inizializzo 
        ListaUtenti lstUtenti = getListaUtenti();
        List<ListaSpedizioni> lstList = new ArrayList<>();
        //Mi recupero tutti gli utenti registrati
        for (int i = 0; i < lstUtenti.getNumeroUtenti(); i++) {
            Utente us = lstUtenti.getUtenteFromIdx(i);
            //Scarto quello di cui devo salvare le modifiche
            if (!us.getNomeUtente().equals(user)) {
                ListaSpedizioni lst_tmp = us.getListaSpedizioni();
                lstList.add(lst_tmp);
            }
        }
        //Elimino il contenuto del file ship
        try {
            clearFile(file_ship);
        } catch (FileNotFoundException ex) {
            System.out.println("Error - Clear file");
        }
        //Salvo la nuova lista spedizione del nuovo utente
        for (int y = 0; y < ls.getNumeroSpedizioni(); y++) {
            DataUtility.salvaSpedizione(ls.getSpedizione(y));
        }
        //Salvo le precedenti informazioni
        for (int i = 0; i < lstList.size(); i++) {
            ListaSpedizioni lst_tmp = lstList.get(i);
            for (int z = 0; z < lst_tmp.getNumeroSpedizioni(); z++) {
                DataUtility.salvaSpedizione(lst_tmp.getSpedizione(z));
            }
        }
    }
}
