package modelo;

import java.util.List;

import dto.PracticaDTO;
import dto.TurnoDTO;
import persistencia.dao.interfaz.PracticasDelTurnoDAO;
import persistencia.dao.interfaz.TurnoDAO;
import persistencia.dao.mysql.PracticasDelTurnoDAOImpl;
import persistencia.dao.mysql.TurnoDAOImpl;

public class Admin_Turno
{
	
	private TurnoDAO turno;
	private PracticasDelTurnoDAO practicas;
	
	public Admin_Turno()
	{
		turno = new TurnoDAOImpl();
		practicas = new PracticasDelTurnoDAOImpl();
	}
	
	public void agregarTurno(TurnoDTO nuevoTurno)
	{
		turno.insert(nuevoTurno);
	}

	public void borrarTurno(TurnoDTO turno_a_eliminar) 
	{
		turno.delete(turno_a_eliminar);
	}
	
	public void actualizarTurno(TurnoDTO nuevoTurno){
		turno.update(nuevoTurno);
	}
	
	public List<TurnoDTO> obtenerTurnos()
	{
		List<TurnoDTO> lista = turno.readAll();
		for(TurnoDTO t : lista){
			t.setPracticas(obtenerPracticasDelTurno(t));
		}
		return lista;
	}
	
	public List<PracticaDTO> obtenerPracticasDelTurno(TurnoDTO t){
		return practicas.readAll(t);
	}
	
	public boolean borrarPracticaDelTurno(PracticaDTO p, TurnoDTO t){
		return practicas.delete(p, t);
	}
	
	public boolean agregarPracticaEnTurno(PracticaDTO p, TurnoDTO t){
		return practicas.insert(p, t);
	}
	
	public boolean autorizarPracticaEnTurno(PracticaDTO p, TurnoDTO t){
		return practicas.update(t, p);
	}
}
