package es.poo.universidad;

import java.util.Scanner;

/**
 * Clase Permanente hereda de Profesor, esta clase tiene como nuevo atributo, la antiguedad que son los años 
 * @author usuario2020
 *
 */
public class Permanente extends Profesor {
	private int antiguedad;
	
	/**
	 * Constructor
	 * @param antiguedad: número de antiguedad
	 */
	public Permanente(int antiguedad) {
		this.antiguedad=antiguedad;
	}
	
	/**
	 * Agregamos la antiguedad(número de años)
	 */
	public void agregarAntiguedad() {
		super.pedirDatos();
		Scanner entrada = new Scanner(System.in);
		System.out.print("Años trabajados: ");
		this.antiguedad = Integer.parseInt(entrada.next());
	}
	
	@Override
	public void mostrar() {
		System.out.println("");
		super.mostrar();
		System.out.println("Antiguedad: "+ this.antiguedad+" años");
		mostrarEdad();
	}

}
