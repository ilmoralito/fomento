<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<g:render template="toolbar"/>
	<g:if test="${dividends}">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Periodo</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${dividends}" var="dividend">
					<tr>
						<td><g:link action="show" params="[period:dividend.period]">${dividend.period}</g:link></td>
						<td style="width:1px;"><g:link action="delete" params="[period:dividend.period]"><span class="glyphicon glyphicon-trash"></span></g:link></td>
					</tr>
				</g:each>
			</tbody>
		</table>
	</g:if>
	<g:else>
		<h3>Nada que mostrar</h3>
	</g:else>
</body>
</html>
