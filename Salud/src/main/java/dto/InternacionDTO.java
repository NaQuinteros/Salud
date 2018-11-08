package dto;

public class InternacionDTO {

	private int _idInternacion;
	private String _nombreUsuario;
	private String _password;
	private String _nombre;


	public InternacionDTO(String _nombreUsuario, String _password, String _nombre) {
		super();
		this._nombreUsuario = _nombreUsuario;
		this._password = _password;
		this._nombre = _nombre;
	}

	public InternacionDTO(int _idInternacion, String _nombreUsuario, String _password, String _nombre) {
		super();
		this._idInternacion = _idInternacion;
		this._nombreUsuario = _nombreUsuario;
		this._password = _password;
		this._nombre = _nombre;
	}

	public int get_idInternacion() {
		return _idInternacion;
	}

	public void set_idInternacion(int _idInternacion) {
		this._idInternacion = _idInternacion;
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

	public String get_nombre() {
		return _nombre;
	}

	public void set_nombre(String _nombre) {
		this._nombre = _nombre;
	}
}
