package johnnythemystic.applications;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import edu.ucam.pojos.Cultivo;
import edu.ucam.pojos.Finca;

public class DataThread extends Thread {

	private Socket socket;
	private String command;
	private Hashtable<Integer, Finca> tableFincas;
	//private ArrayList<Cultivo> arrayCultivos;

	private ServerThread objetoServer = new ServerThread();
	private Integer idFinca, idCultivo;
	
	// Constructor para 	ADDFINCA	/	LISTFINCAS	/	AUTOMATICO	/	LISTCULTIVOS
	public DataThread(Socket socket, String instruction, Hashtable<Integer, Finca> tablaFincas){
		this.socket = socket;
		this.command = instruction;
		this.tableFincas = tablaFincas;
	}
	
	// Constructor para		GETCULTIVOS	/	ADDCULTIVO	/	UPDATEFINCA	/	GETFINCA	/	REMOVEFINCA
	public DataThread(Socket socket, String instruction, Integer id, Hashtable<Integer, Finca> tablaFincas){
		this.socket = socket;
		this.command = instruction;
		this.idFinca = id;
		this.tableFincas = tablaFincas;
	}
	
	// Constructor para 	REMOVECULTIVO	/	
	public DataThread(Socket socket, String instruction, Integer idFinca, Integer idCultivo, Hashtable<Integer, Finca> tablaFincas){
		this.socket = socket;
		this.command = instruction;
		this.idFinca = idFinca;
		this.idCultivo = idCultivo;
		this.tableFincas = tablaFincas;
	}
	
	public void run(){
		System.out.println("Nueva conexión de datos creada. Puerto: " + this.socket.getLocalPort());
		
		// Evaluación de los comandos recibidos.
		switch (command) {
			case "AUTOMATICO":
				automatico();
				break;
		// ****** Fincas ***********************************************
			case "ADDFINCA":
				addFinca();
				break;
			case "UPDATEFINCA":
				updateFinca();
				break;
			case "GETFINCA":
				getFinca();
				break;
			case "REMOVEFINCA":
				removeFinca();
				break;
			case "LISTFINCAS":
				listFincas();
				break;
		// ****** Cultivos *********************************************
			case "ADDCULTIVO":
				addCultivo();
				break;
			case "GETCULTIVOS":
				getCultivos();
				break;
			case "REMOVECULTIVO":
				removeCultivo();
				break;
			case "LISTCULTIVOS":
				listCultivos();
				break;
			default:
				break;
		}
	}
	
	public void addFinca() {
		Finca f = new Finca();
		
		// ************* Obtenemos el ID ************
		Integer key, size;
		size = tableFincas.size();
		if ((size == null) || (size == 0)){		// Si no existen Fincas se asigna el valor 1
			key = 1;
		}else {
			f = tableFincas.get(size);
			key = f.getId()+1;
		}		
		// ********** Recuperamos el Objeto y lo Guardamos **********
		try {
			ObjectInputStream ios = new ObjectInputStream(socket.getInputStream());
			f = (Finca)ios.readObject();
			f.setId(key);
			tableFincas.put(key, f);
			objetoServer.modificarTablaFincas(tableFincas);		// Modificamos la Hashtable en el Hilo Servidor para poder tener acceso después
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getFinca(){
		Finca f = new Finca();
		f =	tableFincas.get(idFinca);
		sendObjeto(f);
	}
	
	public void updateFinca(){
		Finca f = new Finca();
		try {
			ObjectInputStream ios = new ObjectInputStream(socket.getInputStream());
			f = (Finca)ios.readObject();
			f.setId(idFinca);
			tableFincas.replace(idFinca, f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		objetoServer.modificarTablaFincas(tableFincas);
	}
	
	public void removeFinca(){
		Finca f = new Finca();
		f =	tableFincas.get(idFinca);
		if (f != null) {
			tableFincas.remove(idFinca, f);
			objetoServer.modificarTablaFincas(tableFincas);
		}
	}
	
	public void listFincas(){
		ArrayList<Finca> array = new ArrayList<Finca>();
		
		Set<Integer> keys = tableFincas.keySet();
		for (Integer key: keys){
			array.add(tableFincas.get(key));
		}
		
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(array);
			oos.flush();
			System.out.println("Enviado");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void listCultivos() {
		ArrayList<Cultivo> arrayC = new ArrayList<Cultivo>();
		Set<Integer> keys = tableFincas.keySet();
		for (Integer key: keys){
			ArrayList<Cultivo> description = tableFincas.get(key).getCultivos();
			Integer fin = description.size()-1;
			for (int i = 0; i <= fin; i++) {
				arrayC.add(new Cultivo(description.get(i).getId(), description.get(i).getDescription()));
			}
		}
		sendArrayCultivo(arrayC);
	}
	
	public void getCultivos() {
		Finca finca = new Finca();
		finca = tableFincas.get(idFinca);
		sendObjeto(finca);
	}
	
	public void addCultivo() {
		try {
			int next;
			
			Finca f = tableFincas.get(idFinca);
			if (f != null) {
				if(f.getCultivos().size() == 0) {
					next = 1;
				}else {
					next = (f.getCultivo(f.getCultivos().size()).getId())+1;	// Buscamos el valor ID de la ultima posicion y sumamos 1
				}
				
				ObjectInputStream ios = new ObjectInputStream(socket.getInputStream());
				String name = (String)ios.readObject();
	
				f.addCultivo(new Cultivo(next, name));
			    objetoServer.modificarTablaFincas(tableFincas);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeCultivo(){
		Finca finca = new Finca();
		finca = tableFincas.get(idFinca);
		if (finca != null) {
			finca.removeCultivo(idCultivo);
			tableFincas.replace(idFinca, finca);
		    objetoServer.modificarTablaFincas(tableFincas);
		}
	}
	
	public void sendArrayCultivo(ArrayList<Cultivo> array) {	// Método encargado de enviar ArrayList de cultivos
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(array);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendObjeto(Finca finca) {			// Método encargado de enviar Objeto Finca
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(finca);
			oos.flush();
			System.out.println("Dentro send");
			//oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void automatico() {			// Método extra para pruebas rápidas. Crea fincas y cultivos.
		Finca f1 = new Finca(1, "Finca 1", 220);
		tableFincas.put(1, f1);
		Finca f2 = new Finca(2, "Finca 2", 250);
		tableFincas.put(2, f2);
		Finca f3 = new Finca(3, "Finca 3", 7340);
		tableFincas.put(3, f3);
		Finca f4 = new Finca(4, "Finca 4", 543);
		tableFincas.put(4, f4);
	    
		f1.addCultivo(new Cultivo(1, "Cebada"));
		f1.addCultivo(new Cultivo(2, "Lechugas"));
	    f2.addCultivo(new Cultivo(1, "Maíz"));
	    f2.addCultivo(new Cultivo(2, "Cebada"));
		f3.addCultivo(new Cultivo(1, "Cebada")); 	
	    f4.addCultivo(new Cultivo(1, "Girasoles")); 	
	    f4.addCultivo(new Cultivo(2, "Remolacha"));
	    f4.addCultivo(new Cultivo(3, "Maíz"));
	    
	    objetoServer.modificarTablaFincas(tableFincas);
	}
}
