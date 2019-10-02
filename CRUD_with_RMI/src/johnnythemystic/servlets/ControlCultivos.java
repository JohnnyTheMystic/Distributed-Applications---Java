package johnnythemystic.servlets;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import johnnythemystic.actionscultivos.ActionAddCultivo;
import johnnythemystic.actionscultivos.ActionAdminCultivos;
import johnnythemystic.actionscultivos.ActionCultivos;
import johnnythemystic.actionscultivos.ActionDeleteCultivo;
import johnnythemystic.actionscultivos.ActionShowCultivos;
import johnnythemystic.actionscultivos.ActionUpdateCultivo;

/**
 * Servlet implementation class ControlCultivos
 */
@WebServlet("/controlCultivos")
public class ControlCultivos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Tabla que guarda las acciones que se pueden ejecutar en nuestra aplicación.
	private static Hashtable<String, ActionCultivos> actions = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControlCultivos() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		super.init();

		if (actions == null) {
			// Inicializamos la tabla con un objeto de cada una clase de hija de Accion.
			actions = new Hashtable<String, ActionCultivos>();
			actions.put("ADD_CULTIVO", new ActionAddCultivo());
			actions.put("SHOW_CULTIVOS", new ActionShowCultivos());
			actions.put("DELETE_CULTIVO", new ActionDeleteCultivo());
			actions.put("ADMIN_CULTIVOS", new ActionAdminCultivos());
			actions.put("UPDATE_CULTIVO", new ActionUpdateCultivo());
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recuperamos el parámetro que identifica la acción a ejecutar. ADD_USER,
		// SHOW_USER, etc.
		String actionParam = (String) ((request.getParameter("idaction") != null) ? request.getParameter("idaction"): "");

		// Definimos una página por defecto. Se actualizará en base a la acción que se
		// ejecute.
		String path = "/pages/actionnotfound.html";

		// Comprobamos que se recibe parámetro que identifica la acción a ejecutar.
		if (!actionParam.equals("")) {

			// Comprobamos si el usuario ha abierto sesión anteriormente.
			boolean logged = (boolean) (request.getSession().getAttribute("LOGGED") != null) ? true : false;

			if (logged || "LOGIN".equals(actionParam)) {

				// Recuperamos el objeto que representa la acción recibida, si no se encuentra
				// se reenvía a la página de actionnotfound.html
				ActionCultivos action = actions.get(actionParam);
				if (action != null) {
					// Se ejecuta la acción que devolverá el path a la página a la que enviar la
					// petición. Cada acción es reponsable de la página que se ejecuta.
					path = action.execute(request, response);
				}
			} else {
				// Si no está autorizado cambiamos la ruta a la que reenvía la petición.
				path = "/pages/notauthorized.html";
			}
		}

		// En cualquiera de los casos se reenvía al path configurado.
		this.getServletContext().getRequestDispatcher(path).forward(request, response);
	}

}
