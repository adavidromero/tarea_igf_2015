<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="org.springframework.context.*" %>
    <%@ page import="org.springframework.context.support.*" %>
    <%@ page import="org.springframework.web.context.support.*" %>
    <%@ page import="com.fia.igf.app.negocio.*" %>
    <%@ page import="com.fia.igf.app.utilidades.ui.*" %>
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
<title>Listado de Aplicativos</title>
<%@include file="../css_js_incluidos.jsp" %>
</head>
<body>
<!-- Incluir barra de navegación -->
<div id="wrapper">
<%@include file="../navbar.jsp" %>
<%


    ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    CtrlAtributo ctrlAtributo = (CtrlAtributo)ac.getBean("ctrlAtributo");
    ListHelper listHelper = (ListHelper)ac.getBean("listHelper");
	String operacion=request.getParameter("operacion");
	Atributo atributo = null;
	String cAtributo=(request.getParameter("cAtributo") != null) ? request.getParameter("cAtributo") : "" ;
	String cClase=(request.getParameter("cClase") != null) ? request.getParameter("cClase") : "" ;
	String metodo="";
	String descripcion="";
	String descripcionTipoDatoAtributo="";
	String usuario="";
	String fechaIngreso="";
	String tipoAtributo="";
	String readonly="";
	String idReadonly="";
	boolean esOperacionEditar=false;
	boolean esOperacionVer=false;

	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    if(operacion != null){
        esOperacionEditar=operacion.equalsIgnoreCase("editar");
        esOperacionVer=operacion.equalsIgnoreCase("ver");
    }

	if((esOperacionEditar || esOperacionVer) && cAtributo!="" && cClase!=""){
		atributo = ctrlAtributo.obtenerPorId(cAtributo,cClase);
		descripcion=atributo.getdAtributo();
		metodo=atributo.getcMetodo().getdMetodo();
		descripcion=atributo.getdAtributo();
		descripcionTipoDatoAtributo=atributo.getdTipoDatoAtributo();
		usuario=atributo.getcUsuario();
		tipoAtributo=atributo.getcTipoAtributo().getdTipoAtributo();
		
		try{
			fechaIngreso=formatter.format(atributo.getfIngreso());
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
                        <h1>Atributos</h1>
                        <div class="pull-right"><a href="<%=context_path %>/atributo/lista.jsp">
                        <button class="btn btn-primary">Regresar a Listado</button></a></div>
                        <form class="form-horizontal" action="<%=context_path %>/atributo/operaciones.jsp" method="post">
                        <input type="text" id="operacion" name="operacion" value="<%=operacion %>" class="hidden">
                        <fieldset>

                        <!-- Form Name -->
                        <legend>Nuevo Atributo</legend>

                        <!-- Text input cambiar por select-->
                        <div class="form-group">
                          <label class="col-md-4 control-label" for="textinput">Clase:</label>  
                          <div class="col-md-4">
                          <%=listHelper.generaListaClasesParaAtributo(atributo, "clase", readonly) %>
                          <span class="help-block"></span>  
                          </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                          <label class="col-md-4 control-label" for="textinput">Codigo Atributo:</label>  
                          <div class="col-md-4">
                          <input id="id" name="id" placeholder="ej: AM001" class="form-control input-md" type="text"
                          value="<%=cAtributo %>" <%=readonly %> <%=idReadonly %>>
                          <span class="help-block"></span>  
                          </div>
                        </div>
                        
                        
                        <!-- Text input-->
                        <div class="form-group">
                          <label class="col-md-4 control-label" for="textinput">Metodo:</label>  
                          <div class="col-md-4">
                          <input id="metodo" name="metodo" placeholder="" class="form-control input-md" type="text"
                          value="<%=metodo %>" <%=readonly %> <%=idReadonly %>>
                          <span class="help-block"></span>  
                          </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                          <label class="col-md-4 control-label" for="textinput">Descripción:</label>  
                          <div class="col-md-4">
                          <input id="descripcion" name="descripcion" placeholder="escriba su descripción"
                           class="form-control input-md" type="text" value="<%=descripcion %>" <%=readonly %>>
                          <span class="help-block"></span>  
                          </div>
                        </div>
                        
                        
                        <!-- Text input-->
                        <div class="form-group">
                          <label class="col-md-4 control-label" for="textinput">Descripción Tipo Dato Atributo:</label>  
                          <div class="col-md-4">
                          <input id="descripcionTipoDatoAtributo" name="descripcionTipoDatoAtributo" placeholder="escriba su descripción"
                           class="form-control input-md" type="text" value="<%=descripcionTipoDatoAtributo %>" <%=readonly %>>
                          <span class="help-block"></span>  
                          </div>
                        </div>
                        
                        <!-- Text input-->
                        <div class="form-group">
                          <label class="col-md-4 control-label" for="textinput">Usuario:</label>  
                          <div class="col-md-4">
                          <input id="descripcion" name="descripcion" placeholder="escriba su descripción"
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
                          <label class="col-md-4 control-label" for="textinput">Tipo Atributo:</label>  
                          <div class="col-md-4">
                          <%=listHelper.listaTipoAtributo(atributo, "tipoAtributo", readonly) %>
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