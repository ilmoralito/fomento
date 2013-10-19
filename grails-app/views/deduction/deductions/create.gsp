<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="deduction/deductions">
	<r:require modules="bootstrap, deductions"/>
</head>
<body>
	<g:render template="/layouts/deduction/toolbar"/>
	<g:hasErrors bean="${deduction}">
		<g:renderErrors bean="${deduction}"/>
	</g:hasErrors>

	<g:form>
		<div class="form-group">
			<g:hiddenField name="totalBeforeDeduction" value="${(deduction?.totalBeforeDeduction) ?: total}"/>
			<g:hiddenField name="totalAfterDeduction" value="${deduction?.totalAfterDeduction}"/>

			<label for="deduction">Deduccion</label>
			<div>
				<input type="range" name="percentage" id="percentage" min="0" max="100" step="10" value="${(deduction?.percentage) ?: 0}">
				<br>
				<span name="amount" id="amount">${(deduction?.percentage) ?: 0}</span>%
			</div>
		</div>
		<div class="form-group">
			<label for="reason">Razon de deduccion</label>
			<g:textArea name="reason" value="${deduction?.reason}" class="form-control"/>
		</div>
		<g:submitButton name="confirm" value="Confirmar deduccion" class="btn btn-default"/>
	</g:form>
</body>
</html>
