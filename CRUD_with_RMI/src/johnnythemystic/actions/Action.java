package johnnythemystic.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Action {
	
	//Todas las acciones deberán implementar este método que contendrá la funcionalidad correspondiente a su acción.
	//Recibe request y response para que pueda acceder a los parámetros de la petición y la sesión de usuario.
	//Devuelve un String con el path hacia la página que redirige.
	public abstract String execute(HttpServletRequest request, HttpServletResponse response);

}
