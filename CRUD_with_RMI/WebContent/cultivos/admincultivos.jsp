<%@page import="java.util.ArrayList"%>
<%@page import="johnnythemystic.beans.Finca"%>
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
<title>Gestión de Cultivos</title>
</head>
<body style="background-color:#E6E6FA">

<%@ include file = "/cultivos/showcultivos.jsp" %>

<div align="center">
<table border=2 cellspacing=10 cellpadding=2 bordercolor="0066FF">
	<tr><th><b>Añadir Cultivo a Finca</b></th></tr>
	<tr><td><form id="addForm" action="controlCultivos?idaction=ADD_CULTIVO" method="POST">
		<p>Id de la Finca:<input type="text" id="user" name="idFinca"/></p>
		<p>Descripción del Cultivo:<input type="text" id="hectareas" name="descripcionCultivo"/></p>
		<input align="left" type="submit" value="Añadir Cultivo"/>
	</form></td></tr>
	</table>
</div>

<br><br>
<div align="center">
<table border=2 cellspacing=10 cellpadding=2 bordercolor="0066FF">
	<tr><td align="center"><b>Eliminar Cultivo</b></td>
	<tr><td><form id="deleteForm" action="controlCultivos?idaction=DELETE_CULTIVO" method="POST">
		<p>Id de la Finca:<input type="text" id="user" name="idFinca"/></p>
		<p>Descripción del Cultivo:<input type="text" id="hectareas" name="descripcionCultivo"/></p>
		<input align="left" type="submit" value="Eliminar Cultivo"/>
	</form></td></tr></table>
</div><br><br>
<div align="center">
<button onclick="location.href='controlCultivos?idaction=SHOW_CULTIVOS'" style='width:180px'>Ver Cultivos</button>
</div>

<div align="right">
<button onclick="location.href='controlFincas?idaction=ADMIN_FINCAS'" style='width:180px'>Gestión Fincas</button>
</div>

</body>
</html>