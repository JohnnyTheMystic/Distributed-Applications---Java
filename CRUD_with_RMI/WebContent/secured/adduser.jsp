<%@page import="johnnythemystic.servlets.Control"%>
<%@page import="java.util.Enumeration"%>
<%@page import="johnnythemystic.beans.User"%>
<%@page import="java.util.Hashtable"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Usuario Añadido</title>
</head>
<body>
<%
	out.println("Usuario " + request.getParameter("user") + " Añadido al Registro <br>");
	
	out.println("Usuarios actualmente registrados: <br><br>");
	Enumeration<String> e = Control.getUsers().keys();
	Object clave;
	while( e.hasMoreElements() ){
	  clave = e.nextElement();
	  out.println( "Nombre : " + clave + "<br>");
	}
%>
</body>
</html>