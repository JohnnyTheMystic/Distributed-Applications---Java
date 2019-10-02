<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Eliminado Cultivo</title>
</head>
<body>
<%
	out.println("Eliminado Cultivo <b>" + request.getParameter("descripcionCultivo") + " de la Finca <b>" + request.getParameter("idFinca") + "</b><br>");
%>

<div align="right">
	<button onclick="location.href='controlCultivos?idaction=ADMIN_CULTIVOS'" style='width:180px'>Gestión Fincas</button>
</div>
</body>
</html>