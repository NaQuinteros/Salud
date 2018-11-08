package presentacion.vista;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;

public class UIInternacionAgregar extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JDateChooser dateEgresoProgramado;
	private JDateChooser dateIngresoProgramado;
	private JButton btnGuardar;
	private String[] columnasHabitaciones = {"id", "Habitacion" };
	private DefaultTableModel modelHabitaciones;
	private JLabel lblDiagnostico;
	private JTextArea txtDiagnosticoInicial;
	private JTextArea txtObservacionInicial;
	private JComboBox<String> cbMotivo;
	private JTextArea txtObservacionFinal;
	private JTextArea txtDiagnosticoFinal;
	private JLabel lblNombreHab;
	private JButton btnElegir;
	private JDateChooser IngresoReal;
	private JDateChooser EgresoReal;
	private JButton btnPracticas;
	private JLabel lblHabitacionReal;
	private JLabel lblHabReal;
	private JButton btnElegirReal;
	private JLabel lblNombrePaciente;
	private JLabel lblHabitReservada;

	/**
	 * Create the frame.
	 */
	public UIInternacionAgregar() {
		setTitle("Internaci\u00F3n");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100,70, 571, 607);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(22, 36, 262, 478);
		contentPane.add(panel);
		
		JLabel label_5 = new JLabel("DATOS DE INTERNACIÓN TENTATIVOS");
		label_5.setBounds(10, 11, 222, 14);
		panel.add(label_5);
		
		JLabel lblIngresoProgramado = new JLabel("Ingreso programado:");
		lblIngresoProgramado.setBounds(10, 36, 120, 14);
		panel.add(lblIngresoProgramado);
		
		lblHabitReservada = new JLabel("Habitaci\u00F3n Reservada:");
		lblHabitReservada.setBounds(10, 99, 133, 14);
		panel.add(lblHabitReservada);
		
		JLabel lblEgresoProgramado = new JLabel("Egreso Programado:");
		lblEgresoProgramado.setBounds(10, 61, 120, 14);
		panel.add(lblEgresoProgramado);
			
		dateEgresoProgramado = new JDateChooser();
//		dateEgresoProgramado.setDateFormatString(date);
		dateEgresoProgramado.setBounds(129, 61, 123, 20);
		panel.add(dateEgresoProgramado);
		
		dateIngresoProgramado = new JDateChooser();
//		dateIngresoProgramado.setDateFormatString(date);
		dateIngresoProgramado.setBounds(129,36,123,20);
		panel.add(dateIngresoProgramado);
		
		JLabel lblObservacion = new JLabel("Observacion Inicial:");
		lblObservacion.setBounds(12, 188, 163, 14);
		panel.add(lblObservacion);
		JScrollPane scroll = new JScrollPane (JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    scroll.setBounds(10, 213, 242, 110);
		panel.add(scroll);
		
		txtObservacionInicial = new JTextArea();
		scroll.setViewportView(txtObservacionInicial);
		txtObservacionInicial.setLineWrap(true);
		//		panel.add(txtObservacionInicial);
				txtObservacionInicial.setColumns(10);
		
		txtDiagnosticoInicial = new JTextArea();
		txtDiagnosticoInicial.setBounds(10, 359, 242, 108);
		txtDiagnosticoInicial.setLineWrap(true);
		JScrollPane scroll1 = new JScrollPane (txtDiagnosticoInicial,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    scroll1.setBounds(10, 359, 242, 108);
		panel.add(scroll1);
		//panel.add(txtDiagnosticoInicial);
		txtDiagnosticoInicial.setColumns(10);
		
		lblDiagnostico = new JLabel("Diagnostico Inicial");
		lblDiagnostico.setBounds(10, 334, 215, 14);
		panel.add(lblDiagnostico);
		
		lblNombreHab = new JLabel("");
		lblNombreHab.setBounds(139, 99, 113, 14);
		panel.add(lblNombreHab);
		
		btnElegir = new JButton("Elegir");
		btnElegir.setBounds(20, 114, 66, 23);
		panel.add(btnElegir);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(317, 526, 200, 40);
		contentPane.add(btnGuardar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(294, 36, 262, 478);
		contentPane.add(panel_1);
		
		JLabel label = new JLabel("DATOS DE INTERNACIÓN CONCRETOS");
		label.setBounds(10, 11, 222, 14);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("Ingreso Real:");
		label_1.setBounds(10, 36, 120, 14);
		panel_1.add(label_1);
		
		JLabel label_3 = new JLabel("Egreso Real:");
		label_3.setBounds(10, 61, 120, 14);
		panel_1.add(label_3);
		
		EgresoReal = new JDateChooser();
		EgresoReal.setBounds(129, 61, 123, 20);
		panel_1.add(EgresoReal);
		
		IngresoReal = new JDateChooser();
		IngresoReal.setBounds(129, 36, 123, 20);
		panel_1.add(IngresoReal);
		
		JLabel label_4 = new JLabel("Observacion Final:");
		label_4.setBounds(10, 188, 204, 14);
		panel_1.add(label_4);
		
		txtObservacionFinal = new JTextArea();
		txtObservacionFinal.setColumns(10);
		txtObservacionFinal.setBounds(10, 213, 242, 108);
		JScrollPane scroll2 = new JScrollPane (txtObservacionFinal,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    scroll2.setBounds(10, 213, 242, 108);
		panel_1.add(scroll2);
		txtObservacionFinal.setLineWrap(true);
//		panel_1.add(txtObservacionFinal);
		
		txtDiagnosticoFinal = new JTextArea();
		txtDiagnosticoFinal.setColumns(10);
		txtDiagnosticoFinal.setBounds(10, 358, 242, 109);
		JScrollPane scroll3 = new JScrollPane (txtDiagnosticoFinal,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    scroll3.setBounds(10, 358, 242, 108);
		panel_1.add(scroll3);
		txtDiagnosticoFinal.setLineWrap(true);
//		panel_1.add(txtDiagnosticoFinal);
		
		JLabel label_6 = new JLabel("Diagnostico Final");
		label_6.setBounds(10, 333, 222, 14);
		panel_1.add(label_6);
		
		JLabel label_2 = new JLabel("Motivo de Egreso");
		label_2.setBounds(10, 141, 130, 14);
		panel_1.add(label_2);
		

		cbMotivo = new JComboBox<String>();
		cbMotivo.addItem("");
		cbMotivo.addItem("Alta médica");
		cbMotivo.addItem("Obito");
		cbMotivo.addItem("Traslado");
		cbMotivo.addItem("Fuga");
		cbMotivo.setBounds(122, 138, 130, 20);
		panel_1.add(cbMotivo);
		
		lblHabitacionReal = new JLabel("Habitacion Real :");
		lblHabitacionReal.setBounds(10, 101, 100, 14);
		panel_1.add(lblHabitacionReal);
		
		lblHabReal = new JLabel("");
		lblHabReal.setBounds(105, 101, 59, 14);
		panel_1.add(lblHabReal);
		
		btnElegirReal = new JButton("Cambiar");
		btnElegirReal.setBounds(163, 92, 89, 23);
		panel_1.add(btnElegirReal);
		

		
		btnPracticas = new JButton("Prácticas y Autorizaciones");
		btnPracticas.setBounds(65, 526, 176, 40);
		contentPane.add(btnPracticas);
		
		JLabel lblDetallesDeInternacin = new JLabel("Detalles de internaci\u00F3n del paciente : ");
		lblDetallesDeInternacin.setBounds(22, 11, 267, 14);
		contentPane.add(lblDetallesDeInternacin);
		
		lblNombrePaciente = new JLabel("");
		lblNombrePaciente.setBounds(239, 11, 317, 14);
		contentPane.add(lblNombrePaciente);
		
	
		this.modelHabitaciones = new DefaultTableModel(null, columnasHabitaciones) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	



	public JLabel getLblHabReal() {
		return lblHabReal;
	}





	public JLabel getLblHabitReservada() {
		return lblHabitReservada;
	}





	public static long getSerialversionuid() {
		return serialVersionUID;
	}







	public JLabel getLblHabitacionReal() {
		return lblHabReal;
	}





	public JButton getBtnElegirReal() {
		return btnElegirReal;
	}





	public JPanel getContentPane() {
		return contentPane;
	}





	public JButton getBtnGuardar() {
		return btnGuardar;
	}





	public JLabel getLblDiagnostico() {
		return lblDiagnostico;
	}





	public JDateChooser getDateEgresoProgramado() {
		return dateEgresoProgramado;
	}



	public JDateChooser getDateIngresoProgramado() {
		return dateIngresoProgramado;
	}

	public DefaultTableModel getModelHabitaciones() {
		return modelHabitaciones;
	}

	public String[] getColumnasHabitaciones() {
		return columnasHabitaciones;
	}

	public JTextArea getTxtDiagnosticoInicial() {
		return txtDiagnosticoInicial;
	}

	public JTextArea getTxtObservacionInicial() {
		return txtObservacionInicial;
	}

	public JComboBox<String> getCbMotivo() {
		return cbMotivo;
	}

	public JTextArea getTxtObservacionFinal() {
		return txtObservacionFinal;
	}

	public JTextArea getTxtDiagnosticoFinal() {
		return txtDiagnosticoFinal;
	}
	
	public JLabel getLblNombreHab() {
		return lblNombreHab;
	}


	public JLabel getLblNombrePaciente(){
		return lblNombrePaciente;
	}


	public JButton getBtnElegir() {
		return btnElegir;
	}





	public JDateChooser getIngresoReal() {
		return IngresoReal;
	}





	public JDateChooser getEgresoReal() {
		return EgresoReal;
	}





	public JButton getBtnPracticas() {
		return btnPracticas;
	}
}
