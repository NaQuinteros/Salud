package modelo;

import java.io.IOException;
import java.util.List;

import dto.PacienteDTO;
import dto.PracticaDTO;
import persistencia.dao.interfaz.PracticaDAO;
import persistencia.dao.interfaz.SesionesDAO;
import persistencia.dao.mysql.PracticaDAOImpl;
import persistencia.dao.mysql.SesionesDAOImpl;

public class Admin_Practica {
private PracticaDAO practica;
private SesionesDAO sesiones;
	
	public Admin_Practica() throws IOException
	{
		practica = new PracticaDAOImpl();
		sesiones = new SesionesDAOImpl();
	}
	
	public void agregarPractica(PracticaDTO nuevoPractica)
	{
		practica.insert(nuevoPractica);
	}

	public void borrarPractica(PracticaDTO practica_a_eliminar) 
	{
		practica.delete(practica_a_eliminar);
	}
	
	public List<PracticaDTO> obtenerPracticas()
	{
		List<PracticaDTO> lista = practica.readAll();
		return lista;
	}
	
	public void asignarSesiones(PacienteDTO paciente, PracticaDTO practica, int sesiones){
		this.sesiones.insert(paciente, practica, sesiones);
	}

	public void borrarSesiones(PacienteDTO paciente, PracticaDTO practica){
		this.sesiones.delete(paciente, practica);
	}
	
	public Integer obtenerSesiones(PacienteDTO paciente, PracticaDTO practica){
		return this.sesiones.readSesiones(paciente, practica);
	}
	
	public void modificarSesiones(PacienteDTO paciente, PracticaDTO practica, int sesiones){
		this.sesiones.update(paciente, practica, sesiones);
	}
	
	public List<Integer> obtenerPracticasConSesiones(PacienteDTO paciente){
		return this.sesiones.readPracticas(paciente);
	}
}
