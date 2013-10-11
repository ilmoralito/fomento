<!doctype html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title><g:layoutTitle default="Socios"/></title>
	<r:layoutResources/>
</head>
<body>
	<g:render template="/layouts/main-navbar"/>

	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<g:layoutBody/>
				<g:if test="${flash.message}"><br><strong>${flash.message}</strong></g:if>
			</div>
			<div class="col-md-4">
				<h3>Total acumulado</h3>
				<p><h1>C$ ${total}</h1></p>
				<h3>Total despues de deduccion</h3>
				<p><h1>C$ 258</h1></p>
			</div>
		</div>
	</div>
	<r:layoutResources/>
</body>
</html>