package persistencia.dao.interfaz;

import java.util.List;

import dto.EspecialidadDTO;

public interface EspecialidadDAO  {

	public boolean insert(EspecialidadDTO especialidad);

	public boolean delete(EspecialidadDTO especialidad);
	
	public boolean update(EspecialidadDTO especialidad);
	
	public List<EspecialidadDTO> readAll();	
	
}
