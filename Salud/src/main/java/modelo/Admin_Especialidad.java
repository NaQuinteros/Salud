package modelo;

import java.util.List;

import dto.EspecialidadDTO;
import persistencia.dao.interfaz.EspecialidadDAO;
import persistencia.dao.mysql.EspecialidadDAOImpl;

public class Admin_Especialidad {
	private EspecialidadDAO especialidad;

	public Admin_Especialidad() {
		especialidad = new EspecialidadDAOImpl();
	}

	public boolean agregarEspecialidad(EspecialidadDTO nuevaEspecialidad) {
		return especialidad.insert(nuevaEspecialidad);
	}

	public boolean borrarEspecialidad(EspecialidadDTO Especialidad_a_eliminar) {
		return especialidad.delete(Especialidad_a_eliminar);
	}

	public boolean editarEspecialidad(EspecialidadDTO e) {
		return especialidad.update(e);
	}

	public List<EspecialidadDTO> obtenerEspecialidades() {
		List<EspecialidadDTO> lista = especialidad.readAll();
		return lista;
	}

}
