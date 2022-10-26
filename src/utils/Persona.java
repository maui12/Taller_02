package utils;

public class Persona {
	
	private String nombre;
	private String identificacion;
	private String especialidad;
	private Equipo equipo;
	
	public Persona(String nombre, String identificacion, String especialidad) {
		this.nombre = nombre;
		this.identificacion = identificacion;
		this.especialidad = especialidad;
		this.equipo = null;
	}
	
	
	
	//------

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	
	
	
}
