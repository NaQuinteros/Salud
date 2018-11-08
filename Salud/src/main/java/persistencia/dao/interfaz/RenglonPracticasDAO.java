package persistencia.dao.interfaz;

import java.util.List;

import dto.PracticaDTO;
import dto.RenglonHistoriaClinicaDTO;

public interface RenglonPracticasDAO {

	public boolean insert(PracticaDTO practica, RenglonHistoriaClinicaDTO renglon);

	public List<PracticaDTO> readAll(RenglonHistoriaClinicaDTO renglon);
	
}
