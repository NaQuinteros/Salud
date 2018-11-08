package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class UICrearHabitacion extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField identificacion;
	private JButton btnAceptar;
	private JTextField descripcion;
	
	public UICrearHabitacion() {
		getContentPane().setLayout(null);
		this.setResizable(false);
		this.setTitle("Crear habitación");
		this.setSize(260, 210);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		identificacion = new JTextField();
		identificacion.setBounds(20, 49, 214, 20);
		getContentPane().add(identificacion);
		identificacion.setColumns(10);

		JLabel lblNombreDeUsuario = new JLabel("Identificaci\u00F3n:");
		lblNombreDeUsuario.setBounds(10, 24, 89, 14);
		getContentPane().add(lblNombreDeUsuario);

		JLabel lblUsuario = new JLabel("Descripci\u00F3n:");
		lblUsuario.setBounds(10, 83, 89, 14);
		getContentPane().add(lblUsuario);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(145, 147, 89, 23);
		getContentPane().add(btnAceptar);
		
		descripcion = new JTextField();
		descripcion.setColumns(10);
		descripcion.setBounds(20, 108, 214, 20);
		getContentPane().add(descripcion);
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public void setBtnAceptar(JButton btnAceptar) {
		this.btnAceptar = btnAceptar;
	}

	public JTextField getNombre() {
		return identificacion;
	}

	public void setNombre(JTextField nombre) {
		this.identificacion = nombre;
	}

	public JTextField getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(JTextField identificacion) {
		this.identificacion = identificacion;
	}

	public JTextField getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(JTextField descripcion) {
		this.descripcion = descripcion;
	}

}
