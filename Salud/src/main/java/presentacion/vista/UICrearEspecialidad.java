package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class UICrearEspecialidad extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField nombre;
	private JButton btnAceptar;

	public UICrearEspecialidad() {
		getContentPane().setLayout(null);
		this.setResizable(false);
		this.setTitle("Crear especialidad");
		this.setSize(272, 142);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		nombre = new JTextField();
		nombre.setBounds(135, 23, 121, 20);
		getContentPane().add(nombre);
		nombre.setColumns(10);

		JLabel lblNombreDeUsuario = new JLabel("Especialidad: ");
		lblNombreDeUsuario.setBounds(10, 26, 84, 14);
		getContentPane().add(lblNombreDeUsuario);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(167, 79, 89, 23);
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
