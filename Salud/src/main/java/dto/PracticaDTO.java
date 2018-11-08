package dto;

import java.sql.Date;

public class PracticaDTO implements Comparable<PracticaDTO> {
	private int codPractica;
	private ModuloDTO modulo;
	private String descripcionPractica;
	private String honorarios;
	private String nivelAutoraziacion;
	private Autorizacion autorizacion;
	private Date fechaAutorizacion;
	private Integer sesiones;
	private Integer codigoAutorizacion;

	public enum Autorizacion {
		AUTORIZADA("Autorizada"), REQUIERE_AUTORIZACION("Requiere Autorización"), REQUIERE_PAGO(
				"Requiere Pago"), ABONADA("Abonada");
		private String nombreEstado;

		private Autorizacion(String nombreEstado) {
			this.nombreEstado = nombreEstado;
		}

		@Override
		public String toString() {
			return nombreEstado;
		}

	};

	public PracticaDTO(int codigo, ModuloDTO modulo, String descripcion, String honorario, Autorizacion autorizacion) {
		this.codPractica = codigo;
		this.modulo = modulo;
		this.descripcionPractica = descripcion;
		this.honorarios = honorario;
		this.autorizacion = autorizacion;

	}

	public PracticaDTO(ModuloDTO modulo, String descripcion, String honorarios) {
		this.modulo = modulo;
		this.descripcionPractica = descripcion;
		this.honorarios = honorarios;
	}

	public PracticaDTO(int int1, ModuloDTO modulo2, String string, String string2) {
		this.codPractica = int1;
		this.modulo = modulo2;
		this.descripcionPractica = string;
		this.honorarios = string2;
	}

	public int getCodPractica() {
		return codPractica;
	}

	public void setCodPractica(int codPractica) {
		this.codPractica = codPractica;
	}

	public ModuloDTO getModulo() {
		return modulo;
	}

	public void setModulo(ModuloDTO modulo) {
		this.modulo = modulo;
	}

	public String getDescripcionPractica() {
		return descripcionPractica;
	}

	public void setDescripcionPractica(String descripcionPractica) {
		this.descripcionPractica = descripcionPractica;
	}

	public String getHonorarios() {
		return honorarios;
	}

	public void setHonorarios(String honorarios) {
		this.honorarios = honorarios;
	}

	@Override
	public String toString() {
		return descripcionPractica;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codPractica;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PracticaDTO other = (PracticaDTO) obj;
		if (codPractica != other.codPractica)
			return false;
		return true;
	}

	public void autorizar(Date fecha, Integer codigo) {
		if (fecha != null)
			this.autorizacion = Autorizacion.AUTORIZADA;
		this.setFechaAutorizacion(fecha);
		this.setCodigoAutorizacion(codigo);
	}

	public void pagar(Date fecha) {
		this.setFechaAutorizacion(fecha);
		if (fecha != null)
			this.autorizacion = Autorizacion.AUTORIZADA;
	}

	public Date getFechaAutorizacion() {
		return fechaAutorizacion;
	}

	public void setFechaAutorizacion(Date fechaAutorizacion) {
		this.fechaAutorizacion = fechaAutorizacion;
	}

	public String getNivelAutoraziacion() {
		return nivelAutoraziacion;
	}

	public Autorizacion getAutorizacion() {
		return autorizacion;
	}

	public void setNivelAutoraziacion(String nivelAutoraziacion) {
		this.nivelAutoraziacion = nivelAutoraziacion;
		if (nivelAutoraziacion == null || nivelAutoraziacion.equals(""))
			this.autorizacion = Autorizacion.REQUIERE_PAGO;
		else
			this.autorizacion = Autorizacion.REQUIERE_AUTORIZACION;
	}

	public void setAutorizacion(Autorizacion autorizacion) {
		this.autorizacion = autorizacion;
	}

	@Override
	public int compareTo(PracticaDTO that) {
		return this.descripcionPractica.compareTo(that.getDescripcionPractica());
	}

	public Integer getSesiones() {
		return sesiones;
	}

	public void setSesiones(Integer sesiones) {
		if (sesiones == null)
			this.sesiones = 0;
		else
			this.sesiones = sesiones;
	}

	public Integer getCodigoAutorizacion() {
		return codigoAutorizacion;
	}

	public void setCodigoAutorizacion(Integer codigoAutorizacion) {
		this.codigoAutorizacion = codigoAutorizacion;
	}

}
