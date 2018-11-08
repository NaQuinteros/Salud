package persistencia.dao.interfaz;

import java.util.List;

import dto.InternacionesDTO;

public interface InternacionesDAO {

	boolean insert(InternacionesDTO reserva);

	boolean delete(InternacionesDTO habitacion);

	List<InternacionesDTO> readAll();

	boolean update(InternacionesDTO aux);

}
