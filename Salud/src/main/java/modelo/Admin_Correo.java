package modelo;

import dto.CorreoDTO;
import persistencia.dao.interfaz.CorreoDAO;
import persistencia.dao.mysql.CorreoDAOImpl;

public class Admin_Correo {
	private CorreoDAO correo;

	public Admin_Correo() {
		correo = new CorreoDAOImpl();
	}

	public boolean agregarCorreo(CorreoDTO c) {
		return correo.insert(c);
	}

	public boolean borrarCorreo(CorreoDTO c) {
		return correo.delete(c);
	}

	public boolean editarCorreo(CorreoDTO c) {
		return correo.update(c);
	}

	public CorreoDTO obtenerEnviador() {
		CorreoDTO c = correo.read();
		return c;
	}

}
