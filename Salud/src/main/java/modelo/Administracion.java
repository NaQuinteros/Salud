package modelo;

import java.util.ArrayList;
import java.util.List;

import dto.AdministradorDTO;
import dto.ContableDTO;
import dto.InternacionDTO;
import dto.MedicoDTO;
import dto.RecepcionDTO;
import persistencia.dao.interfaz.AdministradorDAO;
import persistencia.dao.interfaz.ContableDAO;
import persistencia.dao.interfaz.InternacionDAO;
import persistencia.dao.interfaz.MedicoDAO;
import persistencia.dao.interfaz.RecepcionDAO;
import persistencia.dao.mysql.AdministradorDAOImpl;
import persistencia.dao.mysql.ContableDAOImpl;
import persistencia.dao.mysql.InternacionDAOImpl;
import persistencia.dao.mysql.MedicoDAOImpl;
import persistencia.dao.mysql.RecepcionDAOImpl;

public class Administracion {

	private MedicoDAO _medico;
	private RecepcionDAO _recepcion;
	private AdministradorDAO _administrador;
	private ContableDAO _contable;
	private InternacionDAO personalI;
	
	public Administracion() {
		personalI = new InternacionDAOImpl();
		_medico = new MedicoDAOImpl();
		_recepcion = new RecepcionDAOImpl();
		_contable = new ContableDAOImpl();
		_administrador = new AdministradorDAOImpl();
	}
	
	public void crearContable(ContableDTO contable) {
		_contable.insert(contable);
	}
	
	public void borrarContable(ContableDTO contable) {
		_contable.delete(contable);
	}
	
	public ArrayList<ContableDTO> obtenerContables() {
		return (ArrayList<ContableDTO>) _contable.readAll();
	}
	
	public void editarContable(ContableDTO contable) {
		_contable.update(contable);
	}

	public void crearAdministrador(AdministradorDTO admin) {
		_administrador.insert(admin);
	}

	public void borrarAdministrador(AdministradorDTO admin) {
		_administrador.delete(admin);
	}
	
	public ArrayList<AdministradorDTO> obtenerAdministradores() {
		return _administrador.readAll();
	}
	
	public void editarAdministrador(AdministradorDTO admin) {
		_administrador.update(admin);
	}
	
	public void crearMedico(MedicoDTO medico) {
		_medico.insert(medico);
	}
	
	public boolean borrarMedico(MedicoDTO medico) {
		return _medico.delete(medico);
	}
	
	public void editarMedico(MedicoDTO medico) {
		_medico.update(medico);
	}
	
	public void crearRecepcion(RecepcionDTO recepcion) {
		_recepcion.insert(recepcion);
	}
	
	public void borrarRecepcion(RecepcionDTO recepcion) {
		_recepcion.delete(recepcion);
	}
	
	public void editarRecepcion(RecepcionDTO recepcion) {
		_recepcion.update(recepcion);
	}
	
	public List<RecepcionDTO> obtenerRecepcion() {
		return _recepcion.readAll();
	}
	
	public List<MedicoDTO> obtenerMedicos() {
		return _medico.readAll();
	}
	
	public void crearPersonalInternacion(InternacionDTO internacion){
		personalI.insert(internacion);
	}
	
	public void editarPersonalInternacion(InternacionDTO internacion){
		personalI.update(internacion);
	}
	
	public void borrarPersonalInternacion(InternacionDTO internacion){
		personalI.delete(internacion);
	}
	
	public List<InternacionDTO> obtenerPersonalInternacion(){
		return personalI.readAll();
	}
	
}
