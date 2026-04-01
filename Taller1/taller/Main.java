package taller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
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
		
		//arreglos para cada usuario
		
		//Martin
		String [] auxMartin = new String[300];
		String [] fechaMartin = new String[300];
		int [] horasMartin = new int[300];
		String [] actividadMartin = new String[300];
		
		//Catalina
		String [] auxCatalina = new String[300];
		String [] fechaCatalina = new String[300];
		int [] horasCatalina = new int[300];
		String [] actividadCatalina = new String[300];
		
		//Estefania
		String [] auxEstefania = new String[300];
		String [] fechaEstefania = new String[300];
		int [] horasEstefania = new int[300];
		String [] actividadEstefania = new String[300];
		
		
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
			int m = 0;
			int c = 0;
			int e = 0;
			while (lectorRegistros.hasNext()) {
				String linea = lectorRegistros.nextLine();
				String[] partes = linea.split(";");
				usuario [i] = partes[0];
				fecha [i] = partes[1];
				horas [i] = Integer.parseInt(partes[2]);
				actividad [i] = partes[3];
				
				if (partes[0].equals("Martin")) {
					auxMartin [m]= partes[0];
					fechaMartin [m] = partes[1];
					horasMartin [m] = Integer.parseInt(partes[2]);
					actividadMartin [m] = partes[3];
					m++;
				} else if (partes[0].equals("Catalina")) {
					auxCatalina [c]= partes[0];
					fechaCatalina [c] = partes[1];
					horasCatalina [c] = Integer.parseInt(partes[2]);
					actividadCatalina [c] = partes[3];
					c++;
				} else if (partes[0].equals("Estefania")) {
					auxEstefania [e]= partes[0];
					fechaEstefania [e] = partes[1];
					horasEstefania [e] = Integer.parseInt(partes[2]);
					actividadEstefania [e] = partes[3];
					e++;
				}			
				i++;			
			}		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Scanner sc = new Scanner(System.in);
		
		//inicio de la interaccion con el sistema
		System.out.println("Bienvenido! ¿a que deseas acceder?");
		System.out.print("1) Menu de usuarios \n2) Menu de analisis \n3) Salir\nrespuesta: ");	
		int entrada = menuPrincipal();
		String usuarioActual;
		String contraseñaActual;
		boolean usuarioValido = true;
		boolean contraseñaCorrecta = true;
		
		String respuesta = menuUsuarios(entrada, contraseñas);
		
		

		
		
		
		
		
		
		
		sc.close();
		//try - catch que sobreescribe el archivo Usuarios.txt
		//en caso de que no se haga ningun cambio lo sobreescribira igual pero no cambiara el contenido
		//como si usaras suprimir y luego Ctrl + z	
		try (BufferedWriter sobreEscribirUsuarios = new BufferedWriter(new FileWriter("Usuarios.txt",false))) {
			int i = 0;
			do {
				if (i>0) {
					sobreEscribirUsuarios.newLine(); }
			
				sobreEscribirUsuarios.write(id[i] + ";" + contraseñas[i]);
				i++;
			} while(i<3);

			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//try - catch que sobreexcribe el archivo Registros.txt
		//se aplica lo mismo que con la sobreescritura anterior
		try (BufferedWriter sobreEscribirRegistros = new BufferedWriter(new FileWriter("Registros",false))) {
			int i = 0;
			do {
				if (i>0) {
					sobreEscribirRegistros.newLine(); }
				
				sobreEscribirRegistros.write(usuario[i] + ";" + fecha[i] + ";" + horas [i] + ";" + actividad[i]);
				i++;
			} while(i<300);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//funcion menu principal, osea:
	//1) Menu de usuarios
	//2) Menu de analisis
	//3) Salir
	public static int menuPrincipal() {
		int entrada = -1;
		boolean entradaValida = true;
		Scanner sc = new Scanner(System.in);
		do {
			try {
				entrada = sc.nextInt();
				entradaValida = false;
				if (entrada > 3 || entrada < 1) {
					entradaValida = true;
					System.out.print("no existe la opcion " + entrada + " intentelo de nuevo. \nrespuesta: ");
				}
			} catch (InputMismatchException e) {
				System.out.println("Error: entrada invalida.");
				System.out.print("intente de nuevo: ");
				sc.next(); }
			} while (entradaValida);
		return entrada;
		
	}
	public static String menuUsuarios(int entrada, String[] contraseñas) {
		String usuarioActual;
		String contraseñaActual;
		boolean usuarioValido = true;
		boolean contraseñaCorrecta = true;
		Scanner sc = new Scanner(System.in);
		
		//reaccion del sistema dependiendo la opcion elegida previamente en menuPrincipal()
		switch(entrada) {
		
		//caso 1, menu de usuario, pide el ID de usuario y la respectiva contraseña.
		case (1):
			do {
			System.out.print("usuario: ");
			usuarioActual = sc.nextLine();
			
			if (usuarioActual.equals("Martin")) {
				usuarioValido = false;
				System.out.println("usuario encontrado.");
				do {
					System.out.print("introduzca su contraseña: ");
					contraseñaActual = sc.nextLine();
					if (contraseñaActual.equals(contraseñas[0])) {
						contraseñaCorrecta = false;
						return "0";
					}
					System.out.println("contraseña incorrecta, intentelo de nuevo.");
				} while(contraseñaCorrecta);
				break;
			} else if (usuarioActual.equals("Catalina")) {
				usuarioValido = false;
				System.out.println("usuario encontrado.");
				do {
					System.out.print("introduzca su contraseña: ");
					contraseñaActual = sc.nextLine();
					if (contraseñaActual.equals(contraseñas[1])) {
						contraseñaCorrecta = false;
						return "1";
					}
					System.out.println("contraseña incorrecta, intentelo de nuevo.");
				} while(contraseñaCorrecta);
				break;
			}else if (usuarioActual.equals("Estefania")) {
				usuarioValido = false;
				System.out.println("usuario encontrado.");
				do {
					System.out.print("introduzca su contraseña: ");
					contraseñaActual = sc.nextLine();
					if (contraseñaActual.equals(contraseñas[2])) {
						contraseñaCorrecta = false;
						return "2";
					}
					System.out.println("contraseña incorrecta, intentelo de nuevo.");
				} while(contraseñaCorrecta);
				break; }
			
			System.out.println("usuario no registrado, intente de nuevo.");
			} while (usuarioValido);
			break;
		
		//caso 2, menu de analisis, aun por desarrollar.
		case(2):
			return "caso 2";
		
		
		//caso 3, salir del sistema. (shutdown)
		case(3):
			System.out.println("bye!");
			return "salir";
		}
		return "esto ni va a aparecer xd";
	}
}
