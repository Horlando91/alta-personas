package es.poo.universidad;

import java.util.Scanner;

/**
 * Clase Permanente hereda de Profesor, esta clase tiene como nuevo atributo, la antiguedad que son los a�os 
 * @author usuario2020
 *
 */
public class Permanente extends Profesor {
	private int antiguedad;
	
	/**
	 * Constructor
	 * @param antiguedad: n�mero de antiguedad
	 */
	public Permanente(int antiguedad) {
		this.antiguedad=antiguedad;
	}
	
	/**
	 * Agregamos la antiguedad(n�mero de a�os)
	 */
	public void agregarAntiguedad() {
		super.pedirDatos();
		Scanner entrada = new Scanner(System.in);
		System.out.print("A�os trabajados: ");
		this.antiguedad = Integer.parseInt(entrada.next());
	}
	
	@Override
	public void mostrar() {
		System.out.println("");
		super.mostrar();
		System.out.println("Antiguedad: "+ this.antiguedad+" a�os");
		mostrarEdad();
	}

}
