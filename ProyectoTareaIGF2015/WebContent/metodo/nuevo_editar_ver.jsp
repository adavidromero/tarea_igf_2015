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
    ListHelper2 listHelper = (ListHelper2)ac.getBean("listHelper2");
	String operacion=request.getParameter("operacion");
	String cMetodo = (request.getParameter("idMetodo") != null) ? request.getParameter("idMetodo") : "" ;
	String cClase = (request.getParameter("idClase") != null) ? request.getParameter("idClase") : "" ;
	String idTipoMetodo= "";
	String descripcion = "";
	String tipoRetorno = "";
	String usuario = "";
	String fechaIngreso = "";
	String activo = "";
	String parametros = "";
	Metodo metodo = null; 
	String readonly = "";
	String idReadonly = "";
	boolean esOperacionEditar = false;
	boolean esOperacionVer = false;

	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    if(operacion != null){
        esOperacionEditar=operacion.equalsIgnoreCase("editar");
        esOperacionVer=operacion.equalsIgnoreCase("ver");
    }

	if((esOperacionEditar || esOperacionVer)  && cClase!="" && cMetodo!=""){
		//System.out.println(idMetodo+"\n"+idClase+"\n"+idTipoMetodo+"\n");
		//Metodo metodo = ctrlMetodo.obtenerPorId(idMetodo);
		metodo = ctrlMetodo.obtenerPorIdCompuesto(cClase,cMetodo);
		descripcion=metodo.getdMetodo();
		idTipoMetodo= "";
		descripcion = metodo.getdMetodo();
		tipoRetorno = metodo.getdTipoRetorno();
		usuario = metodo.getcUsuario();
		activo = metodo.getbActivo().toString();
		parametros = metodo.getnParametros().toString();
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
                          <input id="cMetodo" name="cMetodo" placeholder="Digite un nuevo codigo del metodo" class="form-control input-md" type="text"
                          value="<%=cMetodo %>" <%=readonly %> <%=idReadonly %>>
                          <span class="help-block"></span>  
                          </div>
                        </div>
                        
                        <!-- Text input-->
                        <div class="form-group">
                          <label class="col-md-4 control-label" for="textinput">C�digo de clase:</label>  
                          <div class="col-md-4">
                          <%=listHelper.generaListaClasesMetodo(metodo, "cClase", readonly) %>
                          <span class="help-block"></span>  
                          </div>
                        </div>
                        
                        <!-- Text input-->
                        <div class="form-group">
                          <label class="col-md-4 control-label" for="textinput">C�digo de tipo de m�todo:</label>  
                          <div class="col-md-4">
                          <%=listHelper.generaListaTiposMetodoParaMetodo(metodo, "cTipoMetodo", readonly) %>
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
                          <span class="help-block">Escriba 0 para inactivo 1 para activo</span>  
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