package taller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		//Bastián Felipe Perines Flores
		//22.386.978-5
		//ICCI
		
		//archivos a leer y guardar su informacion
		File usuarios = new File("Usuarios.txt");
		File registros = new File("Registros.txt");
		
		//arreglos para el archivo Usuarios.txt
		String [] id = new String [3];
		String [] contraseñas = new String[3];
		
		//arreglos para el archivo Registros.txt
		String [] usuario = new String[300];
		String [] fecha = new String[300];
		int [] horas = new int[300];
		String [] actividad = new String[300];
		
		//try - catch que lee y guarda el archivo Usuarios.txt
		try (Scanner lectorUsuarios = new Scanner(usuarios)) {
			int i = 0;
			while (lectorUsuarios.hasNext()) {
				String linea = lectorUsuarios.nextLine();
				String[] partes = linea.split(";");
				id [i] = partes[0];
				contraseñas [i] = partes[1];
				i++;
				}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();			
		}
		
		//try - catch que lee y guarda el archivo Registros.txt
		try (Scanner lectorRegistros = new Scanner(registros)) {
			int i = 0;
			while (lectorRegistros.hasNext()) {
				String linea = lectorRegistros.nextLine();
				String[] partes = linea.split(";");
				usuario[i] = partes[0];
				fecha [i] = partes[1];
				horas [i] = Integer.parseInt(partes[2]);
				actividad[i] = partes[3];
				i++;
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//try - catch que sobreescribe el archivo Usuarios.txt
		//en caso de que no se haga ningun cambio lo sobreescribira igual pero no cambiara el contenido
		//como si usaras suprimir y luego Ctrl + z
		
		try (PrintWriter sobreEscribirUsuarios = new PrintWriter(usuarios)) {
			int i = 0;
			while (i<3) {
			sobreEscribirUsuarios.println(id[i] + ";" + contraseñas[i]);
			i++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//try - catch que sobreexcribe el archivo Registros.txt
		//se aplica lo mismo que con la sobreescritura anterior
		
		try (PrintWriter sobreEscribirRegistros = new PrintWriter(registros)) {
			int i = 0;
			while (i<300 && usuario[i] != null) {
				sobreEscribirRegistros.println(usuario[i] + ";" + fecha[i] + ";" + horas [i] + ";" + actividad[i]);
				i++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
