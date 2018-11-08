package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import dto.EspecialidadDTO;

public class UITurnosCancelados extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel modelMedicos;
	private JTable tablaMedicos;
	private JTable tablaTurnos;
	private DefaultTableModel modelTurnos;
	private JComboBox<EspecialidadDTO> comboBoxEspecialidad;
	private JButton btnReestablecer;
	private JButton btnLiberar;
	private JButton btnReasignar;
	private String[] columnasMedico = { "ID", "Nombre", "Matrícula", "Especialidad" };
	private String[] columnasTurno = { "ID", "DNI Paciente", "Nombre Paciente", "Fecha Original", "Teléfono", "Médico" };
	private JCheckBox chckbxTodosLosTurnos;
	private JScrollPane scrollPaneMedicos;

	public UITurnosCancelados() {
		setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		setTitle("Turnos Cancelados");
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

		btnReestablecer.setBounds(317, 32, 112, 28);
		panel_1.add(btnReestablecer);

		// tabla de medicos!!

		modelMedicos = new DefaultTableModel(null, this.columnasMedico) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};

		modelTurnos = new DefaultTableModel(null, this.columnasTurno) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};

		JPanel panel = new JPanel();
		panel.setBounds(0, 76, 752, 241);
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
		scrollPaneMedicos = new JScrollPane(tablaMedicos);
		scrollPaneMedicos.setBounds(10, 23, 732, 207);
		panel.add(scrollPaneMedicos);
		tablaMedicos.setAutoCreateColumnsFromModel(false);
		tablaMedicos.removeColumn(column);
		tablaMedicos.revalidate();

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 363, 752, 208);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblHorarios_1 = new JLabel("Turnos Cancelados");
		lblHorarios_1.setBounds(10, 11, 732, 23);
		panel_2.add(lblHorarios_1);
		lblHorarios_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblHorarios_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		tablaTurnos = new JTable(modelTurnos);
		TableColumnModel columnModel2 = tablaTurnos.getColumnModel();
		TableColumn column2 = columnModel2.getColumn(0);
		tablaTurnos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaTurnos.setAutoCreateColumnsFromModel(false);
		tablaTurnos.removeColumn(column2);
		tablaTurnos.revalidate();
		JScrollPane scrollPane_1 = new JScrollPane(tablaTurnos);
		scrollPane_1.setBounds(10, 34, 732, 166);
		panel_2.add(scrollPane_1);

		JPanel panel_4 = new JPanel();
		scrollPane_1.setColumnHeaderView(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		btnReasignar = new JButton("Asignar Turno Nuevo");
		btnReasignar.setBounds(106, 604, 200, 50);
		contentPane.add(btnReasignar);

		btnLiberar = new JButton("Marcar como Resuelto");
		btnLiberar.setBounds(431, 604, 200, 50);
		contentPane.add(btnLiberar);

		chckbxTodosLosTurnos = new JCheckBox("Turnos de Todos los M\u00E9dicos");
		chckbxTodosLosTurnos.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxTodosLosTurnos.setBounds(10, 324, 736, 32);
		chckbxTodosLosTurnos.setSelected(true);
		contentPane.add(chckbxTodosLosTurnos);
		btnLiberar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnReasignar.addActionListener(new ActionListener() {
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
		return modelTurnos;
	}

	public JButton getBtnBorrar() {
		return btnLiberar;
	}

	public JButton getBtnAsignar() {
		return btnReasignar;
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

	public DefaultTableModel getTableModelTurnos() {
		return modelTurnos;
	}

	public JButton getBtnReestablecer() {
		return btnReestablecer;
	}

	public String[] getColumnasMedico() {
		return columnasMedico;
	}

	public JButton getBtnLiberar() {
		return btnLiberar;
	}

	public JButton getBtnReasignar() {
		return btnReasignar;
	}

	public JCheckBox getChckbxTodosLosTurnos() {
		return chckbxTodosLosTurnos;
	}

	public JTable getTablaTurnos() {
		return tablaTurnos;
	}

	public DefaultTableModel getModelTurnos() {
		return modelTurnos;
	}

	public JScrollPane getScrollPaneMedicos() {
		return scrollPaneMedicos;
	}

	public String[] getColumnasTurno() {
		return columnasTurno;
	}

	public String getPaciente() {
		// TODO Auto-generated method stub
		return null;
	}

}
