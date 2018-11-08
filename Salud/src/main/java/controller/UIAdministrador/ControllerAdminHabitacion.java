package controller.UIAdministrador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dto.HabitacionDTO;
import modelo.Admin_Habitacion;
import presentacion.vista.UIAdministrador;
import presentacion.vista.UICrearHabitacion;

public class ControllerAdminHabitacion {

	private UIAdministrador gui;
	private boolean edicion;
	private Admin_Habitacion admin;
	private UICrearHabitacion crearHabitacion;
	private HabitacionDTO UsuarioQueSeEstaEditandoPorqueMeDabaPajaSolucionarloMejor;

	public ControllerAdminHabitacion(UIAdministrador gui) {
		this.admin = new Admin_Habitacion();
		this.gui = gui;
		gui.setVisible(true);
		crearHabitacion = new UICrearHabitacion();
		inicializador();
	}

	private void inicializador() {
		this.controllerCrearHabitacion();
		this.controllerEditarHabitacion();
		this.controllerEliminarHabitacion();
		controllerAceptar();
		llenarTabla();
	}

	private void controllerAceptar() {
		crearHabitacion.getBtnAceptar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean f = false;

				for (HabitacionDTO habitacion : admin.obtenerHabitaciones()) {
					if (habitacion.getNombre().toLowerCase()
							.equals(crearHabitacion.getIdentificacion().getText().toLowerCase())) {
						f = true;
						break;
					}
				}
				if (f && !edicion) {
					JOptionPane.showMessageDialog(null, "La habitación ya existe");
				} else {
					if (!edicion) {
						if (camposIncompletos()) {
							JOptionPane.showMessageDialog(null, "Verifique los datos ingresados");
						} else {
							admin.agregarHabitacion(new HabitacionDTO(crearHabitacion.getIdentificacion().getText(),
									crearHabitacion.getDescripcion().getText()));
							crearHabitacion.dispose();
						}
					} else {
						if (camposIncompletos() || habitacionRepetida() ) {
							JOptionPane.showMessageDialog(null, "Verifique los datos ingresados");
						} else {
							admin.editarHabitacion(new HabitacionDTO(
									UsuarioQueSeEstaEditandoPorqueMeDabaPajaSolucionarloMejor.getId(),
									crearHabitacion.getNombre().getText(), crearHabitacion.getDescripcion().getText()));
							edicion = false;
							crearHabitacion.dispose();
						}
					}
				}
				llenarTabla();
			}
		});
	}

	@SuppressWarnings("unused")
	private boolean noSeRepite(String n) {
		for (HabitacionDTO h : admin.obtenerHabitaciones()) {
			if (n.equals(h.getNombre())
					&& !h.getNombre().equals(UsuarioQueSeEstaEditandoPorqueMeDabaPajaSolucionarloMejor.getNombre())) {
				return false;
			}
		}
		return true;
	}

	private void llenarTabla() {
		gui.getModelHabitaciones().setRowCount(0);
		gui.getModelHabitaciones().setColumnCount(0);
		gui.getModelHabitaciones().setColumnIdentifiers(gui.getColumnasHabitaciones());
		ArrayList<HabitacionDTO> cuentas = (ArrayList<HabitacionDTO>) admin.obtenerHabitaciones();
		for (HabitacionDTO cuenta : cuentas) {
			Object[] fila = { cuenta.getNombre(), cuenta.getDesc() };
			gui.getModelHabitaciones().addRow(fila);
		}
	}

	private void controllerEditarHabitacion() {
		gui.getEditarHabitacion().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (gui.getTablaHabitaciones().getSelectedRow() != -1) {
					String nombre = (String) gui.getTablaHabitaciones()
							.getValueAt(gui.getTablaHabitaciones().getSelectedRow(), 0);
					for (HabitacionDTO habitacion : admin.obtenerHabitaciones()) {
						if (habitacion.getNombre().equals(nombre)) {
							UsuarioQueSeEstaEditandoPorqueMeDabaPajaSolucionarloMejor = habitacion;
							crearHabitacion.getNombre().setText(habitacion.getNombre());
							crearHabitacion.getDescripcion().setText(habitacion.getDesc());
						}
					}
					edicion = true;
					crearHabitacion.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione una fila");
				}
			}

		});
	}

	private void controllerEliminarHabitacion() {
		gui.getEliminarHabitacion().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (gui.getTablaHabitaciones().getSelectedRow() != -1) {
					String hab = (String) gui.getTablaHabitaciones()
							.getValueAt(gui.getTablaHabitaciones().getSelectedRow(), 0);
					for (HabitacionDTO habitacion : admin.obtenerHabitaciones()) {
						if (habitacion.getNombre().equals(hab)) {
							if (!admin.eliminarHabitacion(habitacion)) {
								JOptionPane.showMessageDialog(null, "No se puede eliminar una habitación asignada");
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

	private void controllerCrearHabitacion() {
		gui.getBtnCrearHabitacin().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				crearHabitacion.getNombre().setText("");
				crearHabitacion.getDescripcion().setText("");
				crearHabitacion.setVisible(true);
			}

		});
	}

	private boolean habitacionRepetida() {
		for(HabitacionDTO habitacion : this.admin.obtenerHabitaciones()) {
			if(habitacion.getNombre().toLowerCase().equals(crearHabitacion.getNombre().getText().toLowerCase()) && 
					UsuarioQueSeEstaEditandoPorqueMeDabaPajaSolucionarloMejor.getId() != habitacion.getId()) {
				return true;
			}
		}
		return false;
	}
	
	private boolean camposIncompletos() {
		if (crearHabitacion.getNombre().getText().equals("") || crearHabitacion.getNombre().getText().length() > 20
				|| crearHabitacion.getDescripcion().getText().length() > 20) {
			return true;
		}
		return false;
	}
}
