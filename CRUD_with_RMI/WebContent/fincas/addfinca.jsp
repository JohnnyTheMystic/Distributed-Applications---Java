<%@page import="johnnythemystic.servlets.Control"%>
<%@page import="java.util.Enumeration"%>
<%@page import="johnnythemystic.beans.User"%>
<%@page import="java.util.Hashtable"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Finca A�adida</title>
</head>
<body>
<%
	out.println("Registrada y/o Modificada la Finca <b>" + request.getParameter("nombreFinca") + "</b> con Hect�reas <b>" + request.getParameter("hectareas") + "</b><br>");
%>

<div align="right">
	<button onclick="location.href='controlFincas?idaction=ADMIN_FINCAS'" style='width:180px'>Gesti�n Fincas</button>
</div>
<div align="right">
	<button onclick="location.href='controlCultivos?idaction=ADMIN_CULTIVOS'" style='width:180px'>Gesti�n Cultivos</button>
</div>
</body>
</html>