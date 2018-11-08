package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UICrearCuenta extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField nombre;
	private JTextField usuario;
	private JTextField pass;
	private JButton btnAceptar;
	@SuppressWarnings("unused")
	private CreadorCuenta creadorCuenta;

	public UICrearCuenta(CreadorCuenta creadorCuenta) {
		getContentPane().setLayout(null);
		this.setResizable(false);
		this.setTitle("Crear cuenta");
		this.setSize(280, 200);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		nombre = new JTextField();
		nombre.setBounds(141, 23, 86, 20);
		getContentPane().add(nombre);
		nombre.setColumns(10);

		JLabel lblNombreDeUsuario = new JLabel("Nombre");
		lblNombreDeUsuario.setBounds(10, 23, 121, 14);
		getContentPane().add(lblNombreDeUsuario);

		usuario = new JTextField();
		usuario.setColumns(10);
		usuario.setBounds(141, 54, 86, 20);
		getContentPane().add(usuario);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 54, 121, 14);
		getContentPane().add(lblUsuario);

		pass = new JTextField();
		pass.setColumns(10);
		pass.setBounds(141, 85, 86, 20);
		getContentPane().add(pass);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(10, 85, 121, 14);
		getContentPane().add(lblContrasea);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(creadorCuenta.obtenerCuenta(getNombre().getText(), getUsuario().getText(), getPass().getText())) {
					dispose();
				}
			}
		});
		btnAceptar.setBounds(141, 126, 89, 23);
		getContentPane().add(btnAceptar);
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

}
