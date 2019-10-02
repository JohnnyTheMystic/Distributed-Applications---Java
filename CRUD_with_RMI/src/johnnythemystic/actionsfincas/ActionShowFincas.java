package johnnythemystic.actionsfincas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionShowFincas extends ActionFincas {
	@Override
	public String execute(HttpServletRequest request,HttpServletResponse response) {
		return "/fincas/showfincas.jsp";
	}
}
