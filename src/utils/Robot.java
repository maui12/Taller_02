package utils;

import java.util.ArrayList;
import java.util.List;

public class Robot {
	
	private String nombre;
	private List<Pieza> piezas;
	private Arma arma;
	private Equipo equipo;
	private Persona piloto;
	
	public Robot(String nombre, Arma arma, Equipo equipo, Persona piloto) {
		this.nombre = nombre;
		this.piezas = new ArrayList<Pieza>();
		this.arma = null;
		this.equipo = equipo;
		this.piloto = piloto;
	}
	
	
	
}
