package dto;

public class PatologiaDTO {

	private String idPatologia;
	private String descripcion;
	private boolean importante = false;

	public PatologiaDTO(String idPatologia, String descripcion) {
		super();
		this.idPatologia = idPatologia;
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return this.getDescripcion();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((idPatologia == null) ? 0 : idPatologia.hashCode());
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
		PatologiaDTO other = (PatologiaDTO) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (idPatologia == null) {
			if (other.idPatologia != null)
				return false;
		} else if (!idPatologia.equals(other.idPatologia))
			return false;
		return true;
	}

	public String getIdPatologia() {
		return idPatologia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public boolean isImportante() {
		return importante;
	}

	public void setImportante(boolean importante) {
		this.importante = importante;
	}

	public void setIdPatologia(String idPatologia) {
		this.idPatologia = idPatologia;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
