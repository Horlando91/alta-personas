package es.poo.universidad;

import java.util.ArrayList;

import java.util.Scanner;
/**
 * Clase hija Alumno que proviene de Persona, esta clase obtendrá un nuevo atributo que serán las asignaturas del alumno
 * @author usuario2020
 *
 */
public class Alumno extends Persona {
	
	private ArrayList<String> asignaturas;

	// Constructor
	public Alumno() {
		this.asignaturas = new ArrayList<>();
	}
	
	@Override
	public void pedirDatos() {
		
		Scanner entrada = new Scanner(System.in);
		String nif, nombre, apellidos, fechaNacimiento, edadValida;
		int numeroAsignaturas=0;
		
		do {
			System.out.print("Digite el nif: ");
			nif = entrada.next();
			
			if (comprobarNifUnico(nif))System.out.println("El nif ya existe el la base de datos.\n");
			
		} while (comprobarNifUnico(nif));
		
		System.out.print("Digite el nombre: ");
		nombre = entrada.next();
		
		System.out.print("Digite los apellidos: ");
		apellidos = entrada.next();
		
		do {
			System.out.print("Digite la fecha de nacimiento(dd/MM/yyyy): ");
			fechaNacimiento = entrada.next();
			edadValida = calcularEdad(fechaNacimiento);
			
		} while (edadValida.equals(""));
		
		numeroAsignaturas = pedirAsignaturas();
		
		if(numeroAsignaturas==0)return;
		
		for (int i = 0; i<numeroAsignaturas; i++) {
			System.out.print("Introduzca el nombre de la asignatura: ");
			asignaturas.add(entrada.next());
		}
		
		setNif((nif));
		setNombre(primeraLetraMayuscula(nombre));
		setApellidos(primeraLetraMayuscula(apellidos));
		setFechaNacimiento(fechaNacimiento);
		
	}
	
	// Getter
	public ArrayList<String> getAsignaturas() {
		return asignaturas;
	}

	// Setter
	public void setAsignaturas(ArrayList<String> asignaturas) {
		this.asignaturas = asignaturas;
	}

	/**
	 * Devuelve el número de asignaturas en las que nos vamos a matricular
	 * @return devuelve el número de asignaturas en un número de tipo entero
	 */
	private Integer pedirAsignaturas() {
		int numeroAsignaturas=0;
		Scanner entrada = new Scanner(System.in);
		System.out.print("Digite el número de asignaturas matriculadas: ");
		
		 try {
			 numeroAsignaturas = Integer.parseInt(entrada.next());
		} catch (NumberFormatException e) {
				System.out.print("Debe introducir un número.\n");
				
		} catch(Exception e) {
				System.out.println("Ha ocurrido un error"); 
		}
		 
		 return numeroAsignaturas;
	}
	
	@Override
	public void mostrar() {
		System.out.println(":::::::::::::::::::::::::::::::::::::");
		System.out.println("Nif: "+ getNif());
		System.out.println("Nombre: "+ getNombre());
		System.out.println("Apellidos: "+ getApellidos());
		System.out.println("Fecha de nacimiento: "+ getFechaNacimiento());
		mostrarEdad();
		System.out.println(":::::::::::::ASIGNATURAS MATRICULADAS::::::::::::::");
		for (String nombreAsignatura : asignaturas) {
			System.out.println("Asignatura: "+nombreAsignatura);
		}
	}
}
