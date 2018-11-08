package dto;

public class RenglonInformeObraSocialDTO {
	private int codigoPractica;
	private String descripcionPractica;
	private String paciente;
	private String medico;
	private double monto;

	public RenglonInformeObraSocialDTO(int codigoPractica, String descripcionPractica, String paciente, String osYPlan,
			double monto) {
		this.codigoPractica = codigoPractica;
		this.descripcionPractica = descripcionPractica;
		this.paciente = paciente;
		this.medico = osYPlan;
		this.monto = monto;
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

	public String getMedico() {
		return medico;
	}

	public void setMedico(String medico) {
		this.medico = medico;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}


}
