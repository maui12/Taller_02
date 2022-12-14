package logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dominio.Equipo;
import dominio.Persona;
import dominio.Robot;

//
//Mauricio Diaz 21.227.728-2
//Carolina Torres 20.036.467-8
public class App {
	
	public static void main(String[] args) throws IOException {
		
		Sistema sistema = new SistemaImpl();		
		
		lectura(sistema);
		
		menu(sistema,verificarAcceso(sistema));	
	}
	
	/**
	 * Reads the text files and saves the information from the system.
	 * @param sistema
	 * @throws FileNotFoundException
	 */
	public static void lectura(Sistema sistema) throws FileNotFoundException {	
		sistema.leerMateriales();
		sistema.leerPiezas();
		sistema.leerArmas();
		sistema.leerPaises();
		sistema.leerPersonas();
		sistema.leerRobots();
	}
	
	//---------------
	
	/**
	 * Checks the access to the system. 
	 * @param sistema
	 * @return a number, depending who is trying to access the system.
	 */
	public static int verificarAcceso(Sistema sistema) {
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			System.out.println("---------------------");
			System.out.println("-Ingrese nombre: ");
			String nombre = scan.nextLine();
			System.out.println("-Ingrese identificacion: ");
			String id = scan.nextLine();
			int ver = sistema.acceder(nombre, id);
			if(ver == 0 || ver == 1) {
				System.out.println("======================");
				System.out.println("|  Acceso concedido  |");
				System.out.println("======================");
				return sistema.acceder(nombre, id);
			}
			else {
				System.out.println("======================");
				System.out.println("|   Acceso denegado  |");
				System.out.println("======================");
				System.out.println("Ingrese los datos nuevamente.");
			}
		}
	}
	/**
	 * Runs the main menu of the system.
	 * @param sistema
	 * @param verificacion
	 * @throws IOException
	 */
	public static void menu(Sistema sistema,int verificacion) throws IOException{
		Scanner scan = new Scanner(System.in);
		//Menu normal
		if(verificacion == 0) {
			while(true) {
				System.out.println("------------------------");
				System.out.println("Seleccione una opcion: ");
				System.out.println(" ");
				System.out.println("1) Ingresar pieza o arma");
				System.out.println("2) Ensamblar robot");
				System.out.println("3) Crear equipo");
				System.out.println("4) Buscar por tipos de piezas");
				System.out.println("5) Buscar por material");
				System.out.println("6) Crear modelo");
				System.out.println("7) Revisar piezas");
				System.out.println("8) Mostrar Personas");
				System.out.println("9) Mostrar Equipos"); 
				System.out.println("10) Mostrar todos los robots"); 
				System.out.println("11) Revisar munici?n"); 
				System.out.println("12) Mostrar todos los Pa?ses"); 
				System.out.println("13) A?adir stock piezas"); 
				System.out.println("14) A?adir stock Materiales");
				System.out.println("15) Mostrar todas las Piezas y armas");
				System.out.println("16) Cambiar piezas");
				System.out.println("17) Salir");
				System.out.println("------------------------");
				
				String respuesta = scan.nextLine();
				switch(respuesta) {
					case "1":
						ingresarPiezaoArma(sistema);
						break;
					case "2":
						sistema.ensamblarRobot();
						break;
					case "3":
						sistema.crearEquipo();
						break;
					case "4":
						buscarPiezaPorTipo(sistema);
						break;
					case "5":
						buscarMaterialPorNombre(sistema);
						break;
					case "6":
						sistema.crearModelo();
						break;
					case "7":
						revisarRobot(sistema);
						break;
					case "8":
						sistema.mostrarPersonas();
						break;
					case "9":
						sistema.mostrarEquipos();
						break;
					case "10":
						sistema.mostrarRobots();
						break;
					case "11":
						sistema.revisarMunicion();
						break;
					case "12":
						sistema.mostrarPaises();
						break;
					case "13":
						agregarPiezas(sistema);
						break;
					case "14":
						agregarStockMateriales(sistema);
						break;
					case "15":
						sistema.mostrarPiezasArmas();
						break;
					case "16":
						cambiarPiezasDeRobot(sistema);
						break;
					case "17":
						sistema.cerrarSistema();
						break;
					default:
						System.out.println("Opci?n no v?lida.");	
						break;
				}
				if(respuesta.equals("17")) {
					break;
				}
			}
		}
		
		//Menu secreto
		if(verificacion == 1) {
			Scanner scan1 = new Scanner(System.in);
			System.out.println("Menu secreto");
			System.out.println("1) Activar produccion de recursos nucleares.");
			System.out.println("2) Destruir todo.");
			String respuesta = scan1.nextLine();
			
			switch(respuesta) {
				case "1":
					sistema.activarRecursoNuclear();
					break;
				case "2":
					sistema.destruirTodo();
					break;
			}
		}
	}
	
	//1)
	/**
	 * Creates a new piece or weapon with all their features from the system.
	 * @param sistema
	 */
	public static void ingresarPiezaoArma(Sistema sistema) {
		Scanner scan1 = new Scanner(System.in);
		System.out.print("Desea ingresar una Pieza o una Arma?: ");
		String respuesta = scan1.nextLine();
		
		sistema.ingresarPiezaOArma(respuesta);
	}
	
	//4)
	/**
	 * Looks for a certain piece's type and shows all the countries with the available pieces from the system.
	 * @param sistema
	 */
	public static void buscarPiezaPorTipo(Sistema sistema) {
		Scanner scan1 = new Scanner(System.in);
		System.out.print("Ingrese tipo de Pieza a buscar: ");
		String respuesta = scan1.nextLine();
		
		sistema.buscarTipo(respuesta);
	}
	
	//5)
	/**
	 * Looks for a certain material and shows the country that produces it with the available stock from the system.
	 * @param sistema
	 */
	public static void buscarMaterialPorNombre(Sistema sistema) {
		Scanner scan1 = new Scanner(System.in);
		System.out.print("Ingrese nombre del Material a buscar: ");
		String respuesta = scan1.nextLine();
		
		sistema.buscarMaterial(respuesta);
	}
	
	//7)
	/**
	 * Checks if the entered robot has its parts incorrectly associated from the system.
	 * @param sistema
	 */
	public static void revisarRobot(Sistema sistema) {
		Scanner scan1 = new Scanner(System.in);
		System.out.print("Ingrese nombre del Robot a revisar: ");
		String respuesta = scan1.nextLine();
		
		sistema.revisarPiezas(respuesta);
	}
	
	//13)
	/**
	 * Adds stock to the piece of a certain country from the system.
	 * @param sistema
	 */
	public static void agregarPiezas(Sistema sistema) {
		Scanner scan1 = new Scanner(System.in);
		System.out.print("Ingrese nombre de un Pais: ");
		String respuesta = scan1.nextLine();
		
		sistema.anadirStockP(respuesta);
	}
	
	//14)
	/**
	 * Adds stock to the material of a certain country from the system.
	 * @param sistema
	 */
	public static void agregarStockMateriales(Sistema sistema) {
		Scanner scan1 = new Scanner(System.in);
		System.out.print("Ingrese nombre de un Material para agregar mas stock: ");
		String respuesta = scan1.nextLine();
		
		sistema.anadirStockM(respuesta);
	}
	//15)
	/**
	 * Allows to change the pieces of a certain robot from the system.
	 * @param sistema
	 */
	public static void cambiarPiezasDeRobot(Sistema sistema) {
		Scanner scan1 = new Scanner(System.in);
		System.out.print("Ingrese nombre de un Robot para cambiar sus piezas: ");
		String respuesta = scan1.nextLine();
		
		sistema.cambiarPiezas(respuesta);
	}
}
