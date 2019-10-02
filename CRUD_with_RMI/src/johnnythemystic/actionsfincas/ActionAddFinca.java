package johnnythemystic.actionsfincas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import johnnythemystic.beans.Finca;
import johnnythemystic.servlets.ControlFincas;


public class ActionAddFinca extends ActionFincas {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String nombreFinca = (String) request.getParameter("nombreFinca");
		int hectareas = Integer.parseInt(request.getParameter("hectareas"));
		int id;
		
		if (ControlFincas.fincas.size() == 0) {
			id = 0;
		}else if (ControlFincas.fincas.size() == 1) {
			id = 1;
		}else id = ControlFincas.fincas.size();

		ControlFincas.fincas.add(new Finca(id,nombreFinca,hectareas));
		
		return "/fincas/addfinca.jsp";
	}
}
