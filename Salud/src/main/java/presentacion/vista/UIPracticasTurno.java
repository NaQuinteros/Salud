package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class UIPracticasTurno extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablaPracticas;
	private DefaultTableModel modelPracticas;
	private JButton btnBorrar;
	private JButton btnAutorizar1;
	private String[] columnasPractica = { "Código", "Descripción de la Práctica", "Arancel", "Costo Final", "Estado",
			"Código Aut.", "Fecha Aut.", "Sesiones" };
	private JButton btnAgregar;
	private JLabel lblTurno;
	private JDateChooser dateAutorizacion;
	private JSpinner spSesionesAutorizacion;
	private JLabel lblSesiones;
	private JTabbedPane tabbedPane;
	private JLabel label;
	private JTextField txtCodigo;
	private JButton btnPagar;
	private JSpinner spSesionesParticular;

	public UIPracticasTurno() {
		setTitle("Pr\u00E1cticas y Autorizaciones");
		setResizable(false);
		setBounds(100, 30, 1088, 623);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		lblTurno = new JLabel("Turno");
		lblTurno.setHorizontalAlignment(SwingConstants.CENTER);
		lblTurno.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTurno.setBounds(12, 11, 1060, 23);
		contentPane.add(lblTurno);

		modelPracticas = new DefaultTableModel(null, this.columnasPractica) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};

		btnBorrar = new JButton("Borrar Pr\u00E1ctica");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		btnBorrar.setBounds(12, 355, 207, 40);
		contentPane.add(btnBorrar);

		tablaPracticas = new JTable(modelPracticas);
		JScrollPane scrollPane_1 = new JScrollPane(tablaPracticas);
		scrollPane_1.setBounds(12, 143, 1060, 201);
		TableColumnModel tcm = tablaPracticas.getColumnModel();
		tcm.getColumn(0).setMaxWidth(80);
		tcm.getColumn(0).setPreferredWidth(80);
		tcm.getColumn(1).setMaxWidth(350);
		tcm.getColumn(1).setPreferredWidth(350);
		tcm.getColumn(2).setMaxWidth(100);
		tcm.getColumn(2).setPreferredWidth(100);
		tcm.getColumn(3).setMaxWidth(100);
		tcm.getColumn(3).setPreferredWidth(100);
		tcm.getColumn(4).setMaxWidth(180);
		tcm.getColumn(4).setMaxWidth(180);
		tcm.getColumn(5).setMaxWidth(80);
		tcm.getColumn(5).setPreferredWidth(80);
		tcm.getColumn(6).setMaxWidth(100);
		tcm.getColumn(6).setPreferredWidth(100);
		tcm.getColumn(7).setMaxWidth(80);
		tcm.getColumn(7).setPreferredWidth(80);

		contentPane.add(scrollPane_1);

		JPanel panel_4 = new JPanel();
		scrollPane_1.setColumnHeaderView(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		JLabel lblPracticas = new JLabel("Pr\u00E1cticas");
		lblPracticas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPracticas.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPracticas.setBounds(12, 120, 1060, 23);
		contentPane.add(lblPracticas);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 43, 1060, 76);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		btnAgregar = new JButton("Agregar Pr\u00E1ctica");
		btnAgregar.setBounds(10, 11, 1040, 51);
		panel_1.add(btnAgregar);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 429, 1060, 154);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Con Autorizaci\u00F3n", null, panel, null);
		panel.setLayout(null);

		JLabel lblFechaDeAutorizacin = new JLabel("C\u00F3digo de Autorizaci\u00F3n:");
		lblFechaDeAutorizacin.setBounds(10, 5, 185, 20);
		panel.add(lblFechaDeAutorizacin);
		lblFechaDeAutorizacin.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaDeAutorizacin.setFont(new Font("Tahoma", Font.BOLD, 13));

		dateAutorizacion = new JDateChooser();
		dateAutorizacion.setBounds(540, 5, 110, 20);
		panel.add(dateAutorizacion);
		dateAutorizacion.setEnabled(true);

		label = new JLabel("Fecha de Autorizaci\u00F3n:");
		label.setBounds(368, 5, 157, 20);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(label);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(173, 6, 110, 20);
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);

		btnAutorizar1 = new JButton("Autorizar Pr\u00E1ctica");
		btnAutorizar1.setBounds(302, 62, 392, 40);
		panel.add(btnAutorizar1);

		lblSesiones = new JLabel("Sesiones:");
		lblSesiones.setBounds(848, 5, 77, 20);
		panel.add(lblSesiones);
		lblSesiones.setHorizontalAlignment(SwingConstants.LEFT);
		lblSesiones.setFont(new Font("Tahoma", Font.BOLD, 13));

		spSesionesAutorizacion = new JSpinner();
		spSesionesAutorizacion.setBounds(935, 6, 110, 20);
		panel.add(spSesionesAutorizacion);
		spSesionesAutorizacion.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Sin Autorizaci\u00F3n", null, panel_2, null);
		panel_2.setLayout(null);

		btnPagar = new JButton("Cobrar Pr\u00E1ctica");
		btnPagar.setBounds(302, 62, 392, 40);
		panel_2.add(btnPagar);

		spSesionesParticular = new JSpinner();
		spSesionesParticular.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spSesionesParticular.setBounds(502, 31, 92, 20);
		panel_2.add(spSesionesParticular);

		JLabel label_1 = new JLabel("Sesiones:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBounds(302, 31, 190, 20);
		panel_2.add(label_1);

		JLabel lblAutorizacinDePrcticas = new JLabel("Autorizaci\u00F3n y Pagos");
		lblAutorizacinDePrcticas.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutorizacinDePrcticas.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAutorizacinDePrcticas.setBounds(12, 402, 1060, 16);
		contentPane.add(lblAutorizacinDePrcticas);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DefaultTableModel getModelPracticas() {
		return modelPracticas;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
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

	public JTable getTablaPracticas() {
		return tablaPracticas;
	}

	public JButton getBtnAutorizar() {
		return btnAutorizar1;
	}

	public String[] getColumnasPractica() {
		return columnasPractica;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JLabel getLblTurno() {
		return lblTurno;
	}

	public JDateChooser getDateChooser() {
		return dateAutorizacion;
	}

	public JLabel getLblSesiones() {
		return lblSesiones;
	}

	public JButton getBtnAutorizar1() {
		return btnAutorizar1;
	}

	public JDateChooser getDateAutorizacion() {
		return dateAutorizacion;
	}

	public JSpinner getSpSesionesAutorizacion() {
		return spSesionesAutorizacion;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public JLabel getLabel() {
		return label;
	}

	public JButton getBtnPagar() {
		return btnPagar;
	}

	public JSpinner getSpSesionesParticular() {
		return spSesionesParticular;
	}

}
