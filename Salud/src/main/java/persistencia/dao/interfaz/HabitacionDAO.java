package persistencia.dao.interfaz;

import java.util.List;

import dto.HabitacionDTO;

public interface HabitacionDAO {

	public boolean insert(HabitacionDTO habitacion);

	public boolean delete(HabitacionDTO habitacion);

	public boolean update(HabitacionDTO habitacion);
	
	List<HabitacionDTO> readAll();

}
