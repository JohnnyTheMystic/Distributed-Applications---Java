package johnnythemystic.actionscultivos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionAdminCultivos extends ActionCultivos {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		return "/cultivos/admincultivos.jsp";
	}
}
