package com.eliatolin.gest_spedizioni.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
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

/**
 *
 * @author eliatolin
 */
//Tabella che rappresenta tutte le spedizioni per utente o per tutti gli utenti.
public class TabellaSpedizioni extends JFrame implements ActionListener, WindowListener {

    private ListaUtenti lstUtenti;
    private ListaSpedizioni lstSpedizioni;
    private Utente user;
    private boolean isAdmin;

    private JButton btnBack;
    private JButton btnRemove;
    private JButton btnStartModify;
    private JButton btnRefund;

    private JPanel centerPanel, bottomPanel;
    private JPanel tablePanel;

    private JTable table;
    private SpedizioniTableModel tablemodel;

    private boolean enableModify = false;
    Dimension dButton = new Dimension(120, 40);

    public TabellaSpedizioni() {

        setSize(600, 600);

        centerPanel = new JPanel();
        bottomPanel = new JPanel();
        tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());

        btnBack = new JButton("Indietro");
        btnBack.addActionListener(this);
        btnRefund = new JButton("Rimborso");
        btnRefund.addActionListener(this);

        btnRemove = new JButton("Rimuovi");
        btnRemove.addActionListener(this);
        btnStartModify = new JButton("Modifica");
        btnStartModify.addActionListener(this);

        btnRemove.setPreferredSize(dButton);
        btnStartModify.setPreferredSize(dButton);
        btnRefund.setPreferredSize(dButton);
        btnBack.setPreferredSize(dButton);
        enableModify = false;

        tablePanel.add(centerPanel, BorderLayout.CENTER);
        tablePanel.add(bottomPanel, BorderLayout.SOUTH);
    }

    public TabellaSpedizioni(Utente u, ListaUtenti l) {
        this();
        user = u;
        lstUtenti = l;
        setAdministrator(false);

        tablemodel = new SpedizioniTableModel(user.getListaSpedizioni());
        table = new JTable(tablemodel);
        table.setDefaultRenderer(Object.class, new LayoutCelle());
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        JScrollPane scrollpane = new JScrollPane(table);
        centerPanel.add(scrollpane);

        bottomPanel.add(btnBack);
        bottomPanel.add(btnRefund);

        this.add(tablePanel);
    }

    public TabellaSpedizioni(ListaUtenti l) {
        this();
        lstUtenti = l;
        setAdministrator(true);
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
        centerPanel.add(scrollPane);
        btnStartModify.setBackground(Color.RED);
        bottomPanel.add(btnBack);
        bottomPanel.add(btnRemove);
        bottomPanel.add(btnStartModify);

        this.add(tablePanel);
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdministrator(boolean admin) {
        this.isAdmin = admin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
            }
        }

        if (e.getSource() == btnRemove) {
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
            } else {
                JOptionPane.showMessageDialog(this, "Non è possibile cancellare"
                        + " la spedizione perchè non è terminata", "", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if (e.getSource() == btnStartModify) {

            enableModify = !enableModify;
            if (enableModify) {
                btnStartModify.setBackground(Color.GREEN);
            } else {
                btnStartModify.setBackground(Color.RED);
            }

            btnStartModify.setOpaque(true);
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    Thread t = new Thread();

                    while (enableModify) {
                        t = new ThreadSpedizioniCasuali(tablemodel);
                        t.start();
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        t.interrupt();
                    }
                }
            };
            new Thread(r).start();
        }

        if (e.getSource() == btnBack) {
            DataUtility.salvaInfo(lstUtenti);
            WelcomeScreen ws = new WelcomeScreen();
            ws.setVisible(true);
            this.dispose();
        }

    }

    @Override
    public void windowClosing(WindowEvent e) {
        DataUtility.salvaInfo(lstUtenti);
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
