package dominio;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
	
	private String nombre;
	private List<Persona> integrantes;
	
	public Equipo(String nombre) {
		this.nombre = nombre;
		integrantes = new ArrayList<Persona>();
	}
	
	public void agregarPersona(Persona p) {
		integrantes.add(p);
	}
	
	public Persona getPiloto() {
		for(Persona p : integrantes) {
			if(p.getEspecialidad().equalsIgnoreCase("piloto")) {
				return p;
			}
		}
		return null;
	}
	
	//------

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Persona> getIntegrantes() {
		return integrantes;
	}

	public void setIntegrantes(List<Persona> integrantes) {
		this.integrantes = integrantes;
	}
	
	
	
	
	
	
	
}
