package presentacion.vista;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JDateChooser;

import dto.EspecialidadDTO;

public class UIRecepcion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombrePaciente;
	private JTextField textApellidoPaciente;
	private JTextField textDniPaciente;
	private JTextField textEmailPaciente;
	private JTextField textTelefonoPaciente;
	private JTextField textNombreContacto;
	private JTextField textTelefonoContacto;
	private JTextField textNroAfiliado;

	/***/
	private JButton btnRegistrarPaciente;
	private JButton btnConsultarPaciente;
	private JButton btnEditarPaciente;
	private JMenuItem hHorarios;
	private JMenuItem mntmConsultarSala;
	private JMenuItem mntmTurnosCancelados;
	/***/
	private JTextField comboBoxObraSocial;
	private JTextField comboBoxPlan;
	private JComboBox<EspecialidadDTO> comboBoxEspecialidad;
	private JButton btnAsignar;
	private JButton btnImprimirComprobante;
	private JButton btnImprimirLista;
	private JButton btnCancelar;
	private JButton btnPracticas;
	private JLabel lblL, lblM, lblX, lblJ, lblV, lblD, lblS;
	private JButton btnReestablecer;
	private JDateChooser dateMedicos;
	private JTable tablaMedicos;
	private JTable tablaTurnos;
	private String[] columnasMedico = { "ID", "Nombre", "Matrícula", "Especialidad" };
	private String[] columnasHorario = { "Hora", "Disponibilidad", "Paciente" };
	private DefaultTableModel modelMedicos;
	private DefaultTableModel modeldisponibilidad;

	/**
	 * Create the frame.
	 */
	public UIRecepcion() {
		setResizable(false);
		setTitle("Recepci\u00F3n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1061, 529);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnLoguin = new JMenu("Horarios");
		menuBar.add(mnLoguin);

		hHorarios = new JMenuItem("Gestionar horarios de m\u00E9dicos");
		mnLoguin.add(hHorarios);

		JMenu mnSalaDeEspera = new JMenu("Sala de Espera");
		menuBar.add(mnSalaDeEspera);

		mntmConsultarSala = new JMenuItem("Consultar Sala");
		mnSalaDeEspera.add(mntmConsultarSala);

		JMenu mnTurnos = new JMenu("Turnos");
		menuBar.add(mnTurnos);

		mntmTurnosCancelados = new JMenuItem("Turnos cancelados");
		mnTurnos.add(mntmTurnosCancelados);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		JPanel RecepcionistaPanel = new JPanel();
		contentPane.add(RecepcionistaPanel, "name_401259860592");
		RecepcionistaPanel.setLayout(null);

		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setBounds(10, 0, 242, 208);
		RecepcionistaPanel.add(panel_6);
		panel_6.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(10, 75, 109, 14);
		panel_6.add(lblNewLabel);

		textNombrePaciente = new JTextField();
		textNombrePaciente.setText("");
		textNombrePaciente.setBounds(79, 75, 153, 20);
		panel_6.add(textNombrePaciente);
		textNombrePaciente.setColumns(10);
		textNombrePaciente.setEditable(false);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(10, 100, 109, 14);
		panel_6.add(lblApellido);

		textApellidoPaciente = new JTextField();
		textApellidoPaciente.setBounds(79, 100, 153, 20);
		panel_6.add(textApellidoPaciente);
		textApellidoPaciente.setColumns(10);
		textApellidoPaciente.setEditable(false);

		JLabel lblDnilc = new JLabel("DNI/LC");
		lblDnilc.setBounds(10, 147, 109, 14);
		panel_6.add(lblDnilc);

		textDniPaciente = new JTextField();
		textDniPaciente.setBounds(79, 150, 153, 20);
		panel_6.add(textDniPaciente);
		textDniPaciente.setColumns(10);
		textDniPaciente.setEditable(false);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 172, 109, 14);
		panel_6.add(lblEmail);

		textEmailPaciente = new JTextField();
		textEmailPaciente.setBounds(79, 175, 153, 20);
		panel_6.add(textEmailPaciente);
		textEmailPaciente.setColumns(10);
		textEmailPaciente.setEditable(false);

		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setBounds(10, 125, 109, 14);
		panel_6.add(lblTelefono);

		textTelefonoPaciente = new JTextField();
		textTelefonoPaciente.setBounds(79, 125, 153, 20);
		panel_6.add(textTelefonoPaciente);
		textTelefonoPaciente.setColumns(10);
		textTelefonoPaciente.setEditable(false);

		JLabel lblInformacinDelPaciente = new JLabel("DATOS DEL PACIENTE");
		lblInformacinDelPaciente.setBounds(10, 11, 222, 14);
		panel_6.add(lblInformacinDelPaciente);

		JToolBar toolBar_2 = new JToolBar();
		toolBar_2.setFloatable(false);
		toolBar_2.setBounds(6, 36, 222, 27);
		panel_6.add(toolBar_2);

		btnRegistrarPaciente = new JButton("Registrar");
		toolBar_2.add(btnRegistrarPaciente);

		btnConsultarPaciente = new JButton("Consultar");
		toolBar_2.add(btnConsultarPaciente);

		btnEditarPaciente = new JButton("Editar");
		toolBar_2.add(btnEditarPaciente);

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7.setBounds(10, 359, 242, 87);
		RecepcionistaPanel.add(panel_7);
		panel_7.setLayout(null);

		JLabel lblInformacinDelContacto = new JLabel("Informaci\u00F3n de Emergencia");
		lblInformacinDelContacto.setBounds(10, 6, 222, 14);
		panel_7.add(lblInformacinDelContacto);

		JLabel lblNombreDelContacto = new JLabel("Nombre:");
		lblNombreDelContacto.setBounds(10, 31, 77, 14);
		panel_7.add(lblNombreDelContacto);

		textNombreContacto = new JTextField();
		textNombreContacto.setBounds(97, 28, 135, 20);
		panel_7.add(textNombreContacto);
		textNombreContacto.setColumns(10);
		textNombreContacto.setEditable(false);

		JLabel lblTelefonoDelContacto = new JLabel("Tel\u00E9fono:");
		lblTelefonoDelContacto.setBounds(10, 56, 110, 14);
		panel_7.add(lblTelefonoDelContacto);

		textTelefonoContacto = new JTextField();
		textTelefonoContacto.setBounds(97, 56, 135, 20);
		panel_7.add(textTelefonoContacto);
		textTelefonoContacto.setColumns(10);
		textTelefonoContacto.setEditable(false);

		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8.setBounds(10, 218, 242, 130);
		RecepcionistaPanel.add(panel_8);
		panel_8.setLayout(null);

		JLabel lblCoberturaMdica = new JLabel("Cobertura");
		lblCoberturaMdica.setBounds(10, 11, 106, 14);
		panel_8.add(lblCoberturaMdica);

		JLabel lblObraSocial = new JLabel("Obra Social:");
		lblObraSocial.setBounds(10, 36, 90, 14);
		panel_8.add(lblObraSocial);

		comboBoxObraSocial = new JTextField();
		comboBoxObraSocial.setBounds(98, 36, 134, 20);
		panel_8.add(comboBoxObraSocial);
		comboBoxObraSocial.setEditable(false);

		JLabel lblPlan = new JLabel("Plan:");
		lblPlan.setBounds(10, 66, 63, 14);
		panel_8.add(lblPlan);

		comboBoxPlan = new JTextField();
		comboBoxPlan.setBounds(98, 66, 134, 20);
		panel_8.add(comboBoxPlan);
		comboBoxPlan.setEditable(false);

		JLabel lblNroDeAfiliado = new JLabel("Nº de Afiliado:");
		lblNroDeAfiliado.setBounds(10, 100, 74, 14);
		panel_8.add(lblNroDeAfiliado);

		textNroAfiliado = new JTextField();
		textNroAfiliado.setBounds(98, 97, 134, 20);
		panel_8.add(textNroAfiliado);
		textNroAfiliado.setColumns(10);
		textNroAfiliado.setEditable(false);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(262, 0, 763, 446);
		RecepcionistaPanel.add(panel_1);
		panel_1.setLayout(null);

		modeldisponibilidad = new DefaultTableModel(null, this.columnasHorario) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};
		tablaTurnos = new JTable(modeldisponibilidad);
		TableColumnModel tcm = tablaTurnos.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(90);
		tcm.getColumn(0).setMaxWidth(90);
		tcm.getColumn(1).setPreferredWidth(120);
		tcm.getColumn(1).setMaxWidth(120);
		tcm.getColumn(0).setResizable(false);
		tcm.getColumn(1).setResizable(false);
		tcm.getColumn(2).setResizable(false);
		JScrollPane scrollPane_1 = new JScrollPane(tablaTurnos);
		JScrollPane scrollPane = new JScrollPane(tablaTurnos);
		scrollPane.setBounds(10, 228, 476, 156);
		panel_1.add(scrollPane);

		modelMedicos = new DefaultTableModel(null, this.columnasMedico) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};
		tablaMedicos = new JTable(modelMedicos);
		tablaMedicos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1 = new JScrollPane(tablaMedicos);
		scrollPane_1.setBounds(10, 64, 743, 129);
		panel_1.add(scrollPane_1);
		tablaMedicos.setAutoCreateColumnsFromModel(false);
		TableColumnModel columnModel = tablaMedicos.getColumnModel();
		TableColumn column = columnModel.getColumn(0);
		tablaMedicos.removeColumn(column);
		tablaMedicos.revalidate();

		JLabel label = new JLabel("M\u00E9dicos");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(10, 41, 743, 23);
		panel_1.add(label);

		dateMedicos = new JDateChooser();
		dateMedicos.setEnabled(true);
		dateMedicos.setBounds(496, 255, 257, 22);
		panel_1.add(dateMedicos);

		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFecha.setBounds(496, 228, 257, 23);
		panel_1.add(lblFecha);

		JPanel panel = new JPanel();
		panel.setForeground(new Color(50, 205, 50));
		panel.setBounds(507, 204, 229, 23);
		panel_1.add(panel);
		panel.setLayout(null);

		lblL = new JLabel("L");
		lblL.setForeground(new Color(50, 205, 50));
		lblL.setBounds(75, 5, 6, 14);
		lblL.setHorizontalAlignment(SwingConstants.CENTER);
		lblL.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblL.setEnabled(false);
		panel.add(lblL);

		lblM = new JLabel("M");
		lblM.setForeground(new Color(50, 205, 50));
		lblM.setBounds(86, 5, 10, 14);
		lblM.setHorizontalAlignment(SwingConstants.CENTER);
		lblM.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblM.setEnabled(false);
		panel.add(lblM);

		lblX = new JLabel("X");
		lblX.setForeground(new Color(50, 205, 50));
		lblX.setBounds(101, 5, 7, 14);
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblX.setEnabled(false);
		panel.add(lblX);

		lblJ = new JLabel("J");
		lblJ.setForeground(new Color(50, 205, 50));
		lblJ.setBounds(113, 5, 6, 14);
		lblJ.setHorizontalAlignment(SwingConstants.CENTER);
		lblJ.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblJ.setEnabled(false);
		panel.add(lblJ);

		lblV = new JLabel("V");
		lblV.setForeground(new Color(50, 205, 50));
		lblV.setBounds(124, 5, 7, 14);
		lblV.setHorizontalAlignment(SwingConstants.CENTER);
		lblV.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblV.setEnabled(false);
		panel.add(lblV);

		JLabel label_6 = new JLabel("");
		label_6.setBounds(136, 12, 0, 0);
		panel.add(label_6);

		JLabel label_7 = new JLabel("");
		label_7.setBounds(141, 12, 0, 0);
		panel.add(label_7);

		JLabel label_8 = new JLabel("");
		label_8.setBounds(146, 12, 0, 0);
		panel.add(label_8);

		JLabel label_9 = new JLabel("");
		label_9.setBounds(151, 12, 0, 0);
		panel.add(label_9);

		JLabel label_10 = new JLabel("");
		label_10.setBounds(156, 12, 0, 0);
		panel.add(label_10);

		lblS = new JLabel("S");
		lblS.setForeground(new Color(50, 205, 50));
		lblS.setBounds(141, 5, 7, 14);
		lblS.setHorizontalAlignment(SwingConstants.CENTER);
		lblS.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblS.setEnabled(false);
		panel.add(lblS);

		lblD = new JLabel("D");
		lblD.setForeground(new Color(50, 205, 50));
		lblD.setBounds(151, 5, 8, 14);
		lblD.setHorizontalAlignment(SwingConstants.CENTER);
		lblD.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblD.setEnabled(false);
		panel.add(lblD);

		btnAsignar = new JButton("Asignar Turno");
		btnAsignar.setEnabled(false);
		btnAsignar.setBounds(10, 395, 312, 40);
		panel_1.add(btnAsignar);

		btnImprimirComprobante = new JButton("Comprobante de Turno");
		btnImprimirComprobante.setEnabled(false);
		btnImprimirComprobante.setBounds(496, 344, 257, 40);
		panel_1.add(btnImprimirComprobante);

		btnImprimirLista = new JButton("Lista de Turnos de Hoy");
		btnImprimirLista.setBounds(496, 293, 257, 40);
		panel_1.add(btnImprimirLista);

		btnCancelar = new JButton("Cancelar Turno");
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(332, 395, 154, 40);
		panel_1.add(btnCancelar);

		btnPracticas = new JButton("Pr\u00E1cticas y Autorizaciones");
		btnPracticas.setEnabled(false);
		btnPracticas.setBounds(496, 395, 257, 40);
		panel_1.add(btnPracticas);

		JLabel label_13 = new JLabel("Especialidad:");
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_13.setBounds(10, 11, 104, 25);
		panel_1.add(label_13);

		comboBoxEspecialidad = new JComboBox<EspecialidadDTO>();
		comboBoxEspecialidad.setBounds(112, 11, 358, 25);
		panel_1.add(comboBoxEspecialidad);

		btnReestablecer = new JButton("Reestablecer");
		btnReestablecer.setBounds(480, 11, 273, 25);
		panel_1.add(btnReestablecer);

		JLabel lblTurnos = new JLabel("Turnos");
		lblTurnos.setBounds(10, 204, 476, 16);
		panel_1.add(lblTurnos);
		lblTurnos.setHorizontalAlignment(SwingConstants.CENTER);
		lblTurnos.setFont(new Font("Tahoma", Font.BOLD, 13));

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(9, 328, 243, 2);
		RecepcionistaPanel.add(separator_1);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JTextField getTextNombrePaciente() {

		return this.textNombrePaciente;
	}

	public JTextField getTextApellidoPaciente() {

		return this.textApellidoPaciente;
	}

	public JTextField getTextDniPaciente() {

		return this.textDniPaciente;
	}

	public JTextField getTextTelefonoPaciente() {

		return this.textTelefonoPaciente;
	}

	public JTextField getTextEmailPaciente() {

		return this.textEmailPaciente;
	}

	public JTextField getTextNroAfiliado() {

		return this.textNroAfiliado;
	}

	public JTextField getTextObraSocial() {

		return this.comboBoxObraSocial;
	}

	public JTextField getTextPlan() {

		return this.comboBoxPlan;
	}

	public JButton getButtonRegistrarPaciente() {

		return this.btnRegistrarPaciente;
	}

	public JButton getButtonConsultarPaciente() {

		return this.btnConsultarPaciente;
	}

	public JButton getButtonEditarPaciente() {

		return this.btnEditarPaciente;
	}

	public JButton getButtonEliminarPaciente() {

		return null;
	}

	public JTextField getTextNombreContacto() {

		return this.textNombreContacto;
	}

	public JTextField getTextTelefonoContacto() {

		return this.textTelefonoContacto;
	}

	public void mostrar(boolean value) {
		this.setVisible(true);
	}

	public boolean isvisible() {
		return this.isVisible();
	}

	public JMenuItem gethHorarios() {
		return hHorarios;
	}

	public void sethHorarios(JMenuItem hHorarios) {
		this.hHorarios = hHorarios;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JButton getBtnRegistrarPaciente() {
		return btnRegistrarPaciente;
	}

	public JButton getBtnConsultarPaciente() {
		return btnConsultarPaciente;
	}

	public JButton getBtnEditarPaciente() {
		return btnEditarPaciente;
	}

	public JTextField getComboBoxObraSocial() {
		return comboBoxObraSocial;
	}

	public JTextField getComboBoxPlan() {
		return comboBoxPlan;
	}

	public JComboBox<EspecialidadDTO> getComboBoxEspecialidad() {
		return comboBoxEspecialidad;
	}

	public JButton getBtnAsignar() {
		return btnAsignar;
	}

	public JButton getBtnImprimirComprobante() {
		return btnImprimirComprobante;
	}

	public JButton getBtnImprimirLista() {
		return btnImprimirLista;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JButton getBtnPracticas() {
		return btnPracticas;
	}

	public JLabel getLblL() {
		return lblL;
	}

	public JLabel getLblM() {
		return lblM;
	}

	public JLabel getLblX() {
		return lblX;
	}

	public JMenuItem getMntmConsultarSala() {
		return mntmConsultarSala;
	}

	public DefaultTableModel getModelMedicos() {
		return modelMedicos;
	}

	public DefaultTableModel getModeldisponibilidad() {
		return modeldisponibilidad;
	}

	public JLabel getLblJ() {
		return lblJ;
	}

	public JLabel getLblV() {
		return lblV;
	}

	public JLabel getLblD() {
		return lblD;
	}

	public JLabel getLblS() {
		return lblS;
	}

	public JButton getBtnReestablecer() {
		return btnReestablecer;
	}

	public JDateChooser getDateMedicos() {
		return dateMedicos;
	}

	public JTable getTablaMedicos() {
		return tablaMedicos;
	}

	public JTable getTablaTurnos() {
		return tablaTurnos;
	}

	public String[] getColumnasMedico() {
		return columnasMedico;
	}

	public String[] getColumnasHorario() {
		return columnasHorario;
	}

	public DefaultTableModel getTableModelMedicos() {
		return modelMedicos;
	}

	public DefaultTableModel getTableModelTurnos() {
		return modeldisponibilidad;
	}

	public JMenuItem getMntmTurnosCancelados() {
		return mntmTurnosCancelados;
	}

}
