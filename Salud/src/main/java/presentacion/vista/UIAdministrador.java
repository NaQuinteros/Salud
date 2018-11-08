package presentacion.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class UIAdministrador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable tableRecepcion;
	private JTable tablaInternacion;
	private JTable tablaMedico;
	private JTable tablaAdmin;
	private JButton btnCrearNuevaCuentaRecepcion;
	private JButton btnEliminarRecepcion;
	private JButton btnEditarRecepcion;
	private DefaultTableModel modelRecepcion;
	private String[] columnasRecepcion;
	private String[] columnasAdministrador;
	private DefaultTableModel modelAdmin;
	private JButton crearCuentaAdmin;
	private JButton EliminarCuentaAdmin;
	private JButton EditarCuentaAdmin;
	private JButton crearContable;
	private JButton eliminarContable;
	private JButton editarContable;
	private JTable tablaContable;
	private String[] columnasContable;
	private DefaultTableModel modelContable;
	private String[] columnasInternacion;
	private DefaultTableModel modelInternacion;
	private JButton crearInternacion;
	private JButton EliminarInternacion;
	private JButton editarInternacion;
	private JButton crearMedico;
	private JButton eliminarMedico;
	private JButton editarMedico;
	private String[] columnasMedico;
	private DefaultTableModel modelMedico;
	private JTable tablaHabitaciones;
	private JTable tablaEspecialidad;
	private JButton eliminarEspecialidad;
	private JButton editarEspecialidad;
	private JButton btnCrearEspecialidad;
	private JButton btnCrearHabitacin;
	private JButton btnImportar;
	private JButton btnExportar;
	private JButton editarHabitacion;
	private JButton eliminarHabitacion;
	private String[] columnasHabitaciones;
	private DefaultTableModel modelHabitaciones;
	private String[] columnasEspecialidad;
	private DefaultTableModel modelEspecialidad;
	private JButton btnAceptar;
	private JTextField txtCorreo;
	private JTextField txtPassword;

	public UIAdministrador() {
		getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 625, 339);
		getContentPane().add(tabbedPane);
		this.setTitle("Administrador");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(661, 400);
		JPanel recepcion = new JPanel();
		tabbedPane.addTab("Recepción", null, recepcion, null);
		recepcion.setLayout(null);
		this.setResizable(false);
		btnCrearNuevaCuentaRecepcion = new JButton("Crear cuenta");
		btnCrearNuevaCuentaRecepcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCrearNuevaCuentaRecepcion.setBounds(10, 11, 134, 23);
		recepcion.add(btnCrearNuevaCuentaRecepcion);

		btnEliminarRecepcion = new JButton("Eliminar");
		btnEliminarRecepcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminarRecepcion.setBounds(10, 45, 134, 23);
		recepcion.add(btnEliminarRecepcion);

		btnEditarRecepcion = new JButton("Editar");
		btnEditarRecepcion.setBounds(10, 79, 134, 23);
		recepcion.add(btnEditarRecepcion);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(154, 8, 458, 292);
		recepcion.add(scrollPane);

		tableRecepcion = new JTable();
		columnasRecepcion = new String[] { "Nombre", "Usuario", "Contraseña" };
		modelRecepcion = new DefaultTableModel(null, columnasRecepcion) {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("unused")
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tableRecepcion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableRecepcion.setModel(modelRecepcion);
		scrollPane.setViewportView(tableRecepcion);

		JPanel internacion = new JPanel();
		tabbedPane.addTab("Internación", null, internacion, null);
		internacion.setLayout(null);

		editarInternacion = new JButton("Editar");
		editarInternacion.setBounds(10, 79, 134, 23);
		internacion.add(editarInternacion);

		EliminarInternacion = new JButton("Eliminar");
		EliminarInternacion.setBounds(10, 45, 134, 23);
		internacion.add(EliminarInternacion);

		crearInternacion = new JButton("Crear cuenta");
		crearInternacion.setBounds(10, 11, 134, 23);
		internacion.add(crearInternacion);

		JButton button_9 = new JButton("Editar");
		button_9.setBounds(10, 464, 134, 23);
		internacion.add(button_9);

		JButton button_10 = new JButton("Eliminar");
		button_10.setBounds(10, 430, 134, 23);
		internacion.add(button_10);

		JButton btnCrearHabitacin_1 = new JButton("Crear habitaci\u00F3n");
		btnCrearHabitacin_1.setBounds(10, 396, 134, 23);
		internacion.add(btnCrearHabitacin_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(154, 8, 458, 292);
		internacion.add(scrollPane_1);

		tablaInternacion = new JTable();
		tablaInternacion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		columnasInternacion = new String[] { "Nombre", "Usuario", "Contraseña" };
		modelInternacion = new DefaultTableModel(null, columnasInternacion) {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("unused")
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tablaInternacion.setModel(modelInternacion);
		scrollPane_1.setViewportView(tablaInternacion);

		JPanel medico = new JPanel();
		tabbedPane.addTab("Médico", null, medico, null);
		medico.setLayout(null);

		editarMedico = new JButton("Editar");
		editarMedico.setBounds(10, 79, 134, 23);
		medico.add(editarMedico);

		eliminarMedico = new JButton("Eliminar");
		eliminarMedico.setBounds(10, 45, 134, 23);
		medico.add(eliminarMedico);

		crearMedico = new JButton("Crear cuenta");
		crearMedico.setBounds(10, 11, 134, 23);
		medico.add(crearMedico);

		JButton button_12 = new JButton("Editar");
		button_12.setBounds(10, 464, 134, 23);
		medico.add(button_12);

		JButton button_13 = new JButton("Eliminar");
		button_13.setBounds(10, 430, 134, 23);
		medico.add(button_13);

		JButton btnCrearHabitacion = new JButton("Crear especialidad");
		btnCrearHabitacion.setBounds(10, 396, 134, 23);
		medico.add(btnCrearHabitacion);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(154, 8, 458, 292);
		medico.add(scrollPane_3);

		tablaMedico = new JTable();
		tablaMedico.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		columnasMedico = new String[] { "Nombre", "Usuario", "Contraseña", "Especialidad", "Matricula" };
		modelMedico = new DefaultTableModel(null, columnasMedico) {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("unused")
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tablaMedico.setModel(modelMedico);
		scrollPane_3.setViewportView(tablaMedico);

		columnasAdministrador = new String[] { "Nombre", "Usuario", "Contrase\u00F1a" };
		modelAdmin = new DefaultTableModel(null, columnasAdministrador) {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("unused")
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JPanel contable = new JPanel();
		tabbedPane.addTab("Contable", null, contable, null);
		contable.setLayout(null);

		crearContable = new JButton("Crear cuenta");
		crearContable.setBounds(10, 11, 134, 23);
		contable.add(crearContable);

		eliminarContable = new JButton("Eliminar");
		eliminarContable.setBounds(10, 45, 134, 23);
		contable.add(eliminarContable);

		editarContable = new JButton("Editar");
		editarContable.setBounds(10, 79, 134, 23);
		contable.add(editarContable);

		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(154, 8, 458, 292);
		contable.add(scrollPane_6);

		columnasContable = new String[] { "Nombre", "Usuario", "Contraseña" };
		modelContable = new DefaultTableModel(null, columnasContable) {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("unused")
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tablaContable = new JTable();
		tablaContable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaContable.setModel(modelContable);
		scrollPane_6.setViewportView(tablaContable);

		JPanel administrador = new JPanel();
		tabbedPane.addTab("Administrador", null, administrador, null);
		administrador.setLayout(null);

		EditarCuentaAdmin = new JButton("Editar");
		EditarCuentaAdmin.setBounds(10, 79, 134, 23);
		administrador.add(EditarCuentaAdmin);

		EliminarCuentaAdmin = new JButton("Eliminar");
		EliminarCuentaAdmin.setBounds(10, 45, 134, 23);
		administrador.add(EliminarCuentaAdmin);

		crearCuentaAdmin = new JButton("Crear cuenta");
		crearCuentaAdmin.setBounds(10, 11, 134, 23);
		administrador.add(crearCuentaAdmin);

		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(154, 8, 458, 292);
		administrador.add(scrollPane_5);
		tablaAdmin = new JTable();
		tablaAdmin.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaAdmin.setModel(modelAdmin);
		scrollPane_5.setViewportView(tablaAdmin);

		JPanel Especialidades = new JPanel();
		tabbedPane.addTab("Especialidades", null, Especialidades, null);
		Especialidades.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(154, 11, 458, 292);
		Especialidades.add(scrollPane_2);

		columnasEspecialidad = new String[] { "Especialidad" };
		modelEspecialidad = new DefaultTableModel(null, columnasEspecialidad) {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("unused")
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tablaEspecialidad = new JTable();
		tablaEspecialidad.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaEspecialidad.setModel(modelEspecialidad);
		scrollPane_2.setViewportView(tablaEspecialidad);

		btnCrearEspecialidad = new JButton("Crear nueva");
		btnCrearEspecialidad.setBounds(10, 11, 134, 23);
		Especialidades.add(btnCrearEspecialidad);

		eliminarEspecialidad = new JButton("Eliminar");
		eliminarEspecialidad.setBounds(10, 45, 134, 23);
		Especialidades.add(eliminarEspecialidad);

		editarEspecialidad = new JButton("Editar");
		editarEspecialidad.setBounds(10, 79, 134, 23);
		Especialidades.add(editarEspecialidad);

		JPanel Habitaciones = new JPanel();
		tabbedPane.addTab("Habitaciones", null, Habitaciones, null);
		Habitaciones.setLayout(null);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(154, 11, 458, 292);
		Habitaciones.add(scrollPane_4);

		columnasHabitaciones = new String[] { "Identificación", "Descripción" };
		modelHabitaciones = new DefaultTableModel(null, columnasHabitaciones) {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("unused")
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tablaHabitaciones = new JTable();
		tablaHabitaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaHabitaciones.setModel(modelHabitaciones);
		scrollPane_4.setViewportView(tablaHabitaciones);

		editarHabitacion = new JButton("Editar");
		editarHabitacion.setBounds(10, 79, 134, 23);
		Habitaciones.add(editarHabitacion);

		eliminarHabitacion = new JButton("Eliminar");
		eliminarHabitacion.setBounds(10, 45, 134, 23);
		Habitaciones.add(eliminarHabitacion);

		btnCrearHabitacin = new JButton("Crear habitaci\u00F3n");
		btnCrearHabitacin.setBounds(10, 11, 134, 23);
		Habitaciones.add(btnCrearHabitacin);

		JPanel basededatos = new JPanel();
		tabbedPane.addTab("Otros", null, basededatos, null);
		basededatos.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 11, 600, 159);
		basededatos.add(panel);
		panel.setLayout(null);
		
				txtCorreo = new JTextField();
				txtCorreo.setBounds(377, 31, 213, 20);
				panel.add(txtCorreo);
				txtCorreo.setColumns(10);
				
						JLabel lblCorreo = new JLabel("Correo:");
						lblCorreo.setBounds(10, 34, 46, 14);
						panel.add(lblCorreo);
						
								JLabel lblPassword = new JLabel("Password:");
								lblPassword.setBounds(10, 71, 68, 14);
								panel.add(lblPassword);
								
										txtPassword = new JTextField();
										txtPassword.setBounds(377, 68, 213, 20);
										panel.add(txtPassword);
										txtPassword.setColumns(10);
										
												btnAceptar = new JButton("Aceptar");
												btnAceptar.setBounds(10, 114, 580, 34);
												panel.add(btnAceptar);
												
												JLabel lblCredencialesDeCorreo = new JLabel("Credenciales de correo");
												lblCredencialesDeCorreo.setBounds(10, 9, 213, 14);
												panel.add(lblCredencialesDeCorreo);
												
												JPanel panel_1 = new JPanel();
												panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
												panel_1.setBounds(10, 181, 600, 119);
												basededatos.add(panel_1);
												panel_1.setLayout(null);
												
														btnImportar = new JButton("Importar base de datos");
														btnImportar.setBounds(10, 36, 580, 30);
														panel_1.add(btnImportar);
														
																btnExportar = new JButton("Exportar base de datos");
																btnExportar.setBounds(10, 77, 580, 31);
																panel_1.add(btnExportar);
																
																JLabel lblGestinDeLa = new JLabel("Gesti\u00F3n de la base de datos");
																lblGestinDeLa.setBounds(10, 11, 277, 14);
																panel_1.add(lblGestinDeLa);

	}

	public JButton getBtnCrearNuevaCuentaRecepcion() {
		return btnCrearNuevaCuentaRecepcion;
	}

	public void setBtnCrearNuevaCuentaRecepcion(JButton btnCrearNuevaCuentaRecepcion) {
		this.btnCrearNuevaCuentaRecepcion = btnCrearNuevaCuentaRecepcion;
	}

	public JButton getBtnEliminarRecepcion() {
		return btnEliminarRecepcion;
	}

	public void setBtnEliminarRecepcion(JButton btnEliminarRecepcion) {
		this.btnEliminarRecepcion = btnEliminarRecepcion;
	}

	public JButton getBtnEditarRecepcion() {
		return btnEditarRecepcion;
	}

	public void setBtnEditarRecepcion(JButton btnEditarRecepcion) {
		this.btnEditarRecepcion = btnEditarRecepcion;
	}

	public DefaultTableModel getModelRecepcion() {
		return modelRecepcion;
	}

	public void setModelRecepcion(DefaultTableModel modelRecepcion) {
		this.modelRecepcion = modelRecepcion;
	}

	public String[] getColumnasRecepcion() {
		return columnasRecepcion;
	}

	public void setColumnasRecepcion(String[] columnasRecepcion) {
		this.columnasRecepcion = columnasRecepcion;
	}

	public JTable getTableRecepcion() {
		return tableRecepcion;
	}

	public void setTableRecepcion(JTable tableRecepcion) {
		this.tableRecepcion = tableRecepcion;
	}

	public JTable getTablaAdmin() {
		return tablaAdmin;
	}

	public void setTablaAdmin(JTable tablaAdmin) {
		this.tablaAdmin = tablaAdmin;
	}

	public DefaultTableModel getModelAdmin() {
		return modelAdmin;
	}

	public void setModelAdmin(DefaultTableModel modelAdmin) {
		this.modelAdmin = modelAdmin;
	}

	public JButton getCrearCuentaAdmin() {
		return crearCuentaAdmin;
	}

	public String[] getColumnasAdministrador() {
		return columnasAdministrador;
	}

	public void setColumnasAdministrador(String[] columnasAdministrador) {
		this.columnasAdministrador = columnasAdministrador;
	}

	public void setCrearCuentaAdmin(JButton crearCuentaAdmin) {
		this.crearCuentaAdmin = crearCuentaAdmin;
	}

	public JButton getCrearInternacion() {
		return crearInternacion;
	}

	public JButton getEliminarInternacion() {
		return EliminarInternacion;
	}

	public JButton getEditarInternacion() {
		return editarInternacion;
	}

	public JButton getEliminarCuentaAdmin() {
		return EliminarCuentaAdmin;
	}

	public void setEliminarCuentaAdmin(JButton eliminarCuentaAdmin) {
		EliminarCuentaAdmin = eliminarCuentaAdmin;
	}

	public JButton getEditarCuentaAdmin() {
		return EditarCuentaAdmin;
	}

	public void setEditarCuentaAdmin(JButton editarCuentaAdmin) {
		EditarCuentaAdmin = editarCuentaAdmin;
	}

	public JButton getCrearContable() {
		return crearContable;
	}

	public JButton getEliminarContable() {
		return eliminarContable;
	}

	public JButton getEditarContable() {
		return editarContable;
	}

	public JTable getTablaContable() {
		return tablaContable;
	}

	public String[] getColumnasContable() {
		return columnasContable;
	}

	public DefaultTableModel getModelContable() {
		return modelContable;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JTable getTablaInternacion() {
		return tablaInternacion;
	}

	public String[] getColumnasInternacion() {
		return columnasInternacion;
	}

	public DefaultTableModel getModelInternacion() {
		return modelInternacion;
	}

	public JTable getTablaMedico() {
		return tablaMedico;
	}

	public JButton getCrearMedico() {
		return crearMedico;
	}

	public JButton getEliminarMedico() {
		return eliminarMedico;
	}

	public JButton getEditarMedico() {
		return editarMedico;
	}

	public String[] getColumnasMedico() {
		return columnasMedico;
	}

	public DefaultTableModel getModelMedico() {
		return modelMedico;
	}

	public JTable getTablaHabitaciones() {
		return tablaHabitaciones;
	}

	public JTable getTablaEspecialidad() {
		return tablaEspecialidad;
	}

	public JButton getEliminarEspecialidad() {
		return eliminarEspecialidad;
	}

	public JButton getEditarEspecialidad() {
		return editarEspecialidad;
	}

	public JButton getBtnCrearEspecialidad() {
		return btnCrearEspecialidad;
	}

	public JButton getBtnCrearHabitacin() {
		return btnCrearHabitacin;
	}

	public JButton getBtnImportar() {
		return btnImportar;
	}

	public JButton getBtnExportar() {
		return btnExportar;
	}

	public JButton getEditarHabitacion() {
		return editarHabitacion;
	}

	public JButton getEliminarHabitacion() {
		return eliminarHabitacion;
	}

	public String[] getColumnasHabitaciones() {
		return columnasHabitaciones;
	}

	public DefaultTableModel getModelHabitaciones() {
		return modelHabitaciones;
	}

	public String[] getColumnasEspecialidad() {
		return columnasEspecialidad;
	}

	public DefaultTableModel getModelEspecialidad() {
		return modelEspecialidad;
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public JTextField getTxtCorreo() {
		return txtCorreo;
	}

	public JTextField getTxtPassword() {
		return txtPassword;
	}
}