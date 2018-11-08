package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dto.EspecialidadDTO;

public class UICrearMedico extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField nombre;
	private JTextField usuario;
	private JTextField pass;
	private JButton btnAceptar;
	private JLabel lblEspecialidad;
	private JComboBox<EspecialidadDTO> cmbEspecialidades;
	private JTextField matricula;
	private JLabel lblMatricula;

	public UICrearMedico() {
		this.setTitle("Crear médico");
		getContentPane().setLayout(null);
		this.setResizable(false);
		this.setSize(251, 259);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		nombre = new JTextField();
		nombre.setBounds(106, 23, 121, 20);
		getContentPane().add(nombre);
		nombre.setColumns(10);

		JLabel lblNombreDeUsuario = new JLabel("Nombre");
		lblNombreDeUsuario.setBounds(10, 23, 86, 14);
		getContentPane().add(lblNombreDeUsuario);

		usuario = new JTextField();
		usuario.setColumns(10);
		usuario.setBounds(106, 54, 121, 20);
		getContentPane().add(usuario);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 54, 86, 14);
		getContentPane().add(lblUsuario);

		pass = new JTextField();
		pass.setColumns(10);
		pass.setBounds(106, 85, 121, 20);
		getContentPane().add(pass);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(10, 85, 86, 14);
		getContentPane().add(lblContrasea);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(138, 196, 89, 23);
		getContentPane().add(btnAceptar);

		lblEspecialidad = new JLabel("Especialidad");
		lblEspecialidad.setBounds(10, 150, 84, 14);
		getContentPane().add(lblEspecialidad);

		cmbEspecialidades = new JComboBox<EspecialidadDTO>();
		cmbEspecialidades.setBounds(106, 147, 121, 20);
		getContentPane().add(cmbEspecialidades);

		matricula = new JTextField();
		matricula.setColumns(10);
		matricula.setBounds(106, 116, 121, 20);
		getContentPane().add(matricula);

		lblMatricula = new JLabel("Matricula");
		lblMatricula.setBounds(10, 119, 84, 14);
		getContentPane().add(lblMatricula);
	}

	public JComboBox<EspecialidadDTO> getCmbEspecialidades() {
		return cmbEspecialidades;
	}

	public void setCmbEspecialidades(JComboBox<EspecialidadDTO> cmbEspecialidades) {
		this.cmbEspecialidades = cmbEspecialidades;
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public void setBtnAceptar(JButton btnAceptar) {
		this.btnAceptar = btnAceptar;
	}

	public JTextField getNombre() {
		return nombre;
	}

	public void setNombre(JTextField nombre) {
		this.nombre = nombre;
	}

	public JTextField getUsuario() {
		return usuario;
	}

	public void setUsuario(JTextField usuario) {
		this.usuario = usuario;
	}

	public JTextField getPass() {
		return pass;
	}

	public void setPass(JTextField pass) {
		this.pass = pass;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JTextField getMatricula() {
		return matricula;
	}

	public void setMatricula(JTextField matricula) {
		this.matricula = matricula;
	}

}
