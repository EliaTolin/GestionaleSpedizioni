/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eliatolin.gest_spedizioni.models;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author eliatolin
 */

public class LayoutCelle extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        String stato = (String)table.getValueAt(row, 5);
        switch (stato) {

            case "IN_PREPARAZIONE": {
                setBackground(Color.YELLOW);
                break;
            }
            case "IN_TRANSITO": {
                setBackground(Color.MAGENTA);
                break;
            }
            case "RICEVUTO": {
                setBackground(Color.GREEN);
                break;
            }
            case "FALLITA": {
                setBackground(Color.RED);
                break;
            }
            case "RICHIESTA_RIMBORSO": {
                setBackground(Color.ORANGE);
                break;
            }
            case "RIMBORSO_EROGATO": {
                setBackground(Color.CYAN);
                break;
            }
            default: {
                setBackground(Color.DARK_GRAY);
                break;
            }
        }
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        return this;
    }

}
