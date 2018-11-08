package persistencia.dao.interfaz;

import dto.CorreoDTO;

public interface CorreoDAO {
	
	public boolean insert(CorreoDTO c);

	public boolean delete(CorreoDTO c);

	public boolean update(CorreoDTO c);

	public CorreoDTO read();
	
}
