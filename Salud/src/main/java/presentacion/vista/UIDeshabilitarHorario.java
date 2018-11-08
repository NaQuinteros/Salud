package presentacion.vista;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.toedter.calendar.JDateChooser;

import dto.MedicoDTO;

public class UIDeshabilitarHorario extends JFrame{
	private static final long serialVersionUID = 1L;
	private JButton btnDeshabilitar;
	private JSpinner h1, m1, h2, m2;
	private JDateChooser dateInicio, dateFin;
	private JFrame frame;
	private JComboBox<MedicoDTO> comboBox;



	/**
	 * Create the application.
	 */
	public UIDeshabilitarHorario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.setTitle("Deshabilitar Rango Horario");
		frame.setResizable(false);
		frame.setBounds(100, 100, 361, 221);
		frame.getContentPane().setLayout(null);

		comboBox = new JComboBox<>();
		comboBox.setSelectedItem(null);
		comboBox.setBounds(6, 30, 335, 20);
		frame.getContentPane().add(comboBox);

		JLabel lblMdico = new JLabel("M\u00E9dico");
		lblMdico.setHorizontalAlignment(SwingConstants.CENTER);
		lblMdico.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMdico.setBounds(105, 11, 138, 10);
		frame.getContentPane().add(lblMdico);

		h1 = new JSpinner();
		h1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		h1.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		h1.setBounds(254, 61, 45, 20);
		frame.getContentPane().add(h1);

		m1 = new JSpinner();
		m1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		m1.setModel(new SpinnerNumberModel(0, 0, 55, 5));
		m1.setBounds(296, 61, 45, 20);
		frame.getContentPane().add(m1);

		dateInicio = new JDateChooser();
		dateInicio.setBounds(74, 61, 169, 20);
		frame.getContentPane().add(dateInicio);

		JLabel lblDe = new JLabel("Desde");
		lblDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblDe.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDe.setBounds(6, 61, 45, 20);
		frame.getContentPane().add(lblDe);

		JLabel lblA = new JLabel("Hasta");
		lblA.setHorizontalAlignment(SwingConstants.CENTER);
		lblA.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblA.setBounds(6, 92, 45, 20);
		frame.getContentPane().add(lblA);

		h2 = new JSpinner();
		h2.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		h2.setBounds(254, 92, 45, 20);
		frame.getContentPane().add(h2);

		m2 = new JSpinner();
		m2.setModel(new SpinnerNumberModel(0, 0, 55, 5));
		m2.setBounds(296, 91, 45, 20);
		frame.getContentPane().add(m2);

		dateFin = new JDateChooser();
		dateFin.setBounds(74, 93, 169, 20);
		frame.getContentPane().add(dateFin);

		btnDeshabilitar = new JButton("Deshabilitar");
		btnDeshabilitar.setBounds(74, 151, 214, 23);
		frame.getContentPane().add(btnDeshabilitar);
		
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public JButton getBtnDeshabilitar() {
		return btnDeshabilitar;
	}

	public JSpinner getH1() {
		return h1;
	}

	public JSpinner getM1() {
		return m1;
	}

	public JSpinner getH2() {
		return h2;
	}

	public JSpinner getM2() {
		return m2;
	}

	public JDateChooser getDateInicio() {
		return dateInicio;
	}

	public JDateChooser getDateFin() {
		return dateFin;
	}

	public JFrame getFrmAbmHorariosMdicos() {
		return frame;
	}

	public JComboBox<MedicoDTO> getComboBox() {
		return comboBox;
	}
	
	public void setvisible(boolean value) {
		this.frame.setVisible(value);
	}

}
