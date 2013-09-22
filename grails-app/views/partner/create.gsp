<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<div class="pull-right">
				<div class="btn-group">
					<g:link action="create" class="btn btn-default">Crear socio</g:link>
				</div>
			</div>
		</div>
	</div>

	<g:hasErrors bean="${partner}">
		<g:renderErrors bean="${partner}"/>
	</g:hasErrors>

	<g:form action="create">
		<g:render template="form"/>
		<g:submitButton name="save" value="Guardar" class="btn btn-default"/>
	</g:form>
</body>
</html>
