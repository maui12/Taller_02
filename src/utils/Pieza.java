package utils;

public class Pieza {

	private String nombre;
	private String codigo;
	private String tipo;
	private int cantidadProducida;
	private Pais paisOrigen;
	private int cantidadMaterial;
	private Material material;
	
	public Pieza(String nombre, String codigo, String tipo, int cantidadMaterial) {
		this.nombre = nombre;
		this.codigo = codigo;
		this.tipo = tipo;
		cantidadProducida = 0;
		paisOrigen = null;
		this.cantidadMaterial = cantidadMaterial;
		material = null;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public int getCantidadProducida() {
		return cantidadProducida;
	}

	public void setCantidadProducida(int cantidadProducida) {
		this.cantidadProducida = cantidadProducida;
	}

	public Pais getPaisOrigen() {
		return paisOrigen;
	}

	public void setPaisOrigen(Pais paisOrigen) {
		this.paisOrigen = paisOrigen;
	}
	
	public int getCantidadMaterial() {
		return cantidadMaterial;
	}

	public void setCantidadMaterial(int cantidadMaterial) {
		this.cantidadMaterial = cantidadMaterial;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	@Override
	public String toString() {
		return "Pieza [nombre=" + nombre + ", codigo=" + codigo + ", tipo=" + tipo + ", cantidadProducida="
				+ cantidadProducida + ", paisOrigen=" + paisOrigen + ", cantidadMaterial=" + cantidadMaterial
				+ ", material=" + material + "]";
	}
	
}
