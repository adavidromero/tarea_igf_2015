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
	<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listado de Tipos de M�todos</title>
<%@include file="../css_js_incluidos.jsp" %>
</head>
<body>
<!-- Incluir barra de navegaci�n -->
<div id="wrapper">
<%@include file="../navbar.jsp" %>
<%


    ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    CtrlMetodo ctrlMetodo = (CtrlMetodo)ac.getBean("ctrlMetodo");
	String operacion=request.getParameter("operacion");
	String idMetodo = (request.getParameter("idMetodo") != null) ? request.getParameter("idMetodo") : "" ;
	String idClase = (request.getParameter("idClase") != null) ? request.getParameter("idClase") : "" ;
	String idTipoMetodo=(request.getParameter("idTipoMetodo") != null) ? request.getParameter("idTipoMetodo") : "" ;
	String descripcion = "";
	String tipoRetorno = "";
	String usuario = "";
	String fechaIngreso = "";
	String activo = "";
	String parametros = "";
	
	String readonly = "";
	String idReadonly = "";
	boolean esOperacionEditar = false;
	boolean esOperacionVer = false;

	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    if(operacion != null){
        esOperacionEditar=operacion.equalsIgnoreCase("editar");
        esOperacionVer=operacion.equalsIgnoreCase("ver");
    }

	if((esOperacionEditar || esOperacionVer)  && idMetodo!="" && idClase!="" && idTipoMetodo!=""){
		//System.out.println(idMetodo+"\n"+idClase+"\n"+idTipoMetodo+"\n");
		Metodo metodo = ctrlMetodo.obtenerPorId(idMetodo);
		descripcion=metodo.getdMetodo();
		try{
			fechaIngreso=formatter.format(metodo.getfIngreso());
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}

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
                        <h1>Tipos de M�todos</h1>
                        <div class="pull-right"><a href="<%=context_path %>/metodo/lista.jsp">
                        <button class="btn btn-primary">Regresar a Listado</button></a></div>
                        <form class="form-horizontal" action="<%=context_path %>/metodo/operaciones.jsp" method="post">
                        <input type="text" id="operacion" name="operacion" value="<%=operacion %>" class="hidden">
                        <fieldset>

                        <!-- Form Name -->
                        <legend>Nuevo M�todo</legend>

                        <!-- Text input-->
                        <div class="form-group">
                          <label class="col-md-4 control-label" for="textinput">C�digo del m�todo:</label>  
                          <div class="col-md-4">
                          <input id="idMetodo" name="idMetodo" placeholder="Digite un nuevo codigo del metodo" class="form-control input-md" type="text"
                          value="<%=idMetodo %>" <%=readonly %> <%=idReadonly %>>
                          <span class="help-block"></span>  
                          </div>
                        </div>
                        
                        <!-- Text input-->
                        <div class="form-group">
                          <label class="col-md-4 control-label" for="textinput">C�digo de clase:</label>  
                          <div class="col-md-4">
                          <input id="idClase" name="idClase" placeholder="Digite un codigo de clase existente" class="form-control input-md" type="text"
                          value="<%=idClase %>" <%=readonly %> <%=idReadonly %>>
                          <span class="help-block"></span>  
                          </div>
                        </div>
                        
                        <!-- Text input-->
                        <div class="form-group">
                          <label class="col-md-4 control-label" for="textinput">C�digo de tipo de m�todo:</label>  
                          <div class="col-md-4">
                          <input id="idClase" name="idClase" placeholder="Digite un codigo de tipo de m�todo existente" class="form-control input-md" type="text"
                          value="<%=idTipoMetodo %>" <%=readonly %> <%=idReadonly %>>
                          <span class="help-block"></span>  
                          </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                          <label class="col-md-4 control-label" for="textinput">Descripci�n:</label>  
                          <div class="col-md-4">
                          <input id="descripcion" name="descripcion" placeholder="Escriba su descripci�n"
                           class="form-control input-md" type="text" value="<%=descripcion %>" <%=readonly %>>
                          <span class="help-block"></span>  
                          </div>
                        </div>
                        
                        <!-- Text input-->
                        <div class="form-group">
                          <label class="col-md-4 control-label" for="textinput">Tipo de retorno:</label>  
                          <div class="col-md-4">
                          <input id="tipoRetorno" name="tipoRetorno" placeholder="Escriba el tipo de retorno"
                           class="form-control input-md" type="text" value="<%=tipoRetorno %>" <%=readonly %>>
                          <span class="help-block"></span>  
                          </div>
                        </div>
						
						<!-- Text input-->
                        <div class="form-group">
                          <label class="col-md-4 control-label" for="textinput">Usuario:</label>  
                          <div class="col-md-4">
                          <input id="usuario" name="usuario" placeholder="Escriba su usuario"
                           class="form-control input-md" type="text" value="<%=usuario %>" <%=readonly %>>
                          <span class="help-block"></span>  
                          </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                          <label class="col-md-4 control-label" for="textinput">Fecha</label>  
                          <div class="col-md-4">
                          <input id="fechaIngreso" name="fechaIngreso" placeholder="dd/mm/yyyy" 
                          class="form-control input-md datepicker" type="text" 
                          value="<%=fechaIngreso %>" <%=readonly %>>
                          <span class="help-block"></span>  
                          </div>
                        </div>
                        
                        
                        <!-- Text input-->
                        <div class="form-group">
                          <label class="col-md-4 control-label" for="textinput">Activo:</label>  
                          <div class="col-md-4">
                          <input id="activo" name="activo" placeholder="Escriba un n�mero para activo"
                           class="form-control input-md" type="text" value="<%=activo %>" <%=readonly %>>
                          <span class="help-block"></span>  
                          </div>
                        </div>
                        
                        <!-- Text input-->
                        <div class="form-group">
                          <label class="col-md-4 control-label" for="textinput">Par�metros:</label>  
                          <div class="col-md-4">
                          <input id="parametros" name="parametros" placeholder="Escriba la cantidad de par�metros"
                           class="form-control input-md" type="text" value="<%=parametros %>" <%=readonly %>>
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