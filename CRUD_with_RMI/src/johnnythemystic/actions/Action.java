package johnnythemystic.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Action {
	
	//Todas las acciones deber�n implementar este m�todo que contendr� la funcionalidad correspondiente a su acci�n.
	//Recibe request y response para que pueda acceder a los par�metros de la petici�n y la sesi�n de usuario.
	//Devuelve un String con el path hacia la p�gina que redirige.
	public abstract String execute(HttpServletRequest request, HttpServletResponse response);

}
