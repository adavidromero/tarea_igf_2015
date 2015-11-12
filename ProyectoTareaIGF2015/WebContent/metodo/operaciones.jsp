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
    String uriLista="/metodo/lista.jsp";
    String msg="";
	String id=(request.getParameter("id") != null) ? request.getParameter("id") : "" ;

	boolean esOperacionCrear=false;
	boolean esOperacionEditar=false;
	boolean esOperacionEliminar=false;

    boolean metodoCreado=false;
    boolean metodoEditado=false;
    boolean metodoEliminado=false;

    if(operacion != null){
    	esOperacionCrear=operacion.equalsIgnoreCase("crear");
        esOperacionEditar=operacion.equalsIgnoreCase("editar");
        esOperacionEliminar=operacion.equalsIgnoreCase("eliminar");
    }

    if((esOperacionCrear || esOperacionEditar || esOperacionEliminar) && id!=""){
    	ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    	CtrlMetodo ctrlMetodo = (CtrlMetodo)ac.getBean("ctrlMetodo");
    	Metodo metodo = new Metodo();

    	if(esOperacionEliminar){
    		metodoEliminado = ctrlMetodo.borraMetodo(id);
    		if(metodoEliminado==true){
    			msg="Tipo de método eliminado";
    		}else{
    			msg="Tipo de método no eliminado";
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
//                  public boolean crearMetodo(Integer cMetodo, Clase cClase, TipoMetodo ctipoMetodo, String dMetodo, String dtipoRetorno, String usuario, Date fechaIngreso, Integer activo, Integer parametros ){
    		metodoCreado=ctrlMetodo.crearMetodo(Integer.parseInt(id.trim()), descripcion, fechaIngreso);

    		if(metodoCreado){
    			msg="Tipo de método creado";
    		}else{
    			msg="Tipo de metodo no creado";
    		}
    	}
    	
    	if(esOperacionEditar){
    		metodo=ctrlMetodo.obtenerPorId(id);
    		metodo.setdMetodo(descripcion);
    		
    		metodo.setfIngreso(fechaIngreso);
    		ctrlMetodo.actualizar(metodo);
    		msg="Tipo de método modificado con exito!";
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