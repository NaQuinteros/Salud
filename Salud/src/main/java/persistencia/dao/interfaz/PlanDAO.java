package persistencia.dao.interfaz;

import java.util.List;

import dto.ObraSocialDTO;
import dto.PlanDTO;

public interface PlanDAO {

	public boolean insert(PlanDTO plan, ObraSocialDTO obra);

	public boolean delete(PlanDTO plan);

	public boolean update(PlanDTO plan);

	public List<PlanDTO> readAll(ObraSocialDTO obra);

}
