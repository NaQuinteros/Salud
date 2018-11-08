package modelo;

import java.util.List;

import dto.HabitacionDTO;
import persistencia.dao.interfaz.HabitacionDAO;
import persistencia.dao.mysql.HabitacionDAOImpl;

public class Admin_Habitacion {

	private HabitacionDAO habitacion;

	public Admin_Habitacion() {
		habitacion = new HabitacionDAOImpl();
	}

	public List<HabitacionDTO> obtenerHabitaciones() {
		return this.habitacion.readAll();
	}

	public void agregarHabitacion(HabitacionDTO habitacion) {
		this.habitacion.insert(habitacion);
	}

	public boolean eliminarHabitacion(HabitacionDTO habitacion) {
		return this.habitacion.delete(habitacion);
	}

	public String idHabToString(int idHabitacion) {
		List<HabitacionDTO> listaHab = this.obtenerHabitaciones();
		for (int j = 0; j < listaHab.size(); j++) {
			if (listaHab.get(j).getId() == idHabitacion)
				return listaHab.get(j).getNombre();
		}
		return "";
	}

	public int getIdByNombre(String nombreHab) {
		List<HabitacionDTO> listaHab = this.obtenerHabitaciones();
		for (int j = 0; j < listaHab.size(); j++) {
			if (listaHab.get(j).getNombre() == nombreHab)
				return listaHab.get(j).getId();
		}
		return 0;
	}

	public void editarHabitacion(HabitacionDTO habitacion) {
		this.habitacion.update(habitacion);
	}
}
