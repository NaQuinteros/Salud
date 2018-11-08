package dto;

public class CorreoDTO {

	public CorreoDTO(String usuarioCorreo, String password, String destino, String asunto, String mensaje) {
		this.usuarioCorreo = usuarioCorreo;
		this.password = password;
		this.destino = destino;
		this.asunto = asunto;
		this.mensaje = mensaje;
	}
	

	public CorreoDTO(int id, String usuarioCorreo, String password) {
		this.id = id;
		this.usuarioCorreo = usuarioCorreo;
		this.password = password;
	}
	
	int id;
	String usuarioCorreo;
	String password;
	String destino;
	String asunto;
	String mensaje;

	public String getUsuarioCorreo() {
		return usuarioCorreo;
	}

	public void setUsuarioCorreo(String usuarioCorreo) {
		this.usuarioCorreo = usuarioCorreo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}