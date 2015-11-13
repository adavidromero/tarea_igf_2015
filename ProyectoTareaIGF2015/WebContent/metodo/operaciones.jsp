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
	String cMetodo = (request.getParameter("cMetodo") != null) ? request.getParameter("cMetodo") : "" ;
	String cClase = (request.getParameter("cClase") != null) ? request.getParameter("cClase") : "" ;

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

    if((esOperacionCrear || esOperacionEditar || esOperacionEliminar) && cMetodo!="" && cClase!=""){
    	ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    	CtrlMetodo ctrlMetodo = (CtrlMetodo)ac.getBean("ctrlMetodo");
    	Metodo metodo = new Metodo();

    	if(esOperacionEliminar){
    		metodoEliminado = ctrlMetodo.borraMetodoIdCompuesta(cClase,cMetodo);
    		if(metodoEliminado==true){
    			msg="Tipo de método eliminado";
    		}else{
    			msg="Tipo de método no eliminado";
    		}
    	}else{

  		String descripcion= request.getParameter("descripcion");
   		String strFechaIngreso = request.getParameter("fechaIngreso");
   		String dTipoRetorno = request.getParameter("tipoRetorno");
   		String cUsuario= request.getParameter("usuario");
   		String bActivo= request.getParameter("activo");
   		String nParametros= request.getParameter("parametros");
   		String cTipoMetodo= request.getParameter("cTipoMetodo");

   		Date fechaIngreso= new Date();

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try{
			fechaIngreso = formatter.parse(strFechaIngreso);
		}catch(ParseException e){
			e.printStackTrace();
		}

    	if(esOperacionCrear){
    		metodoCreado=ctrlMetodo.crearMetodo(
                                cClase,
                                cMetodo,
                                descripcion,
                                dTipoRetorno,
                                cUsuario,
                                fechaIngreso,
                                bActivo,
                                nParametros,
                                cTipoMetodo
			);

    		if(metodoCreado){
    			msg="Tipo de método creado";
    			System.out.println(msg);
    		}else{
    			msg="Tipo de metodo no creado";
    			System.out.println(msg);
    		}
    	}
    	
    	if(esOperacionEditar){
    		metodoCreado=ctrlMetodo.editarMetodo(
                                cClase,
                                cMetodo,
                                descripcion,
                                dTipoRetorno,
                                cUsuario,
                                fechaIngreso,
                                bActivo,
                                nParametros,
                                cTipoMetodo
			);
    		
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