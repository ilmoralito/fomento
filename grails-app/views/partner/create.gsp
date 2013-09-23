<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app, datepicker"/>
</head>
<body>
	<g:render template="toolbar"/>

	<g:hasErrors bean="${partner}">
		<g:renderErrors bean="${partner}"/>
	</g:hasErrors>

	<g:form action="create">
		<g:render template="form"/>
		<g:submitButton name="save" value="Guardar" class="btn btn-default"/>
	</g:form>
</body>
</html>
