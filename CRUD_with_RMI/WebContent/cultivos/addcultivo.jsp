<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Añadido Cultivo</title>
</head>
<body>
<%
	out.println("Registrado Cultivo <b>" + request.getParameter("descripcionCultivo") + "</b><br>");
%>

<div align="right">
	<button onclick="location.href='controlFincas?idaction=ADMIN_FINCAS'" style='width:180px'>Gestión Fincas</button>
</div>
<div align="right">
	<button onclick="location.href='controlCultivos?idaction=ADMIN_CULTIVOS'" style='width:180px'>Gestión Cultivos</button>
</div>
</body>
</html>