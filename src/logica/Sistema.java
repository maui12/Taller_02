package logica;

import java.io.FileNotFoundException;
import java.util.List;

import dominio.Persona;

public interface Sistema {
	
	/**
	 * Reads the Materials's text file and stores the information in the system.
	 * @throws FileNotFoundException
	 */
	public void leerMateriales() throws FileNotFoundException;
	/**
	 * Reads the Pieces's text file and stores the information in the system.
	 * @throws FileNotFoundException
	 */
	public void leerPiezas() throws FileNotFoundException;
	/**
	 * Reads the Weapons's text file and stores the information in the system.
	 * @throws FileNotFoundException
	 */
	public void leerArmas() throws FileNotFoundException;
	/**
	 * Reads the Countries's text file and stores the information in the system.
	 * @throws FileNotFoundException
	 */
	public void leerPaises() throws FileNotFoundException;
	/**
	 * Reads the People's text file and stores the information in the system.
	 * @throws FileNotFoundException
	 */
	public void leerPersonas() throws FileNotFoundException;
	/**
	 * Reads the Robots's text file and stores the information in the system.
	 * @throws FileNotFoundException
	 */
	public void leerRobots() throws FileNotFoundException;
	/**
	 * Allows the user to access the system.
	 * @param nombre
	 * @param id
	 * @return a number, depending of who is trying to access.
	 */
	public int acceder(String nombre, String id);
	/**
	 * Creates a new piece or weapon with all their features.
	 * @param elemento
	 */
	public void ingresarPiezaOArma(String elemento);
	/**
	 * Assembles a robot, associating a team and a pilot.
	 * @param tipo
	 */
	public void ensamblarRobot(String tipo);
	/**
	 * Creates a new team.
	 */
	public void ensamblarRobot();
	
	public void crearEquipo();
	/**
	 * Verifies if a certain person exist inside the database.
	 * @param id
	 * @return
	 */
	public boolean verificarPersona(String id);
	/**
	 * Adds a new person to the database.
	 * @param id
	 * @return
	 */
	public Persona anadirPersona(String id);
	/**
	 * Looks for a certain piece's type and shows all the countries with the avalaible pieces.
	 * @param tipo
	 */
	public void buscarTipo(String tipo);
	/**
	 * Looks for a certain material and shows the country that produces it with the available stock.
	 * @param material
	 */
	public void buscarMaterial(String material);
	/**
	 * Creates the models of the existing robots.
	 */
	public void crearModelo();
	/**
	 * Checks if the entered robot has its parts incorrectly associated.
	 * @param robot
	 */
	public void revisarPiezas(String robot);
	/**
	 * Shows all the existing persons inside the system.
	 */
	//8)
	public void mostrarPersonas();
	/**
	 * Shows all the existing teams inside the system.
	 */
	//9)
	public void mostrarEquipos();
	/**
	 * Shows all the existing robots inside the system.
	 */
	public void mostrarRobots();
	/**
	 * Shows all the existing robots and allows to add ammo to one of them.
	 */
	public void revisarMunicion();
	/**
	 * Shows all the existing countries inside the system.
	 */
	public void mostrarPaises();
	/**
	 * Adds stock to the piece of a certain country.
	 * @param pais
	 * @return true or false, depending if the stock were added or not.
	 */
	public boolean anadirStockP(String pais);
	/**
	 * Shows all the existing teams inside the system.
	 */
	public void mostrarPiezasArmas();
	/**
	 * Adds stock to the material of a certain country.
	 * @param material
	 * @return true or false, depending if the stock were added or not.
	 */
	public boolean anadirStockM(String material);
	/**
	 * Allows to change the pieces of a certain robot.
	 * @param robot
	 */
	public void cambiarPiezas(String robot);
	/**
	 * Creates a new material and a brand new nuclear pieces set.
	 */
	public void activarRecursoNuclear();
	/**
	 * Destroys all the current information in the database.
	 */
	public void destruirTodo();
	/**
	 * Closes the system and saves all the information.
	 */
	public void cerrarSistema();
	/**
	 * Creates a new Models's text file.
	 * @return true or false, depending if the text file already exists or not.
	 */
	public boolean crearArchModelos();
	/**
	 * Verifies if a certain team exist in the database.
	 * @param equipo
	 * @return true or false, depending if the team already exists or not.
	 */
	public boolean verificarEquipo(String equipo);
}
