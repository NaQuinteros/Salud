package controller.UIRecepcion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import dto.EspecialidadDTO;
import dto.MedicoDTO;
import dto.PacienteDTO;
import dto.TurnoDTO;
import modelo.Admin_Especialidad;
import modelo.Admin_Horario;
import modelo.Admin_Medico;
import modelo.Admin_Turno;
import presentacion.vista.UITurnosCancelados;

public class ControladorTurnosCancelados implements TableModelListener {
	private UITurnosCancelados ui;
	private Admin_Horario admin_horario;
	private Admin_Medico admin_medico;
	private List<MedicoDTO> medicos;
	private List<EspecialidadDTO> especialidades;
	private Admin_Especialidad admin_especialidad;
	private Admin_Turno admin_turno;
	private List<TurnoDTO> turnos;
	private List<TurnoDTO> turnosCancelados;

	public ControladorTurnosCancelados(UITurnosCancelados ui) throws IOException {
		this.ui = ui;
		this.admin_horario = new Admin_Horario();
		this.admin_medico = new Admin_Medico();
		this.admin_turno = new Admin_Turno();
		this.admin_especialidad = new Admin_Especialidad();
		this.especialidades = new ArrayList<>();
		this.ui.setVisible(true);
		inicializar();
	}

	private void inicializar() {
		medicos = admin_medico.obtenerMedicos();
		turnos = admin_turno.obtenerTurnos();
		setTurnosCancelados();
		llenarListaMedicos();
		llenarComboEspecialidades();
		llenarListaTurnos();
		this.comboBoxEspecialidad();
		this.botonRestablecer();
		this.botonLiberar();
		this.cambiarMedico();
		this.checkBox();
		cambioDeTurno();
	}

	private void setTurnosCancelados() {
		turnosCancelados = new ArrayList<>();
		for (TurnoDTO t : turnos)
			if (t.getPaciente() != null && t.getEstado().equals(TurnoDTO.Estado.CANCELADO))
				turnosCancelados.add(t);
	}

	private void llenarListaTurnos() {
		vaciarTurnos();
		List<TurnoDTO> turnosAMostrar = new ArrayList<>();
		// Si hay un medico seleccionado, filtro los turnos a mostrar
		if (!ui.getChckbxTodosLosTurnos().isSelected() && ui.getTablaMedicos().getSelectedRow() > -1)
			turnosAMostrar.addAll(turnosDelMedico(getIdMedicoSeleccionado()));
		else
			turnosAMostrar.addAll(turnosCancelados);
		for (TurnoDTO t : turnosAMostrar) {
			Object[] fila = { t.getIdTurno(), t.getPaciente().getDni(),
					t.getPaciente().getNombre() + " " + t.getPaciente().getApellido(), t.getFechaString(),
					t.getPaciente().getTelefono(), t.getMedico().get_nombre() };
			this.getUi().getTableModelTurnos().addRow(fila);
		}

	}

	private List<TurnoDTO> turnosDelMedico(int id) {
		List<TurnoDTO> ret = new ArrayList<>();
		for (TurnoDTO t : this.turnosCancelados)
			if (t.getMedico().get_idMedico() == id)
				ret.add(t);
		return ret;
	}

	private void llenarListaMedicos() {
		medicos = admin_medico.obtenerMedicos();
		this.getUi().getTableModelMedicos().setRowCount(0);
		this.getUi().getTableModelMedicos().setColumnCount(0);
		this.getUi().getTableModelMedicos().setColumnIdentifiers(this.getUi().getColumnasMedico());
		for (MedicoDTO m : medicos) {
			Object[] fila = { m.get_idMedico(), m.get_nombre(), m.get_matricula(), m.get_especialidad() };
			this.getUi().getTableModelMedicos().addRow(fila);
		}
	}

	private void llenarListaMedicos(int idEspecialidad) {
		this.getUi().getTableModelMedicos().setRowCount(0);
		this.getUi().getTableModelMedicos().setColumnCount(0);
		this.getUi().getTableModelMedicos().setColumnIdentifiers(this.getUi().getColumnasMedico());

		for (MedicoDTO m : medicos)
			if (m.get_especialidad().getIdEspecialidad() == idEspecialidad) {
				Object[] fila = { m.get_idMedico(), m.get_nombre(), m.get_matricula(), m.get_especialidad() };
				this.getUi().getTableModelMedicos().addRow(fila);
			}
	}

	private void llenarComboEspecialidades() {
		this.getUi().getComboBoxEspecialidad().removeAllItems();
		especialidades.addAll(admin_especialidad.obtenerEspecialidades());
		for (EspecialidadDTO e : especialidades)
			getUi().getComboBoxEspecialidad().addItem(e);
		getUi().getComboBoxEspecialidad().setSelectedIndex(-1);
	}

	private int getIdMedicoSeleccionado() {
		int fila = this.getUi().getTablaMedicos().getSelectedRow();
		int ret = (int) this.getUi().getTablaMedicos().getModel().getValueAt(fila, 0);
		return ret;
	}

	@SuppressWarnings("unused")
	private String getNombreMedicoSeleccionado() {
		int fila = this.getUi().getTablaMedicos().getSelectedRow();
		return (String) this.getUi().getTablaMedicos().getModel().getValueAt(fila, 1);
	}

	private void comboBoxEspecialidad() {
		this.getUi().getComboBoxEspecialidad().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (getUi().getComboBoxEspecialidad().getSelectedIndex() == -1)
					llenarListaMedicos();
				else
					llenarListaMedicos(((EspecialidadDTO) getUi().getComboBoxEspecialidad().getSelectedItem())
							.getIdEspecialidad());
			}

		});

	}

	private void botonRestablecer() {
		this.getUi().getBtnReestablecer().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getUi().getComboBoxEspecialidad().setSelectedIndex(-1);
				llenarListaMedicos();
			}

		});

	}

	private void cambiarMedico() {
		this.getUi().getTablaMedicos().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (ui.getTablaMedicos().getSelectedRow() > -1)
					ui.getChckbxTodosLosTurnos().setSelected(false);
				llenarListaTurnos();
			}
		});
	}

	private void checkBox() {
		this.getUi().getChckbxTodosLosTurnos().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (ui.getChckbxTodosLosTurnos().isSelected())
					ui.getTablaMedicos().clearSelection();

			}
		});
	}

	@SuppressWarnings("unused")
	private MedicoDTO getMedicoSeleccionado() {
		if (getUi().getTablaMedicos().getSelectedRow() == -1)
			return null;
		for (MedicoDTO m : medicos)
			if (m.get_idMedico() == getIdMedicoSeleccionado())
				return m;
		return null;
	}

	private void botonLiberar() {
		this.getUi().getBtnLiberar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (ui.getTablaTurnos().getSelectedRow() > -1) {
					TurnoDTO t = getTurnoSeleccionado();
					t.setEstado(TurnoDTO.Estado.CERRADO);
					admin_turno.actualizarTurno(t);
					setTurnosCancelados();
					llenarListaTurnos();
					JOptionPane.showMessageDialog(null, "El turno se ha marcado como resuelto.");
				} else
					JOptionPane.showMessageDialog(null, "Primero elija un turno.");
			}
		});
	}



	public TurnoDTO getTurnoSeleccionado() {
		if (ui.getTablaTurnos().getSelectedRow() == -1)
			return null;
		int id = (int) ui.getModelTurnos().getValueAt(ui.getTablaTurnos().getSelectedRow(), 0);
		for (TurnoDTO t : turnosCancelados)
			if (t.getIdTurno() == id)
				return t;
		return null;
	}
	
	private void cambioDeTurno() {
		this.ui.getTablaTurnos().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				boolean enabled = ui.getTablaTurnos().getSelectedRow() > -1;
				ui.getBtnReasignar().setEnabled(enabled);
				ui.getBtnLiberar().setEnabled(enabled);
			}
		});
	}

	@SuppressWarnings("unused")
	private boolean hayMedicoSeleccionado() {
		return this.getUi().getTablaMedicos().getSelectedRow() != -1;
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

	private void vaciarTurnos() {
		this.getUi().getTableModelTurnos().setRowCount(0);
		this.getUi().getTableModelTurnos().setColumnCount(0);
		this.getUi().getTableModelTurnos().setColumnIdentifiers(this.getUi().getColumnasTurno());
	}

	@Override
	public void tableChanged(TableModelEvent arg0) {
		this.llenarListaTurnos();

	}

	public List<EspecialidadDTO> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<EspecialidadDTO> especialidades) {
		this.especialidades = especialidades;
	}

	public UITurnosCancelados getUi() {
		return ui;
	}

	public Admin_Horario getAdmin_horario() {
		return admin_horario;
	}

	public Admin_Medico getAdmin_medico() {
		return admin_medico;
	}

	public Admin_Especialidad getAdmin_especialidad() {
		return admin_especialidad;
	}

	public Admin_Turno getAdmin_turno() {
		return admin_turno;
	}

	public List<TurnoDTO> getTurnos() {
		return turnos;
	}

	public PacienteDTO getPaciente() {
		return getTurnoSeleccionado().getPaciente();
	}

}
