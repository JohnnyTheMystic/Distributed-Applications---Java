package johnnythemystic.actionscultivos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import johnnythemystic.servlets.ControlFincas;

public class ActionUpdateCultivo extends ActionCultivos {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int idFinca = Integer.parseInt(request.getParameter("idFinca"));
		String nombreFinca = request.getParameter("nombreFinca");
		int hectareas = Integer.parseInt(request.getParameter("hectareas"));
		
		for (int i=0; i <ControlFincas.fincas.size(); i++){
			if(ControlFincas.fincas.get(i).getId() == idFinca) {
				ControlFincas.fincas.get(i).setHectareas(hectareas);
				ControlFincas.fincas.get(i).setName(nombreFinca);
			}
		}
		
		return "/fincas/addfinca.jsp";
	}
}
