<!doctype html>
<html lang="en">
<head>
	<meta name="layout" content="main">
	<meta charset="UTF-8">
	<title>Editar cuotas</title>
	<r:require modules="bootstrap-css, bootstrap-dropdown, app, jquery-ui, fees"/>
</head>
<body>
	<h4>Actualizar cuota de <strong>${fee.partner}</strong> en periodo ${fee.period}</h4>

	<g:hasErrors bean="${fee}">
		<g:renderErrors bean="${fee}"/>
	</g:hasErrors>

	<div class="row">
		<div class="col-md-4">
			<g:form action="update">
				<g:hiddenField name="id" value="${params?.id}"/>

				<div class="form-group">
					<label for="fee">Cuota de socio</label>
					<g:textField name="fee" value="${fee?.fee}" class="form-control" autofocus="true"/>
				</div>
				<div class="form-group">
					<label for="factoryFee">Cuota de empresa</label>
					<g:textField name="factoryFee" value="${fee?.factoryFee}" class="form-control"/>
				</div>
				<div class="form-group">
					<label for="dateCreated">Fecha de cuota</label>
					<g:textField name="dateCreated" class="form-control" value="${fee?.dateCreated?.format('yyyy-MM-dd')}"/>
				</div>
				<g:submitButton name="send" value="Confirmar" class="btn btn-default"/>
				<g:link controller="partner" action="report" params="[id:fee.partner.id, period:fee.period]" class="btn btn-default">Regresar</g:link>
			</g:form>
		</div>
	</div>
</body>
</html>