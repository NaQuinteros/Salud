package modelo;

import java.util.List;

import dto.PacienteDTO;
import dto.PracticaDTO;
import dto.RenglonHistoriaClinicaDTO;
import persistencia.dao.interfaz.RenglonHistoriaClinicaDAO;
import persistencia.dao.interfaz.RenglonPracticasDAO;
import persistencia.dao.mysql.RenglonHistoriaClinicaDAOImpl;
import persistencia.dao.mysql.RenglonPracticasDAOImpl;

public class Admin_RenglonHistoriaClinica {

	private RenglonHistoriaClinicaDAO dao;
	private RenglonPracticasDAO daorp;

	public Admin_RenglonHistoriaClinica() {
		dao = new RenglonHistoriaClinicaDAOImpl();
		daorp = new RenglonPracticasDAOImpl();
	}

	public boolean crearEntrada(RenglonHistoriaClinicaDTO renglon) {
		return dao.insert(renglon);
	}

	public List<RenglonHistoriaClinicaDTO> obtener(PacienteDTO paciente) {
		return dao.readAll(paciente);
	}
	
	public boolean agregarPracticaEnEntrada(PracticaDTO p, RenglonHistoriaClinicaDTO renglon) {
		return daorp.insert(p, renglon);
	}
	
	public List<PracticaDTO> leerPracticasDeEntrada(RenglonHistoriaClinicaDTO renglon) {
		return daorp.readAll(renglon);
	}

}
