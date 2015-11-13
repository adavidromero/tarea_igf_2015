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
    String uriLista="/aplicativo/lista.jsp";
    String msg="";
	String id=(request.getParameter("id") != null) ? request.getParameter("id") : "" ;
	String cAtributo=(request.getParameter("cAtributo") != null) ? request.getParameter("cAtributo") : "" ;
	String cClase=(request.getParameter("cClase") != null) ? request.getParameter("cClase") : "" ;
	String metodo ="";
	String descripcion="";
	String descripcionTipoDatoAtributo="";
	String usuario="";
	String fechaIngreso="";
	String tipoAtributo="";

	boolean esOperacionCrear=false;
	boolean esOperacionEditar=false;
	boolean esOperacionEliminar=false;

    boolean atributoCreado=false;
    boolean atributoEditado=false;
    boolean atributoEliminado=false;

    if(operacion != null){
    	esOperacionCrear=operacion.equalsIgnoreCase("crear");
        esOperacionEditar=operacion.equalsIgnoreCase("editar");
        esOperacionEliminar=operacion.equalsIgnoreCase("eliminar");
    }

    if((esOperacionCrear || esOperacionEditar || esOperacionEliminar) && cAtributo!="" && cClase!=""){
    	ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    	CtrlAtributo ctrlAtributo = (CtrlAtributo)ac.getBean("ctrlAtributo");
    	Atributo atributo = new Atributo();
    	metodo = atributo.getcMetodo().getcMetodo();

    	if(esOperacionEliminar){
    		atributoEliminado = ctrlAtributo.borraAtributo(Integer.parseInt(cAtributo),
    				Integer.parseInt(cClase));
    		if(atributoEliminado==true){
    			msg="Atributo Eliminado";
    		}else{
    			msg="Atributo No Eliminado";
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
    		atributoCreado=ctrlAtributo.crearAtributo();

    		if(aplicativoCreado){
    			msg="Aplicativo creado";
    		}else{
    			msg="Aplicativo no creado";
    		}
    	}
    	
    	if(esOperacionEditar){
    		aplicativo=ctrlAplicativo.obtenerPorId(id);
    		aplicativo.setdAplicativo(descripcion);
    		aplicativo.setfIngreso(fechaIngreso);
    		ctrlAplicativo.actualizar(aplicativo);
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