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
                        <div class="pull-right"><a href="<%=context_path %>/aplicativo/nuevo_editar_ver.jsp">
                        <button class="btn btn-primary">Nuevo Registro</button></a></div>
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
                        <%
    					ApplicationContext ac = WebApplicationContextUtils.
    						getRequiredWebApplicationContext(getServletContext());
    					CtrlAplicativo ctrlAplicativo = (CtrlAplicativo)ac.getBean("ctrlAplicativo");

    					List aplicativos = ctrlAplicativo.obtenerTodosAplicativos();
						SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
						String strFechaIngreso="";
						int length=aplicativos.size();
    					for(int i=0;i<length;i++){
    						Aplicativo aplicativo=(Aplicativo)aplicativos.get(i);
    						try{
    							strFechaIngreso=formatter.format(aplicativo.getFechaIngreso());
    						}catch(Exception e){
    							System.out.println(e.getStackTrace());
    						}
                        %>
                        	<tr>
                        	<td><%=aplicativo.getId() %></td>
                        	<td><%=aplicativo.getDescripcion() %></td>
                        	<td><%=strFechaIngreso %></td>
                        	<td>
                        	<a href="<%=context_path %>/aplicativo/nuevo_editar_ver.jsp?operacion=ver&id=<%=aplicativo.getId()%>"><span class="glyphicon glyphicon-eye-open"></span></a>
                        	<a href="<%=context_path %>/aplicativo/nuevo_editar_ver.jsp?operacion=editar&id=<%=aplicativo.getId()%>">
                        	<span class="glyphicon glyphicon-pencil"></span></a>
                        	<a href="#"><span class="glyphicon glyphicon-remove"></span></a>
                        	</td>
                        	</tr>
                        	<%
    					}
                        	%>

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