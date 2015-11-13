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
<title>Listado de Métodos</title>
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
                        <h1>Métodos </h1>
                        <div class="pull-right"><a href="<%=context_path %>/metodo/nuevo_editar_ver.jsp?operacion=crear">
                        <button class="btn btn-primary">Nuevo Registro</button></a></div>
                        <table class="table">
                        <thead>
                        	<tr>
                        	<th>Código de clase</th>
                        	<th>Código del metodo</th>
                        	<th>Código del tipo de metodo</th>
                        	<th>Metodo</th>
                        	<th>Tipo de retorno</th>
                        	<th>Usuario</th>
                        	<th>Fecha Ingreso</th>
                        	<th>Activo</th>
                        	<th>Parametros</th>
                        	<th>Operaciones</th>
                        	</tr>
                        </thead>
                        <tbody>
                        <%
    					ApplicationContext ac = WebApplicationContextUtils.
    						getRequiredWebApplicationContext(getServletContext());
    					CtrlMetodo ctrlMetodo = (CtrlMetodo)ac.getBean("ctrlMetodo");

    					List metodos = ctrlMetodo.obtenerTodosMetodos();
						SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
						String strFechaIngreso="";
						int length=metodos.size();
    					for(int i=0;i<length;i++){
    						Metodo metodo=(Metodo)metodos.get(i);
    						try{
    							strFechaIngreso=formatter.format(metodo.getfIngreso());
    						}catch(Exception e){
    							System.out.println(e.getStackTrace());
    						}
                        %>
                        	<tr>
                        	<td><%=metodo.getcClase().getdClase()%></td>
                        	<td><%=metodo.getcMetodo() %></td>
                        	<td><%=metodo.getcTipoMetodo().getcTipoMetodo() %></td>
                        	<td><%=metodo.getdMetodo() %></td>
                        	<td><%=metodo.getdTipoRetorno() %></td>
                        	<td><%=metodo.getcUsuario() %></td>
                        	<td><%=strFechaIngreso %></td>
                        	<td><%=metodo.getbActivo() %></td>
                        	<td><%=metodo.getnParametros() %></td>
                            
                        	<td>
                        	<a href="<%=context_path %>/metodo/nuevo_editar_ver.jsp?operacion=ver&idMetodo=<%=metodo.getcMetodo() %>&idClase=<%= metodo.getcClase().getcClase() %>">
                        	<span class="glyphicon glyphicon-eye-open"></span></a>
                        	<a href="<%=context_path %>/metodo/nuevo_editar_ver.jsp?operacion=editar&idMetodo=<%=metodo.getcMetodo() %>&idClase=<%= metodo.getcClase().getcClase() %>">
                        	<span class="glyphicon glyphicon-pencil"></span></a>
                        	<a class="eliminar" 
                        	data-cmetodo="<%=metodo.getcMetodo() %>" 
                        	data-cclase="<%=metodo.getcClase().getcClase() %>" 
                        	data-dmetodo="<%=metodo.getdMetodo() %>" 
                        	href="#"><span class="glyphicon glyphicon-remove"></span></a>
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
                                <p>Confirmar Eliminación de Método <strong id="elim_caplicacion"></strong></p>
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
                        		var cClase = $(this).attr('data-cclase');
                        		var cMetodo = $(this).attr('data-cmetodo');
                        		var dMetodo = $(this).attr('data-dmetodo');
                        		$('#elim_caplicacion').text(dMetodo);
                        		$('#link_confirma_eliminacion').attr('href',
                        				'operaciones.jsp?operacion=eliminar&cClase='+cClase
                        				+'&cMetodo='+cMetodo);
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