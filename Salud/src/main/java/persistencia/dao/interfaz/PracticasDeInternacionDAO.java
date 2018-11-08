package persistencia.dao.interfaz;

import java.util.List;

import dto.InternacionesDTO;
import dto.MedicoDTO;
import dto.PracticaDTO;

public interface PracticasDeInternacionDAO {

	List<PracticaDTO> readAll(InternacionesDTO internacion);

	boolean delete(PracticaDTO practica, InternacionesDTO internacion);

	boolean update(InternacionesDTO turno, PracticaDTO practica);

	MedicoDTO getMedico(InternacionesDTO internacion, PracticaDTO practica);

	boolean insert(PracticaDTO practica, InternacionesDTO internacion, MedicoDTO medico);

}
