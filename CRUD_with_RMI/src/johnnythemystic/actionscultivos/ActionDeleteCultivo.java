package johnnythemystic.actionscultivos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import johnnythemystic.servlets.ControlFincas;

public class ActionDeleteCultivo extends ActionCultivos {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("idFinca"));
		String descripcion = (String)request.getParameter("descripcionCultivo");

		if((ControlFincas.fincas.get(id).getCultivos() == null) || (ControlFincas.fincas.get(id).getCultivos().size() == 0)) {
			return "/jsp/error.jsp";
		}else {
			for (int i=0; i <ControlFincas.fincas.get(id).getCultivos().size(); i++){
				if (descripcion.equals(ControlFincas.fincas.get(id).getCultivo(i).getDescription())){
					ControlFincas.fincas.get(id).removeCultivo(i);
					break;
				}
			}
		}

		return "/cultivos/deletecultivo.jsp";
	}
}
