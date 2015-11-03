<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="org.springframework.context.*" %>
    <%@ page import="org.springframework.context.support.*" %>
    <%@ page import="org.springframework.web.context.support.*" %>
    <%@ page import="com.fia.igf.app.negocio.*" %>
    <%@ page import="com.fia.igf.app.dominio.*" %>
    <%@ page import="java.text.DateFormat" %>
	<%@ page import="java.text.SimpleDateFormat"%>;
	<%@ page import="java.text.ParseException" %>
	<%@ page import="java.util.Date" %>
    
    <%

    String msg="";
    ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    CtrlAplicativo ctrlAplicativo = (CtrlAplicativo)ac.getBean("ctrlAplicativo");
    String id= request.getParameter("id");
    String descripcion= request.getParameter("descripcion");
    String strFechaIngreso = request.getParameter("fechaIngreso");
    Date fechaIngreso= new Date();
    boolean aplicativoCreado=false;

	SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
	try{
		fechaIngreso = formatter.parse(strFechaIngreso);
		
	}catch(ParseException e){
		e.printStackTrace();
	}
    aplicativoCreado=ctrlAplicativo.crearAplicativo(id, descripcion, fechaIngreso);
    if(aplicativoCreado){
    	msg="Aplicativo creado";
    }else{
    	msg="Aplicativo no creado";
    }
    
    	
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Operaciones</title>
</head>
<body>
Esta pagina deberia de Realizar una operación y al final redireccionar
<%=msg %>
</body>
</html>