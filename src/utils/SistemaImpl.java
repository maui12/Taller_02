package utils;

import java.util.List;

public class SistemaImpl implements Sistema{
	
	
	//8)
	@Override
	public void mostrarPersonas(List<Persona> personas) {
		System.out.println("-----------------------");
		System.out.println("Personas: ");
		System.out.println("Ensambladores: ");
		for(Persona p : personas) {
			if(p.getEspecialidad().equalsIgnoreCase("ensamblador")) {
				System.out.println("-Nombre: " + p.getNombre() + " -Equipo: " + p.getEquipo().getNombre());
			}
		}
		System.out.println("Pilotos: ");
		for(Persona p1 : personas) {
			if(p1.getEspecialidad().equalsIgnoreCase("piloto")) {
				System.out.println("-Nombre: " + p1.getNombre() + " -Equipo: " + p1.getEquipo().getNombre());
			}
		}
		System.out.println("-----------------------");
	}
	
	//9)
	@Override
	public void mostrarEquipos(List<Equipo> equipos) {
		System.out.println("-----------------------");
		System.out.println("Equipos: ");
		for(Equipo e: equipos) {
			System.out.println(e.getNombre());
		}
		System.out.println("-----------------------");
		
		
	}


}
