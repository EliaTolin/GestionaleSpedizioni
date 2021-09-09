/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eliatolin.gest_spedizioni.models;

import javax.swing.table.AbstractTableModel;
import com.eliatolin.gest_spedizioni.models.*;
import java.text.SimpleDateFormat;

/**
 *
 * @author eliatolin
 */
public class SpedizioniTableModel extends AbstractTableModel {

    private final String[] clmName = {"Nome", "Codice", "Destinazione", "Peso", "Data", "Stato", "Valore Assicurato"};

    private ListaSpedizioni lstSpedizioni;

    public SpedizioniTableModel(ListaSpedizioni l) {
        this.lstSpedizioni = l;
    }

    @Override
    public int getColumnCount() {
        return clmName.length;
    }

    @Override
    public int getRowCount() {
        return lstSpedizioni.getNumeroSpedizioni();
    }

    @Override
    public Object getValueAt(int row, int col) {

        Spedizione ship = lstSpedizioni.getSpedizione(row);
        
        String id = ship.getId();
        
        return switch (col) {
            case 0 -> ship.getUtente();
            case 1 -> ship.getId();
            case 2 -> ship.getDestinazione();
            case 3 -> ship.getPeso();
            case 4 -> ship.getDateToString();
            case 5 -> ship.getStato();
            case 6 -> ship.getValoreAssicurato();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int col) {
        return clmName[col];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col == 5;
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
