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
		
		//contadores utiles
		int totalActividades = 0; //contador de total de actividades
		int actividadesMartin = 0; //contador de actividades de martin
		int actividadesCatalina = 0; //contador de actividades de catalina
		int actividadesEstefania = 0; //contador de actividades de estefania
		
		//otros
		Scanner sc = new Scanner(System.in);
		
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
			totalActividades = 0;
			actividadesMartin = 0;
			actividadesCatalina = 0;
			actividadesEstefania = 0;
			while (lectorRegistros.hasNext()) {
				String linea = lectorRegistros.nextLine();
				String[] partes = linea.split(";");
				usuario [totalActividades] = partes[0];
				fecha [totalActividades] = partes[1];
				horas [totalActividades] = Integer.parseInt(partes[2]);
				actividad [totalActividades] = partes[3];
				
				if (partes[0].equals("Martin")) {
					auxMartin [actividadesMartin]= partes[0];
					fechaMartin [actividadesMartin] = partes[1];
					horasMartin [actividadesMartin] = Integer.parseInt(partes[2]);
					actividadMartin [actividadesMartin] = partes[3];
					actividadesMartin++;
				} else if (partes[0].equals("Catalina")) {
					auxCatalina [actividadesCatalina]= partes[0];
					fechaCatalina [actividadesCatalina] = partes[1];
					horasCatalina [actividadesCatalina] = Integer.parseInt(partes[2]);
					actividadCatalina [actividadesCatalina] = partes[3];
					actividadesCatalina++;
				} else if (partes[0].equals("Estefania")) {
					auxEstefania [actividadesEstefania]= partes[0];
					fechaEstefania [actividadesEstefania] = partes[1];
					horasEstefania [actividadesEstefania] = Integer.parseInt(partes[2]);
					actividadEstefania [actividadesEstefania] = partes[3];
					actividadesEstefania++;
				}			
				totalActividades++;			
			}		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//inicio de la interaccion con el sistema
		System.out.println("Bienvenido! ¿a que deseas acceder?");
		System.out.print("1) Menu de usuarios \n2) Menu de analisis \n3) Salir\nrespuesta: ");
		
		gestorLogico(menuPrincipal(sc),usuario,fecha,horas,actividad,id,contraseñas,sc,totalActividades);
	
	}
	
	//funcion menu principal, osea:
	//1) Menu de usuarios
	//2) Menu de analisis
	//3) Salir
	//returna la opcion elegida.
	public static int menuPrincipal(Scanner sc) {
		int entrada = -1;
		boolean entradaValida = true;
		do {
			try {
				entrada = sc.nextInt();
				sc.nextLine();
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
	//gestorLogico reacciona dependiendo que se eligio en menuPrincipal, tambien es el que sobreescribe el archivo al final de toda la
	//interaccion con el operador.
	public static void gestorLogico(int opcion, String[] usuario, String[] fecha, int[] horas, String[] actividad, String[] id, String[] contraseñas, Scanner sc,int totalActividades) {
		switch(opcion) {
		case(1):
			int usuarioLogeado = verificadorUsuarios(contraseñas,sc);
			if (usuarioLogeado == 1) {
				
			}
			menuUsuarios(sc,totalActividades);
			gestorLogico(3,usuario,fecha,horas,actividad,id,contraseñas,sc,totalActividades);
		case(2):
			menuAnalisis();
			gestorLogico(3,usuario,fecha,horas,actividad,id,contraseñas,sc,totalActividades);
		case(3):
			//try - catch que sobreexcribe el archivo Registros.txt
			//se aplica lo mismo que con la sobreescritura anterior
			try (BufferedWriter sobreEscribirRegistros = new BufferedWriter(new FileWriter("Registros.txt",false))) {
				int i = 0;
				boolean primerEscrito = true;
				do { 
					if (usuario[i] == null) {
						i++;
						continue;
					} else if (primerEscrito){
						sobreEscribirRegistros.write(usuario[i] + ";" + fecha[i] + ";" + horas [i] + ";" + actividad[i]);
						primerEscrito = false;
						i++;
					} else {
						sobreEscribirRegistros.newLine();
						sobreEscribirRegistros.write(usuario[i] + ";" + fecha[i] + ";" + horas [i] + ";" + actividad[i]);
						i++;
					}
				} while(i<300);
			
			} catch (IOException e) {
				e.printStackTrace();
			}
			try (BufferedWriter sobreEscribirUsuarios = new BufferedWriter(new FileWriter("Usuarios.txt",false))) {
				int i = 0;
				do {
					if (i>0) {
						sobreEscribirUsuarios.newLine(); }
					
					sobreEscribirUsuarios.write(id[i]+";"+contraseñas[i]);
					i++;
				} while(i<3);
		
			} catch (IOException e) {
				e.printStackTrace();
			}
		System.out.println("bye!");
		sc.close();
		System.exit(0);	
		}		
	}
	//funcion que se encarga de verificar si el usuario y su respectiva contraseña son ingresados correctamente.
	public static int verificadorUsuarios(String[] contraseñas,Scanner sc) {
		String usuarioActual;
		String contraseñaActual;
		boolean usuarioValido = true;
		boolean contraseñaCorrecta = true;
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
						return 0;
					}
					System.out.println("contraseña incorrecta, intentelo de nuevo.");
				} while(contraseñaCorrecta);
				
			} else if (usuarioActual.equals("Catalina")) {
				usuarioValido = false;
				System.out.println("usuario encontrado.");
				do {
					System.out.print("introduzca su contraseña: ");
					contraseñaActual = sc.nextLine();
					if (contraseñaActual.equals(contraseñas[1])) {
						contraseñaCorrecta = false;
						return 1;
					}
					System.out.println("contraseña incorrecta, intentelo de nuevo.");
				} while(contraseñaCorrecta);
				
			}else if (usuarioActual.equals("Estefania")) {
				usuarioValido = false;
				System.out.println("usuario encontrado.");
				do {
					System.out.print("introduzca su contraseña: ");
					contraseñaActual = sc.nextLine();
					if (contraseñaActual.equals(contraseñas[2])) {
						contraseñaCorrecta = false;
						return 2;
					}
					System.out.println("contraseña incorrecta, intentelo de nuevo.");
				} while(contraseñaCorrecta);
			}
			System.out.println("usuario no registrado, intente de nuevo.");
			} while (usuarioValido);
		return -1;
	}
	
	
	public static void menuUsuarios(Scanner sc, int totalActividades) {
		int respuesta;
		boolean respuestaValida;
		do {
			System.out.println("que deseas realizar?\n1) Registrar actividad.\n2) Modificar actividad.\n3) Eliminar actividad.\n4) Cambiar contraseña.\n5) Salir.\nrespuesta: ");
			respuesta = -1;
			respuestaValida = true;
			do {
				try {
					respuesta = sc.nextInt();
					sc.nextLine();
					respuestaValida = false;
					if (respuesta > 5 || respuesta < 1) {
						respuestaValida = true;
						System.out.print("no existe la opcion " + respuesta + " intentelo de nuevo. \nrespuesta: ");
					}
				} catch (InputMismatchException e) {
					System.out.println("Error: entrada invalida.");
					System.out.print("intente de nuevo: ");
					sc.next(); }
				} while (respuestaValida);
			if (respuesta == 1) {
				System.out.println(totalActividades);
			} else if (respuesta == 2) {
				
			} else if (respuesta == 3) {
				
			} else if (respuesta == 4) {
				
			} else if (respuesta == 5) {
				return;
			}
		} while (respuesta!=5);
		
		
		
		return;
	}
	
	public static void menuAnalisis() {
		return;
	}
	
		
	
}
