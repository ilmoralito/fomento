<!doctype html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title><g:layoutTitle default="Socios"/></title>
	<r:layoutResources/>
</head>
<body>
	<div class="container main">
		<div class="row">
			<div class="col-md-12">
				<g:layoutBody/>
				<g:if test="${flash.message}"><strong>${flash.message}</strong></g:if>
			</div>
		</div>
	</div>
	<r:layoutResources/>
</body>
</html>