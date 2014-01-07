<!doctype html>
<html lang="en">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<g:render template="tool-bar"/>
	<h4>Crear Usuario</h4>
	<div class="col-md-6">
		<g:form action="save">
			<g:render template="form"/>
			<g:submitButton name="btnregistration" value="${message(code:'org.fomento.btnsave')}"class="btn btn-default pull-right"/>
		</g:form>
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