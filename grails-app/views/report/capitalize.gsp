<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Capitalizar</title>
	<r:require modules="bootstrap-css, bootstrap-dropdown, capitalize"/>
</head>
<body>
	<g:render template="toolbar"/>
	<div class="row">
		<div class="col-md-6">
			<h2>${dividend.partner}, ${dividend.period}</h2>
			<g:form action="capitalize">
				<g:hiddenField name="id" value="${params?.id}"/>
				<g:hiddenField name="capitalizationId" value="${dividend?.capitalization?.id}"/>
				<p>Seleccione el porcentaje a capitalizar</p>
				<input type="range" name="percentage" id="percentage" min="0" max="100" value="${(dividend?.capitalization?.percentage) ? 100 - dividend?.capitalization?.percentage : 0}">
				<output for="percentage" id="range">100</output><span>%</span>

				<br><br>
				<div class="row">
					<div class="col-md-4">
						<p>Dividendo del periodo</p>
						<h4 id="capital">${dividend.partnerDividend - (dividend.partnerDividend * 0.1)}</h4>
					</div>
					<div class="col-md-4">
						<p>Total a capitalizar</p>
						<h4 id="capitalized"></h4>
					</div>
					<div class="col-md-4">
						<p>Total a no capitalizar</p>
						<h4 id="nocapitalized"></h4>
					</div>
				</div>

				<br>
				<g:submitButton name="send" value="Capitalizar" class="btn btn-default"/>
				<g:link action="show" params="[period:dividend.period]" class="btn btn-default">Regresar</g:link>
			</g:form>
		</div>
		<div class="col-md-6">
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
