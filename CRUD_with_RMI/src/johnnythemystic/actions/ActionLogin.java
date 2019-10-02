package johnnythemystic.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import johnnythemystic.beans.User;
import johnnythemystic.servlets.Control;

public class ActionLogin extends Action {

	@Override
	public String execute(HttpServletRequest request,HttpServletResponse response) {

		String userParam = (String) ((request.getParameter("user")!=null)?request.getParameter("user"):"");
		String passParam = (String) ((request.getParameter("pass")!=null)?request.getParameter("pass"):"");
			
		String path = "/index.html";
		User user = null;
		if((user=Control.getUsers().get(userParam))!=null){
			if(passParam.equals(user.getPassword())){
				request.getSession().setAttribute("LOGGED", true);
				if(user.getAdmin() == true) {
					request.getSession().setAttribute("ADMIN_LOGGED", true);
					path = "/secured/adminPrincipal.jsp";
				}else {
					path = "/jsp/userLogged.jsp";
				}
			}
		}
		
		return path;
	}

}
