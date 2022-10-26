package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Sistema sistema = new SistemaImpl();
		
		List<Persona> personas = new ArrayList<Persona>();
		List<Equipo> equipos = new ArrayList<Equipo>();
		List<Robot> robots = new ArrayList<Robot>();
		
		lectura(personas, equipos);
		
		menu(sistema, personas, equipos, robots);
		
	}
	
	public static void lectura(List<Persona> personas,List<Equipo> equipos) throws FileNotFoundException {
		
		Scanner scan = new Scanner(new File("Personas.txt"));
		
		while(scan.hasNextLine()) {
			String [] linea = scan.nextLine().split(",");
			Persona p = new Persona(linea[0],linea[1],linea[2]);
			
			if(crearEquipo(linea[3], equipos)) {
				Equipo e = new Equipo(linea[3]);
				p.setEquipo(e);
				equipos.add(e);
			} 
			else {
				p.setEquipo(buscarEquipoPorNombre(linea[3], equipos));
			}
			personas.add(p);
		}
		
		llenarEquipos(personas, equipos);
		
		scan = new Scanner(new File("robots.txt"));
		while(scan.hasNextLine()) {
			String [] linea = scan.nextLine().split(",");
			
		}
	}
	
	public static void llenarEquipos(List<Persona> personas,List<Equipo> equipos) {
		
		for(Equipo e : equipos) {
			for(int i = 0 ; i < personas.size() ; i++) {
				if(personas.get(i).getEquipo().getNombre().equals(e.getNombre())) {
					e.agregarPersona(personas.get(i));
				}
			}
		}
	}
	
	public static boolean crearEquipo(String equipo, List<Equipo> equipos) {
		for(Equipo e: equipos) {
			if(e.getNombre().equals(equipo)) {
				return false;
			}
		}
		return true;
	}
	
	public static Equipo buscarEquipoPorNombre(String nombre,List<Equipo> equipos) {
		for(Equipo e : equipos) {
			if(e.getNombre().equals(nombre)) {
				return e;
			}
		}
		return null;
	}
	
	//---------------
	
	public static void menu(Sistema sistema, List<Persona> personas, List<Equipo> equipos, List<Robot> robots) {
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.println("Seleccione una opcion: ");
			
			System.out.println("8) Mostrar Personas");
			System.out.println("9) Mostrar Equipos");
			
			String respuesta = scan.nextLine();
			switch(respuesta) {
				
				case "8":
					sistema.mostrarPersonas(personas);
					break;
				case "9":
					sistema.mostrarEquipos(equipos);
					break;
			}
		}
	}
}
