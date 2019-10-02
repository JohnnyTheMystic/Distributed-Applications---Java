package johnnythemystic.actionsfincas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import johnnythemystic.servlets.ControlFincas;

public class ActionDeleteFinca extends ActionFincas {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String nombreFinca = (String) request.getParameter("nombreFinca");
		
		if (ControlFincas.fincas.size() == 0){
			return "/jsp/error.jsp";
		}else{
			for (int i=0; i <ControlFincas.fincas.size(); i++){
			  if(nombreFinca.equals(ControlFincas.fincas.get(i).getName())) {
					ControlFincas.fincas.remove(i);
			  }
			}
			return "/fincas/deletefinca.jsp";
		}
	}

}
