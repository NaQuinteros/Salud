package modelo;

import java.util.ArrayList;
import java.util.List;

import dto.InternacionesDTO;
import dto.MedicoDTO;
import dto.PracticaDTO;
import persistencia.dao.interfaz.InternacionesDAO;
import persistencia.dao.interfaz.PracticasDeInternacionDAO;
import persistencia.dao.mysql.InternacionesDAOImpl;
import persistencia.dao.mysql.PracticasDeInternacionDAOImpl;

public class Admin_Internaciones {
	private InternacionesDAO reserva;
	private PracticasDeInternacionDAO practicas;

	public Admin_Internaciones() {
		this.reserva = new InternacionesDAOImpl();
		this.practicas = new PracticasDeInternacionDAOImpl();
	}

	public List<InternacionesDTO> obtenerInternaciones() {
		return this.reserva.readAll();
	}

	public void agregarInternacion(InternacionesDTO reserva) {
		this.reserva.insert(reserva);
	}

	public void eliminarInternacion(InternacionesDTO reserva) {
		this.reserva.delete(reserva);
	}

	public void agregarPracticaEnInternacion(PracticaDTO practicaSeleccionadaCombo, InternacionesDTO internacion, MedicoDTO medico) {
		this.practicas.insert(practicaSeleccionadaCombo, internacion, medico);

	}

	public boolean autorizarPracticaEnInternacion(PracticaDTO practica, InternacionesDTO internacion) {
		return practicas.update(internacion, practica);

	}

	public boolean borrarPracticaDeInternacion(PracticaDTO practica, InternacionesDTO internacion) {
		return practicas.delete(practica, internacion);
	}

	public List<PracticaDTO> obtenerPracticasDeInternacion(InternacionesDTO hab) {
		return this.practicas.readAll(hab);
	}

	public String motivoToString(int motivoId) {
		if (motivoId == 0)
			return "";
		if (motivoId == 1)
			return "Alta Medica";
		if (motivoId == 2)
			return "Obito";
		if (motivoId == 3)
			return "Traslado";
		return "Fuga";
	}

	public List<InternacionesDTO> obtenerInternacionesDePaciente(int idPaciente) {
		List<InternacionesDTO> ret = new ArrayList<InternacionesDTO>();
		List<InternacionesDTO> lista = this.obtenerInternaciones();
		for (InternacionesDTO aux : lista)
			if (aux.getIdPaciente() == idPaciente)
				ret.add(aux);
		return ret;
	}

	public InternacionesDTO internacionById(int idInt) {
		List<InternacionesDTO> lista = this.obtenerInternaciones();
		for (InternacionesDTO aux : lista)
			if (aux.getId() == idInt)
				return aux;
		return null;
	}

	public void updateInternacion(InternacionesDTO aux) {
		reserva.update(aux);
		return;
	}

	public MedicoDTO obtenerMedicoDePractica(InternacionesDTO internacion, PracticaDTO practica) {
		return practicas.getMedico(internacion, practica);
	}

}
