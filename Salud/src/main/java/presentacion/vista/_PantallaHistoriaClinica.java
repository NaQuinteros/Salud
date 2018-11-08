package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public interface _PantallaHistoriaClinica {

	JTextField getTxtNombre();

	JTextField getTxtApellido();

	JTextField getTxtDNI();

	JTextField getTxtEmail();

	JTextField getTxtTeléfono();

	JTextField getTxtOcupacion();

	JTextField getTxtNacionalidad();

	JTextField getTxtDireccion();

	JTextField getTxtObra();

	JTextField getTxtPlan();

	JTextField getTxtNum();

	JTable getTable();

	String[] getColumnasHistoria();

	DefaultTableModel getHistoriaClinica();

	String getPaciente();

	JDateChooser getChoNac();

	JButton getBtnEditar();

	JButton getBtnAgregarEntrada();

	JButton getBtnVerHitos();

	JComboBox<String> getCmbEstado();

	JComboBox<String> getCmbSexo();

	JButton getBtnMostrarDetalles();

}