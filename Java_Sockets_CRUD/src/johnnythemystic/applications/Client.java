
package johnnythemystic.applications;
import edu.ucam.pojos.Cultivo;
import edu.ucam.pojos.Finca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
	
	private static Socket socket = new Socket();
	private static Socket socketDatos = new Socket();
	private static Scanner scann = new Scanner(System.in);
	private static String name;
	private static Integer hect;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		try {
		// *** Canal de Comandos Servidor Remoto *********
			do{
				System.out.println("Introduzca ip/dns del Servidor: ");
				String host = scann.nextLine();
				try {
					socket = new Socket(host, 2018);
					//salir = true;
				}catch (Exception e) {		// Para controlar las excepciones por introducir una ip o un dns que no corresponda.
					System.out.println("Introduzca un Host correcto");
				}
			}while (!socket.isConnected());		// No salimos hasta que el Socket no tenga conexion.

		// *******************************
			
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter printWrite = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			String linea = bufferRead.readLine(); // Recuperamos la cadena para saber el puerto del hilo.
			System.out.println(linea);
			
			String [] partes = linea.substring(1, linea.length()).split(" "); // getLocalAdress nos envía una cadena precedida por una barra invertida,
																		// para obtener sólo la Ip necesitamos quitar ese carácter.
																		// Posteriormente seccionamos la cadena para utilizar las partes
						
			System.out.println("Conectándose a "+ partes[0] + " por el puerto " + partes[1]); //Simple aviso por pantalla
			String ip = partes[0];
			Integer puerto = Integer.parseInt(partes[1]);
			
		// *** Canal de Datos ****************
			socketDatos = new Socket(ip, puerto);
		// ************************************
			
			System.out.println(bufferRead.readLine());
						
			String receivedLine = "";
			Integer counter = 1;		// Variable que nos da automáticamente el número de instrucción.
			System.out.print(counter + " ");
			boolean intro = false;
			
			while(intro == false) {
				receivedLine = scann.nextLine();
				printWrite.println(counter + " " + receivedLine);
				printWrite.flush();
				
				String welcome = bufferRead.readLine();
				System.out.println(welcome);
				
				String [] splitWelcome = welcome.split(" ");	// Split al mensaje para ver si nos dan la bienvenida <=> password correcto
				if(splitWelcome[2].equals("Welcome")) {
					intro=true;
				}
				counter++;
				System.out.print(counter + " ");
			}
			
			String commandLine = "";
			
			while(!(commandLine = scann.nextLine()).equalsIgnoreCase("EXIT")){
				printWrite.println(counter + " " + commandLine);
				printWrite.flush();
				
				String checkOk = bufferRead.readLine();
				System.out.println(checkOk);
				String [] okCheck;
				okCheck = checkOk.split(" ");
				if (okCheck[0].equals("OK")){
					Finca finca = new Finca();
					String [] commandParts;
					commandParts = commandLine.split(" ");
					final String command = commandParts[0].toUpperCase();
					switch (command) {
						case "REMOVECULTIVO": // Nada
							break;
						case "AUTOMATICO": // Nada
							break;
						case "LISTCULTIVOS":
							ArrayList<Cultivo> arrayC = new ArrayList<Cultivo>();
							try {
								ObjectInputStream ios = new ObjectInputStream(socketDatos.getInputStream());
								arrayC = (ArrayList<Cultivo>) ios.readObject();
								
								// Para recorrer el Array recibido tenemos que hacerlo de atrás adelante porque lo recibimos al revés.
								// Esto es debido al método keySet()
								Integer fin = arrayC.size()-1;		// empiezan en el 1 y el bucle hasta 0
								System.out.println("----------------------Cultivos----------------------");
								System.out.println("|\tId\tDescripción");
								for (int i = fin; i >= 0; i--) {
									System.out.println("|\t" + arrayC.get(i).getId() + "\t" + arrayC.get(i).getDescription());
								}
								System.out.println("---------------------------------------------------");
	
								//ios.close();
							} catch (Exception e) {
								System.out.println("No existen Cultivos.");
							}
							break;
						case "ADDFINCA":
							System.out.println("Introduzca el Nombre de Finca: ");
							name = scann.nextLine();
							System.out.println("Introduzca Hectareas: ");
							hect = Integer.parseInt(scann.nextLine());
							
							finca = new Finca(1, name, hect);
							sendObject(finca);
							break;
						case "LISTFINCAS":
							ArrayList<Finca> arrayFincas = new ArrayList<Finca>();
							try {
								ObjectInputStream ios = new ObjectInputStream(socketDatos.getInputStream());
								arrayFincas = (ArrayList<Finca>) ios.readObject();
								Integer fin = arrayFincas.size()-1;
								System.out.println("----------------------Listado----------------------");
								System.out.println("|\tId\tNombre\t\t\tHectáreas");
								for (int i = fin; i >= 0; i--) {
									System.out.println("|\t" + arrayFincas.get(i).getId() + "\t" + arrayFincas.get(i).getName() + "\t\t\t" + arrayFincas.get(i).getHectareas());
								}
								System.out.println("---------------------------------------------------");
							} catch (Exception e) {
								System.out.println("No existen Fincas.");
							}
							break;
						case "COUNTFINCAS": // Nada
							break;
						case "UPDATEFINCA":
							System.out.println("Introduzca el Nombre de Finca: ");
							String name = scann.nextLine();
							System.out.println("Introduzca Hectareas: ");
							hect = Integer.parseInt(scann.nextLine());
				
							finca = new Finca(1, name, hect);
							sendObject(finca);
							break;
						case "GETFINCA":
							try {
								ObjectInputStream ios = new ObjectInputStream(socketDatos.getInputStream());
	
								finca = (Finca)ios.readObject();
								String nameFinca = finca.getName();
								Integer nHectareas = finca.getHectareas();
								//ios.close();
								System.out.println("---------------------------------------------------");
								System.out.println("| Nombre: " + nameFinca);
								System.out.println("| Número de Hectáreas: " + nHectareas);
								System.out.println("---------------------------------------------------");
							} catch (Exception e) {
								System.out.println("No existe la Finca");
							}
							break;
						case "REMOVEFINCA": // Nada
							break;
						case "ADDCULTIVO":
							System.out.println("Introduzca Descripción del Cultivo: ");
							name = scann.next();
							ObjectOutputStream oos;
							try {
								oos = new ObjectOutputStream(socketDatos.getOutputStream());
								oos.writeObject(name);
								oos.flush();
							} catch (IOException e) {
								e.printStackTrace();
							}
							break;
						case "GETCULTIVOS":
							Finca temp = new Finca();
							//System.out.println(socketDatos.isClosed()); 
							try {
								ObjectInputStream ios = new ObjectInputStream(socketDatos.getInputStream());
	
								temp = (Finca)ios.readObject();
								ArrayList<Cultivo> description = temp.getCultivos();
								if (description.size() != 0) {
									Integer fin = description.size()-1;
									System.out.println("----------------------Cultivos----------------------");
									System.out.println("|\tId\tDescripción");
									for (int i = 0; i <= fin; i++) {
										System.out.println("|\t" + description.get(i).getId() + "\t" + description.get(i).getDescription());
									}
									System.out.println("---------------------------------------------------");
								}else System.out.println("No existen Cultivos");
	
								//ios.close();
							} catch (Exception e) {
								System.out.println("No existen Cultivos");
							}
							break;
						case "COUNTCULTIVOS":
							break;
						default:
							break;
					}
				}
				counter++;
				System.out.print(counter + " ");
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void sendObject(Finca finca) {			// Método encargado de enviar Objeto Finca
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(socketDatos.getOutputStream());
			oos.writeObject(finca);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
