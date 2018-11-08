package dto;

import java.util.Date;

public class FacturaDTO {
	double monto;
	PracticaDTO practica;
	PacienteDTO paciente;
	MedicoDTO medico;
	Date fecha;
	
	public FacturaDTO(double monto, PracticaDTO practica, PacienteDTO paciente, MedicoDTO medico){
		this.medico = medico;
		this.monto = monto;
		this.practica = practica;
		this.paciente = paciente;
		this.fecha = new Date(System.currentTimeMillis());
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public PracticaDTO getPractica() {
		return practica;
	}

	public void setPractica(PracticaDTO practica) {
		this.practica = practica;
	}

	public PacienteDTO getPaciente() {
		return paciente;
	}

	public void setPaciente(PacienteDTO paciente) {
		this.paciente = paciente;
	}

	public MedicoDTO getMedico() {
		return medico;
	}

	public void setMedico(MedicoDTO medico) {
		this.medico = medico;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
