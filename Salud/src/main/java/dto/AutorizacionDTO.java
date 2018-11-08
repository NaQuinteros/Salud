package dto;

import java.sql.Timestamp;

public class AutorizacionDTO {
	
	private int idAutorizacion;
	private Timestamp fechaAutorizacion;
	private int cantidadSesiones;
	private String diagnostico;
	private int idPaciente;
	private int idMedico;
	private int idPractica;
	private int idTipoPractica;
	
	public AutorizacionDTO(int idaut, Timestamp faut, int cantses, String diag, int idpa, int idme, int idpra, int idtipo){
		setIdAutorizacion(idaut);
		setFechaAutorizacion(faut);
		setCantidadSesiones(cantses);
		setDiagnostico(diag);
		setIdPaciente(idpa);
		setIdMedico(idme);
		setIdPractica(idpra);
		setIdTipoPractica(idtipo);
	}
	
	public int getIdAutorizacion() {
		return idAutorizacion;
	}
	public Timestamp getFechaAutorizacion() {
		return fechaAutorizacion;
	}
	public int getCantidadSesiones() {
		return cantidadSesiones;
	}
	public String getDiagnostico() {
		return diagnostico;
	}
	public int getIdPaciente() {
		return idPaciente;
	}
	public int getIdMedico() {
		return idMedico;
	}
	public int getIdPractica() {
		return idPractica;
	}
	public int getIdTipoPractica() {
		return idTipoPractica;
	}
	public void setIdAutorizacion(int idAutorizacion) {
		this.idAutorizacion = idAutorizacion;
	}
	public void setFechaAutorizacion(Timestamp fechaAutorizacion) {
		this.fechaAutorizacion = fechaAutorizacion;
	}
	public void setCantidadSesiones(int cantidadSesiones) {
		this.cantidadSesiones = cantidadSesiones;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}
	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}
	public void setIdPractica(int idPractica) {
		this.idPractica = idPractica;
	}
	public void setIdTipoPractica(int idTipoPractica) {
		this.idTipoPractica = idTipoPractica;
	}
	
	
	
	
	
}
