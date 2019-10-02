<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Eliminada Finca</title>
</head>
<body>
<%
	out.println("Eliminada la Finca <b>" + request.getParameter("nombreFinca") + "</b><br>");
%>

<div align="right">
	<button onclick="location.href='controlFincas?idaction=ADMIN_FINCAS'" style='width:180px'>Gestión Fincas</button>
</div>
</body>
</html>