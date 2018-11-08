package presentacion.vista;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class UIHabitaciones  extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableHabitaciones;
	private String[] columnasHabitaciones = {"id", "Habitación" };
	private DefaultTableModel modelHabitaciones;
	private JPanel panel_1;
	private JButton btnReservar;

	/**
	 * Create the frame.
	 */
	public UIHabitaciones() {
		setTitle("Habitaciones");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 212, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		this.modelHabitaciones = new DefaultTableModel(null, columnasHabitaciones) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(0, 11, 196, 407);
		contentPane.add(panel_1);
		tableHabitaciones = new JTable(modelHabitaciones);
		tableHabitaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableHabitaciones.setAutoCreateColumnsFromModel(false);
		TableColumnModel columnModel = tableHabitaciones.getColumnModel();
		TableColumn column = columnModel.getColumn(0);
		column.setMaxWidth(0);
		column.setMinWidth(0);
		column.setPreferredWidth(0);
		tableHabitaciones.revalidate();
		
		JScrollPane scrollPane = new JScrollPane(tableHabitaciones);
		scrollPane.setBounds(20, 36, 157, 307);
		panel_1.add(scrollPane);
		
		JLabel lblHabitacionesDisponibles = new JLabel("HABITACIONES DISPONIBLES");
		lblHabitacionesDisponibles.setBounds(10, 11, 167, 14);
		panel_1.add(lblHabitacionesDisponibles);
		
		btnReservar = new JButton("Reservar");
		btnReservar.setBounds(20, 362, 154, 23);
		panel_1.add(btnReservar);
		
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JTable getTableHabitaciones() {
		return tableHabitaciones;
	}

	public String[] getColumnasHabitaciones() {
		return columnasHabitaciones;
	}

	public DefaultTableModel getModelHabitaciones() {
		return modelHabitaciones;
	}

	public JPanel getPanel_1() {
		return panel_1;
	}

	public JButton getBtnReservar() {
		return btnReservar;
	}
	
}
