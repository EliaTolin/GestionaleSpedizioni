package com.eliatolin.gest_spedizioni.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import com.eliatolin.gest_spedizioni.models.*;
import com.eliatolin.gest_spedizioni.models.LayoutCelle;
import com.eliatolin.gest_spedizioni.models.ListaSpedizioni;
import com.eliatolin.gest_spedizioni.models.ListaUtenti;
import com.eliatolin.gest_spedizioni.models.Spedizione;
import com.eliatolin.gest_spedizioni.models.SpedizioniTableModel;
import com.eliatolin.gest_spedizioni.models.Utente;
import com.eliatolin.gest_spedizioni.utils.DataUtility;
import com.eliatolin.gest_spedizioni.utils.ThreadSpedizioniCasuali;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

public class TabellaSpedizioni extends JFrame implements ActionListener, WindowListener {

    private ListaUtenti lstUtenti;
    private ListaSpedizioni lstSpedizioni;
    private Utente user;
    private boolean is_administrator;
    private boolean enable_modify;

    private JButton btnIndietro;
    private JButton btnRimuovi;
    private JButton btnStartModify;
    private JButton btnRefund;

    private JPanel PannelloCentro, PannelloSud;
    private JPanel PannelloTabella;

    private JTable table;
    private SpedizioniTableModel tablemodel;

    private boolean enableModify = false;

    public TabellaSpedizioni() {
        setSize(600, 600);
        PannelloCentro = new JPanel();
        PannelloSud = new JPanel();
        PannelloTabella = new JPanel();
        PannelloTabella.setLayout(new BorderLayout());

        btnIndietro = new JButton("Indietro");
        btnIndietro.addActionListener(this);
        btnRefund = new JButton("Rimborso");
        btnRefund.addActionListener(this);

        btnRimuovi = new JButton("Rimuovi");
        btnRimuovi.addActionListener(this);
        btnStartModify = new JButton("Modifica");
        btnStartModify.addActionListener(this);

        enableModify = false;

        PannelloTabella.add(PannelloCentro, BorderLayout.CENTER);
        PannelloTabella.add(PannelloSud, BorderLayout.SOUTH);
    }

    public TabellaSpedizioni(Utente u, ListaUtenti l) {
        this();
        user = u;
        lstUtenti = l;
        setAdmin(false);

        tablemodel = new SpedizioniTableModel(user.getListaSpedizioni());
        table = new JTable(tablemodel);
        table.setDefaultRenderer(Object.class, new LayoutCelle());
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        JScrollPane scrollpane = new JScrollPane(table);
        PannelloCentro.add(scrollpane);

        PannelloSud.add(btnIndietro);
        PannelloSud.add(btnRefund);

        this.add(PannelloTabella);
    }

    public TabellaSpedizioni(ListaUtenti l) {
        this();
        lstUtenti = l;
        setAdmin(true);

        lstSpedizioni = new ListaSpedizioni();
        for (int i = 0; i < l.getNumeroUtenti(); i++) {
            Utente u = l.getUtenteFromIdx(i);
            ListaSpedizioni tmp = u.getListaSpedizioni();
            for (int j = 0; j < tmp.getNumeroSpedizioni(); j++) {
                lstSpedizioni.Add(tmp.getSpedizione(j));
            }
        }

        tablemodel = new SpedizioniTableModel(lstSpedizioni);
        table = new JTable(tablemodel);
        table.setDefaultRenderer(Object.class, new LayoutCelle());
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        JScrollPane scrollPane = new JScrollPane(table);
        PannelloCentro.add(scrollPane);

        PannelloSud.add(btnIndietro);
        PannelloSud.add(btnRimuovi);
        PannelloSud.add(btnStartModify);

        this.add(PannelloTabella);
    }

    public boolean isAdmin() {
        return is_administrator;
    }

    public void setAdmin(boolean admin) {
        this.is_administrator = admin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIndietro) {
            DataUtility.salvaInfoUtenti(lstUtenti);
            WelcomeScreen ws = new WelcomeScreen();
            ws.setVisible(true);
            this.dispose();
        }

        if (e.getSource() == btnRefund) {
            ListaSpedizioni sped = user.getListaSpedizioni();
            boolean rimborso_richiesto = false;
            for (int i = 0; i < table.getRowCount(); i++) {
                Spedizione s = sped.getSpedizione(i);
                if (s.CheckRimborso()) {
                    table.setValueAt("RIMBORSO_RICHIESTO", i, 5);
                    rimborso_richiesto = true;
                }
            }
            if (!rimborso_richiesto) {
                JOptionPane.showMessageDialog(null, "Nessun rimborso richiesto", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                DataUtility.salvaInfoUtenti(lstUtenti);
            }
        }

        if (e.getSource() == btnRimuovi) {
            ListaSpedizioni lstSped = tablemodel.getLista();
            Spedizione s = lstSped.getSpedizione(table.getSelectedRow());

            if (s.SpedizioneTerminata()) {

                String nome = s.getUtente();
                Utente u = lstUtenti.getUtenteFromName(nome);
                if (u == null) {
                    return;
                }
                ListaSpedizioni ls = u.getListaSpedizioni();

                ls.Remove(s);
                lstSped.Remove(lstSped.getSpedizione(table.getSelectedRow()));
                JOptionPane.showMessageDialog(this, "La spedizione selezionata "
                        + "è stata eliminata", "", JOptionPane.INFORMATION_MESSAGE);
                tablemodel.fireTableDataChanged();
                DataUtility.salvaInfoUtenti(lstUtenti);

            } else {
                JOptionPane.showMessageDialog(this, "Non è possibile cancellare"
                        + " la spedizione perchè non è terminata", "", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if (e.getSource() == btnStartModify) {
            enableModify = !enableModify;
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    Thread t = new Thread();

                    while (enableModify) {
                        t = new ThreadSpedizioniCasuali(tablemodel);
                        t.start();
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        t.interrupt();
                    }
                }
            };
            new Thread(r).start();
            DataUtility.salvaInfoUtenti(lstUtenti);

        }

    }

    @Override
    public void windowClosing(WindowEvent e) {
        DataUtility.salvaInfoUtenti(lstUtenti);
        System.exit(0);
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
