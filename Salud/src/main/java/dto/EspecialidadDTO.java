package dto;

public class EspecialidadDTO {

	private int idEspecialidad;
	private String nombreEspecialidad;

	public EspecialidadDTO(String nombreEspecialidad) {
		setNombreEspecialidad(nombreEspecialidad);
	}

	public EspecialidadDTO(int idEspecialidad, String nombreEspecialidad) {
		setIdEspecialidad(idEspecialidad);
		setNombreEspecialidad(nombreEspecialidad);
	}

	/**
	 * @return the idEspecialidad
	 */
	public int getIdEspecialidad() {
		return idEspecialidad;
	}

	/**
	 * @param idEspecialidad
	 *            the idEspecialidad to set
	 */
	public void setIdEspecialidad(int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	/**
	 * @return the nombreEspecialidad
	 */
	public String getNombreEspecialidad() {
		return nombreEspecialidad;
	}

	/**
	 * @param nombreEspecialidad
	 *            the nombreEspecialidad to set
	 */
	public void setNombreEspecialidad(String nombreEspecialidad) {
		this.nombreEspecialidad = nombreEspecialidad;
	}

	@Override
	public String toString() {
		return this.nombreEspecialidad;
	}
}
