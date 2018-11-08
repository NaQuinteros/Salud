package persistencia.dao.interfaz;

import java.util.List;

import dto.CoberturaDTO;
import dto.PlanDTO;
import dto.PracticaDTO;

public interface CoberturaDAO  {

	public boolean insert(CoberturaDTO cobertura, PlanDTO plan);	
	
	public boolean delete(PracticaDTO practica, PlanDTO plan);

	public List<CoberturaDTO> readAll(PlanDTO plan);

	public boolean update(CoberturaDTO cobertura, PlanDTO plan);

}
