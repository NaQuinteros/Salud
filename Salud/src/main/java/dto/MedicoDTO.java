package dto;

public class MedicoDTO {

	private int _idMedico;
	private String _nombre;
	private String _nombreUsuario;
	private String _password;
	private EspecialidadDTO _especialidad;
	private int _intervaloTurno;
	private int _dni;
	private String _matricula;

	public MedicoDTO(MedicoDTO other) {
		this.set_nombre(other.get_nombre());
		this.set_especialidad(other.get_especialidad());
		this.set_nombreUsuario(other.get_nombreUsuario());
		this.set_password(other.get_password());
		this.set_intervaloTurno(other.get_intervaloTurno());
	}

	public MedicoDTO(String nombre, String nombreUsuario, String password, EspecialidadDTO especialidad) {
		this._nombre = nombre;
		this._nombreUsuario = nombreUsuario;
		this._especialidad = especialidad;
		this._password = password;
		this._intervaloTurno = 0;
	}

	public MedicoDTO(int idMedico, String nombre, String nombreUsuario, String password, EspecialidadDTO especialidad,
			int intervalo) {
		this._idMedico = idMedico;
		this._nombre = nombre;
		this._nombreUsuario = nombreUsuario;
		this._especialidad = especialidad;
		this._password = password;
		this._intervaloTurno = intervalo;
	}

	public int get_idMedico() {
		return this._idMedico;
	}

	public void set_idMedico(int _idMedico) {
		this._idMedico = _idMedico;
	}

	public EspecialidadDTO get_especialidad() {
		return _especialidad;
	}

	public void set_especialidad(EspecialidadDTO _especialidad) {
		this._especialidad = _especialidad;
	}

	public String get_nombreUsuario() {
		return _nombreUsuario;
	}

	public void set_nombreUsuario(String _nombreUsuario) {
		this._nombreUsuario = _nombreUsuario;
	}

	public String get_password() {
		return _password;
	}

	public void set_password(String _password) {
		this._password = _password;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof MedicoDTO) {
			MedicoDTO tmp = (MedicoDTO) obj;
			return tmp.get_idMedico() == this.get_idMedico();
		} else
			return false;
	}

	@Override
	public String toString() {
		return this._nombre + " " + this._matricula;
	}

	public String get_nombre() {
		return _nombre;
	}

	public void set_nombre(String _nombre) {
		this._nombre = _nombre;
	}

	public Integer get_intervaloTurno() {
		Integer intervalo = new Integer(this._intervaloTurno);
		return intervalo;
	}

	public void set_intervaloTurno(int _intervaloTurno) {
		this._intervaloTurno = _intervaloTurno;
	}

	/**
	 * @return the _dni
	 */
	public int get_dni() {
		return _dni;
	}

	/**
	 * @param _dni
	 *            the _dni to set
	 */
	public void set_dni(int _dni) {
		this._dni = _dni;
	}

	/**
	 * @return the _matricula
	 */
	public String get_matricula() {
		return _matricula;
	}

	/**
	 * @param _matricula
	 *            the _matricula to set
	 */
	public void set_matricula(String _matricula) {
		this._matricula = _matricula;
	}
}
