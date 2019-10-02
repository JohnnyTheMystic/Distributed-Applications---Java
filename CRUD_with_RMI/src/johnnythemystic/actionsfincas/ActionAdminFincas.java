package johnnythemystic.actionsfincas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionAdminFincas extends ActionFincas {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		return "/fincas/adminfincas.jsp";
	}

}
