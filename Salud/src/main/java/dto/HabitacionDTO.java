package dto;

public class HabitacionDTO {
	private int id;
	private String nombre;
	private String desc;

	public HabitacionDTO(int id, String nombre, String desc) {
		this.id = id;
		this.nombre = nombre;
		this.desc = desc;
	}

	public HabitacionDTO(String nombre, String desc) {
		this.nombre = nombre;
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
