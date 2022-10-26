package utils;

public class Modelo {

	private String codigo;
	private String robot;
	
	public Modelo(String codigo, String robot) {
		this.codigo = codigo;
		this.robot = robot;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getRobot() {
		return robot;
	}

	public void setRobot(String robot) {
		this.robot = robot;
	}

	@Override
	public String toString() {
		return "Modelo [codigo=" + codigo + ", robot=" + robot + "]";
	}
}
