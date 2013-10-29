<!doctype html>
<html lang="es">
<head>
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<g:if test="${fees}">
		<br>
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
	<g:else>
		<p><br>Nada que mostrar</p>
	</g:else>
</body>
</html>
