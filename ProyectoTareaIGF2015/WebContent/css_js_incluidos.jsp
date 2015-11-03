    <!-- Este archivo es parte de la pantilla principal 
    En este archivo incluir las librerias externas de la plantilla 
    Operaciones como codigo javascript o css especifico que utilicen
    puede ir en el archivo especifico que estén trabajando -->

<%
	String context_path=request.getContextPath().toString();
%>
    <link rel="stylesheet" href="<%=context_path %>/css/bootstrap.css">
    <link rel="stylesheet" href="<%=context_path %>/css/bootstrap-datepicker3.css">
    <link rel="stylesheet" href="<%=context_path %>/css/simple-sidebar.css">
    <script src="<%=context_path %>/js/jquery-2.1.4.js"></script>
    <script src="<%=context_path %>/js/bootstrap.js"></script>
    <script src="<%=context_path %>/js/bootstrap-datepicker.js"></script>
    <script src="<%=context_path %>/js/locales/bootstrap-datepicker.es.min.js"></script>