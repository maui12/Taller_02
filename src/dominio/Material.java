package dominio;

public class Material {
	
	private String nombre;
	private int stock;
	private Pais paisOrigen;
	
	public Material(String nombre, int stock) {
		this.nombre = nombre;
		this.stock = stock;
		paisOrigen = null;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Pais getPaisOrigen() {
		return paisOrigen;
	}

	public void setPaisOrigen(Pais paisOrigen) {
		this.paisOrigen = paisOrigen;
	}

	@Override
	public String toString() {
		return "Material [nombre=" + nombre + ", stock=" + stock + ", paisOrigen=" + paisOrigen + "]";
	}
	
	
	
}
