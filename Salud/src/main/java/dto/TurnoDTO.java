package dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TurnoDTO implements Comparable<TurnoDTO>{

	private int idTurno;
	private MedicoDTO medico;
	private PacienteDTO paciente;
	private Timestamp fecha;
	@SuppressWarnings("unused")
	private String fechaString;
	private String horaString;
	private Estado estado;
	private boolean autorizado;
	private List<PracticaDTO> practicas;
	private boolean notificadoCorreo = false;
	
	public enum Estado {
		LIBRE("Libre"), TOMADO("Tomado"), ESPERANDO("Esperando"), ATENDIENDO("Atendiendo"), CONCRETADO(
				"Concretado"), CANCELADO("Cancelado"), CERRADO("Cerrado");

		private String nombreEstado;

		private Estado(String nombreEstado) {
			this.nombreEstado = nombreEstado;
		}

		@Override
		public String toString() {
			return nombreEstado;
		}
	};

	public TurnoDTO(int idTurno, MedicoDTO medico, PacienteDTO paciente, Timestamp fecha, Estado estado,
			Boolean autorizado) {
		this.idTurno = idTurno;
		this.medico = medico;
		this.paciente = paciente;
		this.fecha = fecha;
		setHoraString();
		this.fechaToString();
		this.estado = estado;
		this.autorizado = autorizado;
		this.setPracticas(new ArrayList<>());
	}

	public TurnoDTO(MedicoDTO medico, PacienteDTO paciente, Timestamp fecha, Estado estado, Boolean autorizado) {
		this.medico = medico;
		this.paciente = paciente;
		this.fecha = fecha;
		setHoraString();
		this.fechaToString();
		this.estado = estado;
		this.autorizado = autorizado;
		this.setPracticas(new ArrayList<>());
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fechaseleccionada) {
		this.fecha = fechaseleccionada;
		setFechaString(fechaToString());
	}

	public int getIdTurno() {
		return idTurno;
	}

	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}

	public MedicoDTO getMedico() {
		return medico;
	}

	public void setMedico(MedicoDTO medico) {
		this.medico = medico;
	}

	public PacienteDTO getPaciente() {
		return paciente;
	}

	public void setPaciente(PacienteDTO paciente) {
		this.paciente = paciente;
	}

	@SuppressWarnings("deprecation")
	public String fechaToString() {
		if (fecha != null)
			return String.format("%02d", fecha.getDate()) + "/" + String.format("%02d", fecha.getMonth()) + "/"
					+ (fecha.getYear() + 1900) + " " + String.format("%02d", fecha.getHours()) + ":"
					+ String.format("%02d", fecha.getMinutes());
		else
			return null;
	}

	@Override
	public String toString() {
		return this.paciente + " tiene turno el día " + fechaToString() + " con el Doctor " + this.medico.get_nombre()
				+ " (" + this.getMedico().get_especialidad().getNombreEspecialidad() + ")";
	}

	public String getFechaString() {
		return fechaToString();
	}

	public String getHoraString() {
		return horaString;
	}

	@SuppressWarnings("deprecation")
	public void setHoraString() {
		this.horaString = String.format("%02d", fecha.getHours()) + ":" + String.format("%02d", fecha.getMinutes());
	}

	public void setFechaString(String fechaString) {
		setHoraString();
		this.fechaString = fechaString;

	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Boolean getAutorizado() {
		return autorizado;
	}

	public void setAutorizado(Boolean autorizado) {
		this.autorizado = autorizado;
	}

	/**
	 * @return the practicas
	 */
	public List<PracticaDTO> getPracticas() {
		return practicas;
	}

	/**
	 * @param practicas the practicas to set
	 */
	public void setPracticas(List<PracticaDTO> practicas) {
		this.practicas = practicas;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TurnoDTO other = (TurnoDTO) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (idTurno != other.idTurno)
			return false;
		if (medico == null) {
			if (other.medico != null)
				return false;
		} else if (!medico.equals(other.medico))
			return false;
		if (paciente == null) {
			if (other.paciente != null)
				return false;
		} else if (!paciente.equals(other.paciente))
			return false;
		return true;
	}

	public boolean isNotificadoCorreo() {
		return notificadoCorreo;
	}

	public void setNotificadoCorreo(boolean notificadoCorreo) {
		this.notificadoCorreo = notificadoCorreo;
	}

	@Override
	public int compareTo(TurnoDTO other) {
		return (int) (this.fecha.getTime()-other.fecha.getTime());
	}

	
	
}
