<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, deductions"/>
</head>
<body>
	<g:render template="/partner/toolbar"/>
	<g:render template="/layouts/navbar"/>

	<g:render template="/deduction/deductions/toolbar"/>

	<table class="table">
		<tbody>
			<tr>
				<td><strong>Periodo</strong></td>
				<td>${ded.period}</td>
			</tr>
			<tr>
				<td><strong>Fecha de deduccion</strong></td>
				<td>${ded.dateCreated.format("yyyy-MM-dd")}</td>
			</tr>
			<tr>
				<td><strong>Saldo antes de deduccion</strong></td>
				<td>${ded.totalBeforeDeduction}</td>
			</tr>
			<tr>
				<td><strong>Porcentage deducido</strong></td>
				<td>${Math.round(ded.percentage * 100)}%</td>
			</tr>
			<tr>
				<td><strong>Saldo despues de deduccion</strong></td>
				<td>${ded.totalAfterDeduction}</td>
			</tr>
			<tr>
				<td><strong>Razon</strong></td>
				<td>${ded.reason}</td>
			</tr>
		</tbody>
	</table>
</body>
</html>
