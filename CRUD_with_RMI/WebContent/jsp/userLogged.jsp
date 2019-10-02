<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenido</title>
</head>
<body>
Bienvenido <%= request.getParameter("user") %> !!
<div align="right">
<button onclick="location.href='controlFincas?idaction=ADMIN_FINCAS'" style='width:180px'>Gestión Fincas</button>
</div>
<div align="right">
<button onclick="location.href='controlCultivos?idaction=ADMIN_CULTIVOS'" style='width:180px'>Gestión Cultivos</button>
</div>
</body>
</html>