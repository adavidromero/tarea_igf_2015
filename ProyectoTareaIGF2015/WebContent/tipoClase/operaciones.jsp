<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="org.springframework.context.*" %>
    <%@ page import="org.springframework.context.support.*" %>
    <%@ page import="org.springframework.web.context.support.*" %>
    <%@ page import="com.fia.igf.app.negocio.*" %>
    <%@ page import="com.fia.igf.app.dominio.*" %>
    <%@ page import="java.text.DateFormat" %>
	<%@ page import="java.text.SimpleDateFormat"%>
	<%@ page import="java.text.ParseException" %>
	<%@ page import="java.util.Date" %>
    
    <%
    String operacion = request.getParameter("operacion");
    String uriLista="/tipoClase/lista.jsp";
    String msg="";
	String id=(request.getParameter("id") != null) ? request.getParameter("id") : "" ;

	boolean esOperacionCrear=false;
	boolean esOperacionEditar=false;
	boolean esOperacionEliminar=false;

    boolean tipoClaseCreado=false;
    boolean tipoClaseEditado=false;
    boolean tipoClaseEliminado=false;

    if(operacion != null){
    	esOperacionCrear=operacion.equalsIgnoreCase("crear");
        esOperacionEditar=operacion.equalsIgnoreCase("editar");
        esOperacionEliminar=operacion.equalsIgnoreCase("eliminar");
    }

    if((esOperacionCrear || esOperacionEditar || esOperacionEliminar) && id!=""){
    	ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    	CtrlTipoClase ctrlTipoClase = (CtrlTipoClase)ac.getBean("ctrlTipoClase");
    	TipoClase tipoClase = new TipoClase();

    	if(esOperacionEliminar){
    		tipoClaseEliminado = ctrlTipoClase.borraClase(id);
    		if(tipoClaseEliminado==true){
    			msg="Tipo de clase Eliminado";
    		}else{
    			msg="Tipo de clase No Eliminado";
    		}
    	}else{

  		String descripcion= request.getParameter("descripcion");
   		String strFechaIngreso = request.getParameter("fechaIngreso");
   		Date fechaIngreso= new Date();

		SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
		try{
			fechaIngreso = formatter.parse(strFechaIngreso);
		}catch(ParseException e){
			e.printStackTrace();
		}

    	if(esOperacionCrear){
    		tipoClaseCreado = ctrlTipoClase.crearTipoClase(id, descripcion, fechaIngreso);

    		if(tipoClaseCreado){
    			msg="Tipo de Clase creado";
    		}else{
    			msg="Tipo de Clase no creado";
    		}
    	}
    	
    	if(esOperacionEditar){
    		tipoClase=ctrlTipoClase.obtenerPorId(id);
    		tipoClase.setdTipoClase(descripcion);
    		tipoClase.setfIngreso(fechaIngreso);
    		ctrlTipoClase.actualizar(tipoClase);
    		msg="Tipo de Clase modificado con exito!";
    	}
    		
    	}


    }
    else{
    	msg="Ninguna operaci�n seleccionada";
    }
    	
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Operaciones</title>
</head>
<body>
Esta pagina deberia de Realizar una operaci�n y al final redireccionar
<%
	String redirectURL = request.getContextPath()+uriLista;
//request.getScheme()+"//"+request.getServerName()+
	//":"+request.getServerPort()+

	//Luego de realizar la operaci�n redireccionar al listado correspondiente
    response.sendRedirect(redirectURL);
%>
<%=redirectURL %>
<%=msg %>

</body>
</html>