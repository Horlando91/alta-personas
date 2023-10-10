package es.poo.universidad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main{

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		  
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		PersistenciaGSON pg = new PersistenciaGSON();
		
		// Obtenemos los alumnos cargados en el objeto pg
		Persona.personas = pg.getPersistence();
		
		menuApp(br, pg.getPersistence(), pg);
		System.gc();
	}
	
	/**
	 * Menú de la aplicación
	 * @param br: nos permite introducir datos por consola
	 * @param personaslist: la lista de personas
	 * @param pg: objeto de tipo Gson con los datos de las personas cargadas desde un fichero
	 * @throws IOException
	 */
	private static void menuApp(BufferedReader br, ArrayList<Persona> personaslist, PersistenciaGSON pg) throws IOException {
		
		int opc;
		
		do {
			System.out.println("1. Alumnos");
			System.out.println("2. Profesores");
			System.out.println("3. Salir");
			System.out.print("Opción: ");
			opc = pedirNumero(br);
			
			switch (opc) {
			case 1:
				menuAlumnos(br, personaslist, pg);
				break;
			case 2:
				menuProfesores(br, personaslist, pg);
				break;
			}
			
		}while(opc!=3);
		
		mensajeDesdepedida();
		
	}
	
	/**
	 * Agregamos una persona, puede ser un alumno o profesor(permanente o asociado)
	 * @param br: nos permite introducir datos por consola
	 * @param personaslist: la lista de personas
	 * @param pg: objeto de tipo Gson con los datos de las personas cargadas desde un fichero
	 * @param persona: nombre del tipo de persona
	 * @throws IOException
	 */
	private static void agregarPersona(BufferedReader br, ArrayList<Persona> personaslist, PersistenciaGSON pg, String persona) throws IOException {
		
		switch (persona) {
		case "alumno":
					Alumno al = new Alumno();
			
					al.pedirDatos();
					
					if(!(al.getAsignaturas().isEmpty()))al.mostrar();
					
					Persona.personas.add(al);
					
					pg.setPersistence(Persona.personas);
			break;
			
		case "permanente":
			
					Permanente permanente = new Permanente(0);
					
					permanente.agregarAntiguedad();
					
					Persona.personas.add(permanente);
					
					pg.setPersistence(Persona.personas);
			
			break;
			
		case "asociado":
			
					Asociado asociado = new Asociado();
					
					asociado.agregarTipo();
					
					Persona.personas.add(asociado);
					
					pg.setPersistence(Persona.personas);
			
			break;

		}
		
	}
	
	/** Función para pedir un número 
	 * @param br: nos permite introducir datos por consola
	 * @return devolvemos un número entero
	 * @throws IOException
	 */
	private static Integer pedirNumero(BufferedReader br) throws IOException {
		int opc=0;
		
		 try {
				opc = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e) {
				System.out.print("Debe introducir un número.\n");
				
		} catch(Exception e) {
				System.out.println("Ha habido un error");
		}
		 return opc;
	}
	
	/**
	 * Menú de los alumnos
	 * @param br: nos permite introducir datos por consola
	 * @param personaslist: la lista de personas
	 * @param pg: objeto de tipo Gson con los datos de las personas cargadas desde un fichero
	 * @throws IOException
	 */
	private static void menuAlumnos(BufferedReader br, ArrayList<Persona> personaslist, PersistenciaGSON pg) throws IOException {
	int opc;
	
		do {
			System.out.println("\n:::::::::ALUMNOS:::::::::");
			System.out.println("1. Agregar");
			System.out.println("2. Eliminar");
			System.out.println("3. Ver alumnos");
			System.out.println("4. Atrás");
			System.out.print("Opción: ");
			opc = pedirNumero(br);
			
			switch (opc) {
			case 1:
				agregarPersona(br, personaslist, pg, "alumno");
				break;
				
			case 2:
				eliminarPersona(personaslist, pg, br, "Alumno");
				break;
				
			case 3:
				mostrarAlumnos(personaslist, "Alumno");
				break;

			default:
				volverAtrasMensaje();
				break;
			}
			
		}while(opc!=4);
		
	}
	
	/**
	 * Menú de los profesores permanentes
	 * @param br: nos permite introducir datos por consola
	 * @param personaslist: la lista de personas
	 * @param pg: objeto de tipo Gson con los datos de las personas cargadas desde un fichero
	 * @throws IOException
	 */
	private static void menuPermanente(BufferedReader br, ArrayList<Persona> personaslist, PersistenciaGSON pg) throws IOException {
		int opc;
		
			do {
				System.out.println("\n:::::::::PROFESORES PERMANENTES:::::::::");
				System.out.println("1. Agregar");
				System.out.println("2. Eliminar");
				System.out.println("3. Ver profesores");
				System.out.println("4. Atrás");
				System.out.print("Opción: ");
				opc = pedirNumero(br);
				
				switch (opc) {
				case 1:
					agregarPersona(br, personaslist, pg, "permanente");
					break;
					
				case 2:
					eliminarPersona(personaslist, pg, br, "Permanente");
					break;
					
				case 3:
					mostrarProfesores(personaslist, "Permanente");
					break;
					
				default:
					opcionIncorrectaMensaje();
					break;
				}
				
			}while(opc!=4);
			
	}
	
	/**
	 * Menú de los profesores asociados
	 * @param br: nos permite introducir datos por consola
	 * @param personaslist: la lista de personas
	 * @param pg: objeto de tipo Gson con los datos de las personas cargadas desde un fichero
	 * @throws IOException
	 */
	private static void menuAsociado(BufferedReader br, ArrayList<Persona> personaslist, PersistenciaGSON pg) throws IOException {
		int opc;
		
			do {
				System.out.println("\n:::::::::PROFESORES ASOCIADOS:::::::::");
				System.out.println("1. Agregar");
				System.out.println("2. Eliminar");
				System.out.println("3. Ver profesores");
				System.out.println("4. Atrás");
				System.out.print("Opción: ");
				opc = pedirNumero(br);
				
				switch (opc) {
				case 1:
					agregarPersona(br, personaslist, pg, "asociado");
					break;
					
				case 2:
					eliminarPersona(personaslist, pg, br, "Asociado");
					break;
					
				case 3:
					mostrarProfesores(personaslist, "Asociado");
					break;

				default:
					volverAtrasMensaje();
					break;
				}
				
			}while(opc!=4);
			
	}
	
	/**
	 * Menú de los profesores
	 * @param br: nos permite introducir datos por consola
	 * @param personaslist: la lista de personas
	 * @param pg: objeto de tipo Gson con los datos de las personas cargadas desde un fichero
	 * @throws IOException
	 */
	private static void menuProfesores(BufferedReader br, ArrayList<Persona> personaslist, PersistenciaGSON pg) throws IOException {
		int opc;
		
			do {
				System.out.println("\n:::::::::PROFESORES:::::::::");
				System.out.println("1. Asociados");
				System.out.println("2. Permanentes");
				System.out.println("3. Atrás");
				System.out.print("Opción: ");
				opc = pedirNumero(br);
				
				switch (opc) {
				case 1:
					menuAsociado(br, personaslist, pg);
					break;
					
				case 2:
					menuPermanente(br, personaslist, pg);
					break;

				default:
					volverAtrasMensaje();
					break;
				}
				
			}while(opc!=3);
			
	}
	
	/**
	 * Eliminar una persona de la lista de personas
	 * @param personaslist: la lista de personas
	 * @param pg: objeto de tipo Gson con los datos de las personas cargadas desde un fichero
	 * @param br: nos permite introducir datos por consola
	 * @throws IOException
	 */
	private static void eliminarPersona(ArrayList<Persona> personaslist, PersistenciaGSON pg, BufferedReader br, String tipo) throws IOException {
		String nif="";
		System.out.print("Introducir nif: ");
		nif=br.readLine();
		
		Persona p = Persona.buscarPersona(nif, personaslist, tipo);
		
		if (p==null) {System.out.println("No se puede eliminar, dicho nif NO existe para el tipo, "+tipo);return;}
			
		System.out.print("Eliminando persona con nif: "+nif);
		personaslist.remove(p);
		
		pg.setPersistence(personaslist);
		
	}

	/**
	 * 
	 * @param personaslist: la lista de personas
	 * @param persona: nombre del tipo de persona
	 */
	private static void mostrarAlumnos(ArrayList<Persona> personaslist, String persona) {
		
		if(personaslist.isEmpty()) {System.out.println("No hay alumnos dados de alta"); return;} 
		
		for (Persona alumno : personaslist) {
			// 
			if(alumno.getClass().getSimpleName().equals(persona)) {
				alumno.mostrar();
			}
		}
		
	}
	
	/**
	 * Muestra el tipo de profesor con sus respectivos datos
	 * @param personaslist: la lista de personas
	 * @param persona: nombre del tipo de persona
	 */
	private static void mostrarProfesores(ArrayList<Persona> personaslist, String persona) {
		
		if(personaslist.isEmpty()) {System.out.println("No hay profesores dados de alta"); return;}
		
		if (persona.equals("Asociado")) {
			for (Persona alumno : personaslist) {
				if(alumno.getClass().getSimpleName().equals(persona)) {
					alumno.mostrar();
				}
			}
		}
		
		if (persona.equals("Permanente")) {
			for (Persona alumno : personaslist) {
				if(alumno.getClass().getSimpleName().equals(persona)) {
					alumno.mostrar();
				}
				
			}
		}
			
	}
	
	/**
	 * Mensaje con la opción incorrecta
	 */
	private static void opcionIncorrectaMensaje() {
		System.out.println("\nOpción incorrecta.\n");
	}
	
	/**
	 * Mensaje de volver hacia atrás
	 */
	private static void volverAtrasMensaje() {
		System.out.println("\nVolviendo hacia atrás.\n");
	}
	
	/**
	 * Mensaje de despedida
	 */
	private static void mensajeDesdepedida() {
		System.out.println("\nHasta la próxima.\n");
	}
	
}
