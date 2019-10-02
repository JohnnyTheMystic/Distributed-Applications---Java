<%@page import="johnnythemystic.beans.Finca"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body style="background-color:#ffE6FF">
<div align="left">
<table border=2 cellspacing=10 cellpadding=2 bordercolor="0066FF"><tr>
<th>Fincas actualmente registradas</th>
</tr>
<%
	ArrayList<Finca> fincas = (ArrayList<Finca>) request.getSession().getAttribute("FINCAS");
	if (fincas == null){
		out.println( "<tr><td> No existen Fincas </td></tr>");
	}else{
		for (int i=0; i <fincas.size(); i++){
			out.println( "<tr><td>Id Finca : " + fincas.get(i).getId()  + "</td>");
		  	out.println( "<td>Nombre Finca : " + fincas.get(i).getName()  + "</td>");
		  	out.println( "<td>Hectáreas : " + fincas.get(i).getHectareas() + "</td></tr>");
		}
	}
%>
</table>
</div>
<br><br>
</body>
</html>