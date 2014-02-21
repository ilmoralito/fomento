<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<r:require modules="bootstrap-css, bootstrap-dropdown, jquery-ui, fees"/>
	<title>Eliminar cuotas por lote</title>
</head>
<body>
	<div class="row">
		<div class="col-md-9">
			<h4>Eliminar cuotas en lote</h4>
			...
		</div>
		<div class="col-md-3">
			<h4>Filtros</h4>
			<g:form action="deleteFees">
				<div class="form-group">
					<label>Fecha de cuota</label>
					<g:select from="${dates}" name="dateCreated" class="form-control"/>
				</div>

				<div class="form-group">
					<label>Tipo de pago</label>
					<br>
					<g:checkBox name="typeOfPayment" value="Catorcena" checked="false"/> Catorcena
					<br>
					<g:checkBox name="typeOfPayment" value="Fin de mes" checked="false"/> Fin de mes
					<br>
					<g:checkBox name="typeOfPayment" value="Bono" checked="false"/> Bono
				</div>

				<g:submitButton name="confirm" value="Listar cuotas afectadas" class="btn btn-default btn-block"/>
			</g:form>
		</div>
	</div>
</body>
</html>
