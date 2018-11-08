package controller.UIAdministrador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dto.AdministradorDTO;
import dto.ContableDTO;
import dto.InternacionDTO;
import dto.MedicoDTO;
import dto.RecepcionDTO;
import modelo.Administracion;
import presentacion.vista.CreadorCuenta;
import presentacion.vista.UIAdministrador;
import presentacion.vista.UICrearCuenta;

public class ControllerAdminInternacion implements CreadorCuenta {

	private Administracion admin_medicoRecepcion;
	private UIAdministrador gui;
	private UICrearCuenta administracion;
	private InternacionDTO UsuarioQueSeEstaEditandoPorqueMeDabaPajaSolucionarloMejor;
	private boolean edicion;

	public ControllerAdminInternacion(UIAdministrador gui) {
		this.admin_medicoRecepcion = new Administracion();
		this.gui = gui;
		gui.setVisible(true);
		this.administracion = new UICrearCuenta(this);
		inicializador();
	}

	private void inicializador() {
		this.controllerCrearInternador();
		this.controllerEditarInternador();
		this.controllerEliminarInternador();
		llenarTabla();
	}

	private void controllerEliminarInternador() {
		gui.getEliminarInternacion().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<InternacionDTO> cuentas = (ArrayList<InternacionDTO>) admin_medicoRecepcion
						.obtenerPersonalInternacion();

				if (gui.getTablaInternacion().getSelectedRow() != -1) {
					String usuario = (String) gui.getTablaInternacion()
							.getValueAt(gui.getTablaInternacion().getSelectedRow(), 1);
					for (InternacionDTO cuenta : cuentas) {
						if (cuenta.get_nombreUsuario().equals(usuario)) {
							admin_medicoRecepcion.borrarPersonalInternacion(cuenta);
							break;
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione una fila");
				}
				llenarTabla();
			}

		});

	}

	private void controllerEditarInternador() {
		this.gui.getEditarInternacion().addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<InternacionDTO> cuentas = (ArrayList<InternacionDTO>) admin_medicoRecepcion
						.obtenerPersonalInternacion();
				if (gui.getTablaInternacion().getSelectedRow() != -1) {
					String usuario = (String) gui.getTablaInternacion()
							.getValueAt(gui.getTablaInternacion().getSelectedRow(), 1);
					for (InternacionDTO cuenta : cuentas) {
						if (cuenta.get_nombreUsuario().equals(usuario)) {
							UsuarioQueSeEstaEditandoPorqueMeDabaPajaSolucionarloMejor = cuenta;
							administracion.getNombre().setText(cuenta.get_nombre());
							administracion.getPass().setText(cuenta.get_password());
							administracion.getUsuario().disable();
							administracion.getUsuario().setText(cuenta.get_nombreUsuario());
						}
					}
					edicion = true;
					administracion.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione una fila");
				}
			}

		});

	}

	private void controllerCrearInternador() {
		gui.getCrearInternacion().addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				administracion.setVisible(true);
				administracion.getNombre().setText("");
				administracion.getPass().setText("");
				administracion.getUsuario().setText("");
				administracion.getUsuario().enable();
			}
		});

	}

	private void llenarTabla() {
		gui.getModelInternacion().setRowCount(0);
		gui.getModelInternacion().setColumnCount(0);
		gui.getModelInternacion().setColumnIdentifiers(gui.getColumnasInternacion());

		ArrayList<InternacionDTO> cuentas = (ArrayList<InternacionDTO>) admin_medicoRecepcion
				.obtenerPersonalInternacion();
		for (InternacionDTO cuenta : cuentas) {
			Object[] fila = { cuenta.get_nombre(), cuenta.get_nombreUsuario(), cuenta.get_password() };
			gui.getModelInternacion().addRow(fila);
		}
	}

	@Override
	public boolean obtenerCuenta(String nombre, String usuario, String pass) {
		if (camposIncompletos()) {
			JOptionPane.showMessageDialog(null, "Verifique los datos ingresados");
			return false;
		}
		ArrayList<String> usuarios = new ArrayList<>();

		// Administradores
		for (AdministradorDTO cuenta : admin_medicoRecepcion.obtenerAdministradores()) {
			usuarios.add(cuenta.getUsuario());
		}
		// Contable
		for (ContableDTO cuenta : admin_medicoRecepcion.obtenerContables()) {
			usuarios.add(cuenta.get_nombreUsuario());
		}
		// Medico
		for (MedicoDTO cuenta : admin_medicoRecepcion.obtenerMedicos()) {
			usuarios.add(cuenta.get_nombreUsuario());
		}
		// Recepcion
		for (RecepcionDTO cuenta : admin_medicoRecepcion.obtenerRecepcion()) {
			usuarios.add(cuenta.get_nombreUsuario());
		}
		// Internacion
		for (InternacionDTO cuenta : admin_medicoRecepcion.obtenerPersonalInternacion()) {
			usuarios.add(cuenta.get_nombreUsuario());
		}
		// Verificación
		for (String user : usuarios) {
			if (user.toLowerCase().equals(usuario.toLowerCase()) && !edicion) {
				JOptionPane.showMessageDialog(null, "El usuario ya existe");
				return false;
			}
		}
		if (!edicion) {
			InternacionDTO r = new InternacionDTO(usuario, pass, nombre);
			admin_medicoRecepcion.crearPersonalInternacion(r);
		} else {
			InternacionDTO editado = new InternacionDTO(
					UsuarioQueSeEstaEditandoPorqueMeDabaPajaSolucionarloMejor.get_idInternacion(), usuario, pass,
					nombre);
			admin_medicoRecepcion.editarPersonalInternacion(editado);
		}
		llenarTabla();
		edicion = false;
		return true;
	}

	private boolean camposIncompletos() {
		if (administracion.getNombre().getText().equals("") || administracion.getNombre().getText().length() > 20
				|| administracion.getUsuario().getText().equals("")
				|| administracion.getUsuario().getText().length() > 20 || administracion.getPass().getText().equals("")
				|| administracion.getPass().getText().length() > 20) {
			return true;
		}
		return false;
	}

}
