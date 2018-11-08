package presentacion.vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dto.PatologiaDTO;

public class UIMedicoHistoriaClinica extends JFrame implements _PantallaHistoriaClinica {

	private static final long serialVersionUID = 1L;

	private JButton btnMostrarDetalles;
	private JComboBox<String> cmbSexo;
	private JComboBox<String> cmbEstado;
	private JButton btnEditar;
	private JDateChooser choNac;
	private String paciente;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDNI;
	private JTextField txtEmail;
	private JTextField txtTeléfono;
	private JTextField txtOcupacion;
	private JTextField txtNacionalidad;
	private JTextField txtDireccion;
	private JTextField txtObra;
	private JTextField txtPlan;
	private JTextField txtNum;
	private JCheckBox importante;
	private String[] columnasHistoria = { "Fecha", "Medico", "Diagnóstico", "Practicas", "Especialidad" };
	private String[] columnasPatologia = { "Patolog\u00EDa", "Importante" };
	private DefaultTableModel historiaClinica;
	private DefaultTableModel patologias;
	private JTable table;
	private JButton btnAgregarEntrada;
	private JButton btnVerHitos;
	private JTable table_patologias;
	private JButton btnQuitarPatologia;
	private JButton agregarPatologia;
	private JComboBox<PatologiaDTO> cmbPatologia;

	private JButton btnFiltrarMedico;

	private JButton btnFiltrarEspecialidad;

	private JComboBox<String> cmbEspecialidad;
	private JComboBox<String> cmbMedico;

	public UIMedicoHistoriaClinica(String paciente) {
		setResizable(false);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.setTitle("Historia clínica");

		JLabel label = new JLabel("Nombre:");
		label.setBounds(10, 36, 46, 14);
		panel.add(label);

		txtNombre = new JTextField();
		txtNombre.setText("");
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(129, 36, 103, 20);
		panel.add(txtNombre);

		JLabel label_1 = new JLabel("Apellido:");
		label_1.setBounds(10, 61, 46, 14);
		panel.add(label_1);

		txtApellido = new JTextField();
		txtApellido.setEditable(false);
		txtApellido.setColumns(10);
		txtApellido.setBounds(129, 61, 103, 20);
		panel.add(txtApellido);

		JLabel lblDni = new JLabel("DNI:");
		lblDni.setBounds(10, 108, 46, 14);
		panel.add(lblDni);

		txtDNI = new JTextField();
		txtDNI.setEditable(false);
		txtDNI.setColumns(10);
		txtDNI.setBounds(129, 111, 103, 20);
		panel.add(txtDNI);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 133, 46, 14);
		panel.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(129, 136, 103, 20);
		panel.add(txtEmail);

		JLabel label_4 = new JLabel("Tel\u00E9fono:");
		label_4.setBounds(10, 86, 46, 14);
		panel.add(label_4);

		txtTeléfono = new JTextField();
		txtTeléfono.setEditable(false);
		txtTeléfono.setColumns(10);
		txtTeléfono.setBounds(129, 86, 103, 20);
		panel.add(txtTeléfono);

		JLabel lblDatosDeRegistro = new JLabel("DATOS DE REGISTRO");
		lblDatosDeRegistro.setBounds(10, 11, 222, 14);
		panel.add(lblDatosDeRegistro);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));

		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(10, 36, 46, 14);
		panel_1.add(lblSexo);

		JLabel lblOcupacin = new JLabel("Ocupaci\u00F3n:");
		lblOcupacin.setBounds(10, 94, 81, 14);
		panel_1.add(lblOcupacin);

		txtOcupacion = new JTextField();
		txtOcupacion.setEditable(false);
		txtOcupacion.setColumns(10);
		txtOcupacion.setBounds(129, 91, 103, 20);
		panel_1.add(txtOcupacion);

		JLabel lblNacimiento = new JLabel("Nacionalidad: ");
		lblNacimiento.setBounds(10, 147, 109, 14);
		panel_1.add(lblNacimiento);

		txtNacionalidad = new JTextField();
		txtNacionalidad.setEditable(false);
		txtNacionalidad.setColumns(10);
		txtNacionalidad.setBounds(129, 144, 103, 20);
		panel_1.add(txtNacionalidad);

		JLabel lblEmail_1 = new JLabel("Direcci\u00F3n:");
		lblEmail_1.setBounds(10, 176, 109, 14);
		panel_1.add(lblEmail_1);

		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(129, 173, 103, 20);
		panel_1.add(txtDireccion);

		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento: ");
		lblFechaDeNacimiento.setBounds(10, 122, 109, 14);
		panel_1.add(lblFechaDeNacimiento);

		JLabel lblDatosDeLa = new JLabel("DATOS DE PACIENTE");
		lblDatosDeLa.setBounds(10, 11, 222, 14);
		panel_1.add(lblDatosDeLa);

		cmbSexo = new JComboBox<>();
		cmbSexo.setEnabled(false);
		cmbSexo.setModel(new DefaultComboBoxModel<String>(new String[] { "Hombre", "Mujer", "Otro" }));
		cmbSexo.setBounds(129, 33, 103, 20);
		panel_1.add(cmbSexo);

		cmbEstado = new JComboBox<>();
		cmbEstado.setEnabled(false);
		cmbEstado.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Casado/a", "Divorciado/a", "Soltero/a", "Viudo/a" }));
		cmbEstado.setBounds(129, 61, 103, 20);
		panel_1.add(cmbEstado);

		JLabel lblEstadoCivil = new JLabel("Estado civil:");
		lblEstadoCivil.setBounds(10, 64, 109, 14);
		panel_1.add(lblEstadoCivil);

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(129, 204, 103, 20);
		panel_1.add(btnEditar);

		choNac = new JDateChooser();
		choNac.setEnabled(false);
		choNac.setBounds(129, 122, 103, 14);
		panel_1.add(choNac);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));

		JLabel lblCobertura = new JLabel("COBERTURA");
		lblCobertura.setBounds(10, 11, 106, 14);
		panel_2.add(lblCobertura);

		JLabel label_3 = new JLabel("Obra Social:");
		label_3.setBounds(10, 36, 106, 14);
		panel_2.add(label_3);

		txtObra = new JTextField();
		txtObra.setEditable(false);
		txtObra.setBounds(142, 36, 90, 20);
		panel_2.add(txtObra);

		JLabel label_6 = new JLabel("Plan:");
		label_6.setBounds(10, 66, 46, 14);
		panel_2.add(label_6);

		txtPlan = new JTextField();
		txtPlan.setEditable(false);
		txtPlan.setBounds(142, 66, 90, 20);
		panel_2.add(txtPlan);

		JLabel label_7 = new JLabel("N\u00BA de Afiliado:");
		label_7.setBounds(10, 100, 83, 14);
		panel_2.add(label_7);

		txtNum = new JTextField();
		txtNum.setEditable(false);
		txtNum.setColumns(10);
		txtNum.setBounds(142, 97, 90, 20);
		panel_2.add(txtNum);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 200, 574, 307);
		panel_3.add(scrollPane);

		table = new JTable();

		this.historiaClinica = new DefaultTableModel(null, columnasHistoria) {

			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unused")
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(historiaClinica);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		scrollPane.setViewportView(table);

		btnVerHitos = new JButton("Mostrar importantes");
		btnVerHitos.setBounds(414, 138, 170, 23);
		panel_3.add(btnVerHitos);

		JLabel lblHistoriaClnica = new JLabel("HISTORIA CL\u00CDNICA");
		lblHistoriaClnica.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHistoriaClnica.setBounds(228, 11, 135, 14);
		panel_3.add(lblHistoriaClnica);

		btnAgregarEntrada = new JButton("Agregar entrada");
		btnAgregarEntrada.setBounds(424, 518, 160, 23);
		panel_3.add(btnAgregarEntrada);

		btnMostrarDetalles = new JButton("Mostrar Detalles");
		btnMostrarDetalles.setBounds(10, 518, 170, 23);
		panel_3.add(btnMostrarDetalles);

		agregarPatologia = new JButton("Agregar");
		agregarPatologia.setBounds(414, 104, 85, 23);
		panel_3.add(agregarPatologia);

		btnQuitarPatologia = new JButton("Quitar");
		btnQuitarPatologia.setBounds(499, 104, 85, 23);
		panel_3.add(btnQuitarPatologia);

		cmbPatologia = new JComboBox<>();
		cmbPatologia.setBounds(414, 66, 170, 20);
		panel_3.add(cmbPatologia);

		JLabel lblPatologas = new JLabel("Patolog\u00EDas");
		lblPatologas.setBounds(414, 41, 107, 14);
		panel_3.add(lblPatologas);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE))
						.addGap(9).addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 594, GroupLayout.PREFERRED_SIZE)
						.addGap(4)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(11)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
								.addGap(11)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)))
				.addContainerGap()));

		importante = new JCheckBox("Importante");
		importante.setFont(new Font("Tahoma", Font.BOLD, 12));
		importante.setBounds(479, 36, 109, 23);
		panel_3.add(importante);

		JLabel lblFiltrarPorMdico = new JLabel("M\u00E9dico:");
		lblFiltrarPorMdico.setBounds(10, 175, 54, 14);
		panel_3.add(lblFiltrarPorMdico);

		JLabel lblFiltrarPorEspecialidad = new JLabel("Especialidad:");
		lblFiltrarPorEspecialidad.setBounds(295, 175, 75, 14);
		panel_3.add(lblFiltrarPorEspecialidad);

		cmbEspecialidad = new JComboBox<String>();
		cmbEspecialidad.setEditable(true);
		cmbEspecialidad.setBounds(370, 172, 117, 20);
		panel_3.add(cmbEspecialidad);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 41, 394, 120);
		panel_3.add(scrollPane_1);

		table_patologias = new JTable();
		this.patologias = new DefaultTableModel(null, columnasPatologia) {

			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unused")
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table_patologias.setModel(patologias);
		scrollPane_1.setViewportView(table_patologias);
		
		btnFiltrarMedico = new JButton("Filtrar");
		btnFiltrarMedico.setBounds(200, 171, 85, 23);
		panel_3.add(btnFiltrarMedico);
		
		btnFiltrarEspecialidad = new JButton("Filtrar");
		btnFiltrarEspecialidad.setBounds(499, 172, 85, 23);
		panel_3.add(btnFiltrarEspecialidad);
		
		cmbMedico = new JComboBox<String>();
		cmbMedico.setEditable(true);
		cmbMedico.setBounds(56, 172, 135, 20);
		panel_3.add(cmbMedico);
		getContentPane().setLayout(groupLayout);
		this.setSize(875,615);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see presentacion.vista._PantallaHistoriaClinica#getTxtNombre()
	 */
	@Override
	public JTextField getTxtNombre() {
		return txtNombre;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see presentacion.vista._PantallaHistoriaClinica#getTxtApellido()
	 */
	@Override
	public JTextField getTxtApellido() {
		return txtApellido;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see presentacion.vista._PantallaHistoriaClinica#getTxtDNI()
	 */
	@Override
	public JTextField getTxtDNI() {
		return txtDNI;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see presentacion.vista._PantallaHistoriaClinica#getTxtEmail()
	 */
	@Override
	public JTextField getTxtEmail() {
		return txtEmail;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see presentacion.vista._PantallaHistoriaClinica#getTxtTeléfono()
	 */
	@Override
	public JTextField getTxtTeléfono() {
		return txtTeléfono;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see presentacion.vista._PantallaHistoriaClinica#getTxtOcupacion()
	 */
	@Override
	public JTextField getTxtOcupacion() {
		return txtOcupacion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see presentacion.vista._PantallaHistoriaClinica#getTxtNacionalidad()
	 */
	@Override
	public JTextField getTxtNacionalidad() {
		return txtNacionalidad;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see presentacion.vista._PantallaHistoriaClinica#getTxtDireccion()
	 */
	@Override
	public JTextField getTxtDireccion() {
		return txtDireccion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see presentacion.vista._PantallaHistoriaClinica#getTxtObra()
	 */
	@Override
	public JTextField getTxtObra() {
		return txtObra;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see presentacion.vista._PantallaHistoriaClinica#getTxtPlan()
	 */
	@Override
	public JTextField getTxtPlan() {
		return txtPlan;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see presentacion.vista._PantallaHistoriaClinica#getTxtNum()
	 */
	@Override
	public JTextField getTxtNum() {
		return txtNum;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see presentacion.vista._PantallaHistoriaClinica#getTable()
	 */
	@Override
	public JTable getTable() {
		return table;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see presentacion.vista._PantallaHistoriaClinica#getColumnasHistoria()
	 */
	@Override
	public String[] getColumnasHistoria() {
		return columnasHistoria;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see presentacion.vista._PantallaHistoriaClinica#getHistoriaClinica()
	 */
	@Override
	public DefaultTableModel getHistoriaClinica() {
		return historiaClinica;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see presentacion.vista._PantallaHistoriaClinica#getPaciente()
	 */
	@Override
	public String getPaciente() {
		return paciente;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see presentacion.vista._PantallaHistoriaClinica#getChoNac()
	 */
	@Override
	public JDateChooser getChoNac() {
		return choNac;
	}

	public void setChoNac(JDateChooser choNac) {
		this.choNac = choNac;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see presentacion.vista._PantallaHistoriaClinica#getBtnEditar()
	 */
	@Override
	public JButton getBtnEditar() {
		return btnEditar;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see presentacion.vista._PantallaHistoriaClinica#getBtnAgregarEntrada()
	 */
	@Override
	public JButton getBtnAgregarEntrada() {
		return btnAgregarEntrada;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see presentacion.vista._PantallaHistoriaClinica#getBtnVerHitos()
	 */
	@Override
	public JButton getBtnVerHitos() {
		return btnVerHitos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see presentacion.vista._PantallaHistoriaClinica#getCmbEstado()
	 */
	@Override
	public JComboBox<String> getCmbEstado() {
		return cmbEstado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see presentacion.vista._PantallaHistoriaClinica#getCmbSexo()
	 */
	@Override
	public JComboBox<String> getCmbSexo() {
		return cmbSexo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see presentacion.vista._PantallaHistoriaClinica#getBtnMostrarDetalles()
	 */
	@Override
	public JButton getBtnMostrarDetalles() {
		return btnMostrarDetalles;
	}

	public JTable getTable_patologias() {
		return table_patologias;
	}

	public JComboBox<String> getCmbMedico() {
		return cmbMedico;
	}

	public String[] getColumnasPatologia() {
		return columnasPatologia;
	}

	public DefaultTableModel getPatologias() {
		return patologias;
	}

	public JButton getBtnQuitarPatologia() {
		return btnQuitarPatologia;
	}

	public JButton getAgregarPatologia() {
		return agregarPatologia;
	}

	public JComboBox<PatologiaDTO> getCmbPatologías() {
		return cmbPatologia;
	}

	public JCheckBox getImportante() {
		return importante;
	}


	public JComboBox<PatologiaDTO> getCmbPatologia() {
		return cmbPatologia;
	}

	public JButton getBtnFiltrarMedico() {
		return btnFiltrarMedico;
	}

	public JButton getBtnFiltrarEspecialidad() {
		return btnFiltrarEspecialidad;
	}

	public JComboBox<String> getCmbEspecialidad() {
		return cmbEspecialidad;
	}

}
