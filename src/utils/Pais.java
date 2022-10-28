package utils;

import java.util.ArrayList;
import java.util.List;

public class Pais {

	private String nombre;
	private Pieza pieza;
	private Arma arma;
	
	public Pais(String nombre) {
		this.nombre = nombre;
		pieza = null;
		arma = null;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Pieza getPieza() {
		return pieza;
	}

	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}

	public Arma getArma() {
		return arma;
	}

	public void setArma(Arma arma) {
		this.arma = arma;
	}

	@Override
	public String toString() {
		return "Pais [nombre=" + nombre + ", pieza=" + pieza + ", arma=" + arma + "]";
	}
}
