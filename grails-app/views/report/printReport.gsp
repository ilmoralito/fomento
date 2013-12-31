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

	<h1>Reporte del Periodo, ${period}</h1>
	<h2>${partner.fullName}</h2>
	<h4>Saldo del Socio: C$ <g:formatNumber number="${socio}" type="number" maxFractionDigits="2" /></h4>
	<h4>Saldo Empresa: C$ <g:formatNumber number="${empresa}" type="number" maxFractionDigits="2" /></h4>
	<h4>Capitalizacion del Periodo: C$ <g:formatNumber number="${capitalizacion}" type="number" maxFractionDigits="2" /></h4>
	<h4>Retiro del Periodo: C$ <g:formatNumber number="${retiro}" type="number" maxFractionDigits="2" /></h4>

	<table class="table table-hover">
		<thead>
			<tr>
				<th>Fecha</th>
				<th>Saldo</th>
				<th>Empresa</th>
				<th>Capitalizacion</th>
				<th>retiro</th>
				<th>Firma</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>Fecha</td>
				<td>C$ <g:formatNumber number="${socio}" type="number" maxFractionDigits="2" /></td>
				<td>C$ <g:formatNumber number="${empresa}" type="number" maxFractionDigits="2" /></td>
				<td>
					C$ <g:formatNumber number="${capitalizacion}" type="number" maxFractionDigits="2" />
				</td>
				<td>C$ <g:formatNumber number="${retiro}" type="number" maxFractionDigits="2" /></h4></td>
				<td>Firma</td>
			</tr>
		</tbody>
	</table>
</body>
</html>