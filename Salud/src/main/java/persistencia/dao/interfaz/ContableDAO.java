package persistencia.dao.interfaz;

import java.util.List;

import dto.ContableDTO;

public interface ContableDAO {

	public boolean insert(ContableDTO contable);

	public boolean delete(ContableDTO contable);

	public boolean update(ContableDTO contable);

	public List<ContableDTO> readAll();

}
