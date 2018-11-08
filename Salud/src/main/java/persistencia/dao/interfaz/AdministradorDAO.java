package persistencia.dao.interfaz;

import java.util.ArrayList;

import dto.AdministradorDTO;

public interface AdministradorDAO {

	boolean insert(AdministradorDTO admin);

	boolean delete(AdministradorDTO admin);

	boolean update(AdministradorDTO admin);
	
	ArrayList<AdministradorDTO> readAll();

}
