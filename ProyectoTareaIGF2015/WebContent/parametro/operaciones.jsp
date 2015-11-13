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
    String uriLista="/parametro/lista.jsp";
    String msg="";
	String cParametro=(request.getParameter("cParametro") != null) ? request.getParameter("cParametro") : "" ;
	String cClase=(request.getParameter("cClase") != null) ? request.getParameter("cClase") : "" ;
	String cMetodo=(request.getParameter("cMetodo") != null) ? request.getParameter("cMetodo") : "" ;
	String dParametro="";
	String dTipoParametro="";
	String cUsuario="";

	boolean esOperacionCrear=false;
	boolean esOperacionEditar=false;
	boolean esOperacionEliminar=false;

    boolean parametroCreado=false;
    boolean parametroEditado=false;
    boolean parametroEliminado=false;

    if(operacion != null){
    	esOperacionCrear=operacion.equalsIgnoreCase("crear");
        esOperacionEditar=operacion.equalsIgnoreCase("editar");
        esOperacionEliminar=operacion.equalsIgnoreCase("eliminar");
    }

    if((esOperacionCrear || esOperacionEditar || esOperacionEliminar) && 
    		cParametro!="" && cClase!="" && cMetodo!=""){
    	ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    	CtrlParametro ctrlParametro = (CtrlParametro)ac.getBean("ctrlParametro");
    	Parametro parametro = new Parametro();

    	if(esOperacionEliminar){
    		parametroEliminado = ctrlParametro.eliminarPorIdCompuesto(cClase, cMetodo, cParametro);
    		if(parametroEliminado==true){
    			msg="TipoAtributo Eliminado";
    		}else{
    			msg="TipoAtributo No Eliminado";
    		}
    	}else{

   		String strFechaIngreso = request.getParameter("fechaIngreso");
		dParametro=request.getParameter("descripcion");
		dTipoParametro=request.getParameter("dTipoParametro");
		cUsuario=request.getParameter("cUsuario");
   		Date fechaIngreso= new Date();

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try{
			fechaIngreso = formatter.parse(strFechaIngreso);
		}catch(ParseException e){
			e.printStackTrace();
		}

    	if(esOperacionCrear){
    		parametroCreado=ctrlParametro.crearParametro(
				cClase,
				cMetodo,
				cParametro,
				dParametro,
				dTipoParametro,
				cUsuario,
				fechaIngreso
    		);

    		if(parametroCreado){
    			msg="TipoAtributo creado";
    		}else{
    			msg="TipoAtributo no creado";
    		}
    	}
    	
    	if(esOperacionEditar){
    		parametroCreado=ctrlParametro.editarParametro(
				cClase,
				cMetodo,
				cParametro,
				dParametro,
				dTipoParametro,
				cUsuario,
				fechaIngreso
    		);
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