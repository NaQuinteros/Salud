package dto;

public class CoberturaDTO {
	private int practica;
	private int porcentaje;
	private boolean autorizacion;
	
	public CoberturaDTO(int practica, int porcentaje, boolean autorizacion){
		this.practica = practica;
		this.porcentaje = porcentaje;
		this.autorizacion = autorizacion;
	}

	public int getCodPractica() {
		return practica;
	}

	public void setPractica(int practica) {
		this.practica = practica;
	}

	public int getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}

	public boolean requiereAutorizacion() {
		return autorizacion;
	}

	public void setAutorizacion(boolean autorizacion) {
		this.autorizacion = autorizacion;
	}

	public int getPractica() {
		return practica;
	}
}
