<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="org.springframework.context.*" %>
    <%@ page import="org.springframework.context.support.*" %>
    <%@ page import="org.springframework.web.context.support.*" %>
    <%@ page import="com.fia.igf.app.negocio.*" %>
    <%@ page import="com.fia.igf.app.dominio.*" %>
    <%@ page import="com.fia.igf.app.utilidades.ui.*" %>
    <%@ page import="java.text.DateFormat" %>
	<%@ page import="java.text.SimpleDateFormat"%>
	<%@ page import="java.text.ParseException" %>
	<%@ page import="java.util.Date" %>
	<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listado de Tipos de Métodos</title>
<%@include file="../css_js_incluidos.jsp" %>
</head>
<body>
<!-- Incluir barra de navegación -->
<div id="wrapper">
<%@include file="../navbar.jsp" %>
<%

    ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    CtrlInterfaceImplementa ctrlInterfaceImplementa = (CtrlInterfaceImplementa)ac.getBean("ctrlInterfaceImplementa");
    ListHelper2 listHelper = (ListHelper2)ac.getBean("listHelper2");
	String operacion=request.getParameter("operacion");
	String id=(request.getParameter("id") != null) ? request.getParameter("id") : "" ;
	String readonly="";
	String idReadonly="";
	boolean esOperacionEditar=false;
	boolean esOperacionVer=false;
	InterfaceImplementa interfaceImplementa =null;

	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    if(operacion != null){
        esOperacionEditar=operacion.equalsIgnoreCase("editar");
        esOperacionVer=operacion.equalsIgnoreCase("ver");
    }

	if((esOperacionEditar || esOperacionVer) && id!=""){
		interfaceImplementa = ctrlInterfaceImplementa.obtenerPorId(Integer.parseInt(id));
		if(esOperacionEditar){
			idReadonly="readonly";
		}

		if(esOperacionVer){
			readonly="readonly";
		}
	}
%>
        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                    <!-- Aqui tiene que ir el contenido -->
                        <h1>Tipos de Métodos</h1>
                        <div class="pull-right"><a href="<%=context_path %>/interfaceimplementa/lista.jsp">
                        <button class="btn btn-primary">Regresar a Listado</button></a></div>
                        <form class="form-horizontal" action="<%=context_path %>/interfaceimplementa/operaciones.jsp" method="post">
                        <input type="text" id="operacion" name="operacion" value="<%=operacion %>" class="hidden">
                        <fieldset>

                        <!-- Form Name -->
                        <legend>Nuevo Aplicativo</legend>

                        <!-- Text input-->
                        <div class="form-group">
                          <label class="col-md-4 control-label" for="textinput">Codigo:</label>  
                          <div class="col-md-4">
                          <input id="id" name="id" placeholder="ej: AM001" class="form-control input-md" type="text"
                          value="<%=id %>" <%=readonly %> <%=idReadonly %>>
                          <span class="help-block"></span>  
                          </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                          <label class="col-md-4 control-label" for="textinput">Interface Hijo:</label>  
                          <div class="col-md-4">
                          <%=listHelper.generarSelectListaInterfaceHijoInterfaceImplementa(interfaceImplementa, "cInterfaceHijo", readonly) %>
                          <span class="help-block"></span>  
                          </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                          <label class="col-md-4 control-label" for="textinput">Interface Hijo:</label>  
                          <div class="col-md-4">
                          <%=listHelper.generarSelectListaInterfacePadreInterfaceImplementa(interfaceImplementa, "cInterfacePadre", readonly) %>
                          <span class="help-block"></span>  
                          </div>
                        </div>


                        <!-- Button -->

						<%
							if(!esOperacionVer){
						%>
                        <div class="form-group">
                          <label class="col-md-4 control-label" for="singlebutton"></label>
                          <div class="col-md-4">
                            <button id="singlebutton" name="singlebutton" class="btn btn-primary">Guardar</button>
                          </div>
                        </div>
						<%
							}
						%>
                        </fieldset>
                        </form>
                        <script>
                        	function readyFn(){
                        		var options={
                                	format: "dd/mm/yyyy",
                                	clearBtn: true,
                                	language: "es",
                                	orientation: "bottom right"
                        		};
                        		$('.datepicker').datepicker(options);
                        	}

                        	$(document).ready(readyFn);
                        </script>
                    </div>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->
</div>
</body>
</html>