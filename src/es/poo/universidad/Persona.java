package es.poo.universidad;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Clase abstracta Persona, representar� a una persona la cual dispondr� de hijos
 * @author usuario2020
 *
 */
public abstract class Persona{
	private String nif;
	private String nombre;
	private String apellidos;
	private String fechaNacimiento;
	protected static ArrayList<Persona> personas;
	

	/**
	 * Funci�n abstracta que servir� para pedir datos
	 */
	protected abstract void pedirDatos();
	
	/**
	 * Funci�n abstracta que servir� para mostrar los datos
	 */
	public abstract void mostrar();
	
	/**
	 * Mostramos la edad
	 */
	protected final void mostrarEdad() {
		
		System.out.print("Edad: "+ calcularEdad(this.fechaNacimiento));
	}
	
	/**
	 * Funci�n que permite calcular la edad a partir de la una fecha de nacimiento
	 * @param edad: Es la fecha de nacimiento en formato dd/MM/yyyy
	 * @return devuelve la edad con los a�os, meses y d�as
	 */
	public String calcularEdad(String edad) {

		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		try {
			LocalDate fechaNac = LocalDate.parse(edad, fmt);
			LocalDate ahora = LocalDate.now();
			Period periodo = Period.between(fechaNac, ahora);
			
			String edadcompleta = String.format(periodo.getYears()+" a�os "+periodo.getMonths()+" meses "+periodo.getDays()+" d�as\n");
			
			return edadcompleta;
                    
		} catch (DateTimeParseException dte) {
			System.out.println("Formato o fecha incorrecta.");
			System.out.println("Formato correcto dd/MM/yyyy.");
			System.out.println("dd:d�a\n"
								+ "MM:mes\n"
								+ "yyyy: a�o\n");
		}
		return "";
		
	}
	
	
	/**
	 * Convierte la primera letra de una palabra a may�scula
	 * @param valor: ser� la palabra a ser transformada
	 * @return devolvemos la palabra ya transformada
	 */
	public static String primeraLetraMayuscula(String valor) {
	    if (valor == null || valor.isEmpty()) {
	        return valor;
	    } else {       
	        return  valor.toUpperCase().charAt(0) + valor.substring(1, valor.length()).toLowerCase();
	    }
	}
	
	/**
	 * Busca una persona en un arraylist
	 * @param nif: nif de la persona a buscar
	 * @param personalist: lista con las personas disponibles
	 * @return devolvemos un objeto de tipo Persona o nulo si no existe
	 */
	public static Persona buscarPersona(String nif, ArrayList<Persona> personalist, String tipo) {
			
		return personalist.stream().filter(user -> (user.getNif().equalsIgnoreCase(nif)) && user.getClass().getSimpleName().equals(tipo))
				.findFirst()
	        	.orElse(null);
	}
	
	/**
	 * Comprobamos la existencia de un nif en la lista de personas
	 * @param nif: nif de la persona
	 * @return devolvemos true si la persona existe, false si no existe
	 */
	protected boolean comprobarNifUnico(String nif) {
	
		for (Persona persona : personas) {
			if (persona.getNif().equals(nif)) return true; 
		}
		return false;
	}
	
	// Setters
	
	protected void setNif(String nif) {
		this.nif = nif;
	}
	
	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	protected void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	protected void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	// Getters
	/**
	 * Devuelve un nif
	 * @return devuelve un nif en una cadena de tipo String
	 */
	protected String getNif() {
		return nif;
	}

	/**
	 * Devuelve un nombre
	 * @return devuelve un nombre en una cadena de tipo String
	 */
	protected String getNombre() {
		return nombre;
	}

	/**
	 * Devuelve los apellidos o apellido
	 * @return devuelve los apellidos en una cadena de tipo String
	 */
	protected String getApellidos() {
		return apellidos;
	}

	/**
	 * Devuelve la fecha de nacimiento
	 * @return devuelve la fecha de nacimiento en una cadena de tipo String
	 */
	protected String getFechaNacimiento() {
		return fechaNacimiento;
	}
	
}
