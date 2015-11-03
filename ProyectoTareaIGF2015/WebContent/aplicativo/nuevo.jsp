<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                    <!-- Aqui tiene que ir el contenido -->
                        <h1>Aplicativos </h1>
                        <div class="pull-right"><a href="<%=context_path %>/aplicativo/lista.jsp"><button class="btn btn-primary">Regresar a Listado</button></a></div>
                        <form class="form-horizontal" action="<%=context_path %>/aplicativo/operaciones.jsp" method="post">
                        <fieldset>

                        <!-- Form Name -->
                        <legend>Nuevo Aplicativo</legend>

                        <!-- Text input-->
                        <div class="form-group">
                          <label class="col-md-4 control-label" for="textinput">Codigo:</label>  
                          <div class="col-md-4">
                          <input id="id" name="id" placeholder="ej: AM001" class="form-control input-md" type="text">
                          <span class="help-block"></span>  
                          </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                          <label class="col-md-4 control-label" for="textinput">Descripción:</label>  
                          <div class="col-md-4">
                          <input id="descripcion" name="descripcion" placeholder="escriba su descripción" class="form-control input-md" type="text">
                          <span class="help-block"></span>  
                          </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                          <label class="col-md-4 control-label" for="textinput">Fecha</label>  
                          <div class="col-md-4">
                          <input id="fechaIngreso" name="fechaIngreso" placeholder="dd/mm/yyyy" class="form-control input-md datepicker" type="text">
                          <span class="help-block"></span>  
                          </div>
                        </div>

                        <!-- Button -->
                        <div class="form-group">
                          <label class="col-md-4 control-label" for="singlebutton"></label>
                          <div class="col-md-4">
                            <button id="singlebutton" name="singlebutton" class="btn btn-primary">Guardar</button>
                          </div>
                        </div>

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