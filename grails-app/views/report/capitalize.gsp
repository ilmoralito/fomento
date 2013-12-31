<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Capitalizar</title>
	<r:require modules="bootstrap, capitalize"/>
</head>
<body>
	<g:render template="toolbar"/>
	<div class="row">
		<div class="col-md-5">
			<h2>${dividend.partner}, ${dividend.period}</h2>
			<g:form action="capitalize">
				<g:hiddenField name="id" value="${params?.id}"/>
				<g:hiddenField name="capitalizationId" value="${dividend?.capitalization?.id}"/>
				<h4>Seleccione el porcentaje a capitalizar</h4>
				<input type="range" name="percentage" id="percentage" class="form-control" min="0" max="100" value="${(dividend?.capitalization?.percentage) ? 100 - dividend?.capitalization?.percentage : 0}">
				<h1 id="range">100</h1>

				<h4>Dividendo del periodo</h4>
				<h1 id="capital">${dividend.partnerDividend - (dividend.partnerDividend * 0.1)}</h1>

				<h4>Total a capitalizar</h4>
				<h1 id="capitalized"></h1>

				<h4>Total a retirar por el socio</h4>
				<h1 id="nocapitalized"></h1>

				<g:submitButton name="send" value="Capitalizar" class="btn btn-default"/>
				<g:link action="show" params="[period:dividend.period]" class="btn btn-default">Regresar</g:link>
			</g:form>
		</div>
		<div class="col-md-5">
			<g:form action="printReport" class="">
				<g:hiddenField name="id" value="${dividend.partner.id}"/>
				<g:hiddenField name="period" value="${dividend.period}"/>
				<g:hiddenField name="iddiv" value="${dividend.id}"/>
				<div class="form-group">
					<h4>Posicion a Imprimir el la tarjeta del socio:</h4> 
					<g:select name="position" from="${['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22']}" valueMessagePrefix="" class="form-control combo"/>
				</div>
				<g:submitButton name="send" value="Reporte" class="btn btn-primary"/>
			</g:form>
		</div>
	</div>
	
</body>
</html>

