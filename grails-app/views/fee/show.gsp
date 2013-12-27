<!doctype html>
<html lang="en">
<head>
	<meta name="layout" content="main">
	<meta charset="UTF-8">
	<title>Editar cuotas</title>
	<r:require modules="bootstrap, app, jquery-ui, fees"/>
</head>
<body>
<g:set var="partner" value="${params?.partner}"/>
	<g:set var="period" value="${params?.period}"/>

	<h4>Actualizar cuotas de ${org.fomento.Partner.findById(params.int("partner"))} del periodo ${period}</h4>

	<g:hasErrors bean="${fee}">
		<g:renderErrors bean="${fee}"/>
	</g:hasErrors>

	<g:form action="update" style="width:50%;">
		<g:hiddenField name="id" value="${params?.id}"/>
		<g:hiddenField name="partner" value="${partner}"/>
		<g:hiddenField name="period" value="${period}"/>
		<g:hiddenField name="dateCre" value="${fee?.dateCreated}"/>

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
			<g:textField name="dateCreated" class="form-control" value="${fee?.dateCreated}"/>
		</div>
		<g:submitButton name="send" value="Confirmar" class="btn btn-default"/>
		<g:link controller="partner" action="report" params="[id:partner, period:period]" class="btn btn-default">
			Regresar
		</g:link>
	</g:form>
</body>
</html>