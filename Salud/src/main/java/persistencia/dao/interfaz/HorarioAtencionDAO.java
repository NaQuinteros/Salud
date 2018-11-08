package persistencia.dao.interfaz;

import java.util.List;

import dto.HorarioAtencionDTO;

public interface HorarioAtencionDAO {
	public boolean insert(HorarioAtencionDTO horario);

	public boolean delete(HorarioAtencionDTO horario);
	
	public boolean update(HorarioAtencionDTO horario);
	
	public List<HorarioAtencionDTO> readAll();

	public boolean delete(int horarioID);
}
