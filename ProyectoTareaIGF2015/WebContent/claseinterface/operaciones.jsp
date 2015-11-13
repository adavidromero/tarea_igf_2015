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
    String uriLista="/claseinterface/lista.jsp";
    String msg="";
	String id=(request.getParameter("id") != null) ? request.getParameter("id") : "" ;
	boolean esOperacionCrear=false;
	boolean esOperacionEditar=false;
	boolean esOperacionEliminar=false;

    boolean claseInterfaceCreada=false;
    boolean claseInterfaceEditada=false;
    boolean claseInterfaceEliminada=false;

    if(operacion != null){

    	System.out.println("PING");
    	esOperacionCrear=operacion.equalsIgnoreCase("crear");
        esOperacionEditar=operacion.equalsIgnoreCase("editar");
        esOperacionEliminar=operacion.equalsIgnoreCase("eliminar");
    }

    if((esOperacionCrear || esOperacionEditar || esOperacionEliminar)){
    	ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    	CtrlClaseInterface ctrlClaseInterface = (CtrlClaseInterface)ac.getBean("ctrlClaseInterface");
    	CtrlClase ctrlClase = (CtrlClase)ac.getBean("ctrlClase");
    	CtrlInterface ctrlInterface = (CtrlInterface)ac.getBean("ctrlInterface");
    	ClaseInterface claseInterface = new ClaseInterface();

    	if(esOperacionEliminar){
    		claseInterfaceEliminada = ctrlClaseInterface.borraClaseInterface(Integer.parseInt(id));
    		if(claseInterfaceEliminada==true){
    			msg="Clase Interface Eliminada";
    		}else{
    			msg="Clase Interface No Eliminado";
    		}
    	}else{

    	/* datos de la tabla a llenar */
  		String descripcion= request.getParameter("codigo");
   		String clase = request.getParameter("clase");
   		String iinterface = request.getParameter("interface");

		if(esOperacionCrear){
    		System.out.println("llega a operacionCrear");
    		claseInterfaceCreada=ctrlClaseInterface.crearClaseInterface(Integer.parseInt(id),clase,iinterface);
    		if(claseInterfaceCreada){
    			msg="Clase Interface creada";
    		}else{
    			msg="Clase Interface no creada";
    		}
    	}
    	
    	if(esOperacionEditar){
    		claseInterface=ctrlClaseInterface.obtenerPorId(Integer.parseInt(id));
    		claseInterface.setcClase(ctrlClase.obtenerPorId(Integer.parseInt(clase)));
    		claseInterface.setcInterface(ctrlInterface.obtenerPorId(Integer.parseInt(iinterface)));
    		ctrlClaseInterface.actualizar(claseInterface);
    		msg="Clase Interface modificada con exito!";
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

	//Luego de realizar la operación redireccionar al listado correspondiente
    response.sendRedirect(redirectURL);
%>
<%=redirectURL %>
<%=msg %>

</body>
</html>