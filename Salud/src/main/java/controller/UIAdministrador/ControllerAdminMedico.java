package controller.UIAdministrador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import dto.AdministradorDTO;
import dto.ContableDTO;
import dto.EspecialidadDTO;
import dto.InternacionDTO;
import dto.MedicoDTO;
import dto.RecepcionDTO;
import modelo.Admin_Especialidad;
import modelo.Administracion;
import presentacion.vista.UIAdministrador;
import presentacion.vista.UICrearMedico;

public class ControllerAdminMedico {

	private Administracion admin_medicoRecepcion;
	private UIAdministrador gui;
	private MedicoDTO UsuarioQueSeEstaEditandoPorqueMeDabaPajaSolucionarloMejor;
	private boolean edicion;
	private UICrearMedico crearMedico;

	public ControllerAdminMedico(UIAdministrador gui) {
		this.admin_medicoRecepcion = new Administracion();
		this.gui = gui;
		gui.setVisible(true);
		admin_medicoRecepcion = new Administracion();
		crearMedico = new UICrearMedico();
		inicializador();
	}

	private void inicializador() {
		this.controllerCrearMedico();
		this.controllerEditarMedico();
		this.controllerEliminarMedico();
		this.aceptar();
		llenarCmb();
		llenarTabla();
	}

	private void llenarCmb() {
		Admin_Especialidad adm = new Admin_Especialidad();
		for (EspecialidadDTO especialidad : adm.obtenerEspecialidades()) {
			this.crearMedico.getCmbEspecialidades().addItem(especialidad);
		}
		this.crearMedico.getCmbEspecialidades().setSelectedItem(-1);
		AutoCompleteDecorator.decorate(this.crearMedico.getCmbEspecialidades());
	}

	private void controllerEliminarMedico() {
		gui.getEliminarMedico().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<MedicoDTO> cuentas = (ArrayList<MedicoDTO>) admin_medicoRecepcion.obtenerMedicos();

				if (gui.getTablaMedico().getSelectedRow() != -1) {
					String usuario = (String) gui.getTablaMedico().getValueAt(gui.getTablaMedico().getSelectedRow(), 1);
					for (MedicoDTO cuenta : cuentas) {
						if (cuenta.get_nombreUsuario().equals(usuario)) {
							if (!admin_medicoRecepcion.borrarMedico(cuenta)) {
								JOptionPane.showMessageDialog(null,
										"No se puede eliminar un médico con turnos generados");
							}
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

	private void controllerEditarMedico() {
		this.gui.getEditarMedico().addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<MedicoDTO> cuentas = (ArrayList<MedicoDTO>) admin_medicoRecepcion.obtenerMedicos();
				if (gui.getTablaMedico().getSelectedRow() != -1) {
					String usuario = (String) gui.getTablaMedico().getValueAt(gui.getTablaMedico().getSelectedRow(), 1);
					for (MedicoDTO cuenta : cuentas) {
						if (cuenta.get_nombreUsuario().equals(usuario)) {
							UsuarioQueSeEstaEditandoPorqueMeDabaPajaSolucionarloMejor = cuenta;
							crearMedico.getNombre().setText(cuenta.get_nombre());
							crearMedico.getPass().setText(cuenta.get_password());
							crearMedico.getUsuario().setText(cuenta.get_nombreUsuario());
							crearMedico.getMatricula().setText(cuenta.get_matricula());
							crearMedico.getUsuario().disable();
							crearMedico.getCmbEspecialidades().setSelectedItem(cuenta.get_especialidad());
						}
					}
					edicion = true;
					crearMedico.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione una fila");
				}
			}

		});

	}

	private void controllerCrearMedico() {
		gui.getCrearMedico().addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				crearMedico.setVisible(true);
				crearMedico.getNombre().setText("");
				crearMedico.getPass().setText("");
				crearMedico.getUsuario().setText("");
				crearMedico.getMatricula().setText("");
				crearMedico.getUsuario().enable();
				crearMedico.getCmbEspecialidades().setSelectedIndex(-1);
			}
		});

	}

	private void llenarTabla() {
		gui.getModelMedico().setRowCount(0);
		gui.getModelMedico().setColumnCount(0);
		gui.getModelMedico().setColumnIdentifiers(gui.getColumnasMedico());

		ArrayList<MedicoDTO> cuentas = (ArrayList<MedicoDTO>) admin_medicoRecepcion.obtenerMedicos();
		for (MedicoDTO cuenta : cuentas) {
			Object[] fila = { cuenta.get_nombre(), cuenta.get_nombreUsuario(), cuenta.get_password(),
					cuenta.get_especialidad().getNombreEspecialidad(), cuenta.get_matricula() };
			gui.getModelMedico().addRow(fila);
		}
	}

	public void aceptar() {

		this.crearMedico.getBtnAceptar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean f = false;
				boolean matriculaRepetida = false;
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
					if (crearMedico.getMatricula().getText().equals(cuenta.get_matricula()))
						matriculaRepetida = true;
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
				if (matriculaRepetida && !edicion) {
					JOptionPane.showMessageDialog(null, "La matricula ya existe");
					matriculaRepetida = false;
				}
				for (String user : usuarios) {
					if (user.toLowerCase().equals(crearMedico.getUsuario().getText().toLowerCase()) && !edicion) {
						JOptionPane.showMessageDialog(null, "El usuario ya existe");
						f = true;
					}
				}
				if (!edicion && !f) {
					if (camposIncompletos()) {
						JOptionPane.showMessageDialog(null, "Verifique los datos ingresados");
					} else {
						EspecialidadDTO especialidad = (EspecialidadDTO) crearMedico.getCmbEspecialidades()
								.getSelectedItem();
						MedicoDTO r = new MedicoDTO(crearMedico.getNombre().getText(),
								crearMedico.getUsuario().getText(), crearMedico.getPass().getText(), especialidad);
						r.set_matricula(crearMedico.getMatricula().getText());
						admin_medicoRecepcion.crearMedico(r);
						crearMedico.dispose();
					}
				} else if (!f) {
					if (camposIncompletos() || matriculaRepetida()) {
						JOptionPane.showMessageDialog(null, "Verifique los datos ingresados");
					} else {
						EspecialidadDTO especialidad = (EspecialidadDTO) crearMedico.getCmbEspecialidades()
								.getSelectedItem();
						MedicoDTO editado = new MedicoDTO(
								UsuarioQueSeEstaEditandoPorqueMeDabaPajaSolucionarloMejor.get_idMedico(),
								crearMedico.getNombre().getText(), crearMedico.getUsuario().getText(),
								crearMedico.getPass().getText(), especialidad, 0);
						editado.set_matricula(crearMedico.getMatricula().getText());
						admin_medicoRecepcion.editarMedico(editado);
						crearMedico.dispose();
						edicion = false;
					}
				}
				llenarTabla();
				matriculaRepetida = false;
			}
		});
	}

	private boolean matriculaRepetida() {
		for(MedicoDTO cuenta : this.admin_medicoRecepcion.obtenerMedicos()) {
			if(cuenta.get_matricula().toLowerCase().equals(crearMedico.getMatricula().getText().toLowerCase()) && 
					UsuarioQueSeEstaEditandoPorqueMeDabaPajaSolucionarloMejor.get_idMedico() != cuenta.get_idMedico()) {
				return true;
			}
		}
		return false;
	}

	private boolean camposIncompletos() {
		if (crearMedico.getNombre().getText().equals("") || crearMedico.getNombre().getText().length() > 20
				|| crearMedico.getMatricula().getText().equals("") || crearMedico.getMatricula().getText().length() > 20
				|| crearMedico.getPass().getText().equals("") || crearMedico.getPass().getText().length() > 20
				|| crearMedico.getCmbEspecialidades().getSelectedItem() == null
				) {
			return true;
		}
		return false;
	}
}
