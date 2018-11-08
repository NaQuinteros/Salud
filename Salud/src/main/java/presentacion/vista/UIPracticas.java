package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

public class UIPracticas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JButton btnOk;
	private JButton btnAgregar_cod;
	private JButton btnAgregar_desc;
	private DefaultTableModel tableModel;
	private String[] columnas = new String[] { "Módulo", "C\u00F3digo", "Práctica" };
	private JButton btnQuitar;
	private JComboBox<String> cmbDesc;
	private JComboBox<String> cmbCod;

	public UIPracticas() {

		this.setTitle("Consultar Pr\u00E1cticas");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JLabel lblCodigoDePrctica = new JLabel("Codigo de pr\u00E1ctica:");
		lblCodigoDePrctica.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCodigoDePrctica.setBounds(10, 11, 155, 14);

		JLabel lblNombreDePrctica = new JLabel("Descripci\u00F3n de pr\u00E1ctica:");
		lblNombreDePrctica.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombreDePrctica.setBounds(10, 60, 227, 23);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 121, 634, 229);

		btnOk = new JButton("Aceptar");
		btnOk.setBounds(246, 356, 168, 28);

		btnAgregar_desc = new JButton("Agregar");
		btnAgregar_desc.setBounds(551, 84, 93, 23);

		btnAgregar_cod = new JButton("Agregar");
		btnAgregar_cod.setBounds(144, 36, 93, 23);

		table = new JTable();
		tableModel = new DefaultTableModel(null, columnas) {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("unused")
			boolean[] columnEditables = new boolean[] { false, false };

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table.setModel(tableModel);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setMaxWidth(60);
		table.getColumnModel().getColumn(0).setPreferredWidth(220);
		table.getColumnModel().getColumn(0).setMaxWidth(220);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		getContentPane().setLayout(null);
		scrollPane.setViewportView(table);
		getContentPane().add(scrollPane);
		getContentPane().add(btnAgregar_cod);
		getContentPane().add(lblNombreDePrctica);
		getContentPane().add(lblCodigoDePrctica);
		getContentPane().add(btnAgregar_desc);
		getContentPane().add(btnOk);

		btnQuitar = new JButton("Quitar");
		btnQuitar.setBounds(10, 356, 93, 23);
		getContentPane().add(btnQuitar);

		cmbDesc = new JComboBox<>();
		cmbDesc.setBounds(10, 85, 531, 20);
		getContentPane().add(cmbDesc);

		cmbCod = new JComboBox<>();
		cmbCod.setBounds(10, 36, 124, 20);
		getContentPane().add(cmbCod);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setResizable(false);
		this.setSize(660, 424);
		this.setVisible(true);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JButton getBtnOk() {
		return btnOk;
	}

	public void setBtnOk(JButton btnOk) {
		this.btnOk = btnOk;
	}

	public JButton getBtnAgregar_cod() {
		return btnAgregar_cod;
	}

	public void setBtnAgregar_cod(JButton btnAgregar_cod) {
		this.btnAgregar_cod = btnAgregar_cod;
	}

	public JButton getBtnAgregar_desc() {
		return btnAgregar_desc;
	}

	public void setBtnAgregar_desc(JButton btnAgregar_desc) {
		this.btnAgregar_desc = btnAgregar_desc;
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public String[] getColumnas() {
		return columnas;
	}

	public void setColumnas(String[] columnas) {
		this.columnas = columnas;
	}

	public JButton getBtnQuitar() {
		return btnQuitar;
	}

	public void setBtnQuitar(JButton btnQuitar) {
		this.btnQuitar = btnQuitar;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JComboBox<String> getCmbDesc() {
		return cmbDesc;
	}

	public JComboBox<String> getCmbCod() {
		return cmbCod;
	}

}
