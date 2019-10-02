<%@page import="java.util.ArrayList"%>
<%@page import="johnnythemystic.beans.Finca"%>
<%@page import="johnnythemystic.servlets.ControlFincas"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Enumeration"%>
<%@page import="johnnythemystic.beans.User"%>
<%@page import="java.util.Hashtable"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestión de Fincas</title>
</head>
<body style="background-color:#E6E6FA">
 <%@include file = "/fincas/showfincas.jsp"%>

<div align="center">
<table border=2 cellspacing=10 cellpadding=2 bordercolor="0066FF">
	<tr><th><b>Añadir Finca</b></th></tr>
	<tr><td><form id="addForm" action="controlFincas?idaction=ADD_FINCA" method="POST">
		<p>Nombre:<input type="text" id="user" name="nombreFinca"/></p>
		<p>Hectáreas:<input type="text" id="hectareas" name="hectareas"/></p>
		<input align="left" type="submit" value="Añadir Finca"/>
	</form></td></tr>
	</table>
</div>

<div align="center">
<table border=2 cellspacing=10 cellpadding=2 bordercolor="0066FF">
	<tr><th><b>Modificar Finca</b></th></tr>
	<tr><td><form id="updateForm" action="controlFincas?idaction=UPDATE_FINCA" method="POST">
		<p>Id de la Finca:<input type="text" id="user" name="idFinca"/></p>
		<p>Nuevo Nombre:<input type="text" id="user" name="nombreFinca"/></p>
		<p>Nuevas Hectáreas:<input type="text" id="hectareas" name="hectareas"/></p>
		<input align="left" type="submit" value="Modificar Finca"/>
	</form></td></tr>
	</table>
</div>

<br><br>
<div align="center">
<table border=2 cellspacing=10 cellpadding=2 bordercolor="0066FF">
	<tr><td align="center"><b>Eliminar Finca</b></td>
	<tr><td><form id="deleteForm" action="controlFincas?idaction=DELETE_FINCA" method="POST">
		Nombre de la Finca a Eliminar:<input type="text" id="user" name="nombreFinca"/><br/>
		<input type="submit" value="Eliminar"/>
	</form></td></tr></table>
</div>
<br><br>

<div align="center">
<button onclick="location.href='controlFincas?idaction=SHOW_FINCAS'" style='width:180px'>Ver Fincas</button>
</div>

<div align="right">
<button onclick="location.href='controlCultivos?idaction=ADMIN_CULTIVOS'" style='width:180px'>Gestión Cultivos</button>
</div>

</body>
</html>