package gestioneSpedizioni.models;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author eliatolin
 */

//Si estende la classe DefaultTableCellRender per gestire il comportamento delle celle
//in base al loro stato
public class LayoutCelle extends DefaultTableCellRenderer {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        String stato = (String) table.getValueAt(row, 5);
        
        //Per ogni stato, imposto un colore
        switch (stato) {

            case "IN_PREPARAZIONE" -> {
                setBackground(Color.LIGHT_GRAY);
            }
            case "TRANSITO" -> {
                setBackground(Color.MAGENTA);
            }
            case "RICEVUTA" -> {
                setBackground(Color.GREEN);
            }
            case "FALLITA" -> {
                setBackground(Color.RED);
            }
            case "RIMBORSO_RICHIESTO" -> {
                setBackground(Color.ORANGE);
            }
            case "RIMBORSO_EROGATO" -> {
                setBackground(Color.CYAN);
            }
            default -> {
                setBackground(Color.DARK_GRAY);
            }
        }
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        return this;
    }

}
