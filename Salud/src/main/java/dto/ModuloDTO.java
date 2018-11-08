package dto;

public class ModuloDTO implements Comparable<ModuloDTO> {
	int codModulo;
	String descripcionModulo;

	public ModuloDTO(int codigo, String descripcion)  {
		this.codModulo = codigo;
		this.descripcionModulo = descripcion;
	}
	
	public ModuloDTO(String descripcion) {
		this.descripcionModulo = descripcion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codModulo;
		result = prime * result + ((descripcionModulo == null) ? 0 : descripcionModulo.hashCode());
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
		ModuloDTO other = (ModuloDTO) obj;
		if (codModulo != other.codModulo)
			return false;
		if (descripcionModulo == null) {
			if (other.descripcionModulo != null)
				return false;
		} else if (!descripcionModulo.equals(other.descripcionModulo))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return this.descripcionModulo;
	}

	public int getCodModulo() {
		return codModulo;
	}

	public void setCodModulo(int codModulo) {
		this.codModulo = codModulo;
	}

	public String getDescripcionModulo() {
		return descripcionModulo;
	}

	public void setDescripcionModulo(String descripcionModulo) {
		this.descripcionModulo = descripcionModulo;
	}

	@Override
	public int compareTo(ModuloDTO that) {
			return this.descripcionModulo.compareTo(that.getDescripcionModulo());
		
	}
}
