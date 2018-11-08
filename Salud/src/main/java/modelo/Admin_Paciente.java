package modelo;

import java.util.List;

import dto.PacienteDTO;
import persistencia.dao.interfaz.PacienteDAO;
import persistencia.dao.mysql.PacienteDAOImpl;

public class Admin_Paciente {

	private PacienteDAO paciente;

	public Admin_Paciente() {
		paciente = new PacienteDAOImpl();
	}

	public void agregarPaciente(PacienteDTO p) {
		paciente.insert(p);
	}

	public void borrarPaciente(PacienteDTO p) {
		paciente.delete(p);
	}
	
	public void editarPaciente(PacienteDTO p) {
		paciente.update(p);
	}

	public List<PacienteDTO> obtenerPacientes() {
		List<PacienteDTO> lista = paciente.readAll();
		return lista;
	}
	
	public PacienteDTO obtenerPacienteById(int idPaciente){
		List<PacienteDTO> lista = paciente.readAll();
		for(PacienteDTO aux : lista){
			if(aux.getIdPaciente()==idPaciente)
				return aux;
		}
		return null;
	}

}
