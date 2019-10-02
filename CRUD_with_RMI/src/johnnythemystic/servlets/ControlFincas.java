package johnnythemystic.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import johnnythemystic.actionsfincas.ActionAddFinca;
import johnnythemystic.actionsfincas.ActionAdminFincas;
import johnnythemystic.actionsfincas.ActionDeleteFinca;
import johnnythemystic.actionsfincas.ActionFincas;
import johnnythemystic.actionsfincas.ActionShowFincas;
import johnnythemystic.actionsfincas.ActionUpdateFinca;
import johnnythemystic.beans.Finca;

/**
 * Servlet implementation class Control
 */

@WebServlet("/controlFincas")
public class ControlFincas extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//Tabla que guarda las acciones que se pueden ejecutar en nuestra aplicaci�n.
	private static Hashtable<String, ActionFincas> actions = null;
	
	//Tabla que guarda las fincas
	public static ArrayList<Finca> fincas = new ArrayList<Finca>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlFincas() {
        super();
    }    

	@Override
	public void init() throws ServletException {
		super.init();
		
		if (actions==null){
			//Inicializamos la tabla con un objeto de cada una clase de hija de Accion.
			actions = new Hashtable<String,ActionFincas>();
			actions.put("ADD_FINCA", new ActionAddFinca());
			actions.put("SHOW_FINCAS", new ActionShowFincas());
			actions.put("DELETE_FINCA", new ActionDeleteFinca());
			actions.put("ADMIN_FINCAS", new ActionAdminFincas());
			actions.put("UPDATE_FINCA", new ActionUpdateFinca());
		}		
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
		
		//Recuperamos el par�metro que identifica la acci�n a ejecutar. ADD_USER, SHOW_USER, etc.	
		String actionParam = (String) ((request.getParameter("idaction")!=null)?request.getParameter("idaction"):"");
		
		//Definimos una p�gina por defecto. Se actualizar� en base a la acci�n que se ejecute.
		String path = "/pages/actionnotfound.html";
		
		//Comprobamos que se recibe par�metro que identifica la acci�n a ejecutar.
		if(!actionParam.equals("")){			
			
			//Comprobamos si el usuario ha abierto sesi�n anteriormente.
			boolean logged = (boolean) (request.getSession().getAttribute("LOGGED")!=null)?true:false;
							
			if (logged || "LOGIN".equals(actionParam)){
				
				//Recuperamos el objeto que representa la acci�n recibida, si no se encuentra se reenv�a a la p�gina de actionnotfound.html
				ActionFincas action = actions.get(actionParam);
				if (action != null){					
					//Se ejecuta la acci�n que devolver� el path a la p�gina a la que enviar la petici�n. Cada acci�n es reponsable de la p�gina que se ejecuta.
					path = action.execute(request, response);
				}
			}else{
				//Si no est� autorizado cambiamos la ruta a la que reenv�a la petici�n.
				path = "/pages/notauthorized.html";
			}
		}
		
		// Creamos el atributo FINCAS para poder trabajar con �l
		request.getSession().setAttribute("FINCAS", fincas);
		
		//En cualquiera de los casos se reenv�a al path configurado.
		this.getServletContext().getRequestDispatcher(path).forward(request, response);
	
	}
}
