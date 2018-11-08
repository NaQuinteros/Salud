package dto;

public class AdministradorDTO {

	private String nombre;
	private String usuario;
	private String pass;
	private int id;

	public AdministradorDTO(String nombre, String usuario, String pass) {
		super();
		this.nombre = nombre;
		this.usuario = usuario;
		this.pass = pass;
	}

	public AdministradorDTO(int id, String nombre, String usuario, String pass) {
		super();
		this.nombre = nombre;
		this.usuario = usuario;
		this.pass = pass;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
