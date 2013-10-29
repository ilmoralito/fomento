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

	<g:if test="${partner?.deductions}">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Fecha de deduccion</th>
					<th>Porcentaje deducido</th>
					<th>Saldo a deduccion</th>
					<th>Saldo despues de deduccion</span></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${partner.deductions}" var="deduction">
					<tr>
						<td>
							<g:if test="${deduction.reason}">
								<g:link event="show" params="[id:deduction.id]">
									${deduction.dateCreated.format("yyyy-MM-dd")}
								</g:link>
							</g:if>
							<g:else>
								${deduction.dateCreated.format("yyyy-MM-dd")}
							</g:else>
						</td>
						<td>${Math.round(deduction.percentage * 100)}%</td>
						<td>${deduction.totalBeforeDeduction}</td>
						<td>${deduction.totalAfterDeduction}</td>
						<td style="width:1px;">
							<g:link event="delete" params="[id:deduction.id]">
								<span class="glyphicon glyphicon-trash">
							</g:link>
						</td>
					</tr>
				</g:each>
			</tbody>
		</table>
	</g:if>
</body>
</html>