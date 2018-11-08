package modelo;

import java.util.List;

import dto.PacienteDTO;
import dto.PatologiaDTO;
import persistencia.dao.interfaz.PatologiaDAO;
import persistencia.dao.interfaz.PatologiaDePacienteDAO;
import persistencia.dao.mysql.PatologiaDAOImpl;
import persistencia.dao.mysql.PatologiaDePacienteDAOImpl;

public class Admin_Patologias {
	
	private PatologiaDAO patologiaDAO;
	private PatologiaDePacienteDAO PatologiaDePacienteDAO;
	
	public Admin_Patologias()
	{
		patologiaDAO = new PatologiaDAOImpl();
		PatologiaDePacienteDAO = new PatologiaDePacienteDAOImpl();
	}
	
	public List<PatologiaDTO> obtenerPatologias()
	{
		List<PatologiaDTO> patologias = patologiaDAO.readAll();
		return patologias;
	}
	
	public List<PatologiaDTO> obtenerPatologiasDelPaciente(PacienteDTO p){
		return PatologiaDePacienteDAO.readAll(p);
	}
	
	public boolean borrarPatologiaDePaciente(PatologiaDTO patologia, PacienteDTO paciente){
		return PatologiaDePacienteDAO.delete(patologia, paciente);
	}
	
	public boolean agregarPatologiaEnPaciente(PatologiaDTO patologia, PacienteDTO paciente){
		return PatologiaDePacienteDAO.insert(patologia, paciente);
	}

}
