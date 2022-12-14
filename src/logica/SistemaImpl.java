package logica;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import dominio.Arma;
import dominio.Equipo;
import dominio.Material;
import dominio.Modelo;
import dominio.Pais;
import dominio.Persona;
import dominio.Pieza;
import dominio.Robot;

public class SistemaImpl implements Sistema{
	
	
	ArrayList<Modelo> modelos = new ArrayList<Modelo>();
	ArrayList<Equipo> equipos = new ArrayList<Equipo>();
	ArrayList<Persona> personas = new ArrayList<Persona>();
	ArrayList<Pieza> piezas = new ArrayList<Pieza>();
	ArrayList<Arma> armas = new ArrayList<Arma>();
	ArrayList<Material> materiales = new ArrayList<Material>();
	ArrayList<Pais> paises = new ArrayList<Pais>();
	ArrayList<Robot> robots = new ArrayList<Robot>();
	
	// ojito
	
	public void leerMateriales() throws FileNotFoundException{
		Scanner archM = new Scanner(new File("Materiales.txt"));
		while(archM.hasNextLine()) {
			String[] partes = archM.nextLine().split(",");
			Material material = new Material(partes[0], Integer.parseInt(partes[1]));
			materiales.add(material);
		}
		archM.close();
	}
	
	public void leerPiezas() throws FileNotFoundException{
		Scanner archP = new Scanner(new File("Piezas.txt"));
		while(archP.hasNextLine()) {
			String[] partes = archP.nextLine().split(",");
			Pieza pieza = new Pieza(partes[0], partes[1], partes[2], Integer.parseInt(partes[4]));
			piezas.add(pieza);
		}
		archP.close();
	}
	
	public void leerArmas() throws FileNotFoundException{
		Scanner archA = new Scanner(new File("Armas.txt"));
		while(archA.hasNextLine()) {
			String[] partes = archA.nextLine().split(",");
			Arma arma = new Arma(partes[0], partes[1], Integer.parseInt(partes[2]), Integer.parseInt(partes[4]));
			armas.add(arma);
		}
		archA.close();
	}
	
	public void leerPaises() throws FileNotFoundException{
		Scanner archPa = new Scanner(new File("Paises.txt"));
		while(archPa.hasNextLine()) {
			String[] partes = archPa.nextLine().split(",");
			Pais pais = new Pais(partes[0]);
			int cont = 0;
			for(Pais p : paises) {
				if(p.getNombre().equals(partes[0])){
					cont++;
				}
			}
			if(cont == 0) {
				paises.add(pais);				
			}
			if(partes[1].equals("pieza")) {
				for(Pieza p : piezas) {
					if(p.getCodigo().equals(partes[3])) {
						p.setPaisOrigen(pais);
						p.setCantidadProducida(Integer.parseInt(partes[2]));
						for(Material m : materiales) {
							if(m.getNombre().equals(partes[4])) {
								p.setMaterial(m);
							}
						}
					}
				}
			}
			else {
				for(Arma a : armas) {
					if(a.getCodigo().equals(partes[3])) {
						a.setPaisOrigen(pais);
						a.setCantidadProducida(Integer.parseInt(partes[2]));
						for(Material m : materiales) {
							if(m.getNombre().equals(partes[4])) {
								a.setMaterial(m);
							}
						}
					}
				}
			}
			
			Scanner scanM = new Scanner(new File("Materiales.txt"));
			while(scanM.hasNextLine()) {
				String [] part = scanM.nextLine().split(",");
				for(int i = 0; i < paises.size(); i++) {
					if(paises.get(i).getNombre().equalsIgnoreCase(part[2])) {
						for(Material m : materiales) {
							if(m.getNombre().equalsIgnoreCase(part[0])) {
								m.setPaisOrigen(paises.get(i));
							}
						}
						
					}
				}
				
			}
		}
		Scanner archP2 = new Scanner(new File("Paises.txt"));
		while(archP2.hasNextLine()) {
			String [] separar = archP2.nextLine().split(",");
			for(Pais p : paises) {
				if(p.getNombre().equalsIgnoreCase(separar[0])) {
					if(separar[1].equalsIgnoreCase("pieza")) {
						for(Pieza pieza : piezas) {
							if(pieza.getCodigo().equals(separar[3])) {
								p.setPieza(pieza);
								break;
							}
						}
						
					}
					else if(separar[1].equalsIgnoreCase("arma")){
						for(Arma a : armas) {
							if(a.getCodigo().equals(separar[3])) {
								p.setArma(a);
							}
						}
					}
				}
			}
		}
	}
	
	//
	
	public void leerPersonas() throws FileNotFoundException{
		Scanner archPe = new Scanner(new File("Personas.txt"));
		while(archPe.hasNextLine()) {
			String[] partes = archPe.nextLine().split(",");
			Persona persona = new Persona(partes[0], partes[1], partes[2]);
			personas.add(persona);
			Equipo equipo = new Equipo(partes[3]);
			if(!verificarEquipo(partes[3])) {
				equipos.add(equipo);
				equipo.agregarPersona(persona);
			}else {
				for(Equipo e : equipos) {
					if(e.getNombre().equals(partes[3])) {
						e.agregarPersona(persona);
					}
				}
			}
			persona.setEquipo(equipo);
		}
		archPe.close();
	}
	
	public void leerRobots() throws FileNotFoundException{
		Scanner archR = new Scanner(new File("robots.txt"));
		while(archR.hasNextLine()) {
			String[] partes = archR.nextLine().split(",");
			Robot robot = new Robot(partes[0]);
			for(int i=1; i<6; i++) {
				try {
					for(Pieza p : piezas) {
						if(p.getNombre().equals(partes[i])) {
							robot.getPiezas().add(p);
						}
						if(partes[i].equals("sin cualidad")) {
							robot.getPiezas().add(null);
						}
					}
				}catch(NullPointerException e) {
					e.printStackTrace();
				}
			}

			
			for(Arma a : armas) {
				try {
					if(a.getNombre().equals(partes[6])) {
						robot.setArma(a);
					}
				}catch(NullPointerException ex){
					ex.printStackTrace();
				}
			}
			for(Equipo e: equipos) {
				try {
					if(e.getNombre().equals(partes[7])) {
						robot.setEquipo(e);
					}
				}catch(NullPointerException er) {
					er.printStackTrace();
				}
				
			}
			for(Persona p : personas) {
				try {
					if(p.getIdentificacion().equals(partes[8])) {
						robot.setPiloto(p);
					}
				}catch(NullPointerException err) {
					err.printStackTrace();
				}
			}
			robots.add(robot);
			
		}
		archR.close();
	}
	
	public int acceder(String nombre, String id) {
		for(Persona p : personas) {
			if(p.getEquipo().getNombre().equals("ADMINISTRACION")) {
				if(p.getNombre().equals(nombre) && p.getIdentificacion().equals(id)) {
					return 0;
				}
			}
		}
		if(nombre.equals("empanada")&&id.equals("porotosconriendas")) {
			return 1;
		}
		return 2;
	}
	
	//----------------
	
	//1)
	public void ingresarPiezaOArma(String elemento) {
		Scanner ing = new Scanner(System.in);
		if(elemento.toLowerCase().equals("pieza")) {
			System.out.print(" - Ingrese nombre de pieza: ");
			String nombre = ing.nextLine();
			System.out.print(" - Ingrese c?digo: ");
			String cod = ing.nextLine();
			System.out.print(" - Ingrese tipo: ");
			String tipo = ing.nextLine();
			System.out.print(" - Ingrese cantidad de piezas: ");
			//falta control de error tipo NumberFormatException
			int cant = Integer.parseInt(ing.nextLine());
			System.out.print(" - Ingrese pa?s de origen: ");
			String pais = ing.nextLine();
			System.out.print(" - Ingrese material: ");
			String mat = ing.nextLine();
			System.out.print(" - Ingrese cantidad de material: ");
			//falta control de error tipo NumberFormatException
			int cantM = Integer.parseInt(ing.nextLine());
			Pieza pieza = new Pieza(nombre, cod, tipo, cantM);
			pieza.setCantidadProducida(cant);
			for(Pais p : paises) {
				if(p.getNombre().equals(pais)) {
					pieza.setPaisOrigen(p);
				}
			}
			for(Material m : materiales) {
				if(m.getNombre().equals(mat)) {
					pieza.setMaterial(m);
				}
			}
			piezas.add(pieza);
			System.out.println("----------------------------------");
			System.out.println("* Pieza ingresada correctamente. *");
			System.out.println("----------------------------------");
			
		}else if(elemento.toLowerCase().equals("arma")) {
			System.out.print(" - Ingrese nombre de arma: ");
			String nombre = ing.nextLine();
			System.out.print(" - Ingrese c?digo: ");
			String cod = ing.nextLine();
			System.out.print(" - Ingrese cantidad de munici?n: ");
			//falta control de error tipo NumberFormatException
			int muni = Integer.parseInt(ing.nextLine());
			System.out.print(" - Ingrese cantidad de armas: ");
			//falta control de error tipo NumberFormatException
			int cant = Integer.parseInt(ing.nextLine());
			System.out.print(" - Ingrese pa?s de origen: ");
			String pais = ing.nextLine();
			System.out.print(" - Ingrese material: ");
			String mat = ing.nextLine();
			System.out.print(" - Ingrese cantidad de material: ");
			//falta control de error tipo NumberFormatException
			int cantM = Integer.parseInt(ing.nextLine());
			Arma arma = new Arma(nombre, cod, muni, cantM);
			arma.setCantidadProducida(cant);
			for(Pais p : paises) {
				if(p.getNombre().equals(pais)) {
					arma.setPaisOrigen(p);
				}
			}
			for(Material m : materiales) {
				if(m.getNombre().equals(mat)) {
					arma.setMaterial(m);
				}
			}
			armas.add(arma);
			System.out.println("----------------------------------");
			System.out.println("* Arma ingresada correctamente. *");
			System.out.println("----------------------------------");
		}
	}
	
	//2)
	public void ensamblarRobot() {
		Scanner ens = new Scanner(System.in);
		System.out.println("============================");
		System.out.println("Ensamblado de un nuevo robot");
		System.out.println("============================");
		System.out.println("Ingrese el nombre del nuevo robot:");
		Robot robotNuevo = new Robot(ens.nextLine());
		for(int i = 0; i < 5; i++) {
			robotNuevo.getPiezas().add(null);
		}
		System.out.println(" ");
		System.out.println("Eliga una Cabeza para el robot: ");
		System.out.println("---------------------");
		System.out.println("* Piezas tipo CABEZA *");
		for(Pieza p : piezas) {
			if(p.getTipo().equals("cabeza")) {
				System.out.println(p.toString());
			}
		}
		System.out.print(" - Ingrese c?digo de pieza: ");
		robotNuevo.getPiezas().set(3, buscarPiezaPorCodigo(ens.nextLine()));
		
		
		System.out.println("Eliga un Torax para el robot: ");
		System.out.println("---------------------");
		System.out.println("* Piezas tipo TORAX *");
		for(Pieza p : piezas) {
			if(p.getTipo().equals("torax")) {
				System.out.println(p.toString());
			}
		}
		System.out.print(" - Ingrese c?digo de pieza: ");
		robotNuevo.getPiezas().set(2, buscarPiezaPorCodigo(ens.nextLine()));
		
		
		System.out.println("Eliga unos Brazos para el robot: ");
		System.out.println("---------------------");
		System.out.println("* Piezas tipo BRAZO *");
		for(Pieza p : piezas) {
			if(p.getTipo().equals("brazos")) {
				System.out.println(p.toString());
			}
		}
		System.out.print(" - Ingrese c?digo de pieza: ");
		robotNuevo.getPiezas().set(0, buscarPiezaPorCodigo(ens.nextLine()));
		
		
		System.out.println("Eliga unas Piernas para el robot: ");
		System.out.println("---------------------");
		System.out.println("* Piezas tipo PIERNAS *");
		for(Pieza p : piezas) {
			if(p.getTipo().equals("piernas")) {
				System.out.println(p.toString());
			}
		}
		System.out.print(" - Ingrese c?digo de pieza: ");
		robotNuevo.getPiezas().set(1, buscarPiezaPorCodigo(ens.nextLine()));
		
		
		System.out.println("?Desea agregar una cualidad extra?(Si/No): ");
		String resp = ens.nextLine();
		
		if(resp.equalsIgnoreCase("si")) {
			System.out.println("Eliga una Cualidad para el robot: ");
			System.out.println("---------------------");
			System.out.println("* Piezas tipo CUALIDAD *");
			for(Pieza p : piezas) {
				if(p.getTipo().equals("cualidad")) {
					System.out.println(p.toString());
				}
			}
			System.out.print(" - Ingrese c?digo de pieza: ");
			robotNuevo.getPiezas().set(4, buscarPiezaPorCodigo(ens.nextLine()));
		}
		
		System.out.println("Eliga una Arma para el robot: ");
		System.out.println("---------------------");
		System.out.println("* Armas disponibles *");
		for(Arma a : armas) {
			System.out.println(a.toString());
		}
		System.out.print(" - Ingrese c?digo de pieza: ");
		robotNuevo.setArma(buscarArmaPorCodigo(ens.nextLine()));
		
		System.out.println("-----------------");
		System.out.println("Equipos disponibles");
		for(Equipo e : equipos) {
			System.out.println(e.getNombre());
		}
		System.out.println("?Que equipo se encargara del robot?: ");
		String resp1 = ens.nextLine();
		
		robotNuevo.setEquipo(buscarEquipoPorNombre(resp1));
		robotNuevo.setPiloto(buscarEquipoPorNombre(resp1).getPiloto());
		robots.add(robotNuevo);
		System.out.println("------------------------");
		System.out.println("Robot creado con exito.");
		System.out.println("------------------------");
		
	}
	
	public Pieza buscarPiezaPorCodigo(String codigo) {
		for(Pieza p: piezas) {
			if(p.getCodigo().equals(codigo)) {
				return p;
			}
		}
		return null;
	}
	
	public Equipo buscarEquipoPorNombre(String nombre) {
		for(Equipo e : equipos) {
			if(e.getNombre().equals(nombre)) {
				return e;
			}
		}
		return null;
	}
	
	public Arma buscarArmaPorCodigo(String codigo) {
		for(Arma a : armas) {
			if(a.getCodigo().equals(codigo)) {
				return a;
			}
		}
		return null;
	}
	
	//3)
	public void crearEquipo() {
		Scanner leerop = new Scanner(System.in);
		System.out.print(" - Ingrese nombre de nuevo equipo: ");
		String equipo = leerop.nextLine();
		if(!verificarEquipo(equipo)) {
			Equipo e = new Equipo(equipo);
			mostrarPersonas();
			int ensam = 0;
			int piloto = 0;
			int numPersonas = 0;
			while(numPersonas<7) {
				System.out.print("- Ingrese identificaci?n de persona: ");
				String id = leerop.nextLine();
				if(!verificarPersona(id)) {
					Persona p = anadirPersona(id);
					if(p.getEspecialidad().toLowerCase().equals("piloto")) {
						if(piloto==0) {
							p.setEquipo(e);
							e.agregarPersona(p);
							piloto++;
						}else {
							System.out.println("Ya hay un piloto en su equipo.");
						}
					}else if(p.getEspecialidad().toLowerCase().equals("ensamblador")) {
						if(ensam<6) {
							p.setEquipo(e);
							e.agregarPersona(p);
							ensam++;
						}else {
							System.out.println("Ya hay 5 ensambladores en su equipo.");
						}
					}
				}else {
					for(Persona p : personas) {
						if(p.getIdentificacion().equals(id)) {
							if(p.getEspecialidad().toLowerCase().equals("piloto")) {
								if(piloto==0) {
									p.setEquipo(e);
									e.agregarPersona(p);
									piloto++;
								}else {
									System.out.println("Ya hay un piloto en su equipo.");
								}
							}else if(p.getEspecialidad().toLowerCase().equals("ensamblador")) {
								if(ensam<6) {
									p.setEquipo(e);
									e.agregarPersona(p);
									ensam++;
								}else {
									System.out.println("Ya hay 5 ensambladores en su equipo.");
								}
							}
						}
					}
				}
				numPersonas ++;
			}
			equipos.add(e);
			System.out.println("Equipo creado.");
		}else {
			System.out.println("Equipo ya existe.");
		}
	}
	
	public boolean verificarPersona(String id) {
		for(Persona p : personas) {
			if(p.getIdentificacion().equals(id)) {
				return true;
			}
		}
		return false;
	}
	
	public Persona anadirPersona(String id) {
		Scanner leer = new Scanner(System.in);
		System.out.print("- Ingrese nombre de persona: ");
		String nombre = leer.nextLine();
		System.out.print("- Ingrese especialidad: ");
		String especialidad = leer.nextLine();
		Persona pers = new Persona(nombre, id, especialidad);
		personas.add(pers);
		return pers;
	}
	
	//4)
	public void buscarTipo(String tipo) {
		for(Pieza p : piezas) {
			if(p.getTipo().equals(tipo)) {
				if(p.getCantidadProducida()>0) {
					System.out.println(p.getPaisOrigen().getNombre()+", Cantidad disponible: "+p.getCantidadProducida());
				}
			}
		}
	}
	
	//5)
	public void buscarMaterial(String material) {
		for(Material m : materiales) {
			if(m.getNombre().equalsIgnoreCase(material)) {
				if(m.getStock()>0) {
					System.out.println(m.getPaisOrigen().getNombre()+", Stock: "+m.getStock());
				}
			}
		}
	}
	
	//6)
	public void crearModelo() {
		for(Robot r : robots) {
			int suma = 0;
			for(Pieza p : r.getPiezas()) {
				int cod = Integer.parseInt(p.getCodigo());
				suma += cod;
			}
			String codigo = Integer.toString(suma)+r.getEquipo().getNombre()+r.getPiloto().getNombre().charAt(0);
			Modelo modelo = new Modelo(codigo, r.getNombre());
			modelos.add(modelo);
		}
	}
	
	//7)
	public void revisarPiezas(String robot) {
			int cont = 0;
			for(Robot r : robots) {
				if(r.getNombre().equals(robot)) {
					cont++;
						for(int i = 0; i < r.getPiezas().size() ; i++) {
							if(!r.getPiezas().get(0).getTipo().equals("brazos")) {
								System.out.println("Pieza erronea, la 1ra pieza debe ser de tipo brazos.");
								cambiarPieza("brazos", r);
							}
							if(!r.getPiezas().get(1).getTipo().equals("piernas")) {
								System.out.println("Pieza erronea, la 2da pieza debe ser de tipo piernas.");
								cambiarPieza("piernas", r);
							}
							if(!r.getPiezas().get(2).getTipo().equals("torax")) {
								System.out.println("Pieza erronea, la 3ra pieza debe ser de tipo t?rax.");
								cambiarPieza("torax", r);
							}
							if(!r.getPiezas().get(3).getTipo().equals("cabeza")) {
								System.out.println("Pieza erronea, la 4ta pieza debe ser de tipo cabeza.");
								cambiarPieza("cabeza", r);
							}
							if(r.getPiezas().get(4) != null) {
								if(!r.getPiezas().get(4).getTipo().equals("cualidad")) {
									System.out.println("Pieza erronea, la 5ta pieza debe ser de tipo cualidad extra.");
									cambiarPieza("cualidad", r);
								}
							}
						}
						System.out.println("===================================");
						System.out.println("No se encontraron piezas erroneas.");
						System.out.println("===================================");
				}
			}
			if(cont == 0) {
				System.out.println("Robot no encontrado.");
			}
	}
	
	public void cambiarPieza(String pieza,Robot robot) {
		Scanner scan = new Scanner(System.in);
		if(pieza.equalsIgnoreCase("brazos")) {
			System.out.println("Piezas del tipo Brazos disponibles:");
			for(Pieza p: piezas) {
				if(p.getTipo().equals(pieza)) {
					System.out.println(p.toString());
				}
			}
			System.out.println("Ingrese el codigo de la pieza a cambiar:");
			String respuesta = scan.nextLine();
			for(Pieza p : piezas) {
				if(p.getCodigo().equals(respuesta)) {
					for(Robot r1 : robots) {
						if(r1.getNombre().equals(robot.getNombre())) {
							r1.reemplazarPieza(robot.getPiezas().get(0), p);
						}
					}
				}
			}
		}
		if(pieza.equalsIgnoreCase("piernas")) {
			System.out.println("Piezas del tipo Piernas disponibles:");
			for(Pieza p: piezas) {
				if(p.getTipo().equals(pieza)) {
					System.out.println(p.toString());
				}
			}
			System.out.println("Ingrese el codigo de la pieza a cambiar:");
			String respuesta = scan.nextLine();
			for(Pieza p : piezas) {
				if(p.getCodigo().equals(respuesta)) {
					for(Robot r1 : robots) {
						if(r1.getNombre().equals(robot.getNombre())) {
							r1.reemplazarPieza(robot.getPiezas().get(1), p);
						}
					}
				}
			}
		}
		if(pieza.equalsIgnoreCase("cabeza")) {
			System.out.println("Piezas del tipo Cabeza disponibles:");
			for(Pieza p: piezas) {
				if(p.getTipo().equals(pieza)) {
					System.out.println(p.toString());
				}
			}
			System.out.println("Ingrese el codigo de la pieza a cambiar:");
			String respuesta = scan.nextLine();
			for(Pieza p : piezas) {
				if(p.getCodigo().equals(respuesta)) {
					for(Robot r1 : robots) {
						if(r1.getNombre().equals(robot.getNombre())) {
							r1.reemplazarPieza(robot.getPiezas().get(3), p);
						}
					}
				}
			}
		}
		if(pieza.equalsIgnoreCase("torax")) {
			System.out.println("Piezas del tipo Torax disponibles:");
			for(Pieza p: piezas) {
				if(p.getTipo().equals(pieza)) {
					System.out.println(p.toString());
				}
			}
			System.out.println("Ingrese el codigo de la pieza a cambiar:");
			String respuesta = scan.nextLine();
			for(Pieza p : piezas) {
				if(p.getCodigo().equals(respuesta)) {
					for(Robot r1 : robots) {
						if(r1.getNombre().equals(robot.getNombre())) {
							r1.reemplazarPieza(robot.getPiezas().get(2), p);
						}
					}
				}
			}
		}
		if(pieza.equalsIgnoreCase("cualidad")) {
			System.out.println("Piezas del tipo Cualidad disponibles:");
			for(Pieza p: piezas) {
				if(p.getTipo().equals(pieza)) {
					System.out.println(p.toString());
				}
			}
			System.out.println("Ingrese el codigo de la pieza a cambiar:");
			String respuesta = scan.nextLine();
			for(Pieza p : piezas) {
				if(p.getCodigo().equals(respuesta)) {
					for(Robot r1 : robots) {
						if(r1.getNombre().equals(robot.getNombre())) {
							r1.reemplazarPieza(robot.getPiezas().get(4), p);
						}
					}
				}
			}
		}
	}
	
	//8)
	@Override
	public void mostrarPersonas() {
		System.out.println("-----------------------");
		System.out.println("Personas: ");
		System.out.println("Ensambladores: ");
		for(Persona p : personas) {
			if(p.getEspecialidad().equalsIgnoreCase("ensamblador")) {
				System.out.println("	" + p.toString());
			}
		}
		System.out.println("Pilotos: ");
		for(Persona p1 : personas) {
			if(p1.getEspecialidad().equalsIgnoreCase("piloto")) {
				System.out.println("	" + p1.toString());
			}
		}
		System.out.println("-----------------------");
	}
	
	//9)
	@Override
	public void mostrarEquipos() {
		System.out.println("-----------------------");
		System.out.println("Equipos: ");
		for(Equipo e: equipos) {
			System.out.println("-----------------------");
			System.out.println(e.getNombre());
			System.out.println("	-Integrantes: ");
			for(int i = 0; i < e.getIntegrantes().size(); i++) {
				System.out.println(e.getIntegrantes().get(i).toString());
			}
		}
		System.out.println("-----------------------");
		
		
	}
	
	//10)
	public void mostrarRobots() {
		System.out.println("-----------------------");
		System.out.println("Robots: ");
		System.out.println("-----------------------");
		for(Robot r: robots) {
			System.out.println("-Nombre: " + r.getNombre());
			System.out.println("-Equipo: " + r.getEquipo().getNombre());
			System.out.println("-Piloto: " + r.getPiloto().getNombre());
			System.out.println("-Piezas: ");
			
			for(Pieza p : r.getPiezas()) {
				if(p!=null) {
					System.out.println("	*Nombre: " + p.getNombre());
					System.out.println("	*Codigo: " + p.getCodigo());					
					System.out.println("	*Pais de origen: " + p.getPaisOrigen().getNombre());
					System.out.println("-----------------------");		
				}else {
					System.out.println("Este robot no tiene cualidad extra.");
					System.out.println("====================");
				}
				
			}
		}
	}

	//11)
	public void revisarMunicion() {
		Scanner rev = new Scanner(System.in);
		mostrarRobots();
		System.out.print(" - Ingrese nombre de robot: ");
		String nombre = rev.nextLine();
		for(Robot r : robots) {
			if(r.getNombre().equals(nombre)) {
				System.out.print(" - Ingrese cantidad de munici?n a aumentar: ");
				//falta control de error tipo NumberFormatException
				int muni = Integer.parseInt(rev.nextLine());
				r.getArma().setMunicion(r.getArma().getMunicion()+muni);
			}
		}
		System.out.println("--------------------------");
		System.out.println("* Municion incrementada. *");
		System.out.println("--------------------------");
	}
	
	//12)
	public void mostrarPaises() {
		System.out.println("-----------------------");
		System.out.println("Paises: ");
		for(Pais p : paises) {
			System.out.println(p.getNombre());
		}
		System.out.println("-----------------------");
	}
	
	//13)
	public boolean anadirStockP(String pais) {
		Scanner stock = new Scanner(System.in);
		System.out.println("-----------------------");
		System.out.println("A?adir stock de pieza: ");
		for(Pais p : paises) {
			if(p.getNombre().equals(pais)) {
				System.out.print(" - Ingrese stock a agregar: ");
				//falta control de error tipo NumberFormatException
				int cant = Integer.parseInt(stock.nextLine());
				p.getPieza().setCantidadProducida(p.getPieza().getCantidadProducida()+cant);
				System.out.println("* Stock agregado correctamente. *");
				System.out.println("-----------------------");
				return true;
			}
		}
		System.out.println("* No se encontr? el pa?s. *");
		System.out.println("-----------------------");
		return false;
	}
	
	//14)
	public boolean anadirStockM(String material) {
		Scanner st = new Scanner(System.in);
		System.out.println("-----------------------");
		System.out.println("A?adir stock de material: ");
		for(Material m : materiales) {
			if(m.getNombre().equals(material)) {
				System.out.print(" - Ingrese stock a agregar: ");
				//falta control de error tipo NumberFormatException
				int cant = Integer.parseInt(st.nextLine());
				m.setStock(m.getStock()+cant);
				System.out.println("* Stock agregado correctamente. *");
				System.out.println("-----------------------");
				return true;
			}
		}
		System.out.println("* No se encontr? el material. *");
		System.out.println("-----------------------");
		return false;
	}
	
	//15)
	public void mostrarPiezasArmas() {
		System.out.println("-----------------------");
		System.out.println("Piezas: ");
		for(Pieza p : piezas) {
			System.out.println(p.toString());
		}
		System.out.println("Armas: ");
		for(Arma a : armas) {
			System.out.println(a.toString());
		}
		System.out.println("-----------------------");
	}
	
	//16)
	public void cambiarPiezas(String robot) {
		Scanner scan = new Scanner(System.in);
		boolean encontrado = false;
		
		for(Robot r : robots) {
			if(r.getNombre().equalsIgnoreCase(robot)) {
				encontrado = true;
				System.out.println("Piezas del robot: ");
				for(Pieza p : r.getPiezas()) {
					System.out.println(p.toString());
				}
				break;
			}
		}
		
		if(!encontrado) {
			System.out.println("--------------------");
			System.out.println("Robot no encontrado");
			System.out.println("--------------------");
			return;
		}
		
		System.out.println("Introduzca el codigo de una Pieza para cambiar:");
		String nombrePieza = scan.nextLine();
		
		for(Pieza p : piezas) {
			if(nombrePieza.equalsIgnoreCase(p.getCodigo())) {
				System.out.println("-Piezas disponibles para cambio:");
				for(Pieza p2 : piezas) {
					if(p2.getTipo().equals(p.getTipo())) {
						System.out.println(p2.toString());
					}
				}
				break;
			}
		}
		System.out.println("?Por cual pieza quiere cambiarla?(Ingrese codigo):");
		String nombrePieza2 = scan.nextLine();
		
		for(Robot r : robots) {
			if(r.getNombre().equalsIgnoreCase(robot)) {
				for(Pieza p1 : piezas) {
					if(p1.getCodigo().equalsIgnoreCase(nombrePieza2) ) {
						r.cambiarPieza(p1);
					}
				}
			}
		}
	}
	
	
	//----------------
	//Menu secreto
	//----------------
	
	public void activarRecursoNuclear() {
		Material m = new Material("Uranio",100000);
		Pais p = new Pais("Area 51");
		m.setPaisOrigen(p);
		materiales.add(m);
		
		Scanner ing = new Scanner(System.in);
		System.out.println("===============================");
		System.out.println("Produccion de piezas nucleares");
		System.out.println("===============================");
		while(true) {
			System.out.print(" - Ingrese nombre de pieza: ");
			String nombre = ing.nextLine();
			System.out.print(" - Ingrese c?digo: ");
			String cod = ing.nextLine();
			System.out.print(" - Ingrese tipo: ");
			String tipo = ing.nextLine();
			System.out.print(" - Ingrese cantidad de material: ");
			String cant = ing.nextLine();
			
			Pieza pieza = new Pieza(nombre,cod,tipo,Integer.parseInt(cant));
			pieza.setMaterial(m);
			pieza.setPaisOrigen(p);
			piezas.add(pieza);
			System.out.println("---------------------------");
			System.out.println("Pieza creada exitosamente.");
			System.out.println("---------------------------");
			System.out.println("Desea producir mas piezas?(Si/No): ");
			String respuesta = ing.nextLine();
			if(respuesta.equalsIgnoreCase("no")) {
				break;
			}
		}	
	}
	
	public void destruirTodo() throws IOException{
		Scanner des = new Scanner(System.in);
		System.out.println("---------------------------");
		System.out.print("?Est? seguro?\n1) S?\n2) No\nSu opci?n: ");
		int op = Integer.parseInt(des.nextLine());
		if(op==1) {
			modelos.clear();
			personas.clear();
			equipos.clear();
			piezas.clear();
			armas.clear();
			materiales.clear();
			paises.clear();
			robots.clear();
			cerrarSistema();
			System.out.println("Destrucci?n completa.");
			System.out.println("---------------------------");
		}else {
			System.out.println("No se hizo nada.");
			System.out.println("---------------------------");
		}
		
	}
	
	public void cerrarSistema() throws IOException{
		System.out.println("Cerrando sistema...");
		BufferedWriter archPe = new BufferedWriter(new FileWriter("Personas.txt"));
		if(!personas.isEmpty()) {
			for(Persona p : personas) {
				String linea = p.getNombre()+","+p.getIdentificacion()+","+p.getEspecialidad()+","+p.getEquipo().getNombre()+"\n";
				archPe.write(linea);
			}
		}else {
			archPe.write("");
		}
		archPe.close();
		
		BufferedWriter archM = new BufferedWriter(new FileWriter("Materiales.txt"));
		if(!materiales.isEmpty()) {
			for(Material m : materiales) {
				String linea = m.getNombre()+","+m.getStock()+","+m.getPaisOrigen().getNombre()+"\n";
				archM.write(linea);
			}
		}else {
			archM.write("");
		}
		archM.close();
		
		BufferedWriter archPa = new BufferedWriter(new FileWriter("Paises.txt"));
		if(!paises.isEmpty()) {
			for(Pais p : paises) {
				String linea = p.getNombre()+",";
				if(p.getArma()!=null) {
					linea += "arma"+","+p.getArma().getCantidadProducida()+","+p.getArma().getCodigo()+","+p.getArma().getMaterial().getNombre()+"\n";
				}else {
					linea += "pieza"+","+p.getPieza().getCantidadProducida()+","+p.getPieza().getCodigo()+","+p.getPieza().getMaterial().getNombre()+"\n";
				}
				archPa.write(linea);
			}
		}else {
			archPa.write("");
		}
		archPa.close();
		
		BufferedWriter archPi = new BufferedWriter(new FileWriter("Piezas.txt"));
		if(!piezas.isEmpty()) {
			for(Pieza p : piezas) {
				String linea = p.getNombre()+","+p.getCodigo()+","+p.getTipo()+","+p.getPaisOrigen().getNombre()+","+p.getCantidadMaterial()+"\n";
				archPi.write(linea);
			}
		}else {
			archPi.write("");
		}
		archPi.close();
		
		BufferedWriter archA = new BufferedWriter(new FileWriter("Armas.txt"));
		if(!armas.isEmpty()) {
			for(Arma a : armas) {
				String linea = a.getNombre()+","+a.getCodigo()+","+a.getMunicion()+","+a.getPaisOrigen().getNombre()+","+a.getCantidadMaterial()+"\n";
				archA.write(linea);
			}
		}else {
			archA.write("");
		}
		archA.close();
		
		BufferedWriter archR = new BufferedWriter(new FileWriter("robots.txt"));
		if(!robots.isEmpty()) {
			for(Robot r : robots) {
				String linea = r.getNombre()+",";
				
				for(Pieza p : r.getPiezas()) {
					if(p!=null) {
						linea += p.getNombre()+",";
					}else {
						linea += "sin cualidad,";
					}
				}
				linea += r.getArma().getNombre()+","+r.getEquipo().getNombre()+","+r.getPiloto().getIdentificacion()+"\n";
				archR.write(linea);
			}
		}else {
			archR.write("");
		}
		archR.close();
		
		crearArchModelos();
		
		BufferedWriter archMo = new BufferedWriter(new FileWriter("modelos.txt"));
		if(!modelos.isEmpty()) {
			for(Modelo m : modelos) {
				String linea = m.getCodigo()+","+m.getRobot()+"\n";
				archMo.write(linea);
			}
		}else {
			archMo.write("");
		}
		archMo.close();
		
		System.out.println("SISTEMA CERRADO.");
	}
	
	public void crearArchModelos() throws IOException {
		String ruta = "modelos.txt";
		File arch = new File(ruta);
		if(!arch.exists()) {
			arch.createNewFile();
		}	
	}
	
	public boolean verificarEquipo(String equipo) {
		for(Equipo e: equipos) {
			if(e.getNombre().equals(equipo)) {
				return true;
			}
		}
		return false;
	}

}
