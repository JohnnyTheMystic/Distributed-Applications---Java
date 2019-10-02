package johnnythemystic.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import johnnythemystic.beans.User;
import johnnythemystic.servlets.Control;

public class ActionAddUser extends Action {

	@Override
	public String execute(HttpServletRequest request,HttpServletResponse response) {
		String nombre = (String) request.getParameter("user");
		String password = (String) request.getParameter("password");
		String admin = (String)request.getParameter("admin");
		
		Boolean administrador = false;
		if ("on".equals(admin)) {
			administrador = true;
		}
		
		Control.users.put(nombre, new User(nombre, password, administrador));
		return "/jsp/adduser.jsp";
	}
	
}
