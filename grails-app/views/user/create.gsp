<!doctype html>
<html lang="en">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<g:render template="tool-bar"/>
	<h4>Crear Usuario</h4>

	<div class="col-md-5">
		<g:render template="frcreate"/>
	</div>
	<br>
	<div class="col-md-6">
		<g:hasErrors bean="${userInstance}">
			<div class="alert alert-warning">
				<g:renderErrors bean="${userInstance}"/>
			</div>
		</g:hasErrors>
	</div>
</body>
</html>