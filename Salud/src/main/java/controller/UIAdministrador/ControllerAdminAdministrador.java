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

public class ControllerAdminAdministrador implements CreadorCuenta {

	private Administracion admin_medicoRecepcion;
	private UIAdministrador gui;
	private UICrearCuenta administracion;
	private AdministradorDTO UsuarioQueSeEstaEditandoPorqueMeDabaPajaSolucionarloMejor;
	private boolean edicion;

	public ControllerAdminAdministrador(UIAdministrador gui) {
		this.administracion = new UICrearCuenta(this);
		this.admin_medicoRecepcion = new Administracion();
		this.gui = gui;
		inicializador();
	}

	private void inicializador() {
		this.controllerCrearAdministrador();
		this.controllerEditarAdministrador();
		this.controllerEliminarAdministrador();
		llenarTabla();
	}

	private void llenarTabla() {
		gui.getModelAdmin().setRowCount(0);
		gui.getModelAdmin().setColumnCount(0);
		gui.getModelAdmin().setColumnIdentifiers(gui.getColumnasAdministrador());

		ArrayList<AdministradorDTO> cuentas = (ArrayList<AdministradorDTO>) admin_medicoRecepcion
				.obtenerAdministradores();
		for (AdministradorDTO cuenta : cuentas) {
			Object[] fila = { cuenta.getNombre(), cuenta.getUsuario(), cuenta.getPass() };
			gui.getModelAdmin().addRow(fila);
		}
	}

	private void controllerEditarAdministrador() {
		this.gui.getEditarCuentaAdmin().addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<AdministradorDTO> cuentas = (ArrayList<AdministradorDTO>) admin_medicoRecepcion
						.obtenerAdministradores();
				if (gui.getTablaAdmin().getSelectedRow() != -1) {
					String usuario = (String) gui.getTablaAdmin().getValueAt(gui.getTablaAdmin().getSelectedRow(), 1);
					for (AdministradorDTO cuenta : cuentas) {
						if (cuenta.getUsuario().equals(usuario)) {
							UsuarioQueSeEstaEditandoPorqueMeDabaPajaSolucionarloMejor = cuenta;
							administracion.getNombre().setText(cuenta.getNombre());
							administracion.getPass().setText(cuenta.getPass());
							administracion.getUsuario().setText(cuenta.getUsuario());
							administracion.getUsuario().disable();
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

	private void controllerEliminarAdministrador() {
		this.gui.getEliminarCuentaAdmin().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<AdministradorDTO> cuentas = (ArrayList<AdministradorDTO>) admin_medicoRecepcion
						.obtenerAdministradores();

				if (gui.getTablaAdmin().getSelectedRow() != -1) {
					String usuario = (String) gui.getTablaAdmin().getValueAt(gui.getTablaAdmin().getSelectedRow(), 1);
					for (AdministradorDTO cuenta : cuentas) {
						if (cuenta.getUsuario().equals(usuario)) {
							admin_medicoRecepcion.borrarAdministrador(cuenta);
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

	private void controllerCrearAdministrador() {
		this.gui.getCrearCuentaAdmin().addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				administracion.setVisible(true);
				administracion.getNombre().setText("");
				administracion.getPass().setText("");
				administracion.getUsuario().setText("");
				administracion.getUsuario().enable();
				llenarTabla();
			};
		});
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
			AdministradorDTO r = new AdministradorDTO(nombre, usuario, pass);
			admin_medicoRecepcion.crearAdministrador(r);
		} else {
			AdministradorDTO editado = new AdministradorDTO(
					UsuarioQueSeEstaEditandoPorqueMeDabaPajaSolucionarloMejor.getId(), nombre, usuario, pass);
			admin_medicoRecepcion.editarAdministrador(editado);
		}
		llenarTabla();
		edicion = false;
		return true;
	}

	private boolean camposIncompletos() {
		if (administracion.getNombre().getText().equals("") || administracion.getNombre().getText().length() > 20
				|| administracion.getUsuario().getText().equals("")
				|| administracion.getUsuario().getText().length() > 20 || administracion.getPass().getText().equals("")
				|| administracion.getPass().getText().length() > 20
				|| !administracion.getNombre().getText().matches("[a-zA-Z]+")) {
			return true;
		}
		return false;
	}

}
