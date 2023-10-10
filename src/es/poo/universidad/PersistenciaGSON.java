package es.poo.universidad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.crypto.AEADBadTagException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
/**
 * Persistencia, encargada de almacenar y recuperar los datos de las personas.
 * Almacenaremos y recuperaremos la información de, los alumnos, profesores asociados y permentes.
 * @author usuario2020
 *
 */

public class PersistenciaGSON {
	private static ArrayList<Persona> persistence = null;
	
	/**
	 * Constructor
	 */
	public PersistenciaGSON() {
		if(PersistenciaGSON.persistence == null) {
			try {
				this.persistenciaFromJSON();
			}
			catch(ClassNotFoundException e) { PersistenciaGSON.persistence = null; }
			catch(IOException e) { PersistenciaGSON.persistence = null; }
		}
	}
	
	/**
	 * Obtenemos los datos de las personas
	 * @return Devolvemos los datos si la persitencia tiene datos, caso contrario, devovemos el array de datos con memoria reservada
	 */
	public ArrayList<Persona> getPersistence(){
		
		if (persistence==null) {
			return new ArrayList<>();
		}
		
		return PersistenciaGSON.persistence;
	}

	
	public void setPersistence(ArrayList<Persona> persistence) {
		PersistenciaGSON.persistence = persistence;	
		this.persistenciaToJSON();
	}
	
	/**
	 * Se realiza la persistencia a partir del arraylist con las personas, en un archivo de tipo json
	 */
	
	private void persistenciaToJSON() {

    	RuntimeTypeAdapterFactory<Persona> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
			    .of(Persona.class, "tipo")
			    .registerSubtype(Persona.class, Persona.class.getName())
			    .registerSubtype(Alumno.class, Alumno.class.getName())
			    .registerSubtype(Profesor.class, Profesor.class.getName())
			    .registerSubtype(Asociado.class, Asociado.class.getName())
    	 		.registerSubtype(Permanente.class, Permanente.class.getName());
    			
    	Gson gson = new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();
    	
		java.lang.reflect.Type listType = new TypeToken<ArrayList<Persona>>() {}.getType();
		
		String object = gson.toJson(PersistenciaGSON.persistence, listType);

		try{
			
				//Intentamos abrir el fichero de texto
				FileOutputStream fop;
				File file = new File("./files/Personas.json");
				fop = new FileOutputStream(file);
				OutputStreamWriter osw = new OutputStreamWriter(fop);

				osw.write(object);//Escribimos la informaciÃ³n
				osw.close();//Y cerramos el fichero de texto

		}
		catch (IOException e){}
		}
	
	/**Recupera el archivo persistido en el arraylist de personas
	 * 
	 * @throws IOException : Error al introducir el archivo
	 * @throws ClassNotFoundException : Si lo que hay en el archivo no coincide con un arraylist de personas
	 */
	
	private void persistenciaFromJSON() throws IOException, ClassNotFoundException {
		String cadena = "";

		try {//Componemos una cadena de texto a partir de varias lineas de archivo, donde estan guardados los datos de los usuarios
			FileInputStream fis;
			File file = new File("./files/Personas.json");
			fis = new FileInputStream(file);
			if (fis != null) {
				InputStreamReader isr = new InputStreamReader(fis);
				BufferedReader br = new BufferedReader(isr);
				String linea = "";
				StringBuilder sb = new StringBuilder();
				while ((linea = br.readLine()) != null) {
					sb.append(linea);//Vamos leyendo linea a linea del texto y los vamos concatenando en un StringBuilder
				}
			isr.close();
			cadena = sb.toString();//El StringBuilder lo transformamos a string
			}
		}
		catch (FileNotFoundException e) { PersistenciaGSON.persistence = null; }
		catch (IOException e) { PersistenciaGSON.persistence = null; }

		if (cadena.isEmpty()) {
			PersistenciaGSON.persistence = null;
		}
		else {
			//Deserializar
			
	    	RuntimeTypeAdapterFactory<Persona> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
				    .of(Persona.class, "tipo")
				    .registerSubtype(Persona.class, Persona.class.getName())
				    .registerSubtype(Alumno.class, Alumno.class.getName())
				    .registerSubtype(Profesor.class, Profesor.class.getName())
				    .registerSubtype(Asociado.class, Asociado.class.getName())
	    	 		.registerSubtype(Permanente.class, Permanente.class.getName());
			
	    	Gson gson = new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();
	    	
			java.lang.reflect.Type listType = new TypeToken<ArrayList<Persona>>() {}.getType();
			PersistenciaGSON.persistence = gson.fromJson(cadena, listType);
		}
	}
}