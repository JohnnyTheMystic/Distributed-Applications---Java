<%@page import="java.util.Enumeration"%>
<%@page import="johnnythemystic.servlets.Control"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Borrado de Usuario</title>
</head>
<body>

<%
	out.println("Usuario " + request.getParameter("user") + " Borrado del Registro <br>");
	
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