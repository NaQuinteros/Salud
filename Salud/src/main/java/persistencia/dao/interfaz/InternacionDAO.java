package persistencia.dao.interfaz;

import java.util.List;

import dto.InternacionDTO;

public interface InternacionDAO {

	public boolean insert(InternacionDTO recepcion);

	public boolean delete(InternacionDTO recepcion);
	
	public boolean update(InternacionDTO recepcion);
	
	public List<InternacionDTO> readAll();
}
