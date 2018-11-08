package modelo;

import java.util.ArrayList;

import dto.CoberturaDTO;
import dto.ObraSocialDTO;
import dto.PlanDTO;
import dto.PracticaDTO;
import persistencia.dao.interfaz.CoberturaDAO;
import persistencia.dao.interfaz.ObraSocialDAO;
import persistencia.dao.interfaz.PlanDAO;
import persistencia.dao.mysql.CoberturaDAOImpl;
import persistencia.dao.mysql.ObraSocialDAOImpl;
import persistencia.dao.mysql.PlanDAOImpl;

public class Admin_Cobertura {

	private ObraSocialDAO obraDao;
	private PlanDAO planDao;
	private CoberturaDAO coberturaDao;

	public Admin_Cobertura() {
		obraDao = new ObraSocialDAOImpl();
		planDao = new PlanDAOImpl();
		coberturaDao = new CoberturaDAOImpl();
	}

	public ArrayList<ObraSocialDTO> obtenerObras() {
		ArrayList<ObraSocialDTO> obras = (ArrayList<ObraSocialDTO>) obraDao.readAll();
		return obras;
	}

	public boolean agregarObra(ObraSocialDTO nueva) {
		return obraDao.insert(nueva);
	}

	public boolean editarObra(ObraSocialDTO obra) {
		return obraDao.update(obra);
	}

	public boolean borrarObra(ObraSocialDTO obra) {
		return obraDao.delete(obra);
	}

	public boolean agregarPlan(PlanDTO plan, ObraSocialDTO obra) {
		return planDao.insert(plan, obra);
	}

	public boolean borrarPlan(PlanDTO plan) {
		return planDao.delete(plan);
	}
	
	public boolean agregarCobertura(CoberturaDTO cobertura, PlanDTO plan){
		return coberturaDao.insert(cobertura, plan);
	}
	
	public boolean borrarCobertura(PracticaDTO practica, PlanDTO plan){
		return coberturaDao.delete(practica, plan);
	}
	
	public boolean editarCobertura(CoberturaDTO cobertura, PlanDTO plan){
		return coberturaDao.update(cobertura, plan);
	}
}
