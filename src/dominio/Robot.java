package dominio;

import java.util.ArrayList;
import java.util.List;

public class Robot {
	
	private String nombre;
	private List<Pieza> piezas;
	private Arma arma;
	private Equipo equipo;
	private Persona piloto;
	
	public Robot(String nombre) {
		this.nombre = nombre;
		piezas = new ArrayList<Pieza>();
		arma = null;
		equipo = null;
		piloto = null;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Pieza> getPiezas() {
		return piezas;
	}

	public void setPiezas(List<Pieza> piezas) {
		this.piezas = piezas;
	}

	public Arma getArma() {
		return arma;
	}

	public void setArma(Arma arma) {
		this.arma = arma;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Persona getPiloto() {
		return piloto;
	}

	public void setPiloto(Persona piloto) {
		this.piloto = piloto;
	}

	@Override
	public String toString() {
		return "Robot [nombre=" + nombre + ", piezas=" + piezas + ", arma=" + arma + ", equipo=" + equipo + ", piloto="
				+ piloto + "]";
	}
	
}
