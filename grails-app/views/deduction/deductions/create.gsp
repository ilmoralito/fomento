<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<g:link controller="fee" action="list" params="[id:partner.id]">Regresar</g:link>

	<g:hasErrors bean="${deduction}">
		<g:renderErrors bean="${deduction}"/>
	</g:hasErrors>

	<g:form>
		<div class="form-group">
			<label for="deduction">Deduccion</label>
			<g:textField name="deduction" value="${total}" class="form-control"/>
		</div>
		<div class="form-group">
			<label for="reason">Razon de deduccion</label>
			<g:textArea name="reason" value="" class="form-control"/>
		</div>
		<g:submitButton name="confirm" value="Confirmar" class="btn btn-default"/>
		<g:link action="deductions" event="cancel" class="btn btn-default">Salir</g:link>
	</g:form>
</body>
</html>
