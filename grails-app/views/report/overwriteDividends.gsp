<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<h1>Va a actualizar un dividendo existente!!!, ${period}</h1>
	<p>usted esta cerca de cambiar todos los valores de un dividendo ya existente, tenga en cuenta que esta cambio afectara algunas cosas, por lo tanto debe estar seguro antes de concretar este paso.</p>

	<g:form action="overwriteDividends">
		<g:render template="info"/>

		<button type="submit" class="btn btn-warning">Confirmar</button>
		<g:link action="dividends" class="btn btn-default">Cancelar</g:link>
	</g:form>
</body>
</html>
