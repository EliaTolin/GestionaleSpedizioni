package gestioneSpedizioni.models;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author eliatolin
 */

//Classe che eredita AbstractTableModel, viene utilizzata per definire la tabella
//spedizioni. 
public class SpedizioniTableModel extends AbstractTableModel {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Nomi delle varie colonne
    private final String[] clmName = {"Nome", "Codice", "Destinazione", "Peso", "Data", "Stato", "Valore Assicurato"};

    private ListaSpedizioni lstSpedizioni;

    //Costruttore della TableModel che accetta come parametro una ListaSpedizioni
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

    //Metodo che ritorna il valore di una determinata cella
    @Override
    public Object getValueAt(int row, int col) {

        Spedizione ship = lstSpedizioni.getSpedizione(row);
        
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
    
    //Metodo che ritorna il nome di una colonna
    @Override
    public String getColumnName(int col) {
        return clmName[col];
    }
    
    //Si utilizza per verificare se una cella è editabile
    @Override
    public boolean isCellEditable(int row, int col) {
        return col == 5;
    }

    //Si imposta lo stato di una spedizione
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String newValue = (String) aValue;
        Spedizione sTmp = lstSpedizioni.getSpedizione(rowIndex);
        sTmp.setStato(newValue);
        fireTableDataChanged();
    }
    
    //ritorna la lista spedizione del tablemodel
    public ListaSpedizioni getLista() {
        return lstSpedizioni;
    }

}
