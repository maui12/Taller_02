package utils;

public class Arma {

	private String nombre;
	private String codigo;
	private int municion;
	
	public Arma(String nombre, String codigo, int municion) {
		this.nombre = nombre;
		this.codigo = codigo;
		this.municion = municion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getMunicion() {
		return municion;
	}

	public void setMunicion(int municion) {
		this.municion = municion;
	}

	@Override
	public String toString() {
		return "Arma [nombre=" + nombre + ", codigo=" + codigo + ", municion=" + municion + "]";
	}
	
}