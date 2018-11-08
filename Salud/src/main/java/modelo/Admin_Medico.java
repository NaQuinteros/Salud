package modelo;

import java.util.List;

import dto.MedicoDTO;
import persistencia.dao.interfaz.MedicoDAO;
import persistencia.dao.mysql.MedicoDAOImpl;

public class Admin_Medico {

	private MedicoDAO medico;

	public Admin_Medico() {
		medico = new MedicoDAOImpl();
	}

	public void agregarMedico(MedicoDTO nuevaMedico) {
		medico.insert(nuevaMedico);
	}

	public void borrarMedico(MedicoDTO medico_a_eliminar) {
		medico.delete(medico_a_eliminar);
	}

	public List<MedicoDTO> obtenerMedicos() {
		List<MedicoDTO> lista = medico.readAll();
		return lista;
	}

	public boolean editarMedico(MedicoDTO medico_a_editar) {
		return medico.update(medico_a_editar);

	}

}
