package johnnythemystic.actionscultivos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import johnnythemystic.beans.Cultivo;
import johnnythemystic.servlets.ControlFincas;

public class ActionAddCultivo extends ActionCultivos {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("idFinca"));
		String descripcion = (String)request.getParameter("descripcionCultivo");
		int idCultivo;

		if((ControlFincas.fincas.get(id).getCultivos() == null) || (ControlFincas.fincas.get(id).getCultivos().size() == 0)) {
			idCultivo = 0;
		}else {
			idCultivo = ControlFincas.fincas.get(id).getCultivos().size()+1;
		}
		ControlFincas.fincas.get(id).addCultivo(new Cultivo(idCultivo,descripcion));

		return "/cultivos/addcultivo.jsp";
	}
}
