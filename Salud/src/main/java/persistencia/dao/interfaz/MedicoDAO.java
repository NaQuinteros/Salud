package persistencia.dao.interfaz;

import java.util.List;

import dto.MedicoDTO;

public interface MedicoDAO {
	
	public boolean insert(MedicoDTO medico);

	public boolean delete(MedicoDTO medico);
	
	public boolean update(MedicoDTO medico);
	
	public List<MedicoDTO> readAll();

	
}
