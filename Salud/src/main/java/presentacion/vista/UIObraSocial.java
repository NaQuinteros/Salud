package presentacion.vista;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.SpinnerNumberModel;

public class UIObraSocial extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablaObras;
	private JTable tablaPlanes;
	private JTable tablaPracticas;
	private DefaultTableModel modelObra;
	private DefaultTableModel modelPlan;
	private DefaultTableModel modelPractica;
	private JButton btnBorrarPlan;
	private JButton btnAgregarPlan;
	private String[] columnasPlan = { "Nombre", "Prácticas Cubiertas", "Pacientes" };
	private String[] columnasObra = { "Nombre", "Planes", "Prácticas Cubiertas", "Pacientes" };
	private String[] columnasPractica = { "Código", "Descripción de la Práctica", "Arancel", "Cobertura",
			"Costo Final", "Autorización" };
	private JCheckBox chckbxAutorizacion;
	private JSpinner spCobertura;
	private JButton btnBorrarPractica;
	private JButton btnAgregarObraSocial;
	private JButton btnBorrarObraSocial;
	private JButton btnCobertura;
	private JButton btnAgregarPractica;
	private JLabel label;

	public UIObraSocial() {
		setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		setTitle("Gesti\u00F3n de Coberturas");
		setResizable(false);
		setBounds(260, 0, 943, 672);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		modelObra = new DefaultTableModel(null, this.columnasObra) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};

		modelPlan = new DefaultTableModel(null, this.columnasPlan) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};

		modelPractica = new DefaultTableModel(null, this.columnasPractica) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};

		JPanel panel = new JPanel();
		panel.setBounds(0, 11, 936, 263);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblPlanes = new JLabel("Planes");
		lblPlanes.setBounds(492, 0, 434, 23);
		panel.add(lblPlanes);
		lblPlanes.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlanes.setFont(new Font("Tahoma", Font.BOLD, 13));

		tablaObras = new JTable(modelObra);
		tablaObras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		TableColumnModel tcmObra = tablaObras.getColumnModel();
		tcmObra.getColumn(1).setMaxWidth(80);
		tcmObra.getColumn(1).setPreferredWidth(80);

		tablaPlanes = new JTable(modelPlan);
		tablaPlanes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		tablaPracticas = new JTable(modelPractica);
		tablaPracticas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		TableColumnModel tcm = tablaPracticas.getColumnModel();
		tcm.getColumn(0).setMaxWidth(60);
		tcm.getColumn(0).setPreferredWidth(60);
		tcm.getColumn(1).setPreferredWidth(500);
		tcm.getColumn(2).setMaxWidth(80);
		tcm.getColumn(2).setPreferredWidth(80);
		tcm.getColumn(3).setMaxWidth(80);
		tcm.getColumn(3).setPreferredWidth(80);
		tcm.getColumn(4).setMaxWidth(80);
		tcm.getColumn(4).setPreferredWidth(80);
		tcm.getColumn(5).setMaxWidth(80);
		tcm.getColumn(5).setPreferredWidth(80);

		JScrollPane panePlanes = new JScrollPane(tablaPlanes);
		panePlanes.setBounds(492, 23, 434, 164);
		panel.add(panePlanes);

		JScrollPane paneObras = new JScrollPane(tablaObras);
		paneObras.setBounds(10, 23, 434, 164);
		panel.add(paneObras);
		
		

		JLabel lblObrasSocialesY = new JLabel("Obras Sociales y Prepagas");
		lblObrasSocialesY.setHorizontalAlignment(SwingConstants.CENTER);
		lblObrasSocialesY.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblObrasSocialesY.setBounds(10, 0, 434, 23);
		panel.add(lblObrasSocialesY);

		btnAgregarObraSocial = new JButton("Agregar OS o Prepaga");
		btnAgregarObraSocial.setBounds(10, 198, 207, 48);
		panel.add(btnAgregarObraSocial);

		btnBorrarObraSocial = new JButton("Borrar OS o Prepaga");
		btnBorrarObraSocial.setEnabled(false);
		btnBorrarObraSocial.setBounds(237, 198, 207, 48);
		panel.add(btnBorrarObraSocial);

		btnAgregarPlan = new JButton("Agregar Plan");
		btnAgregarPlan.setEnabled(false);
		btnAgregarPlan.setBounds(492, 198, 207, 48);
		panel.add(btnAgregarPlan);

		btnBorrarPlan = new JButton("Borrar Plan");
		btnBorrarPlan.setEnabled(false);
		btnBorrarPlan.setBounds(722, 198, 207, 48);
		panel.add(btnBorrarPlan);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 285, 936, 358);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblPracticas = new JLabel("Pr\u00E1cticas Cubiertas");
		lblPracticas.setBounds(10, 0, 916, 23);
		panel_2.add(lblPracticas);
		lblPracticas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPracticas.setFont(new Font("Tahoma", Font.BOLD, 13));

		JScrollPane panePracticas = new JScrollPane(tablaPracticas);
		panePracticas.setBounds(10, 21, 916, 215);
		panel_2.add(panePracticas);

		btnAgregarPractica = new JButton("Agregar Pr\u00E1ctica al Plan");
		btnAgregarPractica.setEnabled(false);
		btnAgregarPractica.setBounds(10, 247, 207, 88);
		panel_2.add(btnAgregarPractica);

		btnCobertura = new JButton("Actualizar Cobertura y Autorizaci\u00F3n");
		btnCobertura.setEnabled(false);
		btnCobertura.setBounds(237, 287, 445, 48);
		panel_2.add(btnCobertura);

		btnBorrarPractica = new JButton("Borrar Pr\u00E1ctica del Plan");
		btnBorrarPractica.setEnabled(false);
		btnBorrarPractica.setBounds(722, 247, 207, 88);
		panel_2.add(btnBorrarPractica);

		spCobertura = new JSpinner();
		spCobertura.setEnabled(false);
		spCobertura.setModel(new SpinnerNumberModel(0, 0, 100, 5));
		spCobertura.setBounds(329, 253, 55, 23);
		panel_2.add(spCobertura);

		chckbxAutorizacion = new JCheckBox("Necesita Autorizaci\u00F3n");
		chckbxAutorizacion.setFont(new Font("Tahoma", Font.BOLD, 13));
		chckbxAutorizacion.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxAutorizacion.setEnabled(false);
		chckbxAutorizacion.setBounds(444, 248, 213, 32);
		panel_2.add(chckbxAutorizacion);
		
		JLabel lblCobertura = new JLabel("Cobertura:");
		lblCobertura.setHorizontalAlignment(SwingConstants.LEFT);
		lblCobertura.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCobertura.setBounds(237, 253, 96, 23);
		panel_2.add(lblCobertura);
		
		label = new JLabel("%");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(385, 253, 22, 23);
		panel_2.add(label);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean setvisible(boolean value) {
		this.setVisible(value);
		return false;
	}

	public boolean isvisible() {

		return this.isVisible();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JTable getTablaObras() {
		return tablaObras;
	}

	public JTable getTablaPlanes() {
		return tablaPlanes;
	}

	public JTable getTablaPracticas() {
		return tablaPracticas;
	}

	public DefaultTableModel getModelObra() {
		return modelObra;
	}

	public DefaultTableModel getModelPlan() {
		return modelPlan;
	}

	public DefaultTableModel getModelPracticas() {
		return modelPractica;
	}

	public JButton getBtnBorrarPlan() {
		return btnBorrarPlan;
	}

	public JButton getBtnAgregarPlan() {
		return btnAgregarPlan;
	}

	public String[] getColumnasPlan() {
		return columnasPlan;
	}

	public String[] getColumnasObra() {
		return columnasObra;
	}

	public String[] getColumnasPractica() {
		return columnasPractica;
	}

	public DefaultTableModel getModelPractica() {
		return modelPractica;
	}

	public JCheckBox getChckbxAutorizacion() {
		return chckbxAutorizacion;
	}

	public JSpinner getSpCobertura() {
		return spCobertura;
	}

	public JButton getBtnBorrarPractica() {
		return btnBorrarPractica;
	}

	public JButton getBtnAgregarObraSocial() {
		return btnAgregarObraSocial;
	}

	public JButton getBtnBorrarObraSocial() {
		return btnBorrarObraSocial;
	}

	public JButton getBtnCobertura() {
		return btnCobertura;
	}

	public JButton getBtnAgregarPractica() {
		return btnAgregarPractica;
	}
}
