<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="deduction/deductions">
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<g:hasErrors bean="${deduction}">
		<g:renderErrors bean="${deduction}"/>
	</g:hasErrors>

	<h2>Aplicar deduccion a ${partner}</h2>

	<g:form>
		<div class="form-group">
			<label for="deduction">Deduccion</label>
			<br>
			<input type="range" name="range" id="range" min="0" max="100" step="10" value="0">
		</div>
		<div class="form-group">
			<label for="reason">Razon de deduccion</label>
			<g:textArea name="reason" value="" class="form-control"/>
		</div>
		<g:submitButton name="confirm" value="Confirmar deduccion" class="btn btn-default"/>
		<g:link action="deductions" event="cancel" class="btn btn-default">Regresar a cuotas de ${partner}</g:link>
	</g:form>
</body>
</html>
