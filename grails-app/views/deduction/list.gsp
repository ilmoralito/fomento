<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, deductions"/>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h3>Datos de ${partner}, periodo ${period}</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-md-9">
				Lorem ipsum dolor sit amet, consectetur adipisicing elit. Adipisci, maxime, iusto corrupti nisi tempore molestias. Non, ad, doloribus ducimus quibusdam earum fugit repellendus dolores eos esse quae quas magnam nisi!
			</div>
			<div class="col-md-3">
				<g:form action="list">
					<div class="form-group">
						<label for="percentage">Porcentaje a no capitalizar</label>
						<input type="range" name="percentage" id="percentage" class="form-control" min="1" max="100" step="1" value="${(params?.percentage) ?: 1}">
						<h1><span id="value"></span>%</h1>
					</div>
					<div class="form-group">
						<label for="totalBeforeDeduction">Dividendos del periodo</label>
						<g:textField name="totalBeforeDeduction" value="235" class="form-control input-lg"/>
					</div>
					<div class="form-group">
						<label for="totalAfterDeduction">Dividendos despues de deduccion</label>
						<g:textField name="totalAfterDeduction" value="" class="form-control input-lg"/>
					</div>
					<button type="submit" name="save" class="btn btn-default">Guardar</button>
				</g:form>
			</div>
		</div>
	</div>
</body>
</html>