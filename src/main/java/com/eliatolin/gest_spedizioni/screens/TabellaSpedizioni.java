package com.eliatolin.gest_spedizioni.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import com.eliatolin.gest_spedizioni.models.*;
import java.awt.event.ActionListener;

//import tabella.LayoutCelle;
//import thread.ThreadSpedizioni;



/**
 * Classe che implementa il frame in cui visualizzo in formato tabellare le spedizioni
 */
public  class TabellaSpedizioni extends JFrame implements ActionListener{

    private ListaUtenti lstUtenti;
    private ListaSpedizioni lstSpedizioni;
    private Utente user;
    private boolean admin;
    private boolean modifiche;
    private JLabel ImmagineUtente;
    private JLabel MessaggioUtente;
    private JLabel NomeUtente;
    private JLabel MessaggioAdmin;

    private JButton Indietro;
    private JButton CancellaFinale;
    private JButton StartModifiche;
    private JButton Rimborso;

    private JPanel PannelloNord, PannelloCentro, PannelloSud;
    private JPanel PannelloTabella;

    private JTable t;
    private SpedizioniTableModel tablemodel;

    
    public TabellaSpedizioni(){
        setSize(600,600);
        PannelloNord = new JPanel();
        PannelloCentro = new JPanel();
        PannelloSud = new JPanel();
        PannelloTabella = new JPanel();
        PannelloTabella.setLayout(new BorderLayout());

        //Icon Image = new ImageIcon(getClass().getResource("immagineutente.jpg"));
        //ImmagineUtente = new JLabel(Image);
        NomeUtente = new JLabel(" ");
        MessaggioUtente = new JLabel("se una spedizione assicurata fallisce puoi richiedere il rimborso con il pulsante rimborso");

        MessaggioAdmin = new JLabel("Premi Modifica per modificare le spedizioni degli utenti " +"\n"+
                "premi Rimuovi per canellare le spedizioni in stato finale ");


        Indietro = new JButton("Indietro");
        Indietro.addActionListener(this);
        Rimborso = new JButton("Rimborso");
        Rimborso.addActionListener(this);

        CancellaFinale = new JButton("Rimuovi");
        CancellaFinale.addActionListener(this);
        StartModifiche = new JButton ("Modifica");
        StartModifiche.addActionListener(this);

        setModifiche(false);

        PannelloTabella.add(PannelloNord, BorderLayout.NORTH);
        PannelloTabella.add(PannelloCentro, BorderLayout.CENTER);
        PannelloTabella.add(PannelloSud, BorderLayout.SOUTH);
    }

    /**
     * metodo costruttore del frame tabella per un user
     * @param u user loggato che sta visualizzando le sue spedizioni
     * @param l lista di utenti a cui appartiene l' user u
     */
    public TabellaSpedizioni (Utente u, ListaUtenti l){
        this();
        user = u;
        lstUtenti = l;
        setAdmin(false);

        PannelloNord.add(ImmagineUtente);
        PannelloNord.add(NomeUtente);
        PannelloNord.add(MessaggioUtente);

        tablemodel = new SpedizioniTableModel(user.getListaSpedizioni());
        t = new JTable(tablemodel);
        t.setDefaultRenderer(Object.class, new LayoutCelle());
        t.setPreferredScrollableViewportSize(t.getPreferredSize());
        JScrollPane scrollpane = new JScrollPane(t);
        PannelloCentro.add(scrollpane);

        PannelloSud.add(Indietro);
        PannelloSud.add(Rimborso);

        this.add(PannelloTabella);
    }


    /**
     * metodo costruttore del frame tabella per l'amministratore
     * @param l lista degli utenti registati
     */
    public TabellaSpedizioni(ListaUtenti l){
        this();
        lstUtenti = l;
        setAdmin(true);

        PannelloNord.add(MessaggioAdmin);

        //creo una lista di spedizioni non di un solo user ma di tutti gli utenti presenti nella lista di utenti
        lstSpedizioni = new ListaSpedizioni();
        for (int i=0; i<l.getNumeroUtenti(); i++){
            Utente u = l.getUtente(i);
            ListaSpedizioni tmp = u.getListaSpedizioni();
            for (int j=0; j<tmp.getNumeroSpedizioni(); j++)
                lstSpedizioni.Add(tmp.getSpedizione(j));
        }

        tablemodel = new SpedizioniTableModel(lstSpedizioni);
        t = new JTable(tablemodel);
        t.setDefaultRenderer(Object.class, new LayoutCelle());
        t.setPreferredScrollableViewportSize(t.getPreferredSize());
        JScrollPane scrollPane = new JScrollPane(t);
        PannelloCentro.add(scrollPane);

        PannelloSud.add(Indietro);
        PannelloSud.add(CancellaFinale);
        PannelloSud.add(StartModifiche);

        this.add(PannelloTabella);
    }

    /**
     * metodo che uso per verificare se sono amministratore o user
     * @return true se sono amministratore, false se sono user
     */
    public boolean isAdmin() { return admin; }


    /**
     * metodo che uso nel costruttore dei frame per decidere se sono l'amministratore o
 se sono un user
     * @param admin booleano che decide se sono admi o user
     */
    public void setAdmin ( boolean admin){ this.admin = admin; }

    /**
     * metodo che uso per decidere se creare o non create Thread che modificano le spedizioni
     * @return true se voglio modificare le spedizioni, false altrimenti
     */
    public boolean isModifiche() { return modifiche; }

    /**
     * metodo per settare/resettare la variabile che crea/arresta la crazione di Thread per
     * le modifiche delle spedizioni
     * @param modifiche stato della richiesta di modifiche /stop modifiche
     */
    public void setModifiche(boolean modifiche) { this.modifiche = modifiche;}


    @Override
    public void actionPerformed(ActionEvent e) {
        String Scelta = e.getActionCommand();

        if (Scelta.equals("Indietro")){
/*
            if (isAdmin()){
                lstUtenti.SalvaLista();
                CambiaSchermata(new FrameIniziale(), this);

            }
            else {
                lstUtenti.SalvaLista();
                CambiaSchermata(new FrameUtenteLoggato(lstUtenti, user), this);
            }
            */
        }

        /*
        seleziono una spedizione, se posso richiedere il rimborso modifico il suo stato,
        altimenti segnalo un messaggio di errore e non faccio niente
         */
        /*
        if (Scelta.equals("Rimborso")){
            ListaSpedizioni sped = user.getSpedizioni();
            int numrimborsi = 0;

            for (int i=0; i<t.getRowCount(); i++){
                Spedizione s = sped.get(i);
                if (s.PossibileRimborso()) {
                    t.setValueAt("RICHIESTA RIMBORSO", i, 5);
                    numrimborsi++;
                }
            }
            if (numrimborsi == 0)
                JOptionPane.showMessageDialog(this, "Nessun rimborso richiesto ", "", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(this, "richiesti "+numrimborsi+" rimborsi", "", JOptionPane.INFORMATION_MESSAGE);
        }

        /*
          seleziono una spedizione da rimuovere, se è in uno stato finale la cancello, alrimenti
          segnalo un errore e non faccio niente
         */
        /*
        if (Scelta.equals("Rimuovi")){
            ListaSpedizioni sped = tablemodel.getLista();
            Spedizione s = sped.get(t.getSelectedRow());

            if (s.StatoFinale()){

                String nome = s.getNomeUtente();
                Utente u = lstUtenti.TrovaUtente(nome);
                ListaSpedizioni ListaSpedUtente = u.getSpedizioni();

                ListaSpedUtente.Cancella(s);
                u.dec();
                sped.Cancella(sped.get(t.getSelectedRow()));
                tablemodel.fireTableDataChanged();
                JOptionPane.showMessageDialog(this, "Spedizione eliminata ","", JOptionPane.INFORMATION_MESSAGE);

            }
            else
                JOptionPane.showMessageDialog(this, "Spedizione non in stato finale", "", JOptionPane.ERROR_MESSAGE);
            //listautenti.SalvaLista();
        }
        */
        /*
         se scelgo di modificare le spedizioni, creo e faccio partire i Thread delle modifiche
         fino a che non premo nuovamente il pulsante modifica
         */
        /*
        if (Scelta.equals("Modifica")) {

            setModifiche(!isModifiche());
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    Thread t = new Thread();

                    while (isModifiche()) {
                        t = new ThreadSpedizioni(tablemodel);
                        t.start();

                        try {Thread.sleep(4000); } catch (InterruptedException e) {e.printStackTrace(); }
                        t.interrupt();
                    }
                }
            };
            new Thread(r).start();
        }
        */
    }
    /*
    @Override
    public void windowClosing(WindowEvent e) {
        lstUtenti.SalvaLista();
        System.exit(0);
    }*/
}