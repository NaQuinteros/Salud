package persistencia.dao.interfaz;

import java.util.List;

import dto.RecepcionDTO;

public interface RecepcionDAO {

	public boolean insert(RecepcionDTO recepcion);

	public boolean delete(RecepcionDTO recepcion);
	
	public boolean update(RecepcionDTO recepcion);
	
	public List<RecepcionDTO> readAll();
	
}
