package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dto.ObraSocialDTO;
import dto.PlanDTO;

public class UIRegistroDePaciente extends JFrame implements __IGuiRegistradorPaciente {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textApellido;
	private JTextField textNombre;
	private JTextField textDni;
	private JTextField textTelefono;
	private JTextField textEmail;
	private JTextField textNroAfiliado;
	private JTextField textNombreContacto;
	private JTextField textTelefonoContacto;
	private boolean editando;
	private JCheckBox chckbxParticular;
	
	private JComboBox<ObraSocialDTO> comboBoxObraSocial;
	private JComboBox<PlanDTO> comboBoxPlan;

	private JButton btnGuardarPaciente;

	public UIRegistroDePaciente() {
		setResizable(false);
		setTitle("Registracion de paciente");
		setBounds(100, 100, 468, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 442, 92);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 11, 64, 14);
		panel.add(lblNombre);
		
		JLabel lblNewLabel = new JLabel("Apellido");
		lblNewLabel.setBounds(241, 11, 66, 14);
		panel.add(lblNewLabel);
		
		textApellido = new JTextField();
		textApellido.setBounds(317, 11, 119, 20);
		panel.add(textApellido);
		textApellido.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setBounds(74, 8, 119, 20);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(10, 36, 46, 14);
		panel.add(lblDni);
		
		textDni = new JTextField();
		textDni.setBounds(74, 33, 119, 20);
		panel.add(textDni);
		textDni.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono");
		lblTelefono.setBounds(241, 36, 66, 14);
		panel.add(lblTelefono);
		
		textTelefono = new JTextField();
		textTelefono.setBounds(317, 36, 118, 20);
		panel.add(textTelefono);
		textTelefono.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("E-mail");
		lblNewLabel_1.setBounds(10, 61, 64, 14);
		panel.add(lblNewLabel_1);
		
		textEmail = new JTextField();
		textEmail.setBounds(74, 58, 119, 20);
		panel.add(textEmail);
		textEmail.setColumns(10);
		
		chckbxParticular = new JCheckBox("Paciente particular");
		chckbxParticular.setBounds(241, 60, 195, 23);
		panel.add(chckbxParticular);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 114, 442, 79);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblObraSocial = new JLabel("Obra social");
		lblObraSocial.setBounds(10, 11, 76, 14);
		panel_1.add(lblObraSocial);
		
		JLabel lblPlan = new JLabel("Plan");
		lblPlan.setBounds(246, 11, 42, 14);
		panel_1.add(lblPlan);
		
		comboBoxObraSocial = new JComboBox<ObraSocialDTO>();
		comboBoxObraSocial.setBounds(108, 8, 111, 20);
		panel_1.add(comboBoxObraSocial);
		
		comboBoxPlan = new JComboBox<PlanDTO>();
		comboBoxPlan.setBounds(298, 8, 121, 20);
		panel_1.add(comboBoxPlan);
		
		JLabel lblNroAfiliado = new JLabel("Nro afiliado");
		lblNroAfiliado.setBounds(10, 46, 76, 14);
		panel_1.add(lblNroAfiliado);
		
		textNroAfiliado = new JTextField();
		textNroAfiliado.setBounds(108, 43, 111, 20);
		panel_1.add(textNroAfiliado);
		textNroAfiliado.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 205, 442, 70);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNombreDeContacto = new JLabel("Nombre de contacto de emergencia");
		lblNombreDeContacto.setBounds(10, 11, 249, 14);
		panel_2.add(lblNombreDeContacto);
		
		textNombreContacto = new JTextField();
		textNombreContacto.setBounds(269, 8, 163, 20);
		panel_2.add(textNombreContacto);
		textNombreContacto.setColumns(10);
		
		JLabel lblTelefonoDeContacto = new JLabel("Tel\u00E9fono de emergencia");
		lblTelefonoDeContacto.setBounds(10, 36, 249, 23);
		panel_2.add(lblTelefonoDeContacto);
		
		textTelefonoContacto = new JTextField();
		textTelefonoContacto.setBounds(269, 37, 163, 20);
		panel_2.add(textTelefonoContacto);
		textTelefonoContacto.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(106, 286, 279, 38);
		contentPane.add(panel_3);
		
		btnGuardarPaciente = new JButton("Guardar contacto");
		panel_3.add(btnGuardarPaciente);
	}

	@Override
	public JTextField getTextNombrePaciente() {
		// TODO Auto-generated method stub
		return this.textNombre;
	}

	@Override
	public JTextField getTextApellidoPaciente() {
		// TODO Auto-generated method stub
		return this.textApellido;
	}

	@Override
	public JTextField getTextDniPaciente() {
		// TODO Auto-generated method stub
		return this.textDni;
	}

	@Override
	public JTextField getTextTelefonoPaciente() {
		// TODO Auto-generated method stub
		return this.textTelefono;
	}

	@Override
	public JTextField getTextEmailPaciente() {
		// TODO Auto-generated method stub
		return this.textEmail;
	}

	@Override
	public JTextField getTextNroAfiliado() {
		// TODO Auto-generated method stub
		return this.textNroAfiliado;
	}

	@Override
	public JTextField getTextNombreContacto() {
		// TODO Auto-generated method stub
		return this.textNombreContacto;
	}

	@Override
	public JTextField getTextTelefonoContacto() {
		// TODO Auto-generated method stub
		return this.textTelefonoContacto;
	}



	@Override
	public boolean isvisible() {
		// TODO Auto-generated method stub
		return this.isVisible();
	}

	@Override
	public JButton getButtonGuardarPaciente() {
		// TODO Auto-generated method stub
		return this.btnGuardarPaciente;
	}

	@Override
	public boolean setvisible(boolean value) {
		// TODO Auto-generated method stub
		this.setVisible(value);;
		return false;
	}

	public boolean isEditando() {
		return editando;
	}

	public void setEditando(boolean editando) {
		this.editando = editando;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JTextField getTextApellido() {
		return textApellido;
	}

	public JTextField getTextNombre() {
		return textNombre;
	}

	public JTextField getTextDni() {
		return textDni;
	}

	public JTextField getTextTelefono() {
		return textTelefono;
	}

	public JTextField getTextEmail() {
		return textEmail;
	}

	public JCheckBox getChckbxParticular() {
		return chckbxParticular;
	}

	public JButton getBtnGuardarPaciente() {
		return btnGuardarPaciente;
	}

	public JComboBox<ObraSocialDTO> getComboBoxObraSocial() {
		return comboBoxObraSocial;
	}

	public JComboBox<PlanDTO> getComboBoxPlan() {
		return comboBoxPlan;
	}

}
