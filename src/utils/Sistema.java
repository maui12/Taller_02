package utils;

import java.io.FileNotFoundException;
import java.util.List;

public interface Sistema {
	
	public void leerMateriales() throws FileNotFoundException;
	
	public void leerPiezas() throws FileNotFoundException;
	
	public void leerArmas() throws FileNotFoundException;
	
	public void leerPaises() throws FileNotFoundException;
	
	public void leerPersonas() throws FileNotFoundException;
	
	public void leerRobots() throws FileNotFoundException;
	
	public boolean acceder(String nombre, String id);
	
	public void ingresarPiezaOArma(String elemento);
	
	public void ensamblarRobot(String tipo);
	
	public void crearEquipo();
	
	public boolean verificarPersona(String id);
	
	public Persona anadirPersona(String id);
	
	public void buscarTipo(String tipo);
	
	public void buscarMaterial(String material);
	
	public void crearModelo();
	
	public void revisarPiezas(String robot);
	
	//8)
	public void mostrarPersonas();
	
	//9)
	public void mostrarEquipos();
	
	public void mostrarRobots();
	
	public void revisarMunicion();
	
	public void mostrarPaises();
	
	public boolean anadirStockP(String pais);
	
	public void mostrarPiezasArmas();
	
	public boolean anadirStockM(String material);
	
	public void cambiarPiezas(String robot);
	
	public void activarRecursoNuclear();
	
	public void destruirTodo();
	
	public void cerrarSistema();
	
	public boolean crearArchModelos();
	
	public boolean verificarEquipo(String equipo);
}
