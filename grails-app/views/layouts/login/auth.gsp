<!doctype html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title><g:layoutTitle default="Iniciar sesion"/></title>
	<r:layoutResources/>
</head>
<body>
	<div class="container main">
		<div class="row">
			<div class="col-md-6">
				<h3>JUFOLI</h3>
				<p>Junta de fomento Mina el Limon</p>
			</div>
			<div class="col-md-6">
				<g:layoutBody/>
				<g:if test="${flash.message}">
					<p><br><strong>${flash.message}</strong></p>
				</g:if>
			</div>
		</div>
	</div>
	<r:layoutResources/>
</body>
</html>