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
    String uriLista="/interfaceimplementa/lista.jsp";
    String msg="";
	String id=(request.getParameter("id") != null) ? request.getParameter("id") : "" ;
	String cInterfaceHijo="";
	String cInterfacePadre="";

	boolean esOperacionCrear=false;
	boolean esOperacionEditar=false;
	boolean esOperacionEliminar=false;

    boolean iiCreado=false;
    boolean iiEditado=false;
    boolean iiEliminado=false;

    if(operacion != null){
    	esOperacionCrear=operacion.equalsIgnoreCase("crear");
        esOperacionEditar=operacion.equalsIgnoreCase("editar");
        esOperacionEliminar=operacion.equalsIgnoreCase("eliminar");
    }

    if((esOperacionCrear || esOperacionEditar || esOperacionEliminar) && id!=""){
    	ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    	CtrlInterfaceImplementa ctrlInterfaceImplementa = (CtrlInterfaceImplementa)ac.getBean("ctrlInterfaceImplementa");
    	InterfaceImplementa interfaceImplementa = new InterfaceImplementa();
    	cInterfaceHijo=request.getParameter("cInterfaceHijo");
    	cInterfacePadre=request.getParameter("cInterfacePadre");

    	if(esOperacionEliminar){
    		iiEliminado = ctrlInterfaceImplementa.borraInterfaceImplementa(Integer.parseInt(id));
    		if(iiEliminado==true){
    			msg="Aplicativo Eliminado";
    		}else{
    			msg="Aplicativo No Eliminado";
    		}
    	}else{

  		String descripcion= request.getParameter("descripcion");

    	if(esOperacionCrear){
    		iiCreado=ctrlInterfaceImplementa.crearInterfaceImplementa(id, cInterfaceHijo, cInterfacePadre);

    		if(iiCreado){
    			msg="Aplicativo creado";
    		}else{
    			msg="Aplicativo no creado";
    		}
    	}
    	
    	if(esOperacionEditar){
    		iiEditado=ctrlInterfaceImplementa.actualizar(id,cInterfaceHijo,cInterfacePadre);
    		msg="Aplicativo modificado con exito!";
    	}
    		
    	}


    }
    else{
    	msg="Ninguna operación seleccionada";
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
<%
	String redirectURL = request.getContextPath()+uriLista;
//request.getScheme()+"//"+request.getServerName()+
	//":"+request.getServerPort()+

	//Luego de realizar la operación redireccionar al listado correspondiente
    response.sendRedirect(redirectURL);
%>
<%=redirectURL %>
<%=msg %>

</body>
</html>