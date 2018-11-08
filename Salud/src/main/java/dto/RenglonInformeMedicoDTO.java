package dto;

public class RenglonInformeMedicoDTO {
	private int codigoPractica;
	private String descripcionPractica;
	private String paciente;
	private String osYPlan;
	private double arancel;

	public RenglonInformeMedicoDTO(int codigoPractica, String descripcionPractica, String paciente, String osYPlan,
			double arancel) {
		this.codigoPractica = codigoPractica;
		this.descripcionPractica = descripcionPractica;
		this.paciente = paciente;
		this.osYPlan = osYPlan;
		this.arancel = arancel;
	}

	public int getCodigoPractica() {
		return codigoPractica;
	}

	public void setCodigoPractica(int codigoPractica) {
		this.codigoPractica = codigoPractica;
	}

	public String getDescripcionPractica() {
		return descripcionPractica;
	}

	public void setDescripcionPractica(String descripcionPractica) {
		this.descripcionPractica = descripcionPractica;
	}

	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	public String getOsYPlan() {
		return osYPlan;
	}

	public void setOsYPlan(String osYPlan) {
		this.osYPlan = osYPlan;
	}

	public double getArancel() {
		return arancel;
	}

	public void setArancel(double arancel) {
		this.arancel = arancel;
	}
}
