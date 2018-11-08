package persistencia.dao.interfaz;

import java.util.List;

import dto.ObraSocialDTO;

public interface ObraSocialDAO  {

	public boolean insert(ObraSocialDTO obra);

	public boolean delete(ObraSocialDTO obra);
	
	public boolean update(ObraSocialDTO obra);
	
	public List<ObraSocialDTO> readAll();	
	
}
