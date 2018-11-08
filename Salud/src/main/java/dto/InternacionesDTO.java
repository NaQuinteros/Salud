package dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import modelo.Admin_Paciente;

public class InternacionesDTO {
	private Integer idHabitacion;
	private int id;
	private int idPaciente;
	private Timestamp inicio;
	private Timestamp fin;
	private Timestamp ingresoReal;
	private Timestamp egresoReal;
	private String diagnosticoInicial;
	private String observacionInicial;
	private int motivoEgreso;
	private String diagnosticoFinal;
	private String observacionFinal;
	private String estado;
	private List<PracticaDTO> practicas;
	private Admin_Paciente admin_paciente= new Admin_Paciente();

public InternacionesDTO(int habitacion, int id, int paciente, Timestamp inicio, Timestamp fin, Timestamp ingReal, Timestamp egReal,
						String diagIni, String obsIni, int motivo,String diagFin,String obsFin, String estado){
	this.idHabitacion=habitacion;
	this.id=id;
	this.idPaciente=paciente;
	this.inicio=inicio;
	this.fin=fin;
	this.ingresoReal= ingReal;
	this.egresoReal= egReal;
	this.diagnosticoInicial=diagIni;
	this.observacionInicial=obsIni;
	this.motivoEgreso=motivo;
	this.diagnosticoFinal=diagFin;
	this.observacionFinal=obsFin;
	this.estado=estado;
	this.practicas=new ArrayList<PracticaDTO>();
}
public InternacionesDTO(int habitacion, int paciente, Timestamp inicio, Timestamp fin, Timestamp ingReal, Timestamp egReal,
		String diag, String obs, int motivo, String diagFin,String obsFin, String estado){
	this.idHabitacion=habitacion;
	this.idPaciente=paciente;
	this.inicio=inicio;
	this.fin=fin;
	this.ingresoReal= ingReal;
	this.egresoReal= egReal;
	this.diagnosticoInicial=diag;
	this.observacionInicial=obs;
	this.motivoEgreso=motivo;
	this.diagnosticoFinal=diagFin;
	this.observacionFinal=obsFin;
	this.estado=estado;
	this.practicas=new ArrayList<PracticaDTO>();
	
}

public InternacionesDTO(int habitacion, int paciente, Timestamp inicio, Timestamp fin,
		String diag, String obs, int motivo, String diagFin,String obsFin, String estado ){
	this.idHabitacion=habitacion;
	this.idPaciente=paciente;
	this.inicio=inicio;
	this.fin=fin;
	this.diagnosticoInicial=diag;
	this.observacionInicial=obs;
	this.motivoEgreso=motivo;
	this.diagnosticoFinal=diagFin;
	this.observacionFinal=obsFin;
	this.estado=estado;
	this.practicas=new ArrayList<PracticaDTO>();
}

public Integer getIdHabitacion() {
	return idHabitacion;
}

public void deleteIdHabitacion(){
	this.idHabitacion=(Integer) null;
}
public void setIdHabitacion(int idHabitacion) {
	this.idHabitacion = idHabitacion;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getIdPaciente() {
	return idPaciente;
}

public void setIdPaciente(int idPaciente) {
	this.idPaciente = idPaciente;
}

public Timestamp getInicio() {
	return inicio;
}

public void setInicio(Timestamp inicio) {
	this.inicio = inicio;
}

public Timestamp getFin() {
	return fin;
}

public void setFin(Timestamp fin) {
	this.fin = fin;
}
public Timestamp getIngresoReal() {
	return this.ingresoReal;
}
public Timestamp getEgresoReal() {
	return this.egresoReal;
}
public String getDiagnosticoInicial() {
	return this.diagnosticoInicial;
}
public String getObservacion() {
	return this.observacionInicial;
}
public int getMotivoEgreso() {
	return this.motivoEgreso;
}

public void setObservacionInicial(String observaciones) {
	observacionInicial = observaciones;
}
public void setIngresoReal(Timestamp ingresoReal) {
	this.ingresoReal = ingresoReal;
}
public void setEgresoReal(Timestamp egresoReal) {
	this.egresoReal = egresoReal;
}
public void setDiagnosticoInicial(String diagnosticoFinal) {
	diagnosticoInicial = diagnosticoFinal;
}
public void setMotivoEgreso(int motivoEgreso) {
	this.motivoEgreso = motivoEgreso;
}
public List<PracticaDTO> getPracticas() {
	return this.practicas;
}
public void setPracticas(List<PracticaDTO> practicas) {
	this.practicas = practicas;
}
public String getDiagnosticoFinal() {
	return diagnosticoFinal;
}
public void setDiagnosticoFinal(String diagnosticoFinal) {
	this.diagnosticoFinal = diagnosticoFinal;
}
public String getObservacionFinal() {
	return observacionFinal;
}
public void setObservacionFinal(String observacionFinal) {
	this.observacionFinal = observacionFinal;
}
public String getEstado() {
	return estado;
}
public void setEstado(String estado) {
	this.estado = estado;
}
public String getObservacionInicial() {
	return observacionInicial;
}

public PacienteDTO getPaciente(){
	return admin_paciente.obtenerPacienteById(idPaciente);
}
}
