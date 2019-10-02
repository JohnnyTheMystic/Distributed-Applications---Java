<%@page import="java.util.Set"%>
<%@page import="java.util.Enumeration"%>
<%@page import="johnnythemystic.beans.User"%>
<%@page import="java.util.Hashtable"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestión de Usuarios - Administración</title>
</head>
<body>
<table border=2 cellspacing=10 cellpadding=2 bordercolor="0066FF"><tr>
<th>Usuarios actualmente registrados</th></tr>
<%
	Hashtable<String,User> usuarios = (Hashtable<String,User>) request.getAttribute("USERS");
	Enumeration<String> e = usuarios.keys();
	Object clave;
	while( e.hasMoreElements() ){
	  clave = e.nextElement();
	  out.println( "<tr><td>Nombre : " + clave + "</td></tr><br>");
	}
%>
</table>

<br><br>
<div align="center">
	<form id="eliminarForm" action="control?idaction=ADD_USER" method="POST">
		<b>Añadir Usuario</b><br>
		Nombre:<input type="text" id="user" name="user"/><br/>
		Password:<input type="text" id="password" name="password"/><br/>
		¿Administrador?:<input type="checkbox" id="user" name="admin"/><br/>
		<input type="submit" value="Añadir Usuario"/>
	</form>
</div>

<br><br>
<div align="center">
	<form id="eliminarForm" action="control?idaction=DELETE_USER" method="POST">
		<b>Eliminar Usuario</b><br>
		Usuario a Eliminar:<input type="text" id="user" name="user"/><br/>
		<input type="submit" value="Eliminar"/>
	</form>
</div>
<div align="right">
<button onclick="location.href='controlFincas?idaction=ADMIN_FINCAS'" style='width:180px'>Gestión Fincas</button>
</div>
<div align="right">
<button onclick="location.href='controlCultivos?idaction=ADMIN_CULTIVOS'" style='width:180px'>Gestión Cultivos</button>
</div>

</body>
</html>