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
<title>Listado de Parametros</title>
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
                        <h1>Parametros </h1>
                        <div class="pull-right"><a href="<%=context_path %>/parametro/nuevo_editar_ver.jsp?operacion=crear">
                        <button class="btn btn-primary">Nuevo Registro</button></a></div>
                        <table class="table">
                        <thead>
                        	<tr>
                        	<th>Codigo</th>
                        	<th>Metodo</th>
                        	<th>Descripción</th>
                        	<th>Descripción Tipo Parametro</th>
                        	<th>Usuario</th>
                        	<th>Fecha Ingreso</th>
                        	<th>Operaciones</th>
                        	</tr>
                        </thead>
                        <tbody>
                        <%
    					ApplicationContext ac = WebApplicationContextUtils.
    						getRequiredWebApplicationContext(getServletContext());
    					CtrlParametro ctrlParametro = (CtrlParametro)ac.getBean("ctrlParametro");

    					List parametros = ctrlParametro.obtenerTodosParametros();
						SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
						String strFechaIngreso="";
    					String cParametro="";
    					String cClase="";
    					String cMetodo="";
						int length=parametros.size();
    					for(int i=0;i<length;i++){
    						Parametro parametro=(Parametro)parametros.get(i);
    						cParametro=parametro.getcParametro().toString();
    						cClase=parametro.getcMetodo().getcClase().getcClase().toString();
    						cMetodo=parametro.getcMetodo().getcMetodo().toString();
    						try{
    							strFechaIngreso=formatter.format(parametro.getfIngreso());
    						}catch(Exception e){
    							System.out.println(e.getStackTrace());
    						}
                        %>
                        	<tr>
                        	<td><%=parametro.getcParametro() %></td>
                        	<td><%=parametro.getcMetodo().getdMetodo() %></td>
                        	<td><%=parametro.getdParametro() %></td>
                        	<td><%=parametro.getdTipoParametro() %></td>
                        	<td><%=parametro.getcUsuario() %></td>
                        	<td><%=strFechaIngreso %></td>
                        	<td>
                        	<a href="<%=context_path %>/parametro/nuevo_editar_ver.jsp?operacion=ver&cParametro=<%=cParametro %>&cMetodo=<%=cMetodo %>&cClase=<%=cClase %>">
                        	<span class="glyphicon glyphicon-eye-open"></span></a>
                        	<a href="<%=context_path %>/parametro/nuevo_editar_ver.jsp?operacion=editar&cParametro=<%=cParametro %>&cMetodo=<%=cMetodo %>&cClase=<%=cClase %>">
                        	<span class="glyphicon glyphicon-pencil"></span></a>
							<a class="eliminar" 
								data-cParametro="<%=cParametro %>" 
								data-cMetodo="<%=cMetodo %>" 
								data-cClase="<%=cClase %>" 
                        		data-descripcion=<%=parametro.getdParametro() %>
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
                                <p>Confirmar Eliminación de TipoAtributo 
								<strong id="elim_descripcion"></strong>
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
                        		var cParametro = $(this).attr('data-cParametro');
                        		var cClase = $(this).attr('data-cClase');
                        		var cMetodo = $(this).attr('data-cMetodo');
                        		var descripcion = $(this).attr('data-descripcion');
                        		$('#elim_descripcion').text(descripcion);
                        		$('#link_confirma_eliminacion').attr('href','operaciones.jsp?operacion=eliminar'+
                        				'&cParametro='+cParametro+
                        				'&cClase='+cClase+
                        				'&cMetodo='+cMetodo
                        				);
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