package persistencia.dao.interfaz;

import java.util.List;

import dto.PatologiaDTO;

public interface PatologiaDAO {

	List<PatologiaDTO> readAll();

}
