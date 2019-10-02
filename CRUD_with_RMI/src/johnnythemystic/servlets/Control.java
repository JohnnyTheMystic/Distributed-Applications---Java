package johnnythemystic.servlets;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import johnnythemystic.actions.Action;
import johnnythemystic.actions.ActionAddUser;
import johnnythemystic.actions.ActionDeleteUser;
import johnnythemystic.actions.ActionLogin;
import johnnythemystic.beans.User;

/**
 * Servlet implementation class Control
 */
@WebServlet("/control")
public class Control extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//Tabla que guarda las acciones que se pueden ejecutar en nuestra aplicación.
	private static Hashtable<String, Action> actions = null;
	
	//Tabla que guarda los usuarios que tienen acceso a nuestra aplicación.
	public static Hashtable<String,User> users = null;
	
	/*
	 *  Implementados Permisos según el tipo de usuario,
	 *  mediante un tercer atributo booleano en la clase User llamado Admin
	 */
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Control() {
        super();
    }    

	@Override
	public void init() throws ServletException {
		super.init();
		
		if (actions==null){
			//Inicializamos la tabla con un objeto de cada una clase de hija de Accion.
			actions = new Hashtable<String,Action>();
			actions.put("ADD_USER", new ActionAddUser());
			actions.put("DELETE_USER", new ActionDeleteUser());
			actions.put("LOGIN", new ActionLogin());
		}		
	}
	
	public static Hashtable<String, User> getUsers() {
		if(Control.users == null){
			Control.users = new Hashtable<String,User>();
			Control.users.put("root", new User("root","1234", true));
			Control.users.put("jose", new User("jose","1234", false));
			Control.users.put("juan", new User("juan","1234", false));
			Control.users.put("admin", new User("admin","admin", true));
		}
		return Control.users;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Recuperamos el parámetro que identifica la acción a ejecutar. ADD_USER, SHOW_USER, etc.	
		String actionParam = (String) ((request.getParameter("idaction")!=null)?request.getParameter("idaction"):"");
		
		//Definimos una página por defecto. Se actualizará en base a la acción que se ejecute.
		String path = "/pages/actionnotfound.html";
		
		//Comprobamos que se recibe parámetro que identifica la acción a ejecutar.
		if(!actionParam.equals("")){			
			
			//Comprobamos si el usuario ha abierto sesión anteriormente.
			boolean logged = (boolean) (request.getSession().getAttribute("LOGGED")!=null)?true:false;
			
					
			if (logged || "LOGIN".equals(actionParam)){
				
				//Recuperamos el objeto que representa la acción recibida, si no se encuentra se reenvía a la página de actionnotfound.html
				Action action = actions.get(actionParam);
				if (action != null){					
					//Se ejecuta la acción que devolverá el path a la página a la que enviar la petición. Cada acción es reponsable de la página que se ejecuta.
					path = action.execute(request, response);
				}
			}else{
				//Si no está autorizado cambiamos la ruta a la que reenvía la petición.
				path = "/pages/notauthorized.html";
			}
		}
		
		// Creamos el atributo FINCAS para poder trabajar con él
		request.setAttribute("USERS", users);
		
		//En cualquiera de los casos se reenvía al path configurado.
		this.getServletContext().getRequestDispatcher(path).forward(request, response);
	
	}
}
