<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<g:render template="toolbar"/>
	<g:if test="${dividends}">
		<h3>Periodo ${params?.period}</h3>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Nombre del socio</th>
					<th>Dividendo del periodo</th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${dividends}" var="dividend">
				<tr>
					<td>${dividend.partner}</td>
					<td>${dividend.dividend}</td>
				</tr>
				</g:each>
			</tbody>
		</table>
	</g:if>
	<g:else>
		<h1>Nada que mostrar</h1>
	</g:else>
</body>
</html>
