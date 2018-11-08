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

public class ControllerAdminRecepcion implements CreadorCuenta {

	private Administracion admin_medicoRecepcion;
	private UIAdministrador gui;
	private UICrearCuenta recepcion;
	private RecepcionDTO UsuarioQueSeEstaEditandoPorqueMeDabaPajaSolucionarloMejor;
	private boolean edicion;

	public ControllerAdminRecepcion(UIAdministrador gui) {
		this.admin_medicoRecepcion = new Administracion();
		this.gui = gui;
		gui.setVisible(true);
		this.recepcion = new UICrearCuenta(this);
		inicializador();
	}

	private void inicializador() {
		this.controllerCrearRecepcion();
		this.controllerEditarRecepcion();
		this.controllerEliminarRecepcion();
		llenarTabla();
	}

	private void controllerCrearRecepcion() {
		this.gui.getBtnCrearNuevaCuentaRecepcion().addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				recepcion.setVisible(true);
				recepcion.getNombre().setText("");
				recepcion.getPass().setText("");
				recepcion.getUsuario().setText("");
				recepcion.getUsuario().enable();
				llenarTabla();
			}

		});
	}

	private void controllerEliminarRecepcion() {
		this.gui.getBtnEliminarRecepcion().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<RecepcionDTO> cuentas = (ArrayList<RecepcionDTO>) admin_medicoRecepcion.obtenerRecepcion();

				if (gui.getTableRecepcion().getSelectedRow() != -1) {
					String usuario = (String) gui.getTableRecepcion()
							.getValueAt(gui.getTableRecepcion().getSelectedRow(), 1);
					for (RecepcionDTO cuenta : cuentas) {
						if (cuenta.get_nombreUsuario().equals(usuario)) {
							admin_medicoRecepcion.borrarRecepcion(cuenta);
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

	private void controllerEditarRecepcion() {
		this.gui.getBtnEditarRecepcion().addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<RecepcionDTO> cuentas = (ArrayList<RecepcionDTO>) admin_medicoRecepcion.obtenerRecepcion();
				if (gui.getTableRecepcion().getSelectedRow() != -1) {
					String usuario = (String) gui.getTableRecepcion()
							.getValueAt(gui.getTableRecepcion().getSelectedRow(), 1);
					for (RecepcionDTO cuenta : cuentas) {
						if (cuenta.get_nombreUsuario().equals(usuario)) {
							UsuarioQueSeEstaEditandoPorqueMeDabaPajaSolucionarloMejor = cuenta;
							recepcion.getNombre().setText(cuenta.getNombre());
							recepcion.getPass().setText(cuenta.get_password());
							recepcion.getUsuario().setText(cuenta.get_nombreUsuario());
							recepcion.getUsuario().disable();
						}
					}
					edicion = true;
					recepcion.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione una fila");
				}
			}

		});
	}

	private void llenarTabla() {
		gui.getModelRecepcion().setRowCount(0);
		gui.getModelRecepcion().setColumnCount(0);
		gui.getModelRecepcion().setColumnIdentifiers(gui.getColumnasRecepcion());

		ArrayList<RecepcionDTO> cuentas = (ArrayList<RecepcionDTO>) admin_medicoRecepcion.obtenerRecepcion();
		for (RecepcionDTO cuenta : cuentas) {
			Object[] fila = { cuenta.getNombre(), cuenta.get_nombreUsuario(), cuenta.get_password() };
			gui.getModelRecepcion().addRow(fila);
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
			RecepcionDTO r = new RecepcionDTO(usuario, pass, nombre);
			admin_medicoRecepcion.crearRecepcion(r);
		} else {
			RecepcionDTO editado = new RecepcionDTO(
					UsuarioQueSeEstaEditandoPorqueMeDabaPajaSolucionarloMejor.get_idRecepcion(), usuario, pass, nombre);
			admin_medicoRecepcion.editarRecepcion(editado);
		}
		llenarTabla();
		edicion = false;
		return true;
	}

	private boolean camposIncompletos() {
		if (recepcion.getNombre().getText().equals("") || recepcion.getNombre().getText().length() > 20
				|| recepcion.getUsuario().getText().equals("") || recepcion.getUsuario().getText().length() > 20
				|| recepcion.getPass().getText().equals("") || recepcion.getPass().getText().length() > 20) {
			return true;
		}
		return false;
	}

}
