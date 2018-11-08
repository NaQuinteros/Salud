package persistencia.dao.interfaz;

import java.util.List;

import dto.ModuloDTO;


public interface ModuloDAO {

	public boolean insert(ModuloDTO paciente);

	public boolean delete(ModuloDTO paciente);
	
	public boolean update(ModuloDTO paciente);
	
	public List<ModuloDTO> readAll();
	
}
