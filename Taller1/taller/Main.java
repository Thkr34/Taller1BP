package taller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		//Bastián Felipe Perines Flores
		//22.386.978-5
		//ICCI
		
		//contadores utiles
		int totalActividades = 0; 
		int cantDiversidadActividades = 0;
		int totalHorasMartin = 0;
		int DiversidadActividadesMartin = 0; 
		int totalHorasCatalina = 0;
		int DiversidadActividadesCatalina = 0;
		int totalHorasEstefania = 0;
		int DiversidadActividadesEstefania = 0; 
		
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
		
		//arreglos para menu analisis
		String[] actividadMasRealizada = new String[300];
		int [] horasActividadMasRealizada = new int[300];
		
 		//arreglos para cada usuario y variables para cada usuario
		
		//Martin
		int [] masRealizadaHorasMartin = new int[300];
		String [] masRealizadaActividadMartin = new String[300];
		
		//Catalina
		int [] masRealizadaHorasCatalina = new int[300];
		String [] masRealizadaActividadCatalina = new String[300];
		
		//Estefania
		int [] masRealizadaHorasEstefania = new int[300];
		String [] masRealizadaActividadEstefania = new String[300];
		
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
			DiversidadActividadesMartin = 0;
			DiversidadActividadesCatalina = 0;
			DiversidadActividadesEstefania = 0;
			
			//ciclo while que lee el archivo y da paso a otros ciclos que guardan informacion especifica para funciones de menuUsuarios y menuAnalisis
			while (lectorRegistros.hasNext()) {
				String linea = lectorRegistros.nextLine();
				//if que evitar caer en lineas vacias o inexistentes (podria ser fatal para el codigo)
				if (linea == null || linea.trim().isEmpty()) {
					continue;
				}
				String[] partes = linea.split(";");
				usuario [totalActividades] = partes[0];
				fecha [totalActividades] = partes[1];
				horas [totalActividades] = Integer.parseInt(partes[2]);
				actividad [totalActividades] = partes[3];
				totalActividades++;
				
				//if que se encarga de realizar funciones para variables usadas en menuAnalisis, especificamente para la opcion actividad mas realizada
				if (!Arrays.asList(actividadMasRealizada).contains(partes[3])) {
					actividadMasRealizada[cantDiversidadActividades] = partes[3];
					horasActividadMasRealizada[cantDiversidadActividades] = Integer.parseInt(partes[2]);
					cantDiversidadActividades++;					
				} else if (Arrays.asList(actividadMasRealizada).contains(partes[3])) {
					int indice = 0;
					for (int i = 0; i<300;i++) {
						if (actividadMasRealizada[i].equals(partes[3])) {
							indice = i;
							break;
						}
					}
					horasActividadMasRealizada[indice] += Integer.parseInt(partes[2]);
				}
				
				
				//similar al if de arriba, solo que enfocado a cada usuario por separado para la opcion actividad mas realizada por cada usuario
				if (partes[0].equals("Martin")) {
					totalHorasMartin += Integer.parseInt(partes[2]);
					if (!Arrays.asList(masRealizadaActividadMartin).contains(partes[3])) {
						masRealizadaActividadMartin[DiversidadActividadesMartin] = partes[3];
						masRealizadaHorasMartin[DiversidadActividadesMartin] = Integer.parseInt(partes[2]);
						DiversidadActividadesMartin++;					
					} else if (Arrays.asList(masRealizadaActividadMartin).contains(partes[3])) {
						int indice = 0;
						for (int i = 0; i<300;i++) {
							if (masRealizadaActividadMartin[i].equals(partes[3])) {
								indice = i;
								masRealizadaHorasMartin[indice] += Integer.parseInt(partes[2]);
								break;
							}
						}
					}
					
				} else if (partes[0].equals("Catalina")) {
					totalHorasCatalina += Integer.parseInt(partes[2]);
					if (!Arrays.asList(masRealizadaActividadCatalina).contains(partes[3])) {
						masRealizadaActividadCatalina[DiversidadActividadesCatalina] = partes[3];
						masRealizadaHorasCatalina[DiversidadActividadesCatalina] = Integer.parseInt(partes[2]);
						DiversidadActividadesCatalina++;					
					} else if (Arrays.asList(masRealizadaActividadCatalina).contains(partes[3])) {
						int indice = 0;
						for (int i = 0; i<300;i++) {
							if (masRealizadaActividadCatalina[i].equals(partes[3])) {
								indice = i;
								masRealizadaHorasCatalina[indice] += Integer.parseInt(partes[2]);
								break;
							}
						}
					}
					
				} else if (partes[0].equals("Estefania")) {
					totalHorasEstefania += Integer.parseInt(partes[2]);
					if (!Arrays.asList(masRealizadaActividadEstefania).contains(partes[3])) {
						masRealizadaActividadEstefania[DiversidadActividadesEstefania] = partes[3];
						masRealizadaHorasEstefania[DiversidadActividadesEstefania] = Integer.parseInt(partes[2]);
						DiversidadActividadesEstefania++;					
					} else if (Arrays.asList(masRealizadaActividadEstefania).contains(partes[3])) {
						int indice = 0;
						for (int i = 0; i<300;i++) {
							if (masRealizadaActividadEstefania[i].equals(partes[3])) {
								indice = i;
								masRealizadaHorasEstefania[indice] += Integer.parseInt(partes[2]);
								break;
							}
						}
					}	
				}
			}		
						
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//inicio de la interaccion con el sistema
		System.out.println("Bienvenido! ¿a que deseas acceder?");
		System.out.print("1) Menu de usuarios \n2) Menu de analisis \n3) Salir\nrespuesta: ");
		int opcion = menuPrincipal(sc);
		
		//switch principal que deriva en menuUsuarios, menuAnalisis o salir
		switch(opcion) {
		case(1):
			int usuarioLogeado = verificadorUsuarios(contraseñas,sc);	
			int respuesta;
			int espaciosDisponibles;
			int horaIngresada;
			boolean fechaValida = true;
			String[] line;
			String fechaIngresada;
			do {
				respuesta = menuUsuarios(sc);
				if (respuesta == 1) {
					espaciosDisponibles = 300 - totalActividades;
					if (espaciosDisponibles < 1) {
						System.out.println("no hay espacio para registras nuevas actividades, intente eliminar algunas primero.");
					} else {
					System.out.println("tienes : " + espaciosDisponibles + " celdas para registrar actividades.");
					do {
						fechaValida = true;
						System.out.print("ingrese la fecha (en formato dd/mm/aaaa): ");
						fechaIngresada = sc.nextLine().trim();
						line = fechaIngresada.split("/");
						if (fechaIngresada.trim().isEmpty()) {
							System.out.println("entrada vacia, intente otra vez.");
							continue;
						} else if (fechaIngresada.split("/").length != 3) {
							System.out.println("fecha mal ingresada, intente otra vez.");
							continue;
						} else if (fechaIngresada.contains(" ") || fechaIngresada.length() != 10) {
							System.out.println("ingrese la fecha sin dejar espacios en blanco, intente otra vez.");
							continue;
						}
						
						int dia = Integer.parseInt(line[0].trim());
						int mes = Integer.parseInt(line[1].trim());
						int año = Integer.parseInt(line[2].trim());
						
						if (dia < 1 || dia > 31) {
							System.out.println("dia fuera de rango, intente de nuevo.");
							continue;
						} else if(mes <1 || mes > 12) {
							System.out.println("mes fuera de rango, intente de nuevo.");
							continue;
						} else if(año != 2026) {
							System.out.println("estamos en el año 2026, no en el año " + año + " , intente de nuevo.");
							continue;
						} 
						fechaValida = false;
						} while (fechaValida);
						horaIngresada = -1;
						boolean horaValida = true;
						System.out.print("ingrese la/s hora/s procastinada/s: ");
						do {
							try {
								horaIngresada = sc.nextInt();
								sc.nextLine();
								horaValida = false;
								if (horaIngresada <= 0) {
									horaValida = true;
									System.out.print("error, debe registrar almenos 1 hora, 0 o menos horas se tomaran como incorrectas, intente de nuevo.\ningrese la/s hora/s procastinada/s: ");
								}
							} catch (InputMismatchException e) {
								System.out.println("Error: entrada invalida.");
								System.out.print("intente de nuevo.\ningrese la/s hora/s procastinada/s: ");
								sc.next(); }
							} while (horaValida);
						
						System.out.print("ingrese la actividad realizada: ");
						String actividadIngresada = sc.nextLine();
						usuario[totalActividades] = id[usuarioLogeado];
						fecha[totalActividades] = fechaIngresada;
						horas[totalActividades] = horaIngresada;
						actividad[totalActividades] = actividadIngresada;
						totalActividades++;
					}
					
				} else if (respuesta == 2) {
					int eleccionModificar = -1;
					int ValorModificar = -1;
					boolean entradaValida = true;
					System.out.println("que actividad quieres modificar?: ");
					int j = 1;
					int[] index = new int[300];
					for (int i = 0; i < usuario.length; i++) {
						if (usuario[i] == null) {
							continue;
						}
						if (usuario[i].equals(id[usuarioLogeado])) {
							System.out.println(j+") " +usuario[i]+";"+fecha[i]+";"+horas[i]+";"+actividad[i]);
							index[j] = i;
							j++;
						}
					}
					System.out.print("respuesta: ");
					do {
						try {
							eleccionModificar = sc.nextInt();
							sc.nextLine();
							entradaValida = false;
							if (eleccionModificar > j-1 || eleccionModificar < 0) {
								entradaValida = true;
								System.out.print("no existe la opcion " + eleccionModificar + " intentelo de nuevo. \nrespuesta: ");
							}
						} catch (InputMismatchException e) {
							System.out.println("Error: entrada invalida.");
							System.out.print("intente de nuevo: ");
							sc.next(); }
						} while (entradaValida);
					
					System.out.print("que deseas modificar?\n0)regresar.\n1)fecha.\n2)hora.\n3)actividad.\nrespuesta: ");
					do {
						try {
							ValorModificar = sc.nextInt();
							sc.nextLine();
							entradaValida = false;
							if (ValorModificar > 3 || ValorModificar < 0) {
								entradaValida = true;
								System.out.print("no existe la opcion " + ValorModificar + " intentelo de nuevo. \nrespuesta: ");
							}
						} catch (InputMismatchException e) {
							System.out.println("Error: entrada invalida.");
							System.out.print("intente de nuevo: ");
							sc.next(); }
						} while (entradaValida);
					
					if (ValorModificar == 0) {
						continue;
					}
					
					if (ValorModificar == 1) {
						do {
							fechaValida = true;
							System.out.print("ingrese la fecha (en formato dd/mm/aaaa): ");
							fechaIngresada = sc.nextLine().trim();
							line = fechaIngresada.split("/");
							if (fechaIngresada.trim().isEmpty()) {
								System.out.println("entrada vacia, intente otra vez.");
								continue;
							} else if (fechaIngresada.split("/").length != 3) {
								System.out.println("fecha mal ingresada, intente otra vez.");
								continue;
							} else if (fechaIngresada.contains(" ") || fechaIngresada.length() != 10) {
								System.out.println("ingrese la fecha sin dejar espacios en blanco, intente otra vez.");
								continue;
							}
							
							int dia = Integer.parseInt(line[0].trim());
							int mes = Integer.parseInt(line[1].trim());
							int año = Integer.parseInt(line[2].trim());
							
							if (dia < 1 || dia > 31) {
								System.out.println("dia fuera de rango, intente de nuevo.");
								continue;
							} else if(mes <1 || mes > 12) {
								System.out.println("mes fuera de rango, intente de nuevo.");
								continue;
							} else if(año != 2026) {
								System.out.println("estamos en el año 2026, no en el año " + año + " , intente de nuevo.");
								continue;
							} 
							fechaValida = false;
							} while (fechaValida);
						fecha[index[eleccionModificar]] = fechaIngresada;
					} else if (ValorModificar == 2) {
						horaIngresada = -1;
						boolean horaValida = true;
						System.out.print("ingrese la/s hora/s: ");
						do {
							try {
								horaIngresada = sc.nextInt();
								sc.nextLine();
								horaValida = false;
								if (horaIngresada <= 0) {
									horaValida = true;
									System.out.print("error, debe registrar almenos 1 hora, 0 o menos horas se tomaran como incorrectas, intente de nuevo.\ningrese la/s hora/s: ");
								}
							} catch (InputMismatchException e) {
								System.out.println("Error: entrada invalida.");
								System.out.print("intente de nuevo.\ningrese la/s hora/s: ");
								sc.next(); }
							} while (horaValida);
						horas[index[eleccionModificar]] = horaIngresada;
					} else if (ValorModificar == 3) {
						System.out.print("ingrese la actividad: ");
						String actividadIngresada = sc.nextLine();
						actividad[index[eleccionModificar]] = actividadIngresada;
					}
					
				} else if (respuesta == 3) {
					int eleccionEliminar = -1;
					boolean entradaValida = true;
					System.out.println("que actividad quieres modificar?: ");
					int j = 1;
					int[] index = new int[300];
					for (int i = 0; i < usuario.length; i++) {
						if (usuario[i]==null) {
							continue;
						}
						if (usuario[i].equals(id[usuarioLogeado])) {
							System.out.println(j+") " +usuario[i]+";"+fecha[i]+";"+horas[i]+";"+actividad[i]);
							index[j] = i;
							j++;
						}
					}
					System.out.print("respuesta: ");
					do {
						try {
							eleccionEliminar = sc.nextInt();
							sc.nextLine();
							entradaValida = false;
							if (eleccionEliminar > j-1 || eleccionEliminar < 0) {
								entradaValida = true;
								System.out.print("no existe la opcion " + eleccionEliminar + " intentelo de nuevo. \nrespuesta: ");
							}
						} catch (InputMismatchException e) {
							System.out.println("Error: entrada invalida.");
							System.out.print("intente de nuevo: ");
							sc.next(); }
						} while (entradaValida);
					usuario[index[eleccionEliminar]] = null;
					fecha[index[eleccionEliminar]] = null;
					horas[index[eleccionEliminar]] = 0;
					actividad[index[eleccionEliminar]] = null;
					totalActividades--;
					
				} else if (respuesta == 4) {
					String nuevaContraseña;
					boolean entradaValida = true;
					do {
					System.out.print("ingresa tu nueva contraseña: ");
					nuevaContraseña = sc.nextLine();
					entradaValida = false;
					if (nuevaContraseña == null || nuevaContraseña.length() <1) {
						System.out.print("la contraseña debe tener almenos 1 caracter, intente otra vez.\nrespuesta: ");
					}
					} while(entradaValida);
					contraseñas[usuarioLogeado] = nuevaContraseña;
					System.out.println("contraseña cambiada correctamente!");
				} else if (respuesta == 5) {
					break;
				}
			} while(respuesta!=5);
			break;
		case(2):
			int eleccion;
			do {
			eleccion = menuAnalisis(sc);
			if (eleccion == 1) {
				printActividadMasRealizada(actividadMasRealizada,horasActividadMasRealizada);
			} else if (eleccion == 2) {
				printActividadMasRealizadaPorUsuario("Martin",masRealizadaActividadMartin,masRealizadaHorasMartin);
				printActividadMasRealizadaPorUsuario("Catalina",masRealizadaActividadCatalina,masRealizadaHorasCatalina);
				printActividadMasRealizadaPorUsuario("Estefania",masRealizadaActividadEstefania,masRealizadaHorasEstefania);
				
			} else if (eleccion == 3) {
				usuarioMasProcastinador(totalHorasMartin,totalHorasCatalina,totalHorasEstefania);
				
			} else if (eleccion == 4) {
					for (int i = 0; i < usuario.length; i++) {
						if (usuario[i] == null) {
							continue;
						} else {	
							System.out.println((i+1)+ ") "+usuario[i]+";"+fecha[i]+";"+horas[i]+";"+actividad[i]);	
							}	
						}
					}
				} while(eleccion!=5);
			break;
		case(3):
			break;	
		}
		
		//try - catch que sobreexcribe el archivo Registros.txt
		try (BufferedWriter sobreEscribirRegistros = new BufferedWriter(new FileWriter("Registros.txt",false))) {
			boolean primerLinea = false;
			for (int i = 0; (i < usuario.length); i++) {
				if (usuario[i] != null) {
					if (primerLinea) {
						sobreEscribirRegistros.newLine();
					}
					String linea = usuario[i]+";"+fecha[i]+";"+horas[i]+";"+actividad[i];
					sobreEscribirRegistros.write(linea);
					
					primerLinea = true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//try - catch que sobreescribe el archivo Usuarios.txt
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
	
	//menu usuarios al cual se llega mediante menuPrincipal
	//este menu se encarga de manejar informacion del usuario que se logeo mediante verificarUsuario, sin dar acceso a la informacion de los demas.
	//estructura de:
	//1) Registrar actividad
	//2) Modificar actividad
	//3) Eliminar Actividad
	//4) Cambiar Contraseña
	//5) Salir
	public static int menuUsuarios(Scanner sc) {
		int respuesta;
		boolean respuestaValida;
		System.out.print("que deseas realizar?\n1) Registrar actividad.\n2) Modificar actividad.\n3) Eliminar actividad.\n4) Cambiar contraseña.\n5) Salir.\nrespuesta: ");
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
		return respuesta;	
	}
	
	//menu de analisis, enfocado a mostrar parametros y a comparar variables con respecto a los demas usuarios. no se necesita verificacion para entrar ya que no permite manipular informacion, solo verla.
	//estructura:
	//1) Actividad mas realizada (global)
	//2) Actividad mas realizada por cada usuario
	//3) Usuario con mayor procastinacion
	//4) Ver todas las actividades
	//5) Salir
	public static int menuAnalisis(Scanner sc) {
		int respuesta;
		boolean respuestaValida;
		System.out.print("que deseas ver?\n1) Actividad más realizada.\n2) Actividad más realizada por cada usuario.\n3) Usuario con mayor procastinacion.\n4) Ver todas las actividades.\n5) Salir.\nrespuesta: ");
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
		return respuesta;
	}
	//funcion que busca el caso adecuado para la opcion usuario con mayor procastinacion del menu de analisis.
	public static void usuarioMasProcastinador(int totalHorasMartin,int totalHorasCatalina,int totalHorasEstefania) {
		if (totalHorasMartin > totalHorasCatalina && totalHorasMartin > totalHorasEstefania) {
			System.out.println("el usuario con mayor procastinacion es Martin, con "+totalHorasMartin+" horas procastinadas en total.");
		} else if (totalHorasCatalina > totalHorasMartin && totalHorasCatalina > totalHorasEstefania) {
			System.out.println("el usuario con mayor procastinacion es Catalina, con "+totalHorasCatalina+" horas procastinadas en total.");
		} else if (totalHorasEstefania > totalHorasMartin && totalHorasEstefania > totalHorasCatalina) {
			System.out.println("el usuario con mayor procastinacion es Estefania, con "+totalHorasEstefania+" horas procastinadas en total.");
		} else if (totalHorasEstefania == totalHorasMartin && totalHorasEstefania > totalHorasCatalina) {
			System.out.println("los usuarios con mas horas procastinadas son Martin y Estefania, con "+totalHorasEstefania+" horas procastinadas en total.");
		} else if (totalHorasEstefania == totalHorasCatalina && totalHorasEstefania > totalHorasMartin) {
			System.out.println("los usuarios con mas horas procastinadas son Catalina y Estefania, con "+totalHorasEstefania+" horas procastinadas en total.");
		} else if (totalHorasMartin == totalHorasCatalina && totalHorasMartin > totalHorasEstefania) {
			System.out.println("los usuarios con mas horas procastinadas son Catalina y Martin, con "+totalHorasMartin+" horas procastinadas en total.");
		} else if (totalHorasMartin == totalHorasCatalina && totalHorasMartin == totalHorasEstefania) {
			System.out.println("los usuarios con mas horas procastinadas son Catalina, Martin y Estefania, con "+totalHorasMartin+" horas procastinadas en total.");
		}
	return;
	}
	
	//funcion que busca la actividad mas realizada globalmente para la funcion actividad mas realizada de menu analisis
	public static void printActividadMasRealizada(String[] actividadMasRealizada, int[] horasActividadMasRealizada) {
		int mayor = -1;
		int indice = 0;
		for (int i=0;i<actividadMasRealizada.length;i++) {
			if (horasActividadMasRealizada[i] > mayor) {
				mayor = horasActividadMasRealizada[i];
				indice = i;
			}
		}
		System.out.println("La actividad mas realizada globalmente fue: "+actividadMasRealizada[indice]+" con un total de: "+mayor+" horas.");	
		
	}
	
	//similar a la funcion anterior, pero buscando la actividad mas realizada por cada usuario.
	public static void printActividadMasRealizadaPorUsuario(String usuario, String[] masRealizadaActividadUsuario, int[] masRealizadaHorasUsuario) {
		int mayor = -1;
		int indice = 0;
		for (int i=0;i<masRealizadaActividadUsuario.length;i++) {
			if (masRealizadaHorasUsuario[i] > mayor) {
				mayor = masRealizadaHorasUsuario[i];
				indice = i;
			}
		}
		System.out.println("La actividad mas realizada por "+usuario+" fue: "+masRealizadaActividadUsuario[indice]+" con un total de: "+mayor+" horas registradas.");	
		
	}
}
