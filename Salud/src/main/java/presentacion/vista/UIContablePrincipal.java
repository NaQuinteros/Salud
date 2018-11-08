package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JSpinner;

import java.awt.Font;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class UIContablePrincipal extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableOS;
	private JTable tableMedicos;
	private JTable tablePracticas;
	private DefaultTableModel modelMedicos;
	private DefaultTableModel modelOS;
	private DefaultTableModel modelPracticas;
	private String[] columnasMedicos = {"idMedico","Médico", "Matrícula", "#Practicas", "Monto Total" };
	private String[] columnasOS = {"idOS","Obra Social", "#Practicas" , "Monto Total" };
	private String[] columnasPracticas = {"IdMedico","IdOS", "Código", "Descripción","Fecha", "Médico", "Paciente", "OS y Plan" , "Arancel" , "Monto cubierto" };
	private JComboBox<String> comboBox;
	private JButton btnImprimirMedico;
	private JButton btnImprimirOS;
	private JSpinner spinner;
	private JLabel lblPracticas;
	private JButton btnBuscarPracticas;
	private JMenuItem menuObras;
	
	
	public UIContablePrincipal() {
		setBounds(200, 10, 1000, 721);
		getContentPane().setLayout(null);
		setTitle("Personal Contable");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.modelMedicos = new DefaultTableModel(null, columnasMedicos) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		
		this.modelOS = new DefaultTableModel(null, columnasOS) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		
		this.modelPracticas = new DefaultTableModel(null, columnasPracticas) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tableOS = new JTable(modelOS);
		tableOS.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableOS.setAutoCreateColumnsFromModel(false);
		JScrollPane scrollPane = new JScrollPane(tableOS);
		scrollPane.setBounds(10, 93, 449, 245);
		TableColumnModel columnModelOS = tableOS.getColumnModel();
		TableColumn columnOS = columnModelOS.getColumn(0);
		columnOS.setMaxWidth(0);
		columnOS.setMinWidth(0);
		columnOS.setPreferredWidth(0);
		getContentPane().add(scrollPane);
		
		tableMedicos = new JTable(modelMedicos);
		tableMedicos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableMedicos.setAutoCreateColumnsFromModel(false);
		JScrollPane scrollPane1 = new JScrollPane(tableMedicos);
		scrollPane1.setBounds(533, 93, 449, 245);
		TableColumnModel columnModelMed = tableMedicos.getColumnModel();
		TableColumn columnMed = columnModelMed.getColumn(0);
		columnMed.setMaxWidth(0);
		columnMed.setMinWidth(0);
		columnMed.setPreferredWidth(0);
		getContentPane().add(scrollPane1);
		
		tablePracticas = new JTable(modelPracticas);
		tablePracticas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePracticas.setAutoCreateColumnsFromModel(false);
		JScrollPane scrollPane2 = new JScrollPane(tablePracticas);
		scrollPane2.setBounds(10, 384, 972, 298);
		TableColumnModel columnModel = tablePracticas.getColumnModel();
		TableColumn column = columnModel.getColumn(0);
		column.setMaxWidth(0);
		column.setMinWidth(0);
		column.setPreferredWidth(0);
		TableColumn column0 = columnModel.getColumn(3);
		column0.setMaxWidth(1000);
		column0.setMinWidth(200);
		column0.setPreferredWidth(500);
		TableColumn column1 = columnModel.getColumn(1);
		column1.setMaxWidth(0);
		column1.setMinWidth(0);
		column1.setPreferredWidth(0);
		TableColumn column2 = columnModel.getColumn(2);
		column2.setMaxWidth(60);
		TableColumn column4 = columnModel.getColumn(4);
		column4.setMinWidth(0);;
		column4.setPreferredWidth(95);
		column4.setMaxWidth(95);
		TableColumn column6 = columnModel.getColumn(6);
		column6.setMinWidth(90);
		column6.setPreferredWidth(90);
		column6.setMaxWidth(500);
		getContentPane().add(scrollPane2);
		
		JLabel lblMes = new JLabel("Mes");
		lblMes.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMes.setBounds(26, 37, 53, 20);
		getContentPane().add(lblMes);
		
		
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("Enero");
		comboBox.addItem("Febrero");
		comboBox.addItem("Marzo");
		comboBox.addItem("Abril");
		comboBox.addItem("Mayo");
		comboBox.addItem("Junio");
		comboBox.addItem("Julio");
		comboBox.addItem("Agosto");
		comboBox.addItem("Septiembre");
		comboBox.addItem("Octubre");
		comboBox.addItem("Noviembre");
		comboBox.addItem("Diciembre");
		comboBox.setBounds(58, 37, 117, 20);
		getContentPane().add(comboBox);
		
		JLabel lblAño = new JLabel("A\u00F1o");
		lblAño.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAño.setBounds(185, 37, 53, 20);
		getContentPane().add(lblAño);
		

		Calendar calendar = Calendar.getInstance();
        int currentYear;
        currentYear = calendar.get(Calendar.YEAR);

        SpinnerModel model =
            new SpinnerNumberModel(currentYear, //initial value
                                   currentYear - 100, //min
                                   currentYear + 100, //max
                                   1);                //step


        Date initDate = calendar.getTime();
        calendar.add(Calendar.YEAR, -100);
        Date earliestDate = calendar.getTime();
        calendar.add(Calendar.YEAR, 200);
        Date latestDate = calendar.getTime();
        model = new SpinnerDateModel(initDate,
                                    earliestDate,
                                    latestDate,
                                    Calendar.YEAR);
        spinner = new JSpinner(model);
        spinner.setEditor(new JSpinner.DateEditor(spinner, "yyyy"));
    	spinner.setBounds(219, 38, 89, 20);
		getContentPane().add(spinner);
		
		JLabel lblMdicos = new JLabel("M\u00E9dicos");
		lblMdicos.setHorizontalAlignment(SwingConstants.CENTER);
		lblMdicos.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMdicos.setBounds(533, 68, 451, 14);
		getContentPane().add(lblMdicos);
		
		JLabel lblObrasSociales = new JLabel("Obras Sociales");
		lblObrasSociales.setHorizontalAlignment(SwingConstants.CENTER);
		lblObrasSociales.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblObrasSociales.setBounds(10, 68, 449, 14);
		getContentPane().add(lblObrasSociales);
		
		lblPracticas = new JLabel("Pr\u00E1cticas");
		lblPracticas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPracticas.setBounds(10, 358, 972, 14);
		lblPracticas.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(lblPracticas);
		
		btnImprimirMedico = new JButton("Imprimir");
		btnImprimirMedico.setBounds(650, 349, 224, 23);
		getContentPane().add(btnImprimirMedico);
		
		btnImprimirOS = new JButton("Imprimir");
		btnImprimirOS.setBounds(97, 349, 224, 23);
		getContentPane().add(btnImprimirOS);
		
		btnBuscarPracticas = new JButton("Buscar Practicas ");
		btnBuscarPracticas.setBounds(326, 37, 158, 23);
		getContentPane().add(btnBuscarPracticas);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 994, 21);
		getContentPane().add(menuBar);
		
		JMenu mnLoguin = new JMenu("Coberturas");
		menuBar.add(mnLoguin);

		menuObras = new JMenuItem("Gestionar Coberturas");
		mnLoguin.add(menuObras);
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public JTable getTableOS() {
		return tableOS;
	}


	public JTable getTableMedicos() {
		return tableMedicos;
	}


	public JTable getTablePracticas() {
		return tablePracticas;
	}


	public DefaultTableModel getModelMedicos() {
		return modelMedicos;
	}


	public DefaultTableModel getModelOS() {
		return modelOS;
	}


	public DefaultTableModel getModelPracticas() {
		return modelPracticas;
	}


	public String[] getColumnasMedicos() {
		return columnasMedicos;
	}


	public String[] getColumnasOS() {
		return columnasOS;
	}


	public String[] getColumnasPracticas() {
		return columnasPracticas;
	}


	public JComboBox<String> getComboBox() {
		return comboBox;
	}


	public JButton getBtnImprimirMedico() {
		return btnImprimirMedico;
	}


	public JButton getBtnImprimirOS() {
		return btnImprimirOS;
	}


	public JSpinner getSpinner() {
		return spinner;
	}


	public JLabel getLblPracticas() {
		return lblPracticas;
	}


	public JButton getBtnBuscarPracticas() {
		return btnBuscarPracticas;
	}


	public JMenuItem getMenuObras() {
		return menuObras;
	}
}

