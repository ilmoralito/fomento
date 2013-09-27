<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app, datepicker"/>
</head>
<body>
	<g:render template="toolbar"/>

	<ul class="nav nav-tabs">
	  	<li class="${(actionName == 'show') ? 'active' : ''}"><g:link action="show" id="${params?.id}">Socio</g:link></li>
	  	<li class="${(actionName == 'fees') ? 'active' : ''}"><g:link action="">Contribuciones</g:link></li>
	</ul>

	<g:hasErrors bean="${partner}">
		<g:renderErrors bean="${partner}"/>
	</g:hasErrors>

	<g:form action="update">
		<br>
		<g:hiddenField name="id" value="${params.id}"/>
		<g:render template="form"/>
		<g:submitButton name="update" value="Actualizar" class="btn btn-default"/>
	</g:form>
</body>
</html>