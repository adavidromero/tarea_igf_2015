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
<title>Listado de Clases Interfaces</title>
<%@include file="../css_js_incluidos.jsp" %>
</head>
<body>
<!-- Incluir barra de navegación -->
<div id="wrapper">
<%@include file="../navbar.jsp" %>
<%

    ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    CtrlClaseInterface ctrlClaseInterface = (CtrlClaseInterface)ac.getBean("ctrlClaseInterface");
    ListHelper listHelper = (ListHelper)ac.getBean("listHelper");
	String operacion=request.getParameter("operacion");
	String id=(request.getParameter("id") != null) ? request.getParameter("id") : "" ;
	String clase="";
	String Interface="";
	String readonly="";
	String idReadonly="";
	boolean esOperacionEditar=false;
	boolean esOperacionVer=false;
	
	ClaseInterface claseinterface = null;

	if((esOperacionEditar || esOperacionVer) && id!=""){
		claseinterface = ctrlClaseInterface.obtenerPorId(Integer.parseInt(id));
		clase=claseinterface.getcClase().getdClase();
		Interface=claseinterface.getcInterface().getdInterface();

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
                        <h1>Clases Interfaces</h1>
                        <div class="pull-right"><a href="<%=context_path %>/claseinterface/lista.jsp">
                        <button class="btn btn-primary">Regresar a Listado</button></a></div>
                        <form class="form-horizontal" action="<%=context_path %>/claseinterface/operaciones.jsp" method="post">
                        <input type="text" id="operacion" name="operacion" value="<%=operacion %>" class="hidden">
                        <fieldset>

                        <!-- Form Name -->
                        <legend>Nueva Clase Interface</legend>

                        <!-- Text input-->
                        <div class="form-group">
                          <label class="col-md-4 control-label" for="textinput">Codigo:</label>  
                          <div class="col-md-4">
                          <input id="id" name="id" placeholder="1" class="form-control input-md" type="text"
                          value="<%=id %>" <%=readonly %> <%=idReadonly %>>
                          <span class="help-block"></span>  
                          </div>
                        </div>

                        <!-- Select Basic -->
                        <div class="form-group">
                          <label class="col-md-4 control-label" for="clase">Clase</label>
                          <div class="col-md-4">
                          	<%=listHelper.generaListaClase(claseinterface, "clase", readonly) %>
                          </div>
                        </div>
                        
                        <!-- Select Basic --> 
                        <div class="form-group">
                          <label class="col-md-4 control-label" for="interface">Interface</label>
                          <div class="col-md-4">
                          	<%=listHelper.generaListaInterface(claseinterface, "interface", readonly) %>
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