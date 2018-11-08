package persistencia.dao.interfaz;

import java.util.List;

import dto.PracticaDTO;
import dto.TurnoDTO;


public interface PracticasDelTurnoDAO {

	public boolean insert(PracticaDTO practica, TurnoDTO turno);

	public boolean delete(PracticaDTO practica, TurnoDTO turno);
	
	public List<PracticaDTO> readAll(TurnoDTO turno);

	boolean update(TurnoDTO turno, PracticaDTO practica);
	
}
