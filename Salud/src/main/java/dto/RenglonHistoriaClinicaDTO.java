package dto;

import java.sql.Timestamp;
import java.util.ArrayList;

public class RenglonHistoriaClinicaDTO {

	private int idEntrada;
	private Timestamp fecha;
	private String medico;
	private String diagnostico;
	private ArrayList<PracticaDTO> practicas;
	private int idPaciente;
	private String especialidad;

	public RenglonHistoriaClinicaDTO(Timestamp fecha, String medico, String diagnostico, int idPaciente, String especialidad) {
		this.fecha = fecha;
		this.medico = medico;
		this.diagnostico = diagnostico;
		this.practicas = new ArrayList<>();
		this.especialidad = especialidad;
		this.idPaciente = idPaciente;
	}

	public RenglonHistoriaClinicaDTO(int idEntrada, Timestamp fecha, String medico, String diagnostico,
			int idPaciente, String especialidad) {
		this.idEntrada = idEntrada;
		this.fecha = fecha;
		this.medico = medico;
		this.diagnostico = diagnostico;
		this.idPaciente = idPaciente;
		this.especialidad = especialidad;
		this.practicas = new ArrayList<>();
	}

	public int getIdEntrada() {
		return idEntrada;
	}

	public void setIdEntrada(int idEntrada) {
		this.idEntrada = idEntrada;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getMedico() {
		return medico;
	}

	public void setMedico(String medico) {
		this.medico = medico;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public ArrayList<PracticaDTO> getPracticas() {
		return practicas;
	}

	public void setPracticas(ArrayList<PracticaDTO> practicas) {
		this.practicas = practicas;
	}

	public int getIdPaciente() {
		return idPaciente;
	}

	@Override
	public String toString() {
		return "RenglonHistoriaClinicaDTO [idEntrada=" + idEntrada + ", fecha=" + fecha + ", medico=" + medico
				+ ", diagnostico=" + diagnostico + ", practicas=" + practicas + ", idPaciente=" + idPaciente + "]";
	}

	public String getEspecialidad() {
		return especialidad;
	}

}