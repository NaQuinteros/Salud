package persistencia.dao.interfaz;

import java.util.List;

import dto.PacienteDTO;
import dto.PracticaDTO;

public interface SesionesDAO {

	public boolean insert(PacienteDTO paciente, PracticaDTO practica, int sesiones);

	public boolean delete(PacienteDTO paciente, PracticaDTO practica);

	public Integer readSesiones(PacienteDTO paciente, PracticaDTO practica);
	
	public List<Integer> readPracticas(PacienteDTO paciente);

	public boolean update(PacienteDTO paciente, PracticaDTO practica, int sesiones);

	
}
