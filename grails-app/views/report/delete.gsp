<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<h4>Si eliminas este dividendo no podras deshacer los cambios! Deseas continuar?</h4>
	<g:form action="delete">
		<g:hiddenField name="period" value="${period}"/>
		<g:submitButton name="delete" value="Si" class="btn btn-primary"/>
		<g:link action="list" class="btn btn-default">No</g:link>
	</g:form>
</body>
</html>