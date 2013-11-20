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
	<h2>${dividend.partner}, ${dividend.period}</h2>
	<g:form action="capitalize" style="width:40%;">
		<g:hiddenField name="id" value="${params?.id}"/>
		<g:hiddenField name="capitalizationId" value="${dividend?.capitalization?.id}"/>
		<h4>Seleccione el porcentaje a capitalizar</h4>
		<input type="range" name="percentage" id="percentage" class="form-control" min="0" max="100" value="${(dividend?.capitalization?.percentage) ? 100 - dividend?.capitalization?.percentage : 0}">
		<h1 id="range">100</h1>

		<h4>Dividendo del periodo</h4>
		<h1 id="capital">${dividend.partnerDividend}</h1>

		<h4>Total a capitalizar</h4>
		<h1 id="capitalized"></h1>

		<h4>Total a retirar por el socio</h4>
		<h1 id="nocapitalized"></h1>

		<g:submitButton name="send" value="Capitalizar" class="btn btn-default"/>
	</g:form>
</body>
</html>

