package presentacion.vista;

import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import dto.MedicoDTO;

public class UISalaEsperaRecepcion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable salaEspera;
	private JButton btnAgregarPaciente;
	private String[] columnasSalaEspera = { "ID", "Nombre", "Apellido", "DNI", "Estado", "Hora" };
	private DefaultTableModel modelTurnos;
	private JComboBox<MedicoDTO> comboBox;

	public UISalaEsperaRecepcion() {
		this.setTitle("Sala de Espera");

		JScrollPane scrollPane = new JScrollPane();

		btnAgregarPaciente = new JButton("Cambiar estado");

		salaEspera = new JTable();

		scrollPane.setViewportView(salaEspera);

		salaEspera.getTableHeader().setReorderingAllowed(false);
		this.modelTurnos = new DefaultTableModel(null, columnasSalaEspera) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		salaEspera.setModel(modelTurnos);
		TableColumnModel columnModel = salaEspera.getColumnModel();
		TableColumn column = columnModel.getColumn(0);
		salaEspera.removeColumn(column);
		salaEspera.revalidate();
		JLabel lblSalaDeEspera = new JLabel("Sala de Espera");
		lblSalaDeEspera.setFont(new Font("Tahoma", Font.BOLD, 15));

		comboBox = new JComboBox<>();

		JLabel lblMdico = new JLabel("M\u00E9dico:");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblMdico, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnAgregarPaciente, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 129,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
						.addComponent(lblSalaDeEspera))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap().addComponent(lblSalaDeEspera).addGap(31)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblMdico).addComponent(
						comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(10).addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnAgregarPaciente).addContainerGap()));
		getContentPane().setLayout(groupLayout);
		salaEspera.getColumnModel().getColumn(0).setResizable(false);
		salaEspera.getColumnModel().getColumn(1).setResizable(false);
		salaEspera.getColumnModel().getColumn(2).setResizable(false);
		salaEspera.getColumnModel().getColumn(3).setResizable(false);
		salaEspera.getColumnModel().getColumn(4).setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setSize(578, 446);
	}

	public JTable getSalaEspera() {
		return salaEspera;
	}

	public JButton getBtnAgregarPaciente() {
		return btnAgregarPaciente;
	}

	public String[] getColumnasSalaEspera() {
		return columnasSalaEspera;
	}

	public DefaultTableModel getModelTurnos() {
		return modelTurnos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JComboBox<MedicoDTO> getComboBox() {
		return comboBox;
	}
}
