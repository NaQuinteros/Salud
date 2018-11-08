package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public interface _PantallaAgregarEntradaAHistoria {

	JTable getTable();

	JButton getBtnQuitarPractica();

	JButton getBtnAgregarPractica();

	JButton getBtnAgregarEntrada();

	JTextArea getTxtAreaDiag();

	DefaultTableModel getModel();

	String[] getNombreColumnas();

}