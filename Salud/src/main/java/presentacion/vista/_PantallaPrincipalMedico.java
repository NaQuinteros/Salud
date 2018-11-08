package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public interface _PantallaPrincipalMedico {

	JTable getSalaEspera();

	JButton getBtnHistoriaClinica();

	DefaultTableModel getModelTurnos();

	String[] getColumnasSalaEspera();

}