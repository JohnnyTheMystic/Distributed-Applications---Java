package johnnythemystic.applications;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private static int port = 2019;
	
	public void launch(){
		try {						
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(2018);	// Conexion para comandos.	
			while(true){
							
				Socket socket = serverSocket.accept();			// Vamos a ir asignando hilos a clientes.
				System.out.println("Conexión Comandos OK");
				
				ServerThread serverThread = new ServerThread(socket); // Pasamos el socket al Hilo Servidor y lo lanzamos
				serverThread.start();
			}		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		(new Server()).launch();		
	}
	
	public static int giveMeAPort() {	// Método que asigna puertos según disponibilidad
		return port++;
	}
}
