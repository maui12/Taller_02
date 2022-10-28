package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

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
		Scanner archM = new Scanner("Materiales.txt");
		while(archM.hasNextLine()) {
			String[] partes = archM.nextLine().split(",");
			Material material = new Material(partes[0], Integer.parseInt(partes[1]));
			materiales.add(material);
		}
		archM.close();
	}
	
	public void leerPiezas() throws FileNotFoundException{
		Scanner archP = new Scanner("Piezas.txt");
		while(archP.hasNextLine()) {
			String[] partes = archP.nextLine().split(",");
			Pieza pieza = new Pieza(partes[0], partes[1], partes[2], Integer.parseInt(partes[4]));
			piezas.add(pieza);
		}
		archP.close();
	}
	
	public void leerArmas() throws FileNotFoundException{
		Scanner archA = new Scanner("Armas.txt");
		while(archA.hasNextLine()) {
			String[] partes = archA.nextLine().split(",");
			Arma arma = new Arma(partes[0], partes[1], Integer.parseInt(partes[2]), Integer.parseInt(partes[4]));
			armas.add(arma);
		}
		archA.close();
	}
	
	public void leerPaises() throws FileNotFoundException{
		Scanner archPa = new Scanner("Paises.txt");
		while(archPa.hasNextLine()) {
			String[] partes = archPa.nextLine().split(",");
			Pais pais = new Pais(partes[0]);
			for(Pais p : paises) {
				if(!p.getNombre().equals(partes[0])){
					paises.add(pais);
				}
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
			for(Material m : materiales) {
				if(m.getNombre().equals(partes[4])) {
					m.setPaisOrigen(pais);
				}
			}
			
		}
		archPa.close();
	}
	
	//
	
	public void leerPersonas() throws FileNotFoundException{
		Scanner archPe = new Scanner("Personas.txt");
		while(archPe.hasNextLine()) {
			String[] partes = archPe.nextLine().split(",");
			Persona persona = new Persona(partes[0], partes[1], partes[2]);
			Equipo equipo = new Equipo(partes[3]);
			if(!verificarEquipo(partes[3])) {
				equipos.add(equipo);
			}
			persona.setEquipo(equipo);
			equipo.agregarPersona(persona);
		}
		archPe.close();
	}
	
	public void leerRobots() throws FileNotFoundException{
		Scanner archR = new Scanner("robots.txt");
		while(archR.hasNextLine()) {
			String[] partes = archR.nextLine().split(",");
			Robot robot = new Robot(partes[0]);
			for(int i=1; i<6; i++) {
				try {
					for(Pieza p : piezas) {
						if(p.getNombre().equals(partes[i])) {
							robot.getPiezas().add(p);
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
		}
		archR.close();
	}
	
	public boolean acceder(String nombre, String id) {
		for(Persona p : personas) {
			if(p.getEquipo().getNombre().equals("ADMINISTRACION")) {
				if(p.getNombre().equals(nombre) && p.getIdentificacion().equals(id)) {
					return true;
				}
			}
		}
		if(nombre.equals("empanada")&&id.equals("porotosconriendas")) {
			//men� secreto
		}
		return false;
	}
	
	public void ingresarPiezaOArma(String elemento) {
		Scanner ing = new Scanner(System.in);
		if(elemento.toLowerCase().equals("pieza")) {
			System.out.print(" - Ingrese nombre de pieza: ");
			String nombre = ing.nextLine();
			System.out.print(" - Ingrese c�digo: ");
			String cod = ing.nextLine();
			System.out.print(" - Ingrese tipo: ");
			String tipo = ing.nextLine();
			System.out.print(" - Ingrese cantidad de piezas: ");
			//falta control de error tipo NumberFormatException
			int cant = Integer.parseInt(ing.nextLine());
			System.out.print(" - Ingrese pa�s de origen: ");
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
			System.out.println("* Pieza ingresada correctamente. *");
		}else if(elemento.toLowerCase().equals("arma")) {
			System.out.print(" - Ingrese nombre de arma: ");
			String nombre = ing.nextLine();
			System.out.print(" - Ingrese c�digo: ");
			String cod = ing.nextLine();
			System.out.print(" - Ingrese cantidad de munici�n: ");
			//falta control de error tipo NumberFormatException
			int muni = Integer.parseInt(ing.nextLine());
			System.out.print(" - Ingrese cantidad de armas: ");
			//falta control de error tipo NumberFormatException
			int cant = Integer.parseInt(ing.nextLine());
			System.out.print(" - Ingrese pa�s de origen: ");
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
			System.out.println("* Arma ingresada correctamente. *");
		}
	}
	
	public void ensamblarRobot(String tipo) {
		Scanner ens = new Scanner(System.in);
		System.out.println("* Piezas tipo CABEZA *");
		for(Pieza p : piezas) {
			if(p.getTipo().equals("cabeza")) {
				System.out.println(p.toString());
			}
		}
		System.out.print(" - Ingrese c�digo de pieza: ");
	}
	
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
				System.out.print("- Ingrese identificaci�n de persona (ingrese 0 para salir): ");
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
					personas.add(p);
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
				numPersonas = piloto + ensam;
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
	
	public void buscarTipo(String tipo) {
		for(Pais p : paises) {
			if(p.getPieza().getTipo().equals(tipo)) {
				if(p.getPieza().getCantidadProducida()>0) {
					System.out.println(p.getNombre()+", Cantidad disponible: "+p.getPieza().getCantidadProducida());
				}
			}
		}
	}
	
	public void buscarMaterial(String material) {
		for(Material m : materiales) {
			if(m.getNombre().equals(material)) {
				if(m.getStock()>0) {
					System.out.println(m.getPaisOrigen().getNombre()+", Stock: "+m.getStock());
				}
			}
		}
	}
	
	public void crearModelo() {
		for(Robot r : robots) {
			int suma = 0;
			for(int i=0; i<5; i++) {
				int cod = Integer.parseInt(r.getPiezas().get(i).getCodigo());
				suma += cod;
			}
			String codigo = Integer.toString(suma)+r.getEquipo().getNombre()+r.getPiloto().getNombre().charAt(0);
			Modelo modelo = new Modelo(codigo, r.getNombre());
			modelos.add(modelo);
		}
	}
	//falta terminar
	public void revisarPiezas(String robot) {
		for(Robot r : robots) {
			if(r.getNombre().equals(robot)) {
				for(Pieza p : r.getPiezas()) {
					if(!r.getPiezas().get(0).getTipo().equals("brazos")) {
						System.out.println("Pieza erronea, la 1ra pieza debe ser de tipo brazos.");
					}
					if(!r.getPiezas().get(1).getTipo().equals("piernas")) {
						System.out.println("Pieza erronea, la 2da pieza debe ser de tipo piernas.");
					}
					if(!r.getPiezas().get(2).getTipo().equals("torax")) {
						System.out.println("Pieza erronea, la 3ra pieza debe ser de tipo t�rax.");
					}
					if(!r.getPiezas().get(3).getTipo().equals("cabeza")) {
						System.out.println("Pieza erronea, la 4ta pieza debe ser de tipo cabeza.");
					}
					if(!r.getPiezas().get(4).getTipo().equals("cualidad extra")) {
						System.out.println("Pieza erronea, la 5ta pieza debe ser de tipo cualidad extra.");
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
				System.out.println("-Nombre: " + p.getNombre() + " -Equipo: " + p.getEquipo().getNombre());
			}
		}
		System.out.println("Pilotos: ");
		for(Persona p1 : personas) {
			if(p1.getEspecialidad().equalsIgnoreCase("piloto")) {
				System.out.println("-Nombre: " + p1.getNombre() + " -Equipo: " + p1.getEquipo().getNombre());
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
			System.out.println(e.getNombre());
		}
		System.out.println("-----------------------");
		
		
	}

	public void mostrarRobots() {
		System.out.println("-----------------------");
		System.out.println("Robots: ");
		for(Robot r: robots) {
			System.out.println(r.toString());
		}
		System.out.println("-----------------------");
	}

	public void revisarMunicion() {
		Scanner rev = new Scanner(System.in);
		mostrarRobots();
		System.out.print(" - Ingrese nombre de robot: ");
		String nombre = rev.nextLine();
		for(Robot r : robots) {
			if(r.getNombre().equals(nombre)) {
				System.out.print(" - Ingrese cantidad de munici�n a aumentar: ");
				//falta control de error tipo NumberFormatException
				int muni = Integer.parseInt(rev.nextLine());
				r.getArma().setMunicion(r.getArma().getMunicion()+muni);
			}
		}
		System.out.println("* Munici�n aumentada. *");
	}
	
	public void mostrarPaises() {
		System.out.println("-----------------------");
		System.out.println("Paises: ");
		for(Pais p : paises) {
			System.out.println(p.getNombre());
		}
		System.out.println("-----------------------");
	}
	
	public boolean anadirStockP(String pais) {
		Scanner stock = new Scanner(System.in);
		System.out.println("-----------------------");
		System.out.println("A�adir stock de pieza: ");
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
		System.out.println("* No se encontr� el pa�s. *");
		System.out.println("-----------------------");
		return false;
	}
	
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
	
	public boolean anadirStockM(String material) {
		Scanner st = new Scanner(System.in);
		System.out.println("-----------------------");
		System.out.println("A�adir stock de material: ");
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
		System.out.println("* No se encontr� el material. *");
		System.out.println("-----------------------");
		return false;
	}
	
	public void cambiarPiezas(String robot) {
		
	}
	
	public void activarRecursoNuclear() {
		
	}
	
	public void destruirTodo() {
		
	}
	
	public void cerrarSistema() {
		
	}
	
	public boolean crearArchModelos() {
		return false;
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
