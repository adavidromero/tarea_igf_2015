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
                        <div class="pull-right"><a href="<%=context_path %>/aplicativo/nuevo.jsp"><button class="btn btn-primary">Nuevo Registro</button></a></div>
                        <table class="table">
                        <thead>
                        	<tr>
                        	<th>Codigo</th>
                        	<th>Descripcion</th>
                        	<th>Fecha Ingreso</th>
                        	<th>Operaciones</th>
                        	</tr>
                        </thead>
                        <tbody>
                        	<tr>
                        	<td>AA001</td>
                        	<td>Descripción de la primera entrada</td>
                        	<td>12 octubre 2015</td>
                        	<td>
                        	<a href="#"><span class="glyphicon glyphicon-eye-open"></span></a>
                        	<a href="#"><span class="glyphicon glyphicon-pencil"></span></a>
                        	<a href="#"><span class="glyphicon glyphicon-remove"></span></a>
                        	</td>
                        	</tr>

                        	<tr>
                        	<td>AA002</td>
                        	<td>Descripción de la segunda entrada</td>
                        	<td>19 octubre 2015</td>
                        	<td>
                        	<a href="#"><span class="glyphicon glyphicon-eye-open"></span></a>
                        	<a href="#"><span class="glyphicon glyphicon-pencil"></span></a>
                        	<a href="#"><span class="glyphicon glyphicon-remove"></span></a>
                        	</td>
                        	</tr>

                        	<tr>
                        	<td>AA002</td>
                        	<td>Descripción de la tercera entrada</td>
                        	<td>15 octubre 2015</td>
                        	<td>
                        	<a href="#"><span class="glyphicon glyphicon-eye-open"></span></a>
                        	<a href="#"><span class="glyphicon glyphicon-pencil"></span></a>
                        	<a href="#"><span class="glyphicon glyphicon-remove"></span></a>
                        	</td>

                        	</tr>
                        </tbody>
                        <tfoot>
                        </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->
</div>
</body>
</html>