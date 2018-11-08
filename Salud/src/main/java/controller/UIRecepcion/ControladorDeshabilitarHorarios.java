package controller.UIRecepcion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JOptionPane;

import dto.MedicoDTO;
import dto.TurnoDTO;
import modelo.Admin_Medico;
import modelo.Admin_Turno;
import presentacion.vista.UIDeshabilitarHorario;

public class ControladorDeshabilitarHorarios implements ActionListener {
	private UIDeshabilitarHorario uiDHorario;
	private MedicoDTO medico;
	private Admin_Medico admin_medico;
	private List<MedicoDTO> medicos;
	private List<TurnoDTO> turnos;
	private Admin_Turno admin_turno;

	public ControladorDeshabilitarHorarios(UIDeshabilitarHorario uiHorario, MedicoDTO medico) throws IOException {
		this.uiDHorario = uiHorario;
		this.admin_medico = new Admin_Medico();
		this.medicos = new ArrayList<>();
		this.turnos = new ArrayList<>();
		this.admin_turno = new Admin_Turno();
		this.medico = medico;
		inicializar();
	}

	private void inicializar() {
		llenarComboMedicos();
		uiDHorario.getBtnDeshabilitar().addActionListener(this);
	}

	private void llenarComboMedicos() {
		this.uiDHorario.getComboBox().removeAllItems();
		medicos.addAll(admin_medico.obtenerMedicos());
		for (MedicoDTO m : medicos)
			this.uiDHorario.getComboBox().addItem(m);
		this.uiDHorario.getComboBox().setSelectedItem(medico);
		this.uiDHorario.getComboBox().addActionListener(this);
	}

	private MedicoDTO medicoSeleccionado() {
		return (MedicoDTO) this.uiDHorario.getComboBox().getSelectedItem();
	}

	private List<TurnoDTO> turnosDelMedico() {
		turnos = admin_turno.obtenerTurnos();
		List<TurnoDTO> ret = new ArrayList<>();
		ret.addAll(turnos);
		int id = medicoSeleccionado().get_idMedico();
		for (TurnoDTO t : this.turnos)
			if (t.getMedico().get_idMedico() != id)
				ret.remove(t);
		return ret;
	}

	@SuppressWarnings("deprecation")
	private List<TurnoDTO> turnosCancelados(GregorianCalendar i, GregorianCalendar f) {
		List<TurnoDTO> turnosDelMedico = turnosDelMedico();
		List<TurnoDTO> ret = new ArrayList<>();
		Timestamp inicio = new Timestamp(i.getTimeInMillis());
		Timestamp fin = new Timestamp(f.getTimeInMillis());
		inicio.setYear(inicio.getYear() + 1900);
		fin.setYear(fin.getYear() + 1900);
		for (TurnoDTO t : turnosDelMedico)
			if ((t.getFecha().after(inicio) || t.getFecha().equals(inicio))
					&& (t.getFecha().before(fin) || t.getFecha().equals(inicio)))
				ret.add(t);
		return ret;
	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.uiDHorario.getBtnDeshabilitar())
			if (this.uiDHorario.getComboBox().getSelectedItem() != null)
				if (uiDHorario.getDateInicio().getDate() != null && uiDHorario.getDateFin().getDate() != null) {
					GregorianCalendar inicio = new GregorianCalendar(uiDHorario.getDateInicio().getDate().getYear(),
							uiDHorario.getDateInicio().getDate().getMonth(),
							uiDHorario.getDateInicio().getDate().getDate(), (int) uiDHorario.getH1().getValue(),
							(int) uiDHorario.getM1().getValue());
					GregorianCalendar fin = new GregorianCalendar(uiDHorario.getDateFin().getDate().getYear(),
							uiDHorario.getDateFin().getDate().getMonth(), uiDHorario.getDateFin().getDate().getDate(),
							(int) uiDHorario.getH2().getValue(), (int) uiDHorario.getM2().getValue());
					if (inicio.before(fin)) {
						List<TurnoDTO> turnosTomados = new ArrayList<>();
						List<TurnoDTO> turnosACancelar = turnosCancelados(inicio, fin);
						for (TurnoDTO t : turnosACancelar) {
							t.setEstado(TurnoDTO.Estado.CANCELADO);
							t.setNotificadoCorreo(true);
							admin_turno.actualizarTurno(t);
							if (t.getPaciente() != null)
								turnosTomados.add(t);
						}
						uiDHorario.getDateInicio().setDate(null);
						uiDHorario.getDateFin().setDate(null);
						uiDHorario.getH1().setValue(0);
						uiDHorario.getH2().setValue(0);
						uiDHorario.getM1().setValue(0);
						uiDHorario.getM2().setValue(0);
						if (!turnosTomados.isEmpty()) {
							JOptionPane.showMessageDialog(null,
									turnosTomados.size() + " turnos han sido cancelados y deberán ser reprogramados.");
									ControladorCorreo c = new ControladorCorreo((ArrayList<TurnoDTO>) turnosACancelar, false);
									Thread tr = new Thread(c);
									tr.start();
						} else {
							JOptionPane.showMessageDialog(null,
									"Se cancelaron los turnos con éxito. Ningún paciente fue afectado.");
						}
					} else
						JOptionPane.showMessageDialog(null, "El rango horario seleccionado es inválido");
				} else {
					JOptionPane.showMessageDialog(null, "Complete las fechas de inicio y fin");
					System.out.println(uiDHorario.getDateInicio().getDate() + "\n" + uiDHorario.getDateFin().getDate());
				}
			else
				JOptionPane.showMessageDialog(null, "Seleccione un médico");
	}

	public UIDeshabilitarHorario getUiDHorario() {
		return uiDHorario;
	}

	public void setUiDHorario(UIDeshabilitarHorario uiDHorario) {
		this.uiDHorario = uiDHorario;
	}
}
