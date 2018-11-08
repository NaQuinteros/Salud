package dto;

import java.sql.Timestamp;
import java.util.List;

public class PacienteDTO {

	private int idPaciente;
	private String nombre;
	private String apellido;
	private int dni;
	private String telefono;
	private String email;
	private ObraSocialDTO obraSocial;
	private PlanDTO plan;
	private String nombreContacto;
	private String telefonoContacto;
	private String nroAfiliado;
	private String sexo;
	private String estadoCivil;
	private String ocupacion;
	private String nacionalidad;
	private Timestamp fechaNacimiento;
	private String direccion;
	private List<PatologiaDTO> patologias;

	public List<PatologiaDTO> getPatologias() {
		return patologias;
	}

	public void setPatologias(List<PatologiaDTO> patologias) {
		this.patologias = patologias;
	}

	public PacienteDTO(int idPaciente, String nombre, String apellido, int dni, String telefono, String email,
			ObraSocialDTO obraSocial, PlanDTO plan, String nombreContacto, String telefonoContacto,
			String nroAfiliado) {
		this.idPaciente = idPaciente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.telefono = telefono;
		this.email = email;
		this.obraSocial = obraSocial;
		this.plan = plan;
		this.nombreContacto = nombreContacto;
		this.telefonoContacto = telefonoContacto;
		this.nroAfiliado = nroAfiliado;
	}

	/**
	 * ctor parametrizado
	 */
	public PacienteDTO(String nombre, String apellido, int dni, String telefono, String email, ObraSocialDTO obraSocial,
			PlanDTO plan, String nombreContacto, String telefonoContacto, String nroAfiliado) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.telefono = telefono;
		this.email = email;
		this.obraSocial = obraSocial;
		this.plan = plan;
		this.nombreContacto = nombreContacto;
		this.telefonoContacto = telefonoContacto;
		this.nroAfiliado = nroAfiliado;
	}

	/**
	 * ctor de copia
	 */
	public PacienteDTO(PacienteDTO rhs) {
		if (rhs == null) {
			throw new NullPointerException("El pciente debe ser distinto de null");
		}

		setIdPaciente(0);
		setNombre(rhs.getNombre());
		setApellido(rhs.getApellido());
		setDni(rhs.getDni());
		setTelefono(rhs.getTelefono());
		setEmail(rhs.getEmail());
		setObraSocial(rhs.getObraSocial());
		setPlan(rhs.getPlan());
		setNombreContacto(rhs.getNombreContacto());
		setTelefonoContacto(rhs.getTelefonoContacto());
		setNroAfiliado(rhs.getNroAfiliado());
	}

	public PacienteDTO() {

	}

	public int getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ObraSocialDTO getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(ObraSocialDTO obraSocial) {
		this.obraSocial = obraSocial;
	}

	public PlanDTO getPlan() {
		return plan;
	}

	public void setPlan(PlanDTO plan) {
		this.plan = plan;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getTelefonoContacto() {
		return telefonoContacto;
	}

	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	public String getNroAfiliado() {
		return nroAfiliado;
	}

	public void setNroAfiliado(String nroAfiliado) {
		this.nroAfiliado = nroAfiliado;
	}

	@Override
	public boolean equals(Object othr) {
		boolean ret = false;
		if (othr instanceof PacienteDTO) {
			PacienteDTO temp = (PacienteDTO) othr;
			if (this.getDni() == temp.getDni()) {
				ret = true;
			}
		}
		return ret;
	}

	@Override
	public String toString() {
		return this.nombre + " " + this.apellido + " DNI: " + this.dni;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Timestamp getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Timestamp fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
