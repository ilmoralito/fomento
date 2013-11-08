<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, deductions"/>
</head>
<body>
	<h3>Datos de ${dividend.partner}, periodo ${dividend.period}</h3>
	<div class="row">
		<div class="col-md-6">
			<g:form action="list">
				<div class="form-group">
					<label for="percentage">Porcentaje a no capitalizar</label>
					<input type="range" name="percentage" id="percentage" class="form-control" min="1" max="100" step="1" value="${(params?.percentage) ?: 1}">
					<h1><span id="value"></span>%</h1>
				</div>
				<div class="form-group">
					<label for="totalBeforeDeduction">Dividendos del periodo</label>
					<g:textField name="totalBeforeDeduction" value="${dividend.partnerDividend}" class="form-control input-lg"/>
				</div>
				<div class="form-group">
					<label for="noCapitalization">Dividendos despues de deduccion</label>
					<g:textField name="noCapitalization" value="" class="form-control input-lg"/>
				</div>
				<button type="submit" name="save" class="btn btn-default">Guardar</button>
				<g:link class="btn btn-default">Borrar</g:link>
			</g:form>
		</div>
		<div class="col-md-6">
		</div>
	</div>
</body>
</html>