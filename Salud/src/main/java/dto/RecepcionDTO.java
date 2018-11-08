package dto;

public class RecepcionDTO {

	private int _idRecepcion;
	private String _nombreUsuario;
	private String _password;
	private String nombre;


	public RecepcionDTO(String _nombreUsuario, String _password, String nombre) {
		this._nombreUsuario = _nombreUsuario;
		this._password = _password;
		this.nombre = nombre;
	}

	public RecepcionDTO(int _idRecepcion, String _nombreUsuario, String _password, String nombre) {
		this._idRecepcion = _idRecepcion;
		this._nombreUsuario = _nombreUsuario;
		this._password = _password;
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Recepcion [_idRecepcion=" + _idRecepcion + ", _nombreUsuario=" + _nombreUsuario + ", _password="
				+ _password + "]";
	}

	public int get_idRecepcion() {
		return _idRecepcion;
	}

	public void set_idRecepcion(int _idRecepcion) {
		this._idRecepcion = _idRecepcion;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
