package dto;

import java.text.DecimalFormat;
import java.util.Date;

public class RenglonComprobantePractica {
	private Integer idOS;
	private int idMedico;
	private int codigo;
	private String Descripcion;
	private Date fechaAut;
	private String nombreMedico;
	private String nombrePaciente;
	private String nombreOS;
	private double montoTotal;
	private double montoCubierto;
	private String nombrePlan;
	private String periodo;
	private PacienteDTO paciente;
	DecimalFormat df = new DecimalFormat("####0.00");
	
	public RenglonComprobantePractica(Integer idObra, int idMed, int cod, String Desc, String nombreMed, String nombrePac,
			String nombreOS,String nombreP, double mTotal, double mCubierto, Date Fecha, String fechames, PacienteDTO Paciente){
		this.idOS= idObra;
		this.idMedico=idMed;
		this.codigo=cod;
		this.Descripcion= Desc;
		this.nombreMedico= nombreMed;
		this.nombrePaciente= nombrePac;
		this.nombreOS= nombreOS;
		this.nombrePlan=nombreP;
		this.montoTotal= mTotal;
		this.montoCubierto= mCubierto;
		this.fechaAut=Fecha;
		this.periodo=fechames;
		this.paciente= Paciente;
	}

	public String getPeriodo() {
		return periodo;
	}

	public Integer getIdOS() {
		return idOS;
	}

	public int getIdMedico() {
		return idMedico;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public String getNombreMedico() {
		return nombreMedico;
	}

	public String getNombreOS() {
		return nombreOS;
	}
	
	public String getNombrePlan(){
		return nombrePlan;
	}

	public double getMontoTotal() {
		return montoTotal;
	}

	public double getMontoCubierto() {
		return montoCubierto;
	}

	public String getNombrePaciente() {
		return nombrePaciente;
	}

	public Date getFechaAut() {
		return fechaAut;
	}
	public PacienteDTO getPaciente(){
		return paciente;
	}
	
}
