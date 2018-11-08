package dto;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class HorarioAtencionDTO implements Comparable<HorarioAtencionDTO> {
	private int idHorario;
	private int diaSemana;
	private int horaInicio;
	private int horaFin;
	private int idMedico;
	private String diaString;

	public HorarioAtencionDTO(int idHorario, int diaSemana, int horaInicio, int horaFin, int idMedico) {
		this.idHorario = idHorario;
		this.diaSemana = diaSemana;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.idMedico = idMedico;
		this.setDiaString();
	}

	public HorarioAtencionDTO(int diaSemana, int horaInicio, int horaFin, int medico) {
		this.diaSemana = diaSemana;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.idMedico = medico;
	}

	public int getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(int diaSemana) {
		this.diaSemana = diaSemana;
	}

	public int getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(int horaInicio) {
		this.horaInicio = horaInicio;
	}

	public int getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(int horaFin) {
		this.horaFin = horaFin;
	}

	public int get_idMedico() {
		return idMedico;
	}

	public void get_idMedico(int medico) {
		this.idMedico = medico;
	}

	public int getIdHorario() {
		return idHorario;
	}

	public void setIdHorario(int idHorario) {
		this.idHorario = idHorario;
	}

	public String getDiaString() {
		return diaString;
	}

	public void setDiaString() {
		if (this.diaSemana == 0)
			this.diaString = "Domingo";
		else if (this.diaSemana == 1)
			this.diaString = "Lunes";
		else if (this.diaSemana == 2)
			this.diaString = "Martes";
		else if (this.diaSemana == 3)
			this.diaString = "Miércoles";
		else if (this.diaSemana == 4)
			this.diaString = "Jueves";
		else if (this.diaSemana == 5)
			this.diaString = "Viernes";
		else if (this.diaSemana == 6)
			this.diaString = "Sábado";
		else
			throw new IllegalArgumentException("El día de la semana es inválido");
	}

	@Override
	public int compareTo(HorarioAtencionDTO other) {
		if (this.diaSemana == other.diaSemana)
			return this.horaInicio - other.horaInicio;
		else
			return this.diaSemana - other.diaSemana;
	}

	public boolean abarca(GregorianCalendar hora, int duracionTurno) {
		return hora.get(Calendar.DAY_OF_WEEK) == this.diaSemana
				&& hora.get(Calendar.HOUR_OF_DAY) * 100 + hora.get(Calendar.MINUTE) >= this.horaInicio
				&& hora.get(Calendar.HOUR_OF_DAY) * 100 + hora.get(Calendar.MINUTE) <= this.horaFin - duracionTurno;
	}
}
