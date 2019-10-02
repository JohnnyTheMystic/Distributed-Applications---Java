package johnnythemystic.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import johnnythemystic.servlets.Control;

public class ActionDeleteUser extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String nombre = (String) request.getParameter("user");
		Control.users.remove(nombre);
		return "/jsp/deleteuser.jsp";
	}

}
