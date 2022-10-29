package dominio;

public class Arma {

	private String nombre;
	private String codigo;
	private int municion;
	private int cantidadProducida;
	private Pais paisOrigen;
	private int cantidadMaterial;
	private Material material;
	
	public Arma(String nombre, String codigo, int municion, int cantidadMaterial) {
		this.nombre = nombre;
		this.codigo = codigo;
		this.municion = municion;
		paisOrigen = null;
		this.cantidadProducida = cantidadProducida;
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

	public int getMunicion() {
		return municion;
	}

	public void setMunicion(int municion) {
		this.municion = municion;
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
		return "Arma [nombre=" + nombre + ", codigo=" + codigo + ", municion=" + municion + ", paisOrigen=" + paisOrigen
				+ ", cantidadProducida=" + cantidadProducida + ", cantidadMaterial=" + cantidadMaterial + ", material="
				+ material + "]";
	}
	
	
}