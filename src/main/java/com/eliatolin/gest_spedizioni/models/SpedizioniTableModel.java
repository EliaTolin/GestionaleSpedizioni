/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eliatolin.gest_spedizioni.models;

import javax.swing.table.AbstractTableModel;
import com.eliatolin.gest_spedizioni.models.*;

/**
 *
 * @author eliatolin
 */
public class SpedizioniTableModel extends AbstractTableModel {

    private String[] clmName = {"Nome", "Codice", "Destinazione", "Peso", "Data", "Stato", "Valore Assicurato"};

    private ListaSpedizioni lstSpedizioni;

    public SpedizioniTableModel(ListaSpedizioni l) {
        this.lstSpedizioni = l;
    }

    public int getColumnCount() {
        return clmName.length;
    }

    public int getRowCount() {
        return lstSpedizioni.getNumeroSpedizioni();
    }

    public Object getValueAt(int row, int col) {

        Spedizione ship = lstSpedizioni.getSpedizione(row);

        switch (col) {
            case 0:
                return ship.getUtente();
            case 1:
                return ship.getId();
            case 2:
                return ship.getDestinazione();
            case 3:
                return ship.getPeso();
            case 4:
                return ship.getDate();
            case 5:
                return ship.getStato();
            case 6:
                return ship.getValoreAssicurato();
            default:
                return null;
        }
    }

    public String getColumnName(int col) {
        return clmName[col];
    }

    public boolean isCellEditable(int row, int col) {
        if (col == 5) {
            return true;
        }
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String newValue = (String) aValue;
        Spedizione sTmp = lstSpedizioni.getSpedizione(rowIndex);
        sTmp.setStato(newValue);
        fireTableDataChanged();
    }

    public ListaSpedizioni getLista() {
        return lstSpedizioni;
    }

}
