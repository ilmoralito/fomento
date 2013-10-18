<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="deduction/deductions">
	<r:require modules="bootstrap, deductions"/>
</head>
<body>
<g:link event="create" class="pull-right">Crear deduccion</g:link>
	<h4>Deducciones de ${partner}</h4>
	<g:if test="${partner?.deductions}">
		<table class="table table-hover">
			<thead>
				<tr>
					<th></th>
					<th></th>
					<th></th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${partner.deductions}" var="deduction">
					<tr>
						<td>${deduction.period}</td>
						<td>${deduction.totalBeforeDeduction}</td>
						<td>${deduction.totalAfterDeduction}</td>
						<td>${deduction.percentage * 100}%</td>
						<td>${deduction.reason}</td>
					</tr>
				</g:each>
			</tbody>
		</table>
	</g:if>
</body>
</html>