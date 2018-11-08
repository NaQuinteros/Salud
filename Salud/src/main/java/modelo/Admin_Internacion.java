package modelo;

import java.util.List;

import dto.InternacionDTO;
import persistencia.dao.interfaz.InternacionDAO;
import persistencia.dao.mysql.InternacionDAOImpl;

public class Admin_Internacion {
 
	private InternacionDAO personalI;
	
	public Admin_Internacion(){
		this.personalI = new InternacionDAOImpl();
	}
	
	public void crearPersonalInternacion(InternacionDTO internacion){
		personalI.insert(internacion);
	}
	
	public void eliminarPersonalInternacion(InternacionDTO internacion){
		personalI.delete(internacion);
	}
	
	public List<InternacionDTO> obtenerPersonalInternacion(){
		return personalI.readAll();
		
	}
}
