<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="deduction/deductions">
	<r:require modules="bootstrap, deductions"/>
</head>
<body>
	<g:render template="/layouts/deduction/toolbar"/>
	<g:if test="${partner?.deductions}">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Periodo</th>
					<th>Saldo a corte | Porcentaje deducido</span></th>
					<th>Saldo despues de corte</span></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${partner.deductions}" var="deduction">
					<tr>
						<td>
							<g:if test="${deduction.reason}">
								<g:link event="show" params="[id:deduction.id]">
									${deduction.period}-<small>${deduction.dateCreated.format("MM-dd")}</small>
								</g:link>
							</g:if>
							<g:else>
								${deduction.period}-<small>${deduction.dateCreated.format("MM-dd")}</small>
							</g:else>
						</td>
						<td>${deduction.totalBeforeDeduction} <small><strong>${Math.round(deduction.percentage * 100)}%</strong></small></td>
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