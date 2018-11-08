package modelo;

import java.util.List;

import dto.HorarioAtencionDTO;
import persistencia.dao.interfaz.HorarioAtencionDAO;
import persistencia.dao.mysql.HorarioAtencionDAOImpl;

public class Admin_Horario {
	private HorarioAtencionDAO horario;
	
	public Admin_Horario(){
		this.horario = new HorarioAtencionDAOImpl();
	}
	
	public void setHorarioDAO(HorarioAtencionDAOImpl horarioDAO) {
		this.horario = horarioDAO;
	}

	public void agregarHorario(HorarioAtencionDTO nuevoHorario)
	{
		horario.insert(nuevoHorario);
	}

	public void borrarHorario(HorarioAtencionDTO horario_a_eliminar) 
	{
		horario.delete(horario_a_eliminar);
	}

	public List<HorarioAtencionDTO> obtenerHorarios() {
		List<HorarioAtencionDTO> lista = horario.readAll();
		return lista;
	}

	public void delete(int idHorario) {
		this.horario.delete(idHorario);
	}

	

}
