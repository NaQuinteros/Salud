package controller.UIRecepcion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import dto.EspecialidadDTO;
import dto.HorarioAtencionDTO;
import dto.MedicoDTO;
import dto.TurnoDTO;
import modelo.Admin_Especialidad;
import modelo.Admin_Horario;
import modelo.Admin_Medico;
import modelo.Admin_Turno;
import presentacion.vista.UIDeshabilitarHorario;
import presentacion.vista.UIHorario;

public class ControladorHorario implements TableModelListener {
	private UIHorario uiHorarios;
	private Admin_Horario admin_horario;
	private Admin_Medico admin_medico;
	private List<MedicoDTO> medicos;
	private List<EspecialidadDTO> especialidades;
	private List<HorarioAtencionDTO> horarios;
	private Admin_Especialidad admin_especialidad;
	private Admin_Turno admin_turno;

	public ControladorHorario(UIHorario uiHorarios) throws IOException {
		this.setUiHorarios(uiHorarios);
		this.admin_horario = new Admin_Horario();
		this.admin_medico = new Admin_Medico();
		this.admin_turno = new Admin_Turno();
		this.admin_especialidad = new Admin_Especialidad();
		this.especialidades = new ArrayList<>();
		this.medicos = new ArrayList<>();
		this.horarios = new ArrayList<>();
		inicializar();
	}

	private void inicializar() {
		medicos = admin_medico.obtenerMedicos();
		horarios = admin_horario.obtenerHorarios();
		llenarListaMedicos();
		llenarComboEspecialidades();
		llenarListaHorarios();
		this.comboBoxEspecialidad();
		this.botonRestablecer();
		this.botonAsignar();
		this.cambiarMedico();
		this.botonBorrar();
		this.botonGenerar();
		this.cambiarHorario();
		this.botonDeshabilitar();
	}

	private List<HorarioAtencionDTO> getHorariosDelMedico() {
		List<HorarioAtencionDTO> horariosDelMedico = new ArrayList<>();
		int idMedico = getIdMedicoSeleccionado();
		for (HorarioAtencionDTO h : horarios)
			if (h.get_idMedico() == idMedico)
				horariosDelMedico.add(h);
		Collections.sort(horariosDelMedico);
		return horariosDelMedico;
	}

	private void llenarListaHorarios() {
		vaciarHorarios();
		if (this.getUiHorarios().getTablaMedicos().getSelectedRow() > -1) {
			horarios = admin_horario.obtenerHorarios();
			List<HorarioAtencionDTO> horariosDelMedico = getHorariosDelMedico();
			uiHorarios.getSpIntevalo().setValue(getMedicoSeleccionado().get_intervaloTurno());
			for (HorarioAtencionDTO h : horariosDelMedico) {
				Object[] fila = { h.getDiaString(),
						String.format("%02d", h.getHoraInicio() / 100) + ":"
								+ String.format("%02d", h.getHoraInicio() % 100),
						String.format("%02d", h.getHoraFin() / 100) + ":"
								+ String.format("%02d", h.getHoraFin() % 100) };
				this.getUiHorarios().getTableModelTurnos().addRow(fila);
			}
		}
	}

	private void llenarListaMedicos() {
		medicos = admin_medico.obtenerMedicos();
		this.getUiHorarios().getTableModelMedicos().setRowCount(0);
		this.getUiHorarios().getTableModelMedicos().setColumnCount(0);
		this.getUiHorarios().getTableModelMedicos().setColumnIdentifiers(this.getUiHorarios().getColumnasMedico());
		for (MedicoDTO m : medicos) {
			Object[] fila = { m.get_idMedico(), m.get_nombre(), m.get_matricula(), m.get_especialidad() };
			this.getUiHorarios().getTableModelMedicos().addRow(fila);
		}
	}

	private void llenarListaMedicos(int idEspecialidad) {
		this.getUiHorarios().getTableModelMedicos().setRowCount(0);
		this.getUiHorarios().getTableModelMedicos().setColumnCount(0);
		this.getUiHorarios().getTableModelMedicos().setColumnIdentifiers(this.getUiHorarios().getColumnasMedico());

		for (MedicoDTO m : medicos)
			if (m.get_especialidad().getIdEspecialidad() == idEspecialidad) {
				Object[] fila = { m.get_idMedico(), m.get_nombre(), m.get_matricula(), m.get_especialidad() };
				this.getUiHorarios().getTableModelMedicos().addRow(fila);
			}
	}

	private void llenarComboEspecialidades() {
		this.getUiHorarios().getComboBoxEspecialidad().removeAllItems();
		especialidades.addAll(admin_especialidad.obtenerEspecialidades());
		for (EspecialidadDTO e : especialidades)
			getUiHorarios().getComboBoxEspecialidad().addItem(e);
		getUiHorarios().getComboBoxEspecialidad().setSelectedIndex(-1);
	}

	private int getIdMedicoSeleccionado() {
		int fila = this.getUiHorarios().getTablaMedicos().getSelectedRow();
		int ret = (int) this.getUiHorarios().getTablaMedicos().getModel().getValueAt(fila, 0);
		return ret;
	}

	@SuppressWarnings("unused")
	private String getNombreMedicoSeleccionado() {
		int fila = this.getUiHorarios().getTablaMedicos().getSelectedRow();
		return (String) this.getUiHorarios().getTablaMedicos().getModel().getValueAt(fila, 1);
	}

	/*
	 * 
	 * Listeners
	 * 
	 */

	private void comboBoxEspecialidad() {
		this.getUiHorarios().getComboBoxEspecialidad().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (getUiHorarios().getComboBoxEspecialidad().getSelectedIndex() == -1)
					llenarListaMedicos();
				else
					llenarListaMedicos(((EspecialidadDTO) getUiHorarios().getComboBoxEspecialidad().getSelectedItem())
							.getIdEspecialidad());
			}

		});

	}

	private void botonRestablecer() {
		this.getUiHorarios().getBtnReestablecer().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getUiHorarios().getComboBoxEspecialidad().setSelectedIndex(-1);
				llenarListaMedicos();
			}

		});

	}

	private void cambiarMedico() {
		this.getUiHorarios().getTablaMedicos().getSelectionModel()
				.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						llenarListaHorarios();
						boolean enabled = uiHorarios.getTablaMedicos().getSelectedRow() > -1;
						uiHorarios.getBtnAsignar().setEnabled(enabled);
						uiHorarios.getCbDia().setEnabled(enabled);
						uiHorarios.getH1().setEnabled(enabled);
						uiHorarios.getH2().setEnabled(enabled);
						uiHorarios.getM2().setEnabled(enabled);
						uiHorarios.getM1().setEnabled(enabled);
						uiHorarios.getBtnGenerar().setEnabled(enabled);
						uiHorarios.getDateDesde().setEnabled(enabled);
						uiHorarios.getDateHasta().setEnabled(enabled);
						uiHorarios.getSpIntevalo().setEnabled(enabled);
					}
				});
	}

	private void cambiarHorario() {
		this.uiHorarios.getTablaHorarios().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				uiHorarios.getBtnBorrar().setEnabled(uiHorarios.getTablaHorarios().getSelectedRow() > -1);

			}
		});
	}

	private void botonAsignar() {
		this.getUiHorarios().getBtnAsignar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (podesAsignar()) {
					HorarioAtencionDTO h = new HorarioAtencionDTO(getUiHorarios().getCbDia().getSelectedIndex(),
							getUiHorarios().getHoraInicio() * 100 + getUiHorarios().getMinutosInicio(),
							getUiHorarios().getHoraFin() * 100 + getUiHorarios().getMinutosFin(),
							getIdMedicoSeleccionado());
					if (!horarioRepetido(h)) {
						admin_horario.agregarHorario(h);
						limpiarSpinners();
						llenarListaHorarios();
					} else
						JOptionPane.showMessageDialog(null,
								"El horario que intenta ingresar ya está incluído en uno existente.");
				}
			}

		});
	}

	private void botonDeshabilitar() {
		this.getUiHorarios().getBtnDeshabilitar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					UIDeshabilitarHorario uidh = new UIDeshabilitarHorario();
					ControladorDeshabilitarHorarios cdh = new ControladorDeshabilitarHorarios(uidh,
							getMedicoSeleccionado());
					cdh.getUiDHorario().setvisible(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}

		});
	}

	private MedicoDTO getMedicoSeleccionado() {
		if (getUiHorarios().getTablaMedicos().getSelectedRow() == -1)
			return null;
		for (MedicoDTO m : medicos)
			if (m.get_idMedico() == getIdMedicoSeleccionado())
				return m;
		return null;
	}

	private void botonBorrar() {
		this.getUiHorarios().getBtnBorrar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<HorarioAtencionDTO> horariosDelMedico = getHorariosDelMedico();
				int[] filas_seleccionadas = getUiHorarios().getTablaHorarios().getSelectedRows();
				for (int fila : filas_seleccionadas) {
					admin_horario.borrarHorario(horariosDelMedico.get(fila));
				}
				llenarListaHorarios();
			}
		});
	}

	private void botonGenerar() {
		this.uiHorarios.getBtnGenerar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (uiHorarios.getDateHasta().getDate() != null && uiHorarios.getDateDesde() != null)
					if (!uiHorarios.getDateHasta().getDate().before(new Date(System.currentTimeMillis()))
							|| uiHorarios.getDateHasta().getDate().equals(new Date(System.currentTimeMillis())))
						if (uiHorarios.getTablaMedicos().getSelectedRow() > -1)
							if ((int) uiHorarios.getSpIntevalo().getValue() > 0)
								if (!horariosDelMedicoSeleccionado().isEmpty())
									if (uiHorarios.getDateDesde().getDate()
											.before(uiHorarios.getDateHasta().getDate())) {
										GregorianCalendar actual = new GregorianCalendar();
										GregorianCalendar fin = new GregorianCalendar();
										List<TurnoDTO> nuevosTurnos = new ArrayList<>();
										List<HorarioAtencionDTO> horariosDelMedico = horariosDelMedicoSeleccionado();
										List<HorarioAtencionDTO> horariosDelDia = new ArrayList<>();
										MedicoDTO medicoSeleccionado = getMedicoSeleccionado();
										int intervalo = medicoSeleccionado.get_intervaloTurno();
										if (uiHorarios.getDateDesde().getDate() == null)
											actual.setTimeInMillis(System.currentTimeMillis());
										else
											actual.setTime(uiHorarios.getDateDesde().getDate());
										fin.setTimeInMillis(uiHorarios.getDateHasta().getDate().getTime());
										fin.add(Calendar.MINUTE, -intervalo * 2);
										while (actual.before(fin)) {
											horariosDelDia = horariosDelDia(horariosDelMedico,
													actual.get(Calendar.DAY_OF_WEEK) - 1);
											nuevosTurnos.addAll(crearTurnos(horariosDelDia, actual, fin));
											actual.add(Calendar.DAY_OF_MONTH, 1);
											actual.set(Calendar.HOUR_OF_DAY, 0);
											actual.set(Calendar.MINUTE, 0);
											actual.set(Calendar.SECOND, 0);
											actual.set(Calendar.MILLISECOND, 0);
										}
										medicoSeleccionado
												.set_intervaloTurno((int) uiHorarios.getSpIntevalo().getValue());
										admin_medico.editarMedico(medicoSeleccionado);
										for (TurnoDTO t : nuevosTurnos)
											admin_turno.agregarTurno(t);
										if (nuevosTurnos.isEmpty())
											JOptionPane.showMessageDialog(null,
													"Ya existen turnos hasta la fecha indicada.");
										else
											JOptionPane.showMessageDialog(null,
													"Se han creado " + nuevosTurnos.size() + " turnos con éxito.");

									} else
										JOptionPane.showMessageDialog(null,
												"La fecha de inicio debe ser anterior a la fecha de fin.");
								else
									JOptionPane.showMessageDialog(null, "El médico no tiene completados sus horarios.");
							else
								JOptionPane.showMessageDialog(null, "Elija la duración de los turnos.");
						else
							JOptionPane.showMessageDialog(null, "Elija el médico para el que quiere crear los turnos.");
					else
						JOptionPane.showMessageDialog(null, "No puede crear turnos para fechas pasadas.");
				else
					JOptionPane.showMessageDialog(null, "Elija las fechas entre las que quiere generar los turnos.");
			}

		});

	}

	private List<HorarioAtencionDTO> horariosDelMedicoSeleccionado() {
		int idMedico = getIdMedicoSeleccionado();
		List<HorarioAtencionDTO> lista = admin_horario.obtenerHorarios();
		List<HorarioAtencionDTO> ret = new ArrayList<>();
		if (lista != null && !lista.isEmpty())
			for (HorarioAtencionDTO h : lista)
				if (h.get_idMedico() == idMedico)
					ret.add(h);
		Collections.sort(ret);
		return ret;
	}

	private List<HorarioAtencionDTO> horariosDelDia(List<HorarioAtencionDTO> horariosDelMedico, int day) {
		List<HorarioAtencionDTO> ret = new ArrayList<>();
		ret.addAll(horariosDelMedico);
		for (HorarioAtencionDTO h : horariosDelMedico) {
			if (h.getDiaSemana() != day)
				ret.remove(h);
		}
		return ret;
	}

	private List<TurnoDTO> crearTurnos(List<HorarioAtencionDTO> hors, GregorianCalendar actual, GregorianCalendar fin) {
		List<TurnoDTO> ret = new ArrayList<>();
		MedicoDTO medic = getMedicoSeleccionado();
		int intervalo = (int) this.uiHorarios.getSpIntevalo().getValue();
		List<TurnoDTO> turnos = admin_turno.obtenerTurnos();
		for (HorarioAtencionDTO h : hors) {
			GregorianCalendar ultimaHora = new GregorianCalendar(actual.get(Calendar.YEAR), actual.get(Calendar.MONTH),
					actual.get(Calendar.DATE), h.getHoraFin() / 100, h.getHoraFin() % 100);
			GregorianCalendar horaActual = new GregorianCalendar(actual.get(Calendar.YEAR), actual.get(Calendar.MONTH),
					actual.get(Calendar.DATE), h.getHoraInicio() / 100, h.getHoraInicio() % 100);
			while (horaActual.before(ultimaHora)) {
				boolean yaExiste = false;
				for (TurnoDTO existente : turnos) {
					int diaActual = actual.get(Calendar.DAY_OF_YEAR);
					GregorianCalendar gcTurno = new GregorianCalendar();
					gcTurno.setTimeInMillis(existente.getFecha().getTime());
					int diaTurno = gcTurno.get(Calendar.DAY_OF_YEAR);
					yaExiste = yaExiste || (diaTurno == diaActual
							&& existente.getMedico().get_idMedico() == getIdMedicoSeleccionado());
				}
				if (!yaExiste)
					ret.add(new TurnoDTO(medic, null, new Timestamp(horaActual.getTimeInMillis()),
							TurnoDTO.Estado.LIBRE, false));
				horaActual.add(Calendar.MINUTE, intervalo);
			}
		}
		return ret;
	}

	private boolean horarioRepetido(HorarioAtencionDTO nuevo) {
		boolean repetido = false;
		for (HorarioAtencionDTO existente : this.horarios) {
			repetido = repetido || nuevo.get_idMedico() == existente.get_idMedico()
					&& nuevo.getDiaSemana() == existente.getDiaSemana()
					&& nuevo.getHoraInicio() >= existente.getHoraInicio()
					&& nuevo.getHoraInicio() <= existente.getHoraFin();
			repetido = repetido || nuevo.get_idMedico() == existente.get_idMedico()
					&& nuevo.getDiaSemana() == existente.getDiaSemana()
					&& nuevo.getHoraFin() >= existente.getHoraInicio() && nuevo.getHoraFin() <= existente.getHoraFin();
		}
		return repetido;
	}

	private void limpiarSpinners() {
		getUiHorarios().getH1().setValue(0);
		getUiHorarios().getM1().setValue(0);
		getUiHorarios().getH2().setValue(0);
		getUiHorarios().getM2().setValue(0);
	}

	private boolean podesAsignar() {
		if (hayMedicoSeleccionado())
			if (spinnersCorrectos())
				if (hayDiaSeleccionado())
					return true;
				else {
					JOptionPane.showMessageDialog(null, "Primero debe seleccionar un día de la semana.");
					return false;
				}
			else {
				JOptionPane.showMessageDialog(null, "El rango horario que ingresó no es válido.");
				return false;
			}
		else {
			JOptionPane.showMessageDialog(null, "Primero debe seleccionar un médico.");
			return false;
		}
	}

	private boolean hayMedicoSeleccionado() {
		return this.getUiHorarios().getTablaMedicos().getSelectedRow() != -1;
	}

	private boolean hayDiaSeleccionado() {
		return getUiHorarios().getCbDia().getSelectedIndex() != -1;
	}

	private boolean spinnersCorrectos() {
		return getUiHorarios().getHoraInicio() * 100
				+ getUiHorarios().getMinutosInicio() < getUiHorarios().getHoraFin() * 100
						+ getUiHorarios().getMinutosFin();
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

	public Admin_Especialidad getEspecialidad() {
		return admin_especialidad;
	}

	public void setEspecialidad(Admin_Especialidad especialidad) {
		this.admin_especialidad = especialidad;
	}

	private void vaciarHorarios() {
		this.getUiHorarios().getTableModelTurnos().setRowCount(0);
		this.getUiHorarios().getTableModelTurnos().setColumnCount(0);
		this.getUiHorarios().getTableModelTurnos().setColumnIdentifiers(this.getUiHorarios().getColumnasHorario());
	}

	@Override
	public void tableChanged(TableModelEvent arg0) {
		this.llenarListaHorarios();

	}

	public List<EspecialidadDTO> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<EspecialidadDTO> especialidades) {
		this.especialidades = especialidades;
	}

	public List<HorarioAtencionDTO> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<HorarioAtencionDTO> horarios) {
		this.horarios = horarios;
	}

	/**
	 * @return the uiHorarios
	 */
	public UIHorario getUiHorarios() {
		return uiHorarios;
	}

	/**
	 * @param uiHorarios
	 *            the uiHorarios to set
	 */
	public void setUiHorarios(UIHorario uiHorarios) {
		this.uiHorarios = uiHorarios;
	}

}
