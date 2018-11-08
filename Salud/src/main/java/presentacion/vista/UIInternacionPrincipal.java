package presentacion.vista;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UIInternacionPrincipal extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableInternacion;
	private JButton btnConsultar;
	private String[] columnasInternacion = {"id","Estado","Paciente","Habitacion", "Ingreso Programado", "Egreso Programado", "Ingreso Real", "Egreso Real","Motivo","Diagnóstico Inicial", "Observacion Inicial" };
	private DefaultTableModel modelInternacion;
	private JLabel txtDNI;
	private JLabel txtApellido;
	private JLabel txtNombre;
	private JLabel txtOS;
	private JButton btnEditar;
	private JButton btnCrear;
	private JButton btnMostrarTodas;
	private JButton btnMostrarSinHabitacion;

	/**
	 * Create the frame.
	 */
	public UIInternacionPrincipal() {
		setResizable(false);
		setTitle("Sector Internaciones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1060, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(12, 30, 842, 36);
		contentPane.add(panel);
		
		JLabel label = new JLabel("Nombre:");
		label.setBounds(10, 11, 78, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Apellido:");
		label_1.setBounds(179, 11, 78, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("DNI:");
		label_2.setBounds(361, 11, 41, 14);
		panel.add(label_2);
		
		JLabel lblObraSocial = new JLabel("Obra Social:");
		lblObraSocial.setBounds(549, 11, 78, 14);
		panel.add(lblObraSocial);
		
		txtNombre = new JLabel("");
		txtNombre.setBounds(62, 11, 88, 14);
		panel.add(txtNombre);
		
		txtApellido = new JLabel("");
		txtApellido.setBounds(230, 11, 121, 14);
		panel.add(txtApellido);
		
		txtDNI = new JLabel("");
		txtDNI.setBounds(412, 11, 103, 14);
		panel.add(txtDNI);
		
		txtOS = new JLabel("");
		txtOS.setBounds(613, 11, 103, 14);
		panel.add(txtOS);
		
	
		this.modelInternacion = new DefaultTableModel(null, columnasInternacion) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableInternacion = new JTable(modelInternacion);
		tableInternacion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableInternacion.setAutoCreateColumnsFromModel(false);
		TableColumnModel columnModel = tableInternacion.getColumnModel();
		TableColumn column = columnModel.getColumn(0);
		column.setMaxWidth(0);
		column.setMinWidth(0);
		column.setPreferredWidth(0);
		//		tableInternacion.removeColumn(column);
		
		tableInternacion.revalidate();
		JScrollPane scrollPane = new JScrollPane(tableInternacion);
		scrollPane.setBounds(10, 116, 1022, 210);
		contentPane.add(scrollPane);
		
		btnEditar = new JButton("Editar Internacion");
		btnEditar.setBounds(439, 337, 241, 55);
		contentPane.add(btnEditar);
		
		btnCrear = new JButton("Agendar Internación");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCrear.setBounds(172, 337, 241, 55);
		contentPane.add(btnCrear);
		
		JLabel lblInternacionesDelPaciente = new JLabel("INTERNACIONES");
		lblInternacionesDelPaciente.setBounds(12, 93, 156, 14);
		contentPane.add(lblInternacionesDelPaciente);
		
		btnConsultar = new JButton("Buscar por Paciente");
		btnConsultar.setBounds(866, 30, 168, 36);
		contentPane.add(btnConsultar);
		
		btnMostrarTodas = new JButton("Mostrar Todas");
		btnMostrarTodas.setBounds(864, 69, 168, 36);
		contentPane.add(btnMostrarTodas);
		
		JLabel label_5 = new JLabel("DATOS DEL PACIENTE");
		label_5.setBounds(12, 11, 139, 14);
		contentPane.add(label_5);
		
		btnMostrarSinHabitacion = new JButton("Mostrar Sin Habitacion");
		btnMostrarSinHabitacion.setBounds(686, 71, 168, 32);
		contentPane.add(btnMostrarSinHabitacion);

	}

	public JButton getBtnMostrarSinHabitacion() {
		return btnMostrarSinHabitacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JTable getTableInternacion() {
		return tableInternacion;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public String[] getColumnasInternacion() {
		return columnasInternacion;
	}

	public DefaultTableModel getModelInternacion() {
		return modelInternacion;
	}

	public JLabel getTxtDNI() {
		return txtDNI;
	}

	public JLabel getTxtApellido() {
		return txtApellido;
	}

	public JLabel getTxtNombre() {
		return txtNombre;
	}

	public JLabel getLblOS() {
		return txtOS;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public JButton getBtnCrear() {
		return btnCrear;
	}

	public JLabel getTxtOS() {
		return txtOS;
	}

	public JButton getBtnMostrarTodas() {
		return btnMostrarTodas;
	}
}

