package controller.UIRecepcion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;

import controller.UIPracticas.ControladorPracticasTurno;
import dto.EspecialidadDTO;
import dto.HorarioAtencionDTO;
import dto.MedicoDTO;
import dto.PacienteDTO;
import dto.PracticaDTO;
import dto.TurnoDTO;
import modelo.Admin_Especialidad;
import modelo.Admin_Horario;
import modelo.Admin_Medico;
import modelo.Admin_Paciente;
import modelo.Admin_Turno;
import presentacion.vista.UIPracticasTurno;
import presentacion.vista.UIRecepcion;
import reportes.ComprobanteDeTurno;
import reportes.ListaDeTurnos;

public class ControladorTurnos {
	private UIRecepcion uiTurnos;
	private Admin_Horario admin_horario;
	private Admin_Medico admin_medico;
	private Admin_Paciente admin_paciente;
	private List<MedicoDTO> medicos;
	private List<TurnoDTO> turnos;
	private List<PacienteDTO> pacientes;
	private List<EspecialidadDTO> especialidades;
	private Admin_Especialidad admin_especialidad;
	private Admin_Turno admin_turno;
	TurnoDTO ultimoTurno;
	private boolean listenersOn = true;

	public ControladorTurnos(UIRecepcion uiTurnos) {
		this.setUiTurnos(uiTurnos);
		this.admin_horario = new Admin_Horario();
		this.admin_medico = new Admin_Medico();
		this.admin_paciente = new Admin_Paciente();
		this.pacientes = admin_paciente.obtenerPacientes();
		this.admin_especialidad = new Admin_Especialidad();
		this.admin_turno = new Admin_Turno();
		this.turnos = new ArrayList<>();
		this.especialidades = new ArrayList<>();
		this.medicos = new ArrayList<>();
		inicializar();
	}

	private void inicializar() {
		uiTurnos.getDateMedicos().setDate(new Date(System.currentTimeMillis()));
		medicos.addAll(admin_medico.obtenerMedicos());
		llenarListaMedicos();
		llenarComboEspecialidades();
		this.comboBoxEspecialidad();
		this.botonRestablecer();
		this.botonAsignar();
		this.botonCancelar();
		this.botonPracticas();
		this.cambioDeMedico();
		this.cambioDeTurno();
		cerrar();
		dateChooserListener();
		imprimirComprobanteListener();
		imprimirListaListener();
	}

	private void botonPracticas() {
		this.uiTurnos.getBtnPracticas().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (turnoSeleccionado() != null && turnoSeleccionado().getPaciente() != null) {
					UIPracticasTurno uiP = new UIPracticasTurno();
					TurnoDTO turno = turnoSeleccionado();
					turno.setPracticas(admin_turno.obtenerPracticasDelTurno(turno));
					try {
						@SuppressWarnings("unused")
						ControladorPracticasTurno cp = new ControladorPracticasTurno(uiP, turno);
						uiP.setvisible(true);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else
					JOptionPane.showMessageDialog(null, "Elija un turno que esté tomado.");
			}
		});
	}

	@SuppressWarnings("unused")
	private Timestamp ultimoTurnoExistente() {
		this.turnos = this.admin_turno.obtenerTurnos();
		if (this.turnos.isEmpty() || this.turnos == null)
			return null;
		Timestamp ret = new Timestamp(0);
		for (TurnoDTO t : turnos)
			if (t.getMedico().get_idMedico() == getIdMedicoSeleccionado() && t.getFecha().after(ret))
				ret = t.getFecha();
		return ret;
	}

	private void imprimirListaListener() {
		this.uiTurnos.getBtnImprimirLista().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (uiTurnos.getTablaMedicos().getSelectedRow() > -1) {
					List<TurnoDTO> turnosDelMedico = turnosDeHoy();
					ListaDeTurnos lista = new ListaDeTurnos(turnosDelMedico);
					lista.mostrar();
				} else
					JOptionPane.showMessageDialog(null, "Primero debe seleccionar un médico");
			}
		});

	}

	private void imprimirComprobanteListener() {
		this.getUiTurnos().getBtnImprimirComprobante().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TurnoDTO turnoAImprimir = turnoSeleccionado();
				if (turnoAImprimir == null) {
					JOptionPane.showMessageDialog(null, "Elija el turno para el que quiere generar un comprobante.");
				} else if (turnoAImprimir.getEstado().equals(TurnoDTO.Estado.TOMADO)) {
					ComprobanteDeTurno comprobante = new ComprobanteDeTurno(turnoAImprimir);
					comprobante.mostrar();
				} else
					JOptionPane.showMessageDialog(null, "No existe un turno en el horario seleccionado.");
			}

		});
	}

	private void dateChooserListener() {
		getUiTurnos().getDateMedicos().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (listenersOn)
					cambioDeFecha();

			}
		});
	}

	private void cambioDeFecha() {
		if (this.uiTurnos.getTablaMedicos().getSelectedRow() >= 0)
			if (this.uiTurnos.getDateMedicos().getDate() != null) {
				List<HorarioAtencionDTO> horariosDelMedico = horariosDelDiaYMedicoSeleccionado();
				if (horariosDelMedico == null || horariosDelMedico.isEmpty()) {
				} else
					llenarListaTurnos();
			} 
	}

	private int getDiaSemanaSeleccionado() {
		@SuppressWarnings("deprecation")
		int diaSemana = this.uiTurnos.getDateMedicos().getDate().getDay();
		return diaSemana;
	}

	@SuppressWarnings("deprecation")
	private void llenarListaTurnos() {
		vaciarTurnos();
		List<TurnoDTO> turnosDelMedico = turnosDelMedicoEsteDia();
		Collections.sort(turnosDelMedico);
		for (TurnoDTO t : turnosDelMedico) {
			Object[] fila = {
					String.format("%02d", t.getFecha().getHours()) + ":"
							+ String.format("%02d", t.getFecha().getMinutes()),
					t.getEstado().toString(), t.getPaciente() };
			this.getUiTurnos().getTableModelTurnos().addRow(fila);
		}
	}

	@SuppressWarnings("deprecation")
	private List<TurnoDTO> turnosDelMedicoEsteDia() {
		int dia = uiTurnos.getDateMedicos().getDate().getDate();
		int mes = uiTurnos.getDateMedicos().getDate().getMonth();
		int año = uiTurnos.getDateMedicos().getDate().getYear();
		this.turnos = this.admin_turno.obtenerTurnos();
		List<TurnoDTO> ret = new ArrayList<>();
		int id = getIdMedicoSeleccionado();
		for (TurnoDTO t : this.turnos) {
			if (t.getMedico().get_idMedico() == id && t.getFecha().getDate() == dia && t.getFecha().getMonth() == mes
					&& t.getFecha().getYear() == año)
				ret.add(t);
		}
		return ret;
	}

	private void llenarListaMedicos() {
		this.getUiTurnos().getTableModelMedicos().setRowCount(0);
		this.getUiTurnos().getTableModelMedicos().setColumnCount(0);
		this.getUiTurnos().getTableModelMedicos().setColumnIdentifiers(this.getUiTurnos().getColumnasMedico());

		for (MedicoDTO m : medicos) {
			Object[] fila = { m.get_idMedico(), m.get_nombre(), m.get_matricula(), m.get_especialidad() };
			this.getUiTurnos().getTableModelMedicos().addRow(fila);
		}

	}

	private void llenarListaMedicos(int idEspecialidad) {
		this.getUiTurnos().getTableModelMedicos().setRowCount(0); // Para vaciar
																	// la tabla
		this.getUiTurnos().getTableModelMedicos().setColumnCount(0);
		this.getUiTurnos().getTableModelMedicos().setColumnIdentifiers(this.getUiTurnos().getColumnasMedico());

		for (MedicoDTO m : medicos)
			if (m.get_especialidad().getIdEspecialidad() == idEspecialidad) {
				Object[] fila = { m.get_idMedico(), m.get_nombre(), m.get_matricula(), m.get_especialidad() };
				this.getUiTurnos().getTableModelMedicos().addRow(fila);
			}

	}

	private void llenarComboEspecialidades() {
		getUiTurnos().getComboBoxEspecialidad().removeAllItems();
		especialidades.addAll(admin_especialidad.obtenerEspecialidades());
		for (EspecialidadDTO e : especialidades)
			uiTurnos.getComboBoxEspecialidad().addItem(e);
		uiTurnos.getComboBoxEspecialidad().setSelectedIndex(-1);
	}

	private List<HorarioAtencionDTO> horariosDelDiaYMedicoSeleccionado() {
		int idMedico = getIdMedicoSeleccionado();
		int diaSemana = getDiaSemanaSeleccionado();
		List<HorarioAtencionDTO> aux = admin_horario.obtenerHorarios();
		List<HorarioAtencionDTO> ret = new ArrayList<>();
		ret.addAll(aux);
		if (ret != null && !ret.isEmpty())
			for (HorarioAtencionDTO h : aux)
				if (h.get_idMedico() != idMedico || h.getDiaSemana() != diaSemana)
					ret.remove(h);
		Collections.sort(ret);
		return ret;
	}

	private List<HorarioAtencionDTO> horariosDelMedicoSeleccionado() {
		int idMedico = getIdMedicoSeleccionado();
		List<HorarioAtencionDTO> horarios = admin_horario.obtenerHorarios();
		List<HorarioAtencionDTO> ret = new ArrayList<>();
		if (horarios != null && !horarios.isEmpty())
			for (HorarioAtencionDTO h : horarios)
				if (h.get_idMedico() == idMedico)
					ret.add(h);
		Collections.sort(ret);

		return ret;
	}

	@SuppressWarnings("unused")
	private MedicoDTO getMedicoSeleccionado() {
		int id = getIdMedicoSeleccionado();
		for (MedicoDTO medico : medicos) {
			if (medico.get_idMedico() == id)
				return medico;
		}
		return null;
	}

	private int getIdMedicoSeleccionado() {
		int fila = this.uiTurnos.getTablaMedicos().getSelectedRow();
		if (fila < 0)
			return -1;
		else
			return (int) this.uiTurnos.getTablaMedicos().getModel().getValueAt(fila, 0);
	}

	private int getHoraSeleccionada() {
		int fila = this.uiTurnos.getTablaTurnos().getSelectedRow();
		String horario = (String) this.uiTurnos.getTablaTurnos().getModel().getValueAt(fila, 0);
		String hora = horario.substring(0, 2);
		return Integer.parseUnsignedInt(hora);
	}

	private int getMinutosSeleccionados() {
		int fila = this.uiTurnos.getTablaTurnos().getSelectedRow();
		String horario = (String) this.uiTurnos.getTablaTurnos().getModel().getValueAt(fila, 0);
		String minutos = horario.substring(3, 5);
		return Integer.parseUnsignedInt(minutos);
	}

	private void comboBoxEspecialidad() {
		this.uiTurnos.getComboBoxEspecialidad().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (listenersOn && uiTurnos.getComboBoxEspecialidad().getSelectedIndex() > -1)
					llenarListaMedicos(((EspecialidadDTO) uiTurnos.getComboBoxEspecialidad().getSelectedItem())
							.getIdEspecialidad());
			}
		});
	}

	private void botonRestablecer() {
		this.uiTurnos.getBtnReestablecer().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listenersOn = false;
				uiTurnos.getComboBoxEspecialidad().setSelectedIndex(-1);
				llenarListaMedicos();
				listenersOn = true;
				resetearHorarios();
			}
		});
	}

	private void botonCancelar() {
		this.uiTurnos.getBtnCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (uiTurnos.getTablaTurnos().getSelectedRow() > -1)
					if (!turnoLibre())
						cancelarTurno();
					else
						JOptionPane.showMessageDialog(null, "No puede cancelar un turno desocupado.");
				else
					JOptionPane.showMessageDialog(null, "Primero seleccione el turno que desea cancelar.");
			}

		});
	}

	private void cancelarTurno() {
		TurnoDTO t = turnoSeleccionado();
		t.setEstado(TurnoDTO.Estado.LIBRE);
		borrarPracticas(t);
		t.setPaciente(null);
		admin_turno.actualizarTurno(t);
		llenarListaTurnos();
	}

	private void borrarPracticas(TurnoDTO t) {
		for (PracticaDTO p : t.getPracticas()) {
			admin_turno.borrarPracticaDelTurno(p, t);
			System.out.println(p);
		}
		t.setPracticas(new ArrayList<PracticaDTO>());
	}

	@SuppressWarnings("deprecation")
	private TurnoDTO turnoSeleccionado() {
		if (uiTurnos.getTablaTurnos().getSelectedRow() < 0)
			return null;
		turnos = admin_turno.obtenerTurnos();
		TurnoDTO ret = null;
		GregorianCalendar gc = new GregorianCalendar(uiTurnos.getDateMedicos().getDate().getYear(),
				uiTurnos.getDateMedicos().getDate().getMonth(), uiTurnos.getDateMedicos().getDate().getDate(),
				getHoraSeleccionada(), getMinutosSeleccionados());
		Timestamp fecha = new Timestamp(gc.getTimeInMillis());
		fecha.setYear(fecha.getYear() + 1900);
		for (TurnoDTO t : turnos)
			if (t.getFecha().equals(fecha)) {
				ret = t;
				ret.setPracticas(admin_turno.obtenerPracticasDelTurno(ret));
			}
		return ret;
	}

	private void cerrar() {
		this.uiTurnos.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				listenersOn = false;
			}
		});
	}

	private void botonAsignar() {
		this.uiTurnos.getBtnAsignar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (uiTurnos.getTablaMedicos().getSelectedRow() > -1)
					if (uiTurnos.getTablaTurnos().getSelectedRow() != -1)
						if (!uiTurnos.getTextDniPaciente().getText().equals(""))
							if (turnoLibre()) {
								TurnoDTO turnoElegido = turnoSeleccionado();
								pacientes = admin_paciente.obtenerPacientes();
								boolean existePaciente = false;
								for (PacienteDTO p : pacientes)
									if (p.getDni() == Integer.parseInt(uiTurnos.getTextDniPaciente().getText())) {
										existePaciente = true;
										turnoElegido.setPaciente(p);
										turnoElegido.setEstado(TurnoDTO.Estado.TOMADO);
									}
								if (existePaciente) {
									admin_turno.actualizarTurno(turnoElegido);
									uiTurnos.getTablaTurnos().clearSelection();
									llenarListaTurnos();
									JOptionPane.showMessageDialog(null, "Turno asignado con éxito.");
								} else
									JOptionPane.showMessageDialog(null,
											"El DNI ingresado no corresponde a un paciente registrado.");
							} else
								JOptionPane.showMessageDialog(null, "El horario elegido no está disponible.");
						else
							JOptionPane.showMessageDialog(null,
									"Debe ingresar el DNI del paciente que solicita el turno. \nSi no está registrado, puede hacerlo con el botón \"Registrar.\"");
					else
						JOptionPane.showMessageDialog(null, "Seleccione el horario en el que desea asignar el turno.");
				else
					JOptionPane.showMessageDialog(null, "Primero seleccione un médico.");
			}
		});
	}

	@SuppressWarnings("unused")
	private PacienteDTO getPaciente() {
		String dni = this.uiTurnos.getTextDniPaciente().getText();
		for (PacienteDTO paciente : pacientes) {
			if (String.valueOf(paciente.getDni()).equals(dni))
				return paciente;
		}
		return null;
	}

	private boolean turnoLibre() {
		int fila = this.uiTurnos.getTablaTurnos().getSelectedRow();
		String estado = (String) this.uiTurnos.getTablaTurnos().getModel().getValueAt(fila, 1);
		return estado.equals("Libre");
	}

	public List<MedicoDTO> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<MedicoDTO> medicos) {
		this.medicos = medicos;
	}

	public Admin_Horario getHorario() {
		return admin_horario;
	}

	public void setHorario(Admin_Horario horario) {
		this.admin_horario = horario;
	}

	public Admin_Medico getAdmin_Medico() {
		return admin_medico;
	}

	public void setMedico(Admin_Medico medico) {
		this.admin_medico = medico;
	}

	public UIRecepcion getUiTurnos() {
		return uiTurnos;
	}

	public void setUiTurnos(UIRecepcion uiTurnos2) {
		this.uiTurnos = uiTurnos2;
	}

	public Admin_Especialidad getEspecialidad() {
		return admin_especialidad;
	}

	public void setEspecialidad(Admin_Especialidad especialidad) {
		this.admin_especialidad = especialidad;
	}

	public Admin_Turno getTurno() {
		return admin_turno;
	}

	public void setTurno(Admin_Turno turno) {
		this.admin_turno = turno;
	}

	public List<TurnoDTO> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<TurnoDTO> turnos) {
		this.turnos = turnos;
	}

	private void cambioDeMedico() {
		this.uiTurnos.getTablaMedicos().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (uiTurnos.getTablaMedicos().getSelectedRow() > -1)
					mostrarHorarios();
				else {
					resetearHorarios();
				}
				if (uiTurnos.getDateMedicos().getDate() != null)
					llenarListaTurnos();
				else
					vaciarTurnos();
			}
		});
	}

	private void cambioDeTurno() {
		this.uiTurnos.getTablaTurnos().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				boolean enabled = uiTurnos.getTablaTurnos().getSelectedRow() > -1 && uiTurnos.getTablaTurnos()
						.getValueAt(uiTurnos.getTablaTurnos().getSelectedRow(), 1).equals("Tomado");
				uiTurnos.getBtnPracticas().setEnabled(enabled);
				uiTurnos.getBtnCancelar().setEnabled(enabled);
				uiTurnos.getBtnImprimirComprobante().setEnabled(enabled);
				enabled = uiTurnos.getTablaTurnos().getSelectedRow() > -1 && uiTurnos.getTablaTurnos()
						.getValueAt(uiTurnos.getTablaTurnos().getSelectedRow(), 1).equals("Libre")
						&& !uiTurnos.getTextDniPaciente().getText().isEmpty();
				uiTurnos.getBtnAsignar().setEnabled(enabled);
			}
		});
	}

	private void mostrarHorarios() {
		List<HorarioAtencionDTO> hdm = horariosDelMedicoSeleccionado();
		List<Boolean> dias = new ArrayList<>(Arrays.asList(false, false, false, false, false, false, false));
		for (HorarioAtencionDTO h : hdm) {
			dias.set(h.getDiaSemana(), true);
		}
		int i = 0;
		uiTurnos.getLblD().setEnabled(dias.get(i++));
		uiTurnos.getLblL().setEnabled(dias.get(i++));
		uiTurnos.getLblM().setEnabled(dias.get(i++));
		uiTurnos.getLblX().setEnabled(dias.get(i++));
		uiTurnos.getLblJ().setEnabled(dias.get(i++));
		uiTurnos.getLblV().setEnabled(dias.get(i++));
		uiTurnos.getLblS().setEnabled(dias.get(i));
	}

	@SuppressWarnings("unused")
	private List<TurnoDTO> turnosDelMedico() {
		turnos = admin_turno.obtenerTurnos();
		List<TurnoDTO> ret = new ArrayList<>();
		ret.addAll(turnos);
		int id = getIdMedicoSeleccionado();
		for (TurnoDTO t : this.turnos)
			if (t.getMedico().get_idMedico() != id)
				ret.remove(t);
		return ret;
	}

	@SuppressWarnings("deprecation")
	private List<TurnoDTO> turnosDeHoy() {
		turnos = admin_turno.obtenerTurnos();
		Timestamp hoy = new Timestamp(System.currentTimeMillis());
		int dia = hoy.getDate();
		int mes = hoy.getMonth();
		int año = hoy.getYear();
		List<TurnoDTO> ret = new ArrayList<>();
		int id = getIdMedicoSeleccionado();
		for (TurnoDTO t : this.turnos)
			if (t.getMedico().get_idMedico() == id)
				if (t.getFecha().getDate() == dia && t.getFecha().getMonth() == mes && t.getFecha().getYear() == año)
					ret.add(t);
		return ret;
	}

	private void vaciarTurnos() {
		this.getUiTurnos().getTableModelTurnos().setRowCount(0);
		this.getUiTurnos().getTableModelTurnos().setColumnCount(0);
		this.getUiTurnos().getTableModelTurnos().setColumnIdentifiers(this.getUiTurnos().getColumnasHorario());
		TableColumnModel tcm = this.uiTurnos.getTablaTurnos().getColumnModel();
		tcm.getColumn(0).setPreferredWidth(90);
		tcm.getColumn(0).setMaxWidth(90);
		tcm.getColumn(1).setPreferredWidth(120);
		tcm.getColumn(1).setMaxWidth(120);
		tcm.getColumn(0).setResizable(false);
		tcm.getColumn(1).setResizable(false);
		tcm.getColumn(2).setResizable(false);
	}


	private void resetearHorarios() {
		uiTurnos.getLblD().setEnabled(false);
		uiTurnos.getLblL().setEnabled(false);
		uiTurnos.getLblM().setEnabled(false);
		uiTurnos.getLblX().setEnabled(false);
		uiTurnos.getLblJ().setEnabled(false);
		uiTurnos.getLblV().setEnabled(false);
		uiTurnos.getLblS().setEnabled(false);
	}

}
