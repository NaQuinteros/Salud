package presentacion.vista;

import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class UIMedicoPrincipal extends JFrame implements _PantallaPrincipalMedico {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable salaEspera;
	private JButton btnHistoriaClinica;
	private String[] columnasSalaEspera = { "Nombre", "Apellido", "DNI", "Hora" };
	private DefaultTableModel modelTurnos;
	

	public UIMedicoPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 661, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setTitle("Inicio médico");
		this.setResizable(false);

		JScrollPane scrollPane = new JScrollPane();

		JLabel lblSalaDeEspera = new JLabel("Sala de espera");
		lblSalaDeEspera.setFont(new Font("Tahoma", Font.BOLD, 15));

		btnHistoriaClinica = new JButton("Ver historia cl\u00EDnica de paciente");

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
						.addComponent(btnHistoriaClinica, Alignment.TRAILING)
						.addComponent(lblSalaDeEspera))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSalaDeEspera)
					.addGap(21)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
					.addComponent(btnHistoriaClinica)
					.addContainerGap())
		);

		salaEspera = new JTable();
		salaEspera.getTableHeader().setReorderingAllowed(false);
		this.modelTurnos = new DefaultTableModel(null, columnasSalaEspera) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		salaEspera.setModel(modelTurnos);
		salaEspera.getColumnModel().getColumn(0).setResizable(false);
		salaEspera.getColumnModel().getColumn(1).setResizable(false);
		salaEspera.getColumnModel().getColumn(2).setResizable(false);
		salaEspera.getColumnModel().getColumn(3).setResizable(false);
		scrollPane.setViewportView(salaEspera);
		contentPane.setLayout(gl_contentPane);
		
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

	@Override
	public JTable getSalaEspera() {
		return salaEspera;
	}

	@Override
	public JButton getBtnHistoriaClinica() {
		return btnHistoriaClinica;
	}

	@Override
	public DefaultTableModel getModelTurnos() {
		return modelTurnos;
	}

	public void setModelTurnos(DefaultTableModel modelTurnos) {
		this.modelTurnos = modelTurnos;
	}

	@Override
	public String[] getColumnasSalaEspera() {
		return columnasSalaEspera;
	}

	public void setColumnasSalaEspera(String[] columnasSalaEspera) {
		this.columnasSalaEspera = columnasSalaEspera;
	}

}
