package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JDateChooser;

import dto.EspecialidadDTO;

public class UIHorario extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel modelMedicos;
	private JTable tablaMedicos;
	private JTable tablaHorarios;
	private DefaultTableModel modelHorarios;
	private JComboBox<EspecialidadDTO> comboBoxEspecialidad;
	private JButton btnReestablecer;
	private JButton btnBorrar;
	private JButton btnAsignar;
	private JComboBox<String> cbDia;
	private JSpinner h1, h2, m1, m2;
	private String[] columnasMedico = { "ID", "Nombre", "Matrícula", "Especialidad" };
	private String[] columnasHorario = { "Día", "Hora Inicio", "Hora Fin" };
	private JButton btnDeshabilitar;
	private JSpinner spIntevalo;
	private JButton btnGenerar;
	private JDateChooser dateHasta;
	private JDateChooser dateDesde;

	public UIHorario() {
		setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		setTitle("Horarios");
		setResizable(false);
		setBounds(320, 0, 758, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 11, 752, 60);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		comboBoxEspecialidad = new JComboBox<EspecialidadDTO>();
		comboBoxEspecialidad.setBounds(112, 8, 608, 20);
		panel_1.add(comboBoxEspecialidad);

		JLabel lblEspecialidad = new JLabel("Especialidad:");
		lblEspecialidad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEspecialidad.setBounds(10, 11, 104, 14);
		panel_1.add(lblEspecialidad);

		btnReestablecer = new JButton("Reestablecer");
		btnReestablecer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnReestablecer.setBounds(311, 30, 112, 28);
		panel_1.add(btnReestablecer);

		// tabla de medicos!!

		modelMedicos = new DefaultTableModel(null, this.columnasMedico) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};

		modelHorarios = new DefaultTableModel(null, this.columnasHorario) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};

		btnDeshabilitar = new JButton("Deshabilitar Turnos");
		btnDeshabilitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDeshabilitar.setBounds(137, 659, 445, 34);
		contentPane.add(btnDeshabilitar);

		JPanel panel = new JPanel();
		panel.setBounds(0, 76, 752, 198);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblMedicos = new JLabel("M\u00E9dicos");
		lblMedicos.setBounds(10, 0, 732, 23);
		panel.add(lblMedicos);
		lblMedicos.setHorizontalAlignment(SwingConstants.CENTER);
		lblMedicos.setFont(new Font("Tahoma", Font.BOLD, 13));

		tablaMedicos = new JTable(modelMedicos);
		TableColumnModel columnModel = tablaMedicos.getColumnModel();
		TableColumn column = columnModel.getColumn(0);
		tablaMedicos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(tablaMedicos);
		scrollPane.setBounds(10, 23, 732, 164);
		panel.add(scrollPane);
		tablaMedicos.setAutoCreateColumnsFromModel(false);
		tablaMedicos.removeColumn(column);
		tablaMedicos.revalidate();

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 504, 752, 144);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblHabilitarTurnos = new JLabel("Habilitar Turnos");
		lblHabilitarTurnos.setBounds(10, 11, 440, 23);
		panel_3.add(lblHabilitarTurnos);
		lblHabilitarTurnos.setHorizontalAlignment(SwingConstants.CENTER);
		lblHabilitarTurnos.setFont(new Font("Tahoma", Font.BOLD, 13));

		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setBounds(20, 52, 48, 23);
		panel_3.add(lblDesde);
		lblDesde.setHorizontalAlignment(SwingConstants.CENTER);

		dateDesde = new JDateChooser();
		dateDesde.setBounds(78, 52, 152, 22);
		panel_3.add(dateDesde);
		dateDesde.setEnabled(false);

		btnGenerar = new JButton("Generar Turnos");
		btnGenerar.setEnabled(false);
		btnGenerar.setBounds(131, 85, 452, 36);
		panel_3.add(btnGenerar);

		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setBounds(240, 51, 48, 23);
		panel_3.add(lblHasta);
		lblHasta.setHorizontalAlignment(SwingConstants.CENTER);

		dateHasta = new JDateChooser();
		dateHasta.setBounds(298, 51, 152, 22);
		panel_3.add(dateHasta);
		dateHasta.setEnabled(false);

		spIntevalo = new JSpinner();
		spIntevalo.setEnabled(false);
		spIntevalo.setBounds(497, 54, 110, 20);
		panel_3.add(spIntevalo);
		spIntevalo.setModel(new SpinnerNumberModel(0, 0, 1440, 5));

		JLabel lblMinutos_1 = new JLabel("Minutos");
		lblMinutos_1.setBounds(617, 53, 48, 23);
		panel_3.add(lblMinutos_1);
		lblMinutos_1.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblMinutos = new JLabel("Duraci\u00F3n de los Turnos");
		lblMinutos.setBounds(497, 11, 165, 23);
		panel_3.add(lblMinutos);
		lblMinutos.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinutos.setFont(new Font("Tahoma", Font.BOLD, 13));

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 285, 752, 208);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		btnAsignar = new JButton("Asignar Rango Horario");
		btnAsignar.setEnabled(false);
		btnAsignar.setBounds(465, 105, 199, 48);
		panel_2.add(btnAsignar);

		btnBorrar = new JButton("Borrar");
		btnBorrar.setEnabled(false);
		btnBorrar.setBounds(522, 164, 86, 36);
		panel_2.add(btnBorrar);

		JLabel lblHorarios_1 = new JLabel("Horarios");
		lblHorarios_1.setBounds(10, 11, 387, 23);
		panel_2.add(lblHorarios_1);
		lblHorarios_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblHorarios_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		tablaHorarios = new JTable(modelHorarios);
		JScrollPane scrollPane_1 = new JScrollPane(tablaHorarios);
		scrollPane_1.setBounds(10, 34, 407, 166);
		panel_2.add(scrollPane_1);

		JPanel panel_4 = new JPanel();
		scrollPane_1.setColumnHeaderView(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		cbDia = new JComboBox<String>();
		cbDia.setEnabled(false);
		cbDia.setBounds(497, 34, 139, 20);
		panel_2.add(cbDia);
		cbDia.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Domingo", "Lunes", "Martes", "Mi\u00E9rcoles", "Jueves", "Viernes", "S\u00E1bado" }));

		JLabel lblDe = new JLabel("de");
		lblDe.setBounds(430, 68, 15, 14);
		panel_2.add(lblDe);
		lblDe.setHorizontalAlignment(SwingConstants.CENTER);

		h1 = new JSpinner();
		h1.setEnabled(false);
		h1.setBounds(455, 65, 48, 20);
		panel_2.add(h1);
		h1.setModel(new SpinnerNumberModel(0, 0, 23, 1));

		m1 = new JSpinner();
		m1.setEnabled(false);
		m1.setBounds(500, 65, 48, 20);
		panel_2.add(m1);
		m1.setModel(new SpinnerNumberModel(0, 0, 55, 5));

		h2 = new JSpinner();
		h2.setEnabled(false);
		h2.setBounds(591, 65, 48, 20);
		panel_2.add(h2);
		h2.setModel(new SpinnerNumberModel(0, 0, 23, 1));

		m2 = new JSpinner();
		m2.setEnabled(false);
		m2.setBounds(637, 65, 48, 20);
		panel_2.add(m2);
		m2.setModel(new SpinnerNumberModel(0, 0, 55, 5));

		JLabel lblA = new JLabel("a");
		lblA.setBounds(558, 68, 23, 14);
		panel_2.add(lblA);
		lblA.setHorizontalAlignment(SwingConstants.CENTER);
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAsignar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DefaultTableModel getModelHorarios() {
		return modelHorarios;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public JButton getBtnAsignar() {
		return btnAsignar;
	}

	public boolean setvisible(boolean value) {
		this.setVisible(value);
		return false;
	}

	public boolean isvisible() {

		return this.isVisible();
	}

	public JComboBox<EspecialidadDTO> getComboBoxEspecialidad() {

		return this.comboBoxEspecialidad;
	}

	public DefaultTableModel getTableModelMedicos() {

		return this.modelMedicos;
	}

	public DefaultTableModel getTableModelTurnos() {

		return this.modelHorarios;
	}

	public JButton getButtonListar() {

		return this.btnReestablecer;
	}

	public JButton getButtonConsultarHorario() {

		return null;
	}

	public JTable getTablaHorarios() {

		return this.tablaHorarios;
	}

	public JComboBox<String> getComboBoxMedicos() {
		return this.getComboBoxMedicos();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public DefaultTableModel getModelMedicos() {
		return modelMedicos;
	}

	public JTable getTablaMedicos() {
		return tablaMedicos;
	}

	public JTable getTableDisponibilidad() {
		return tablaHorarios;
	}

	public DefaultTableModel getModeldisponibilidad() {
		return modelHorarios;
	}

	public JButton getBtnReestablecer() {
		return btnReestablecer;
	}

	public String[] getColumnasMedico() {
		return columnasMedico;
	}

	public JButton getBtnHorarios() {
		return btnBorrar;
	}

	public String[] getColumnasHorario() {
		return columnasHorario;
	}

	public JComboBox<String> getCbDia() {
		return cbDia;
	}

	public int getHoraInicio() {
		return (int) h1.getValue();
	}

	public int getHoraFin() {
		return (int) h2.getValue();
	}

	public int getMinutosInicio() {
		return (int) m1.getValue();
	}

	public int getMinutosFin() {
		return (int) m2.getValue();
	}

	public JSpinner getH1() {
		return h1;
	}

	public JSpinner getH2() {
		return h2;
	}

	public JSpinner getM1() {
		return m1;
	}

	public JSpinner getM2() {
		return m2;
	}

	public JButton getBtnDeshabilitar() {
		return btnDeshabilitar;
	}

	public JSpinner getSpIntevalo() {
		return spIntevalo;
	}

	public JButton getBtnGenerar() {
		return btnGenerar;
	}

	public JDateChooser getDateHasta() {
		return dateHasta;
	}

	public JDateChooser getDateDesde() {
		return dateDesde;
	}
}
