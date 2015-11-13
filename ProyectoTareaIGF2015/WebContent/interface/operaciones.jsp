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
    String uriLista="/interface/lista.jsp";
    String msg="";
	String id=(request.getParameter("id") != null) ? request.getParameter("id") : "" ;

	boolean esOperacionCrear=false;
	boolean esOperacionEditar=false;
	boolean esOperacionEliminar=false;

    boolean interfaceCreado=false;
    boolean interfaceEditado=false;
    boolean interfaceEliminado=false;

    if(operacion != null){
    	esOperacionCrear=operacion.equalsIgnoreCase("crear");
        esOperacionEditar=operacion.equalsIgnoreCase("editar");
        esOperacionEliminar=operacion.equalsIgnoreCase("eliminar");
    }

    if((esOperacionCrear || esOperacionEditar || esOperacionEliminar) && id!=""){
    	ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    	CtrlInterface ctrlInterface = (CtrlInterface)ac.getBean("ctrlInterface");
    	Interface interfaz = new Interface();

    	if(esOperacionEliminar){
    		interfaceEliminado = ctrlInterface.borraInterface(Integer.parseInt(id));
    		if(interfaceEliminado==true){
    			msg="Interface Eliminado";
    		}else{
    			msg="Interface No Eliminado";
    		}
    	}else{

  		String descripcion= request.getParameter("descripcion");
  		String usuario= request.getParameter("usuario");
   		String strFechaIngreso = request.getParameter("fechaIngreso");
   		Date fechaIngreso= new Date();

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try{
			fechaIngreso = formatter.parse(strFechaIngreso);
		}catch(ParseException e){
			e.printStackTrace();
		}

    	if(esOperacionCrear){
    		interfaceCreado=ctrlInterface.crearInterface(Integer.parseInt(id), descripcion, usuario, fechaIngreso);

    		if(interfaceCreado){
    			msg="Interface creado";
    		}else{
    			msg="Interface no creado";
    		}
    	}
    	
    	if(esOperacionEditar){
    		interfaz=ctrlInterface.obtenerPorId(Integer.parseInt(id));
    		interfaz.setdInterface(descripcion);
    		interfaz.setcUsuario(usuario);
    		interfaz.setfIngreso(fechaIngreso);
    		ctrlInterface.actualizar(interfaz);
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