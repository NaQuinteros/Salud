package controller.UIInternacion;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import controller.UIPracticas.ControladorPracticasInternacion;
import dto.HabitacionDTO;
import dto.InternacionesDTO;
import dto.PacienteDTO;
import modelo.Admin_Habitacion;
import modelo.Admin_Internaciones;
import modelo.Admin_Paciente;
import presentacion.vista.UIBuscadorPacientes;
import presentacion.vista.UIHabitaciones;
import presentacion.vista.UIInternacionAgregar;
import presentacion.vista.UIInternacionPrincipal;
import presentacion.vista.UIPracticasInternacion;

public class ControladorInternacionPrincipal {

	private UIInternacionAgregar uiAgregar;
	private UIPracticasInternacion uiPracticas;
	private UIHabitaciones uiHabitacion;
	private UIInternacionPrincipal uiPrincipal;
	private UIBuscadorPacientes uiBuscador;

	private PacienteDTO PacienteSel;
	private InternacionesDTO internacionSel;
	private int habitacionSel;

	private boolean editando;
	private boolean ingresandoHabReal;

	private Admin_Habitacion habit = new Admin_Habitacion();
	private Admin_Paciente adminPaciente = new Admin_Paciente();
	private Admin_Internaciones adminReserva = new Admin_Internaciones();

	@SuppressWarnings("unused")
	private ControladorPracticasInternacion controladorPracticas;

	List<HabitacionDTO> listaHab;
	List<InternacionesDTO> listaInternaciones = new ArrayList<InternacionesDTO>();

	public ControladorInternacionPrincipal() {
		listaInternaciones = adminReserva.obtenerInternaciones();
		listaHab = habit.obtenerHabitaciones();
		uiAgregar = new UIInternacionAgregar();
		uiPrincipal = new UIInternacionPrincipal();
		uiBuscador = new UIBuscadorPacientes();
		uiHabitacion = new UIHabitaciones();
		uiPracticas = new UIPracticasInternacion();
		editando = false;
		inicializar();
	}

	public void inicializar() {
		llenarTablaInternaciones(listaInternaciones);
		btnMostrarTodas();
		btnBuscarPaciente();
		btnAgendarInternacion();
		btnEditarInternacion();
		btnGuardarInternacion();
		btnConsultar();
		btnElegir();
		btnElegirReal();
		btnReservar();
		btnPracticas();
		btnSinHabitacion();
		this.uiPrincipal.setVisible(true);
	}

	private void btnSinHabitacion() {
		uiPrincipal.getBtnMostrarSinHabitacion().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				llenarTablaInternaciones(listaSinHabitacion());
			}

		});

	}

	private List<InternacionesDTO> listaSinHabitacion() {
		List<InternacionesDTO> sinHab = new ArrayList<InternacionesDTO>();
		for (InternacionesDTO aux : listaInternaciones) {
			if (aux.getEstado().equals("Sin Habitación"))
				sinHab.add(aux);
		}
		return sinHab;
	}

	@SuppressWarnings("deprecation")
	private void llenarTablaInternaciones(List<InternacionesDTO> lista) {
		uiPrincipal.getModelInternacion().setRowCount(0);
		uiPrincipal.getModelInternacion().setColumnCount(0);
		uiPrincipal.getModelInternacion().setColumnIdentifiers(uiPrincipal.getColumnasInternacion());
		if (lista.size() > 0) {
			for (int i = 0; i < lista.size(); i++) {
				InternacionesDTO internacionAux = lista.get(i);
				String ingresoReal = "";
				String egresoReal = "";
				String ingresoProgramado = "";
				String egresoProgramado = "";
				if (!(internacionAux.getIngresoReal() == null)) {
					ingresoReal = internacionAux.getIngresoReal().getDate() + "/"
							+ (internacionAux.getIngresoReal().getMonth() + 1) + "/"
							+ (internacionAux.getIngresoReal().getYear() + 1900);
				}
				if (!(internacionAux.getEgresoReal() == null)) {
					egresoReal = internacionAux.getEgresoReal().getDate() + "/"
							+ (internacionAux.getEgresoReal().getMonth() + 1) + "/"
							+ (internacionAux.getEgresoReal().getYear() + 1900);
				}
				if (!(internacionAux.getInicio() == null)) {
					ingresoProgramado = internacionAux.getInicio().getDate() + "/"
							+ (internacionAux.getInicio().getMonth() + 1) + "/"
							+ (internacionAux.getInicio().getYear() + 1900);
				}
				if (!(internacionAux.getFin() == null)) {
					egresoProgramado = internacionAux.getFin().getDate() + "/"
							+ (internacionAux.getFin().getMonth() + 1) + "/"
							+ (internacionAux.getFin().getYear() + 1900);
				}
				Object[] fila = { internacionAux.getId(), internacionAux.getEstado(),
						adminPaciente.obtenerPacienteById(internacionAux.getIdPaciente()).getNombre() + " "
								+ adminPaciente.obtenerPacienteById(internacionAux.getIdPaciente()).getApellido(),
						habit.idHabToString(internacionAux.getIdHabitacion()), ingresoProgramado, egresoProgramado,
						ingresoReal, egresoReal, adminReserva.motivoToString(internacionAux.getMotivoEgreso()),
						internacionAux.getDiagnosticoInicial(), internacionAux.getObservacionInicial() };
				uiPrincipal.getModelInternacion().addRow(fila);

			}
		}
	}

	private void btnBuscarPaciente() {
		this.uiPrincipal.getBtnConsultar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiBuscador.setVisible(true);
			}
		});
	}

	private void btnConsultar() {
		this.uiBuscador.getButtonBuscar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				PacienteSel = null;
				uiPrincipal.getTxtApellido().setText("");
				;
				uiPrincipal.getTxtDNI().setText("");
				uiPrincipal.getTxtNombre().setText("");
				uiPrincipal.getLblOS().setText("");
				String DNI_BUSCADO = uiBuscador.getTextDni().getText();
				if (DNI_BUSCADO.length() == 0) {
					JOptionPane.showMessageDialog(uiBuscador, "No hay ningun dni ingresado para la busqueda");
				} else {
					List<PacienteDTO> lst = adminPaciente.obtenerPacientes();
					for (PacienteDTO aux : lst) {
						if (String.valueOf(aux.getDni()).equals(DNI_BUSCADO)) {
							PacienteSel = aux;
							break;
						}
					}
				}
				if (PacienteSel != null) {

					uiPrincipal.getTxtNombre().setText(PacienteSel.getNombre());
					uiPrincipal.getTxtApellido().setText(PacienteSel.getApellido());
					uiPrincipal.getTxtDNI().setText(String.valueOf(PacienteSel.getDni()));
					if (PacienteSel.getObraSocial() != null)
						uiPrincipal.getLblOS().setText(PacienteSel.getObraSocial().getNombreObraSocial());
					uiBuscador.visible(false);
					uiBuscador.getTextDni().setText("");
					llenarTablaInternaciones(adminReserva.obtenerInternacionesDePaciente(PacienteSel.getIdPaciente()));
				} else {
					JOptionPane.showMessageDialog((Component) uiBuscador,
							"No se ha encontrado con ningún paciente con el dni ingresado");
				}
			}
		});
	}

	private void btnMostrarTodas() {
		uiPrincipal.getBtnMostrarTodas().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent Arg0) {
				llenarTablaInternaciones(adminReserva.obtenerInternaciones());
			}
		});
	}

	private void btnAgendarInternacion() {
		this.uiPrincipal.getBtnCrear().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent Arg0) {
				editando = false;
				uiAgregar.getBtnPracticas().setVisible(false);
				if (PacienteSel != null) {
					internacionSel = null;
					blanquearVentanaAgregar();
					uiAgregar.setVisible(true);
				} else
					JOptionPane.showMessageDialog(uiPrincipal,
							"Debe seleccionar un paciente para Agendar una internación.");
			}
		});

	}

	protected void blanquearVentanaAgregar() {
		uiAgregar.getDateIngresoProgramado().getDateEditor().setDate(null);
		uiAgregar.getDateEgresoProgramado().getDateEditor().setDate(null);
		uiAgregar.getIngresoReal().getDateEditor().setDate(null);
		uiAgregar.getEgresoReal().getDateEditor().setDate(null);
		uiAgregar.getTxtDiagnosticoInicial().setText("");
		uiAgregar.getTxtObservacionInicial().setText("");
		uiAgregar.getTxtDiagnosticoFinal().setText("");
		uiAgregar.getTxtObservacionFinal().setText("");
		uiAgregar.getLblNombreHab().setText("");
		uiAgregar.getLblHabitacionReal().setText("");
		uiAgregar.getCbMotivo().setSelectedIndex(0);
		uiAgregar.getLblHabitacionReal().setText("");

	}

	private void btnEditarInternacion() {
		this.uiPrincipal.getBtnEditar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent Arg0) {
				uiAgregar.getBtnPracticas().setVisible(true);
				editando = true;
				blanquearVentanaAgregar();
				if (uiPrincipal.getTableInternacion().getSelectedColumnCount() > 0
						&& uiPrincipal.getTableInternacion().getSelectedRowCount() > 0) {

					uiAgregar.setVisible(true);
					int idInt = (int) uiPrincipal.getTableInternacion()
							.getValueAt(uiPrincipal.getTableInternacion().getSelectedRow(), 0);
					internacionSel = adminReserva.internacionById(idInt);
					habitacionSel = internacionSel.getIdHabitacion();

					uiAgregar.getLblNombrePaciente().setText((String) uiPrincipal.getTableInternacion()
							.getValueAt(uiPrincipal.getTableInternacion().getSelectedRow(), 2));
					uiAgregar.getDateIngresoProgramado().getDateEditor().setDate(internacionSel.getInicio());
					uiAgregar.getDateEgresoProgramado().getDateEditor().setDate(internacionSel.getFin());
					uiAgregar.getIngresoReal().getDateEditor().setDate(internacionSel.getIngresoReal());
					uiAgregar.getEgresoReal().getDateEditor().setDate(internacionSel.getEgresoReal());
					uiAgregar.getTxtDiagnosticoInicial().setText(internacionSel.getDiagnosticoInicial());
					uiAgregar.getTxtObservacionInicial().setText(internacionSel.getObservacionInicial());
					uiAgregar.getTxtDiagnosticoFinal().setText(internacionSel.getDiagnosticoFinal());
					uiAgregar.getTxtObservacionFinal().setText(internacionSel.getObservacionFinal());
					if(internacionSel.getInicio()!=null && internacionSel.getIngresoReal()==null)
					uiAgregar.getLblNombreHab().setText(habit.idHabToString(internacionSel.getIdHabitacion()));
					else uiAgregar.getLblHabitacionReal().setText(habit.idHabToString(internacionSel.getIdHabitacion()));
					uiAgregar.getCbMotivo().setSelectedIndex(internacionSel.getMotivoEgreso());
				} else {
					JOptionPane.showMessageDialog((Component) uiPrincipal,
							"Debe Seleccionar una internacion de la lista para poder editarla");
				}
			}
		});

	}

	private void btnGuardarInternacion() {
		this.uiAgregar.getBtnGuardar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent Arg0) {

				if (uiAgregar.getDateIngresoProgramado().getDate() != null
						|| uiAgregar.getDateEgresoProgramado().getDate() != null
						|| uiAgregar.getLblNombreHab().getText() != "") {
					if (uiAgregar.getDateIngresoProgramado().getDate() == null
							|| uiAgregar.getDateEgresoProgramado().getDate() == null) {
						JOptionPane.showMessageDialog(uiAgregar, "Ingrese fechas tentativas de ingreso/egreso");
						return;
					}

					if (!uiAgregar.getDateIngresoProgramado().getDate()
							.before(uiAgregar.getDateEgresoProgramado().getDate())) {
						JOptionPane.showMessageDialog(uiAgregar,
								"Ingrese un rango válido de fechas tentativas de ingreso/egreso.");
						return;
					}
					if (uiAgregar.getLblNombreHab().getText() == "" && uiAgregar.getLblHabitacionReal().getText()=="") {
						JOptionPane.showMessageDialog(uiAgregar,
								"Seleccione una habitacion pulsando el boton 'Elegir'. Si no hay habitaciones para seleccionar elija otro rango de fechas.");
						return;
					}
				}

				if (uiAgregar.getCbMotivo().getSelectedIndex() == 0
						&& !(uiAgregar.getEgresoReal().getDateEditor().getDate() == null)) {
					JOptionPane.showMessageDialog(uiAgregar, "Ingrese un motivo de egreso");
					return;
				}
				if (uiAgregar.getCbMotivo().getSelectedIndex() != 0
						&& uiAgregar.getEgresoReal().getDateEditor().getDate() == null) {
					JOptionPane.showMessageDialog(uiAgregar, "Ingrese una fecha de egreso");
					return;
				}
				if (uiAgregar.getEgresoReal().getDate() != null) {
					if (!(uiAgregar.getIngresoReal().getDate().before(uiAgregar.getEgresoReal().getDate()))) {
						JOptionPane.showMessageDialog(uiAgregar,
								"Ingrese un rango válido de fechas reales de ingreso/egreso.");
						return;
					}

				}
				String Estado = "Programado";
				int idPaciente;
				if (editando)
					idPaciente = internacionSel.getIdPaciente();
				else
					idPaciente = PacienteSel.getIdPaciente();

				if (uiAgregar.getIngresoReal().getDateEditor().getDate() != null)
					Estado = "Activo";

				if (uiAgregar.getCbMotivo().getSelectedIndex() != 0
						&& !(uiAgregar.getEgresoReal().getDateEditor().getDate() == null))
					Estado = "Finalizado";

				if (uiAgregar.getLblHabitacionReal().getText() != "")
					DesasignarHabitaciones(habitacionSel, toTimestamp(uiAgregar.getIngresoReal()));

				InternacionesDTO internacion;
				if (uiAgregar.getCbMotivo().getSelectedIndex() == 0 && uiAgregar.getEgresoReal().getDate() == null) {

					internacion = new InternacionesDTO(habitacionSel, idPaciente,
							toTimestamp(uiAgregar.getDateIngresoProgramado()),
							toTimestamp(uiAgregar.getDateEgresoProgramado()), toTimestamp(uiAgregar.getIngresoReal()),
							toTimestamp(uiAgregar.getEgresoReal()), uiAgregar.getTxtDiagnosticoInicial().getText(),
							uiAgregar.getTxtObservacionInicial().getText(), uiAgregar.getCbMotivo().getSelectedIndex(),
							uiAgregar.getTxtDiagnosticoFinal().getText(), uiAgregar.getTxtObservacionFinal().getText(),
							Estado);
					if (uiAgregar.getIngresoReal().getDate() == null)
						internacion.setIngresoReal(null);
					if (uiAgregar.getEgresoReal().getDate() == null)
						internacion.setEgresoReal(null);
					if (uiAgregar.getDateIngresoProgramado().getDate() == null)
						internacion.setInicio(null);
					if (uiAgregar.getDateEgresoProgramado().getDate() == null)
						internacion.setFin(null);
				} else {

					internacion = new InternacionesDTO(habitacionSel, idPaciente,
							toTimestamp(uiAgregar.getDateIngresoProgramado()),
							toTimestamp(uiAgregar.getDateEgresoProgramado()), toTimestamp(uiAgregar.getIngresoReal()),
							toTimestamp(uiAgregar.getEgresoReal()), uiAgregar.getTxtDiagnosticoInicial().getText(),
							uiAgregar.getTxtObservacionInicial().getText(), uiAgregar.getCbMotivo().getSelectedIndex(),
							uiAgregar.getTxtDiagnosticoFinal().getText(), uiAgregar.getTxtObservacionFinal().getText(),
							Estado);
					if (uiAgregar.getDateIngresoProgramado().getDate() == null)
						internacion.setInicio(null);
					if (uiAgregar.getDateEgresoProgramado().getDate() == null)
						internacion.setFin(null);

				}

				if (editando) {
					internacion.setId((int) uiPrincipal.getTableInternacion()
							.getValueAt(uiPrincipal.getTableInternacion().getSelectedRow(), 0));
//					adminReserva.eliminarInternacion(internacionSel);
//					adminReserva.agregarInternacion(internacion);
					adminReserva.updateInternacion(internacion);
				} else {
					adminReserva.agregarInternacion(internacion);
				}

				if (editando) {
					JOptionPane.showMessageDialog(uiAgregar, "Internación Editada");
					uiPrincipal.getModelInternacion().setRowCount(0);
					uiPrincipal.getModelInternacion().setColumnCount(0);
					uiPrincipal.getModelInternacion().setColumnIdentifiers(uiPrincipal.getColumnasInternacion());
					llenarTablaInternaciones(adminReserva.obtenerInternaciones());
				} else {
					JOptionPane.showMessageDialog(uiAgregar, "Internacion Agendada");
					uiPrincipal.getModelInternacion().setRowCount(0);
					uiPrincipal.getModelInternacion().setColumnCount(0);
					uiPrincipal.getModelInternacion().setColumnIdentifiers(uiPrincipal.getColumnasInternacion());
					llenarTablaInternaciones(adminReserva.obtenerInternacionesDePaciente(PacienteSel.getIdPaciente()));
				}
				llenarTablaInternaciones(adminReserva.obtenerInternacionesDePaciente(idPaciente));
				uiAgregar.dispose();

			}
		});
	}

	protected void DesasignarHabitaciones(int idHab, Timestamp ingreso) {
		List<InternacionesDTO> internaciones = adminReserva.obtenerInternaciones();
		for (InternacionesDTO aux : internaciones) {
			if ( aux.getEstado().equals("Programado") && 
					aux.getIdHabitacion()== idHab && 
					aux.getInicio().before(ingreso) &&
					aux.getFin().after(ingreso)) {
				aux.setEstado("Sin Habitación");
				aux.deleteIdHabitacion();
				adminReserva.updateInternacion(aux);
				// adminReserva.eliminarInternacion(aux);
				// adminReserva.agregarInternacion(aux);
			}

		}
	}

	private void btnPracticas() {
		this.uiAgregar.getBtnPracticas().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controladorPracticas = new ControladorPracticasInternacion(uiPracticas, internacionSel);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	private void btnElegir() {
		uiAgregar.getBtnElegir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent Arg0) {
				ingresandoHabReal = false;

				if (uiAgregar.getDateIngresoProgramado().getDate() == null
						|| uiAgregar.getDateEgresoProgramado().getDate() == null) {
					JOptionPane.showMessageDialog(uiAgregar,
							"Debe completar las fechas de ingreso/egreso tentativos antes de seleccionar una habitación.");
					return;
				}
				if (!uiAgregar.getDateIngresoProgramado().getDate()
						.before(uiAgregar.getDateEgresoProgramado().getDate())) {
					JOptionPane.showMessageDialog(uiAgregar,
							"Ingrese un rango válido de fechas tentativas de ingreso/egreso.");
					return;
				}

				// Date date= new Date();
				// GregorianCalendar gc = new
				// GregorianCalendar(date.getYear()+1900, date.getMonth()+1,
				// date.getDate(), 0, 0);
				// Timestamp tp= new Timestamp(gc.getTimeInMillis());
				// System.out.println(date.getMonth());
				// if(!tp.after(uiAgregar.getDateIngresoProgramado().getDate())){
				// JOptionPane.showMessageDialog(uiAgregar, "Ingrese una fecha
				// tentativa de ingreso actual o posterior");
				// return;}

				uiHabitacion.getModelHabitaciones().setRowCount(0);
				uiHabitacion.getModelHabitaciones().setColumnCount(0);
				uiHabitacion.getModelHabitaciones().setColumnIdentifiers(uiHabitacion.getColumnasHabitaciones());
				Timestamp Iprogra = toTimestamp(uiAgregar.getDateIngresoProgramado());
				Timestamp Eprogra = toTimestamp(uiAgregar.getDateEgresoProgramado());
				List<HabitacionDTO> habitacionesLibres = new ArrayList<HabitacionDTO>();
				List<Integer> habitacionesOcupadas = new ArrayList<Integer>();
				List<InternacionesDTO> listainternaciones = adminReserva.obtenerInternaciones();
				for (int i = 0; i < listainternaciones.size(); i++) {
					Timestamp fin = listainternaciones.get(i).getFin();
					Timestamp inicio = listainternaciones.get(i).getInicio();
					if (listainternaciones.get(i).getEstado().equals("Programado") && !(fin.before(Iprogra) && fin.before(Eprogra)
							|| inicio.after(Iprogra) && inicio.after(Eprogra))) {
						habitacionesOcupadas.add(listainternaciones.get(i).getIdHabitacion());
					}
					else if (listainternaciones.get(i).getEstado().equals("Activo") && !Iprogra.before(listainternaciones.get(i).getIngresoReal()) )
						habitacionesOcupadas.add(listainternaciones.get(i).getIdHabitacion());
				
				}
				// si la habitacion no se encuentra ocupada se agrega a la lista
				// res
				if (habitacionesOcupadas.size() == 0)
					habitacionesLibres = listaHab;
				for (int i = 0; i < listaHab.size(); i++) {
					for (int j = 0; j < habitacionesOcupadas.size(); j++)
						if (!(habitacionesOcupadas.contains(listaHab.get(i).getId()))
								&& !(habitacionesLibres.contains(listaHab.get(i))))
							habitacionesLibres.add(listaHab.get(i));

				}
				for (int i = 0; i < habitacionesLibres.size(); i++) {
					Object[] fila = { habitacionesLibres.get(i).getId(), habitacionesLibres.get(i).getNombre() };
					uiHabitacion.getModelHabitaciones().addRow(fila);
				}

				uiHabitacion.setVisible(true);

			}
		});
	}

	private void btnReservar() {
		uiHabitacion.getBtnReservar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent Arg0) {
				if (uiHabitacion.getTableHabitaciones().getSelectedRowCount() > 0) {
					String nombreHabitacion = (String) uiHabitacion.getTableHabitaciones()
							.getValueAt(uiHabitacion.getTableHabitaciones().getSelectedRow(), 1);
					habitacionSel = (int) uiHabitacion.getTableHabitaciones()
							.getValueAt(uiHabitacion.getTableHabitaciones().getSelectedRow(), 0);

					if (!ingresandoHabReal) {
						uiAgregar.getLblNombreHab().setText(nombreHabitacion);
						habitacionSel = (int) uiHabitacion.getTableHabitaciones()
								.getValueAt(uiHabitacion.getTableHabitaciones().getSelectedRow(), 0);
						uiHabitacion.dispose();
						JOptionPane.showMessageDialog(uiAgregar, "Habitación reservada.");
					} else if (uiAgregar.getLblNombreHab().getText() == ""
							|| !uiAgregar.getLblHabitacionReal().equals(uiAgregar.getLblNombreHab())) {
						List<InternacionesDTO> internaciones = adminReserva.obtenerInternaciones();
						for (InternacionesDTO aux : internaciones) {						
							if ( aux.getEstado().equals("Programado") && 
									aux.getIdHabitacion()== habitacionSel && 
									aux.getInicio().before(toTimestamp(uiAgregar.getIngresoReal())) &&
									aux.getFin().after(toTimestamp(uiAgregar.getIngresoReal())))
							{
									int reply = JOptionPane.showConfirmDialog(uiAgregar,"Está seleccionando una habitación ya asignada. Desea continuar?", "",
													JOptionPane.YES_NO_OPTION);
									if (reply == JOptionPane.YES_OPTION) {
										habitacionSel = (int) uiHabitacion.getTableHabitaciones()
												.getValueAt(uiHabitacion.getTableHabitaciones().getSelectedRow(), 0);
										uiAgregar.getLblHabitacionReal().setText(nombreHabitacion);
										uiHabitacion.dispose();
										return;}
							}
							else {
								habitacionSel = (int) uiHabitacion.getTableHabitaciones()
								.getValueAt(uiHabitacion.getTableHabitaciones().getSelectedRow(), 0);
						uiAgregar.getLblHabitacionReal().setText(nombreHabitacion);
						uiHabitacion.dispose();}
						}
					}
				} else
					JOptionPane.showMessageDialog(uiHabitacion,
							"Debe seleccionar una habitación para poder reservarla.");
			}
		});
	}

	private void btnElegirReal() {

		uiAgregar.getBtnElegirReal().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent Arg0) {
				ingresandoHabReal = true;
				if (uiAgregar.getIngresoReal().getDateEditor().getDate() == null) {
					JOptionPane.showMessageDialog(uiAgregar,
							"Debe completar la fecha de ingreso real para seleccionar una habitacion actual.");
					return;
				}
				uiHabitacion.getModelHabitaciones().setRowCount(0);
				uiHabitacion.getModelHabitaciones().setColumnCount(0);
				uiHabitacion.getModelHabitaciones().setColumnIdentifiers(uiHabitacion.getColumnasHabitaciones());
				Timestamp ingresoNuevo = toTimestamp(uiAgregar.getIngresoReal());
				List<HabitacionDTO> habitacionesLibres = new ArrayList<HabitacionDTO>();
				List<Integer> habitacionesOcupadas = new ArrayList<Integer>();
				List<InternacionesDTO> listainternaciones = adminReserva.obtenerInternaciones();
				for (int i = 0; i < listainternaciones.size(); i++) {
					if (listainternaciones.get(i).getEstado().equals("Activo")) {
						Timestamp ingreso = listainternaciones.get(i).getIngresoReal();
						if (!ingresoNuevo.before(ingreso)) {
							habitacionesOcupadas.add(listainternaciones.get(i).getIdHabitacion());
						}
					}
				}
				if (habitacionesOcupadas.size() == 0)
					habitacionesLibres = listaHab;
				else {
					for (int i = 0; i < listaHab.size(); i++) {
						for (int j = 0; j < habitacionesOcupadas.size(); j++)
							if (!(habitacionesOcupadas.contains(listaHab.get(i).getId()))
									&& !(habitacionesLibres.contains(listaHab.get(i))))
								habitacionesLibres.add(listaHab.get(i));
					}
				}
				for (int i = 0; i < habitacionesLibres.size(); i++) {
					Object[] fila = { habitacionesLibres.get(i).getId(), habitacionesLibres.get(i).getNombre() };
					uiHabitacion.getModelHabitaciones().addRow(fila);
				}
				uiHabitacion.setVisible(true);
			}
		});
	}

	protected Timestamp toTimestamp(JDateChooser jdate) {

		if (jdate.getDate() == null) {
			GregorianCalendar gc = new GregorianCalendar(0, 0, 0, 0, 0);
			Timestamp dateTS = new Timestamp(gc.getTimeInMillis());
			return dateTS;

		}
		@SuppressWarnings("deprecation")
		GregorianCalendar gc = new GregorianCalendar(jdate.getDate().getYear() + 1900, jdate.getDate().getMonth(),
				jdate.getDate().getDate(), 0, 0);
		Timestamp dateTS = new Timestamp(gc.getTimeInMillis());
		return dateTS;
	}
}