package persistencia.dao.interfaz;

import java.util.List;

import dto.PracticaDTO;


public interface PracticaDAO {

	public boolean insert(PracticaDTO paciente);

	public boolean delete(PracticaDTO paciente);
	
	public boolean update(PracticaDTO paciente);
	
	public List<PracticaDTO> readAll();
	
}
