<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<h1>Va a actualizar un dividendo existente</h1>
	<p>No se podra deshacer los cambios, desea continuar?</p>

	<g:form action="overwriteDividends">
		<g:render template="info"/>

		<button type="submit" class="btn btn-warning">Confirmar</button>
		<g:link action="dividends" class="btn btn-default">Cancelar</g:link>
	</g:form>
</body>
</html>
