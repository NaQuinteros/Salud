package modelo;

import java.io.IOException;
import java.util.List;

import dto.ModuloDTO;
import persistencia.dao.interfaz.ModuloDAO;
import persistencia.dao.mysql.ModuloDAOImpl;

public class Admin_Modulo {
private ModuloDAO modulo;	
	
	public Admin_Modulo() throws IOException
	{
		modulo = new ModuloDAOImpl();
	}
	
	public void agregarModulo(ModuloDTO nuevoModulo)
	{
		modulo.insert(nuevoModulo);
	}

	public void borrarModulo(ModuloDTO Modulo_a_eliminar) 
	{
		modulo.delete(Modulo_a_eliminar);
	}
	
	public List<ModuloDTO> obtenerModulos()
	{
		List<ModuloDTO> lista = modulo.readAll();
		return lista;
	}

}
