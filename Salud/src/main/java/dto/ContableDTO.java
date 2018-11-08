package dto;

public class ContableDTO {

	private int _idContable;
	private String _nombreUsuario;
	private String _password;
	private String _nombre;

	public ContableDTO(String _nombre, String _nombreUsuario, String _password) {
		this._nombreUsuario = _nombreUsuario;
		this._password = _password;
		this._nombre = _nombre;
	}

	public ContableDTO(int _idContable, String _nombre, String _nombreUsuario, String _password) {
		this._idContable = _idContable;
		this._nombreUsuario = _nombreUsuario;
		this._password = _password;
		this._nombre = _nombre;
	}

	public int get_idContable() {
		return _idContable;
	}

	public void set_idContable(int _idContable) {
		this._idContable = _idContable;
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
