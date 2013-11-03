<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<g:if test="${dividends}">
		<h3>Dividendos</h3>
		<table class="table table-hover">
			<thead>
				<tr>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${dividends}" var="dividend">
				<tr>
					<td><g:link action="show" params="[period:dividend.period]">${dividend.period}</g:link></td>
					<td style="width:1px;">
						<g:link action="delete" params="[period:dividend.period]">
							<span class="glyphicon glyphicon-trash"></span>
						</g:link>
					</td>
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
