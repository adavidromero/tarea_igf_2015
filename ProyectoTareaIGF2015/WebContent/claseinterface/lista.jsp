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
<title>Listado de Clase Interface</title>
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
                        <h1>Clase Interfaces</h1>
                        <div class="pull-right"><a href="<%=context_path %>/claseinterface/nuevo_editar_ver.jsp?operacion=crear">
                        <button class="btn btn-primary">Nuevo Registro</button></a></div>
                        <table class="table">
                        <thead>
                        	<tr>
                        	<th>Codigo</th>
                        	<th>Clase</th>
                        	<th>Interface</th>
                        	<th>Operaciones</th>
                        	</tr>
                        </thead>
                        <tbody>
                        <%
    					ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    					CtrlClaseInterface ctrlClaseInterface = (CtrlClaseInterface)ac.getBean("ctrlClaseInterface");
    					List claseinterfaces = ctrlClaseInterface.obtenerTodosClaseInterfaces();
    					System.out.println(claseinterfaces.isEmpty());
						int length=claseinterfaces.size();
    					for(int i=0;i<length;i++){
    						ClaseInterface claseInterface=(ClaseInterface) claseinterfaces.get(i);
    						String strClase="No definida";
    						if(claseInterface.getcClase()!=null){
    							strClase=claseInterface.getcClase().getdClase();
    						}

                        %>
                        	<tr>
                        	<td><%=claseInterface.getcClaseInterface() %></td>
                        	<td><%=claseInterface.getcClase().getdClase()%></td>
                        	<td><%=claseInterface.getcInterface().getdInterface()%></td>
                        	<td>
                        	<a href="<%=context_path %>/claseinterface/nuevo_editar_ver.jsp?operacion=ver&id=<%=claseInterface.getcClaseInterface() %>">
                        	<span class="glyphicon glyphicon-eye-open"></span></a>
                        	<a href="<%=context_path %>/claseinterface/nuevo_editar_ver.jsp?operacion=editar&id=<%=claseInterface.getcClaseInterface() %>">
                        	<span class="glyphicon glyphicon-pencil"></span></a>
                        	<a class="eliminar" data-codigo="<%=claseInterface.getcClaseInterface().toString() %>" 
                        		data-descripcion=<%=claseInterface.getcClaseInterface() %>
                        	href="#">
                        	<span class="glyphicon glyphicon-remove"></span></a>
                        	</td>
                        	</tr>
                        	<%
    					}
                        	%>

                        </tbody>
                        <tfoot>
                        </tfoot>
                        </table>
                        <div class="modal fade" id="modal-confirmar-eliminacion">
                          <div class="modal-dialog">
                            <div class="modal-content alert alert-danger">
                              <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">Confirmar Eliminación</h4>
                              </div>
                              <div class="modal-body">
                                <p>Confirmar Eliminación de Clase Interface
								<strong id="elim_descripcion"></strong>
								con el codigo:
                                <strong id="elim_codigo"></strong></p>
                              </div>
                              <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                                <a id="link_confirma_eliminacion" href="#"><button type="button" class="btn btn-primary">Confirmar</button></a>
                              </div>
                            </div><!-- /.modal-content -->
                          </div><!-- /.modal-dialog -->
                        </div><!-- /.modal -->

                        <script>
                        function readyFn(){
                        	$(".eliminar").click(function(e){
                        		e.preventDefault();
                        		var codigo = $(this).attr('data-codigo');
                        		var descripcion = $(this).attr('data-descripcion');
                        		$('#elim_codigo').text(codigo);
                        		$('#elim_descripcion').text(descripcion);
                        		$('#link_confirma_eliminacion').attr('href','operaciones.jsp?operacion=eliminar&id='+codigo);
                        		$('#modal-confirmar-eliminacion').modal('show');
                        	});
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