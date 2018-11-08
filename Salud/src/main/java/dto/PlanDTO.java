package dto;

import java.util.ArrayList;
import java.util.List;

public class PlanDTO {

	private int idPlan;
	private String nombrePlan;
	private List<CoberturaDTO> coberturas;

	public PlanDTO(String nombrePlan) {
		setNombrePlan(nombrePlan);
		this.coberturas = new ArrayList<>();
	}

	public PlanDTO(int idPlan, String nombrePlan) {
		setIdPlan(idPlan);
		setNombrePlan(nombrePlan);
		this.coberturas = new ArrayList<>();
	}

	public int getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(int idPlan) {
		this.idPlan = idPlan;
	}

	public String getNombrePlan() {
		return nombrePlan;
	}

	public void setNombrePlan(String tipoPlan) {
		this.nombrePlan = tipoPlan;
	}

	public List<CoberturaDTO> getCobertura() {
		return coberturas;
	}

	public void setCobertura(List<CoberturaDTO> cobertura) {
		this.coberturas = cobertura;
	}

	public void agregarCobertura(CoberturaDTO cobertura) {
		if (!this.coberturas.contains(cobertura))
			this.coberturas.add(cobertura);
	}
	
	public void quitarCobertura(CoberturaDTO cobertura){
		this.coberturas.remove(cobertura);
	}
	
	@Override
	public String toString(){
		return this.getNombrePlan();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombrePlan == null) ? 0 : nombrePlan.hashCode());
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
		PlanDTO other = (PlanDTO) obj;
		if (nombrePlan == null) {
			if (other.nombrePlan != null)
				return false;
		} else if (!nombrePlan.equals(other.nombrePlan))
			return false;
		return true;
	}

}
