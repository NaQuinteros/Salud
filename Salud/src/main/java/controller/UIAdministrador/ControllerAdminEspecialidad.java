package controller.UIAdministrador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dto.EspecialidadDTO;
import modelo.Admin_Especialidad;
import presentacion.vista.UIAdministrador;
import presentacion.vista.UICrearEspecialidad;

public class ControllerAdminEspecialidad {

	private UIAdministrador gui;
	private boolean edicion;
	private Admin_Especialidad admin;
	private UICrearEspecialidad crearEspecialidad;
	private EspecialidadDTO UsuarioQueSeEstaEditandoPorqueMeDabaPajaSolucionarloMejor;

	public ControllerAdminEspecialidad(UIAdministrador gui) {
		this.gui = gui;
		gui.setVisible(true);
		admin = new Admin_Especialidad();
		crearEspecialidad = new UICrearEspecialidad();
		inicializador();
	}

	private void inicializador() {
		crearEspecialidad();
		editarEspecialidad();
		eliminarEspecialidad();
		aceptar();
		llenarTabla();
	}

	private void aceptar() {
		crearEspecialidad.getBtnAceptar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				boolean f = false;

				for (EspecialidadDTO esp : admin.obtenerEspecialidades()) {
					if (esp.getNombreEspecialidad().toLowerCase()
							.equals(crearEspecialidad.getNombre().getText().toLowerCase())) {
						f = true;
						break;
					}
				}
				if (f && !edicion) {
					JOptionPane.showMessageDialog(null, "La especialidad ya existe");
				} else {
					if (camposIncompletos() || especialidadRepetida()) {
						JOptionPane.showMessageDialog(null, "Verifique los datos ingresados");
					} else {
						if (!edicion) {
							admin.agregarEspecialidad(new EspecialidadDTO(crearEspecialidad.getNombre().getText()));
							crearEspecialidad.dispose();
						} else {
							admin.editarEspecialidad(new EspecialidadDTO(
									UsuarioQueSeEstaEditandoPorqueMeDabaPajaSolucionarloMejor.getIdEspecialidad(),
									crearEspecialidad.getNombre().getText()));
							edicion = false;
							crearEspecialidad.dispose();
						}
					}
				}
				llenarTabla();

			}

		});
	}

	private void eliminarEspecialidad() {
		gui.getEliminarEspecialidad().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (gui.getTablaEspecialidad().getSelectedRow() != -1) {
					String e2 = (String) gui.getTablaEspecialidad()
							.getValueAt(gui.getTablaEspecialidad().getSelectedRow(), 0);
					for (EspecialidadDTO esp : admin.obtenerEspecialidades()) {
						if (esp.getNombreEspecialidad().equals(e2)) {
							if (!admin.borrarEspecialidad(esp)) {
								JOptionPane.showMessageDialog(null,
										"No se puede eliminar especialidades asignadas a médicos");
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

	private void editarEspecialidad() {
		gui.getEditarEspecialidad().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (gui.getTablaEspecialidad().getSelectedRow() != -1) {
					String nombre = (String) gui.getTablaEspecialidad()
							.getValueAt(gui.getTablaEspecialidad().getSelectedRow(), 0);
					for (EspecialidadDTO esp : admin.obtenerEspecialidades()) {
						if (esp.getNombreEspecialidad().equals(nombre)) {
							UsuarioQueSeEstaEditandoPorqueMeDabaPajaSolucionarloMejor = esp;
							crearEspecialidad.getNombre().setText(esp.getNombreEspecialidad());
						}
					}
					edicion = true;
					crearEspecialidad.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione una fila");
				}

			}

		});
	}

	private void llenarTabla() {
		gui.getModelEspecialidad().setRowCount(0);
		gui.getModelEspecialidad().setColumnCount(0);
		gui.getModelEspecialidad().setColumnIdentifiers(gui.getColumnasEspecialidad());
		for (EspecialidadDTO esp : admin.obtenerEspecialidades()) {
			Object[] fila = { esp.getNombreEspecialidad() };
			gui.getModelEspecialidad().addRow(fila);
		}

	}

	private void crearEspecialidad() {
		gui.getBtnCrearEspecialidad().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				crearEspecialidad.getNombre().setText("");
				crearEspecialidad.setVisible(true);
			}

		});
	}

	private boolean camposIncompletos() {
		if (crearEspecialidad.getNombre().getText().equals("") || crearEspecialidad.getNombre().getText().length() > 20) {
			return true;
		}
		return false;
	}

	private boolean especialidadRepetida() {
		for(EspecialidadDTO especialidad : this.admin.obtenerEspecialidades()) {
			if(especialidad.getNombreEspecialidad().toLowerCase().equals(crearEspecialidad.getNombre().getText().toLowerCase()) && 
					UsuarioQueSeEstaEditandoPorqueMeDabaPajaSolucionarloMejor.getIdEspecialidad() != especialidad.getIdEspecialidad()) {
				return true;
			}
		}
		return false;
	}
	
}
