<!doctype html>
<html lang="es">
<head>
	<r:require modules="bootstrap, app, datepicker"/>
</head>
<body>
	<!--FEES-->
	<g:if test="${fees}">
		<h4>Cuotas de periodo</h4>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Cuota de socio</th>
					<th>Cuota de empresa</th>
					<th>Fecha de cuota</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${fees}" var="fee">
					<tr>
						<td>${fee}</td>
						<td>${fee.factoryFee}</td>
						<td>${fee.paymentDate.format("yyyy-MM-dd")}</td>
						<td style="width:1px;"></td>
					</tr>
				</g:each>
			</tbody>
		</table>
	</g:if>

	<!--DIVIDENDOS-->
	
</body>
</html>