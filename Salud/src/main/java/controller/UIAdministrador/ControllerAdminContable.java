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

public class ControllerAdminContable implements CreadorCuenta {

	private Administracion admin;
	private UIAdministrador gui;
	private UICrearCuenta administracion;
	private ContableDTO UsuarioQueSeEstaEditandoPorqueMeDabaPajaSolucionarloMejor;
	private boolean edicion;

	public ControllerAdminContable(UIAdministrador gui) {
		this.administracion = new UICrearCuenta(this);
		this.admin = new Administracion();
		this.gui = gui;
		inicializador();
	}

	private void inicializador() {
		controllerEliminarContable();
		controllerCrearContable();
		controllerEditarContable();
		llenarTabla();
	}

	private void controllerEliminarContable() {
		this.gui.getEliminarContable().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<ContableDTO> cuentas = (ArrayList<ContableDTO>) admin.obtenerContables();

				if (gui.getTablaContable().getSelectedRow() != -1) {
					String usuario = (String) gui.getTablaContable().getValueAt(gui.getTablaContable().getSelectedRow(),
							1);
					for (ContableDTO cuenta : cuentas) {
						if (cuenta.get_nombreUsuario().equals(usuario)) {
							admin.borrarContable(cuenta);
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

	private void controllerCrearContable() {
		this.gui.getCrearContable().addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				administracion.setVisible(true);
				administracion.getNombre().setText("");
				administracion.getPass().setText("");
				administracion.getUsuario().setText("");
				administracion.getUsuario().enable();
			};
		});
	}

	private void llenarTabla() {
		gui.getModelContable().setRowCount(0);
		gui.getModelContable().setColumnCount(0);
		gui.getModelContable().setColumnIdentifiers(gui.getColumnasContable());

		ArrayList<ContableDTO> cuentas = (ArrayList<ContableDTO>) admin.obtenerContables();
		for (ContableDTO cuenta : cuentas) {
			Object[] fila = { cuenta.get_nombre(), cuenta.get_nombreUsuario(), cuenta.get_password() };
			gui.getModelContable().addRow(fila);
		}
	}

	private void controllerEditarContable() {
		this.gui.getEditarContable().addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<ContableDTO> cuentas = (ArrayList<ContableDTO>) admin.obtenerContables();
				if (gui.getTablaContable().getSelectedRow() != -1) {
					String usuario = (String) gui.getTablaContable().getValueAt(gui.getTablaContable().getSelectedRow(),
							1);
					for (ContableDTO cuenta : cuentas) {
						if (cuenta.get_nombreUsuario().equals(usuario)) {
							UsuarioQueSeEstaEditandoPorqueMeDabaPajaSolucionarloMejor = cuenta;
							administracion.getNombre().setText(cuenta.get_nombre());
							administracion.getPass().setText(cuenta.get_password());
							administracion.getUsuario().setText(cuenta.get_nombreUsuario());
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

	@Override
	public boolean obtenerCuenta(String nombre, String usuario, String pass) {
		if (camposIncompletos()) {
			JOptionPane.showMessageDialog(null, "Verifique los datos ingresados");
			return false;
		}
		ArrayList<String> usuarios = new ArrayList<>();

		// Administradores
		for (AdministradorDTO cuenta : admin.obtenerAdministradores()) {
			usuarios.add(cuenta.getUsuario());
		}
		// Contable
		for (ContableDTO cuenta : admin.obtenerContables()) {
			usuarios.add(cuenta.get_nombreUsuario());
		}
		// Medico
		for (MedicoDTO cuenta : admin.obtenerMedicos()) {
			usuarios.add(cuenta.get_nombreUsuario());
		}
		// Recepcion
		for (RecepcionDTO cuenta : admin.obtenerRecepcion()) {
			usuarios.add(cuenta.get_nombreUsuario());
		}
		// Internacion
		for (InternacionDTO cuenta : admin.obtenerPersonalInternacion()) {
			usuarios.add(cuenta.get_nombreUsuario());
		}
		// Verificación
		for (String user : usuarios) {
			if (user.toLowerCase().equals(usuario.toLowerCase())&& !edicion) {
				JOptionPane.showMessageDialog(null, "El usuario ya existe");
				return false;
			}
		}
		if (!edicion) {
			ContableDTO r = new ContableDTO(nombre, usuario, pass);
			admin.crearContable(r);
		} else {
			ContableDTO editado = new ContableDTO(
					UsuarioQueSeEstaEditandoPorqueMeDabaPajaSolucionarloMejor.get_idContable(), nombre, usuario, pass);
			admin.editarContable(editado);
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
