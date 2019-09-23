package johnnythemystic.applications;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;

import edu.ucam.pojos.Cultivo;
import edu.ucam.pojos.Finca;


public class ServerThread extends Thread{

	private Socket socket = new Socket();
	private Socket socketData = new Socket();
	private Hashtable<Integer, Finca> tableFincas = new Hashtable<Integer, Finca>();
	private String name;
	private int port;
	
	public ServerThread(){ }
	
	public ServerThread(Socket socket){
		this.socket = socket;
	}
	
	public void run(){
		BufferedReader br;
		PrintWriter pw;

		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
					
			port = Server.giveMeAPort(); 		// Se asigna un puerto nuevo para un hilo de datos

			pw.println(socket.getLocalAddress() + " " + port); 		// Enviamos la dirección ip con el puerto del hilo al Cliente
			pw.flush();
			
			creacionSocketDatos();
			
			String lineaLeida = "";
			boolean user = false, pass = false;
			pw.println("Bienvenido a Gestión de Fincas. Introduzca USER:");
			pw.flush();
			while(user == false) {		// *** Chequeo del Usuario ***
				lineaLeida = br.readLine();
				String [] partes;
				partes = lineaLeida.split(" ");
				if ((partes.length < 3) || (partes.length > 3)) {
					pw.println("Comando no reconocido");
					pw.flush();
				}else {
					final String access = partes[1].toUpperCase();
					if ((partes[1].toUpperCase().equals("USER")) && (partes[2].toUpperCase().equals("ADMIN"))) {
						user = true;
						name = access;
						pw.println("Ok " + partes[0] + " Envía Contraseña");
						pw.flush();
					}else {
						pw.println("FAILED " + partes[0] + " Not User");
						pw.flush();
					}
				}
			}
			
			lineaLeida = "";
			while(pass == false) {		// Chequeo del Password
				lineaLeida = br.readLine();
				String [] partes;
				partes = lineaLeida.split(" ");
				if ((partes.length < 3) || (partes.length > 3)) {
					pw.println("Comando no reconocido");
					pw.flush();
				}else {
					if ((partes[1].toUpperCase().equals("PASS")) && (partes[2].toUpperCase().equals("ADMIN"))) {
						pass = true;
						pw.println("Ok " + partes[0] + " Welcome " + name);	// La palabra Welcome nos ayudará a salir del bucle en la parte del Cliente
						pw.flush();
					}else {
						pw.println("FAILED " + partes[0] + " Prueba de nuevo");
						pw.flush();
					}
				}
			}
			
			while(!(lineaLeida = br.readLine()).equalsIgnoreCase("EXIT")){		// Estructura de la Consola de Comandos
				
				String [] partes;
				partes = lineaLeida.split(" ");			// Leemos la linea y hacemos el split por espacios
				
				if (partes.length == 4) {				// Primer control. Si se han escrito 4 palabras
					final String comando = partes[1].toUpperCase();
					
					// Todas las llamandas al hilo de Datos van a tener la estructura siguiente:
					//	(socketData, Instruccion que queremos enviar, ....Resto de argumentos para los constructores....)
					
					if (comando.equals("REMOVECULTIVO")){
						pw.println("OK " + partes[0] + " Cultivo Eliminado");
						pw.flush();
						
						DataThread threadData;	// Declaración del Hilo de Datos que vamos a ir utilizando.
						
						threadData = new DataThread(socketData, "REMOVECULTIVO", Integer.parseInt(partes[2]), Integer.parseInt(partes[3]), tableFincas); 	
						threadData.start();
					} else {
						pw.println("Comando no reconocido");
						pw.flush();
					}
				}else if ((partes.length < 2) || (partes.length > 3)){		// Si no son 4 palabras la limitación se queda en 2 o 3 palabras.
																			// Tenemos que asegurarnos de que no se introducen ni más ni menos.
					pw.println("Introduzca la petición con la estructura correcta");
					pw.flush();
				}else if(partes.length == 2){							// Evaluaciones en caso de 2 palabras.
					final String comando = partes[1].toUpperCase();
					
					DataThread threadData;	// Declaración del Hilo de Datos que vamos a ir utilizando.

					switch (comando) {
						case "AUTOMATICO":
							pw.println("OK");
							pw.flush();
							threadData = new DataThread(socketData, "AUTOMATICO", tableFincas); 	// Pasamos el socket al Hilo de Datos y lo lanzamos
							threadData.start();
							break;
						case "LISTCULTIVOS":
							pw.println("OK " + partes[0] + " localhost " + port);
							pw.flush();
							threadData = new DataThread(socketData, "LISTCULTIVOS", tableFincas); 		
							threadData.start();
							break;
		// ****** Fincas ***********************************************
						case "ADDFINCA":
							pw.println("OK " + partes[0] + " localhost " + port);
							pw.flush();
							threadData = new DataThread(socketData, "ADDFINCA", tableFincas);
							threadData.start();
							break;
						case "LISTFINCAS":
							pw.println("OK " + partes[0] + " localhost " + port);
							pw.flush();
							threadData = new DataThread(socketData, "LISTFINCAS", tableFincas);
							threadData.start();
							break;
						case "COUNTFINCAS":
							pw.println("OK " + partes[0] + "\tTotal Fincas: "+ tableFincas.size());
							pw.flush();
							break;
						default:
							pw.println("Comando no reconocido");
							pw.flush();
							break;
					}
				} else {	// Las peticiones con 3 parámetros
					final String comando = partes[1].toUpperCase();
					
					DataThread threadData;	// Declaración del Hilo de Datos que vamos a ir utilizando.

					switch (comando) {
						case "UPDATEFINCA":
							pw.println("OK " + partes[0] + " localhost " + port);
							pw.flush();
							threadData = new DataThread(socketData, "UPDATEFINCA", Integer.parseInt(partes[2]), tableFincas);
							threadData.start();
							break;
						case "GETFINCA":
							pw.println("OK " + partes[0] + " localhost " + port);
							pw.flush();
							threadData = new DataThread(socketData, "GETFINCA", Integer.parseInt(partes[2]), tableFincas); 		
							threadData.start();
							break;
						case "REMOVEFINCA":
							pw.println("Ok. Finca Eliminada");
							pw.flush();
							threadData = new DataThread(socketData, "REMOVEFINCA", Integer.parseInt(partes[2]), tableFincas); 		
							threadData.start();
							break;
				// ****** Cultivos *********************************************
						case "ADDCULTIVO":
							pw.println("OK " + partes[0] + " localhost " + port);
							pw.flush();
							threadData = new DataThread(socketData, "ADDCULTIVO", Integer.parseInt(partes[2]), tableFincas); 		
							threadData.start();
							break;
						case "GETCULTIVOS":
							pw.println("OK " + partes[0] + " localhost " + port);
							pw.flush();
							threadData = new DataThread(socketData, "GETCULTIVOS", Integer.parseInt(partes[2]), tableFincas); 		
							threadData.start();
							break;
						case "COUNTCULTIVOS":
							ArrayList<Cultivo> description = tableFincas.get(Integer.parseInt(partes[2])).getCultivos();
							int total = description.size();
							pw.println("OK " + partes[0] + "\tTotal Cultivos: " + total);
							pw.flush();
							break;
						default:
							pw.println("Comando no reconocido");
							pw.flush();
							break;
					}
				}
			}	
		}catch (IOException e) {}
	}
	
	public void modificarTablaFincas(Hashtable<Integer, Finca> tableF) {	// Método que nos va a permitir un mínimo de persistencia en la aplicación
		this.tableFincas = tableF;
	}
	
	public void creacionSocketDatos() {
		// **************** Creacion del Socket para Datos *****************************
		ServerSocket dataSocket;
		try {
			dataSocket = new ServerSocket(port);
			socketData = dataSocket.accept();				// Esperamos al Cliente.
			System.out.println("Conexión Datos OK.");

		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
