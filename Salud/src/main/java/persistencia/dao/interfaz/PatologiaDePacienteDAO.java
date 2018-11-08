package persistencia.dao.interfaz;

import java.util.List;

import dto.PacienteDTO;
import dto.PatologiaDTO;

public interface PatologiaDePacienteDAO {

	List<PatologiaDTO> readAll(PacienteDTO p);

	boolean delete(PatologiaDTO patologia, PacienteDTO paciente);

	boolean insert(PatologiaDTO patologia, PacienteDTO paciente);


}
