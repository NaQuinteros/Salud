package dto;

import java.util.ArrayList;
import java.util.List;

public class ObraSocialDTO {

	private int idObraSocial;
	private String nombreObraSocial;
	private List<PlanDTO> planes;

	public ObraSocialDTO(int idObraSocial, String nombreObraSocial) {
		this.idObraSocial = idObraSocial;
		this.nombreObraSocial = nombreObraSocial;
		this.planes = new ArrayList<>();
	}

	public ObraSocialDTO(String nombreObraSocial) {
		this.nombreObraSocial = nombreObraSocial;
		this.planes = new ArrayList<>();
	}

	public int getIdObraSocial() {
		return idObraSocial;
	}

	public void setIdObraSocial(int idObraSocial) {
		this.idObraSocial = idObraSocial;
	}

	public String getNombreObraSocial() {
		return nombreObraSocial;
	}

	public void setNombreObraSocial(String nombreObraSocial) {
		this.nombreObraSocial = nombreObraSocial;
	}

	public List<PlanDTO> getPlanes() {
		return planes;
	}

	public void setPlanes(List<PlanDTO> planes) {
		this.planes = planes;
	}

	public void agregarPlan(PlanDTO plan) {
		if (!this.planes.contains(plan))
			this.planes.add(plan);
	}
	
	public void quitarPlan(PlanDTO plan){
		this.planes.remove(plan);
	}
	
	@Override
	public String toString(){
		return this.getNombreObraSocial();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombreObraSocial == null) ? 0 : nombreObraSocial.hashCode());
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
		ObraSocialDTO other = (ObraSocialDTO) obj;
		if (nombreObraSocial == null) {
			if (other.nombreObraSocial != null)
				return false;
		} else if (!nombreObraSocial.equals(other.nombreObraSocial))
			return false;
		return true;
	}


}
