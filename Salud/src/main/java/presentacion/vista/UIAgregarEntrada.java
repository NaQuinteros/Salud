package presentacion.vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UIAgregarEntrada extends JFrame implements _PantallaAgregarEntradaAHistoria {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel model;
	private JTextArea txtAreaDiag;
	private JButton btnAgregarPractica;
	private JButton btnAgregarEntrada;
	private String [] nombreColumnas = {"Código", "Práctica"};
	public UIAgregarEntrada() {
		setResizable(false);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setLayout(null);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(428, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
					.addContainerGap())
		);

		btnAgregarPractica = new JButton("Agregar pr\u00E1cticas");
		btnAgregarPractica.setBounds(24, 393, 150, 23);
		panel_1.add(btnAgregarPractica);

		JLabel label_4 = new JLabel("AGREGAR ENTRADA");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_4.setBounds(10, 11, 135, 14);
		panel_1.add(label_4);
		this.setTitle("Nueva entrada");

		btnAgregarEntrada = new JButton("Aceptar");
		btnAgregarEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAgregarEntrada.setBounds(184, 393, 150, 23);
		panel_1.add(btnAgregarEntrada);
		model = new DefaultTableModel(null,	this.nombreColumnas){
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		
				JLabel lblIngreseUnDiagnstico = new JLabel("Ingrese un diagn\u00F3stico:");
				lblIngreseUnDiagnstico.setBounds(10, 36, 150, 14);
				panel_1.add(lblIngreseUnDiagnstico);
				
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(20, 61, 314, 321);
				panel_1.add(scrollPane_1);
				
				txtAreaDiag = new JTextArea();
				scrollPane_1.setViewportView(txtAreaDiag);
				txtAreaDiag.setWrapStyleWord(true);
				txtAreaDiag.setLineWrap(true);
		getContentPane().setLayout(groupLayout);
		
		this.setSize(393, 489);

		
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

	@Override
	public JButton getBtnAgregarPractica() {
		return btnAgregarPractica;
	}


	@Override
	public JButton getBtnAgregarEntrada() {
		return btnAgregarEntrada;
	}


	/* (non-Javadoc)
	 * @see presentacion.vista._PantallaAgregarEntrada#getModel()
	 */
	@Override
	public DefaultTableModel getModel() {
		return model;
	}

	/* (non-Javadoc)
	 * @see presentacion.vista._PantallaAgregarEntrada#getNombreColumnas()
	 */
	@Override
	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	@Override
	public JTable getTable() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public JTextArea getTxtAreaDiag() {
		return this.txtAreaDiag;
	}

	@Override
	public JButton getBtnQuitarPractica() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
