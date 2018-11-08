package persistencia.dao.interfaz;

import java.util.List;
import dto.TurnoDTO;

public interface TurnoDAO {

	public boolean insert(TurnoDTO turno);

	public boolean delete(TurnoDTO turno);

	public boolean update(TurnoDTO turno);
	
	public boolean deleteByID(String id);
	
	public List<TurnoDTO> readAll();
}
