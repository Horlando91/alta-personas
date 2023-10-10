package es.poo.universidad;

import java.util.Scanner;
/**
 * Clase Profesor que extiende de Persona, esta clase dispondrá de un nuevo atributo, este será el salario
 * @author usuario2020
 *
 */
public class Profesor extends Persona{
	private double salario;
	
	@Override
	protected void pedirDatos() {
		Scanner entrada = new Scanner(System.in);
		String nif, nombre, apellidos, fechaNacimiento, edadValida;
		double salario=0;
		
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
		
		do {
			System.out.print("Digite el salario: ");
			salario = entrada.nextDouble();
			
			if((salario==0) && (salario < 1))System.out.print("Compruebe el salario.\n");
		} while ((salario==0) && (salario < 1));
		
		setNif(nif);
		setNombre(primeraLetraMayuscula(nombre));
		setApellidos(primeraLetraMayuscula(apellidos));
		setSalario(salario);
		setFechaNacimiento(fechaNacimiento);
		
	}
	
	@Override
	public void mostrar() {
		System.out.println("Nif: "+ getNif());
		System.out.println("Nombre: "+ getNombre());
		System.out.println("Apellidos: "+ getApellidos());
		System.out.println("Fecha de nacimiento: "+ getFechaNacimiento());
	}

	// Getter
	/**
	 * Obtenemos el salario
	 * @return devolvemos el salario
	 */
	public double getSalario() {
		return salario;
	}

	// Setter
	public void setSalario(double salario) {
		this.salario = salario;
	}
	
}


