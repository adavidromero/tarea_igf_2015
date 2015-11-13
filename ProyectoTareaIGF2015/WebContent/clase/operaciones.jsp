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
    String uriLista="/clase/lista.jsp";
    String msg="";
	String id=(request.getParameter("id") != null) ? request.getParameter("id") : "" ;
	boolean esOperacionCrear=false;
	boolean esOperacionEditar=false;
	boolean esOperacionEliminar=false;

    boolean claseCreada=false;
    boolean claseEditada=false;
    boolean claseEliminada=false;

    if(operacion != null){

    	System.out.println("PING");
    	esOperacionCrear=operacion.equalsIgnoreCase("crear");
        esOperacionEditar=operacion.equalsIgnoreCase("editar");
        esOperacionEliminar=operacion.equalsIgnoreCase("eliminar");
    }

    if((esOperacionCrear || esOperacionEditar || esOperacionEliminar)){
    	ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    	CtrlClase ctrlClase = (CtrlClase)ac.getBean("ctrlClase");
    	CtrlAplicativo ctrlAplicativo = (CtrlAplicativo)ac.getBean("ctrlAplicativo");
    	CtrlTipoClase ctrlTipoClase = (CtrlTipoClase)ac.getBean("ctrlTipoClase");
    	Clase clase = new Clase();

    	if(esOperacionEliminar){
    		claseEliminada = ctrlClase.borraClase(Integer.parseInt(id));
    		if(claseEliminada==true){
    			msg="Aplicativo Eliminado";
    		}else{
    			msg="Aplicativo No Eliminado";
    		}
    	}else{

    	/* datos de la tabla a llenar */
  		String descripcion= request.getParameter("descripcion");
   		String strFechaIngreso = request.getParameter("fechaIngreso");
   		String usuario = request.getParameter("usuario");
   		String aplicativo = request.getParameter("aplicativo");
   		String clasePadre = request.getParameter("clasePadre");
   		String tipoClase = request.getParameter("tipoClase");
   		Date fechaIngreso= new Date();

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try{
			fechaIngreso = formatter.parse(strFechaIngreso);
		}catch(ParseException e){
			e.printStackTrace();
		}

    	if(esOperacionCrear){
    		System.out.println("llega a operacionCrear");
    		claseCreada=ctrlClase.crearClase(Integer.parseInt(id),descripcion,usuario,fechaIngreso,
    				aplicativo,clasePadre,tipoClase);
    		if(claseCreada){
    			msg="Aplicativo creado";
    		}else{
    			msg="Aplicativo no creado";
    		}
    	}
    	
    	if(esOperacionEditar){
    		clase=ctrlClase.obtenerPorId(Integer.parseInt(id));
    		clase.setdClase(descripcion);
    		clase.setcUsuario(usuario);
    		clase.setfIngreso(fechaIngreso);
    		clase.setcAplicativo(ctrlAplicativo.obtenerPorId(aplicativo));
    		clase.setClasePadre(ctrlClase.obtenerPorId(Integer.parseInt(clasePadre)));
    		clase.setcTipoClase(ctrlTipoClase.obtenerPorId(tipoClase));
    		ctrlClase.actualizar(clase);
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

	//Luego de realizar la operación redireccionar al listado correspondiente
    response.sendRedirect(redirectURL);
%>
<%=redirectURL %>
<%=msg %>

</body>
</html>