package es.poo.universidad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 * Clase Asociado que extiende de Profesor, esta clase obtendrá un nuevo atributo que será el tipo de asociado
 * @author usuario2020
 *
 */
public class Asociado extends Profesor{
	private String tipoAsociado;
	private ArrayList<String> listaTipos;
	
	// Constructor
	public Asociado() {
		this.listaTipos = new ArrayList<>();
	}
	
	/**
	 * Agregamos el tipo de asociado 
	 */
	public void agregarTipo() {
		agregarTipos();
		
		pedirDatos();
		Scanner entrada = new Scanner(System.in);
		do {
			System.out.print("Seleccionar tipo(P03, P04, P05, P06): ");
			this.tipoAsociado=entrada.next();
			setTipo(primeraLetraMayuscula(this.tipoAsociado));
			
			if (!listaTipos.contains(this.tipoAsociado)) System.out.println("Tipo erróneo.");
				
		} while (!listaTipos.contains(this.tipoAsociado));
	}
	
	/**
	 * Agregamos una colección de tipos en un array list
	 */
	private void agregarTipos() {
		Collections.addAll(this.listaTipos, "P03","P04", "P05", "P06");
	}
	
	@Override
	public void mostrar() {
		System.out.println("");
		super.mostrar();
		System.out.println("Tipo de asociado: "+ tipoAsociado);
		mostrarEdad();
	}
	
	// Getter
	/**
	 * Obtenemos el tipo
	 * @return devolvemos el tipo en formato String
	 */
	public String getTipo() {
		return tipoAsociado;
	}

	// Setter
	public void setTipo(String tipo) {
		this.tipoAsociado = tipo;
	}
}
