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
<title>Listado de Clase</title>
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
                        <h1>Clases </h1>
                        <div class="pull-right"><a href="<%=context_path %>/clase/nuevo_editar_ver.jsp?operacion=crear">
                        <button class="btn btn-primary">Nuevo Registro</button></a></div>
                        <table class="table">
                        <thead>
                        	<tr>
                        	<th>Codigo</th>
                        	<th>Descripcion</th>
                        	<th>Tipo Clase</th>
                        	<th>Usuario</th>
                        	<th>Fecha Ingreso</th>
                        	<th>Aplicativo</th>
                        	<th>Clase Padre</th>
                        	<th>Operaciones</th>
                        	</tr>
                        </thead>
                        <tbody>
                        <%
    					ApplicationContext ac = WebApplicationContextUtils.
    						getRequiredWebApplicationContext(getServletContext());
    					CtrlClase ctrlClase = (CtrlClase)ac.getBean("ctrlClase");

    					List clases = ctrlClase.obtenerTodosClases();
						SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
						String strFechaIngreso="";
						int length=clases.size();
    					for(int i=0;i<length;i++){
    						Clase clase=(Clase)clases.get(i);
    						try{
    							strFechaIngreso=formatter.format(clase.getfIngreso());
    						}catch(Exception e){
    							System.out.println(e.getStackTrace());
    						}
    						String strClasePadre="No definida";
    						if(clase.getClasePadre()!=null){
    							strClasePadre=clase.getClasePadre().getdClase();
    						}
    						 

                        %>
                        	<tr>
                        	<td><%=clase.getcClase() %></td>
                        	<td><%=clase.getdClase()%></td>
                        	<td><%=clase.getcTipoClase().getdTipoClase() %></td>
                        	<td><%=clase.getcUsuario() %></td>
                        	<td><%=strFechaIngreso %></td>
                        	<td><%=clase.getcAplicativo().getdAplicativo() %></td>
                        	<td><%=strClasePadre %></td>
                        	<td>
                        	<a href="<%=context_path %>/clase/nuevo_editar_ver.jsp?operacion=ver&id=<%=clase.getcClase() %>">
                        	<span class="glyphicon glyphicon-eye-open"></span></a>
                        	<a href="<%=context_path %>/clase/nuevo_editar_ver.jsp?operacion=editar&id=<%=clase.getcClase() %>">
                        	<span class="glyphicon glyphicon-pencil"></span></a>
                        	<a class="eliminar" data-codigo="<%=clase.getcClase().toString() %>" 
                        		data-descripcion=<%=clase.getdClase() %>
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
                                <p>Confirmar Eliminación de Aplicativo 
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