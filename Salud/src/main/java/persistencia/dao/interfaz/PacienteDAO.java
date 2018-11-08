package persistencia.dao.interfaz;

import java.util.List;

import dto.PacienteDTO;


public interface PacienteDAO {

	public boolean insert(PacienteDTO paciente);

	public boolean delete(PacienteDTO paciente);
	
	public boolean update(PacienteDTO paciente);
	
	public List<PacienteDTO> readAll();
	
}
