package persistencia.dao.interfaz;

import java.util.List;

import dto.PacienteDTO;
import dto.RenglonHistoriaClinicaDTO;

public interface RenglonHistoriaClinicaDAO {

	public boolean insert(RenglonHistoriaClinicaDTO renglonhistoriaclinica);
	
	public List<RenglonHistoriaClinicaDTO> readAll(PacienteDTO paciente);

}
