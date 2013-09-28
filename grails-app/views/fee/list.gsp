<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<g:render template="/partner/toolbar"/>
	<g:render template="/layouts/navbar"/>

	<g:if test="${fees}">
		<table class="table">
			<thead>
				<tr>
					<th>Cuota</th>
					<th>Cuota de empresa</th>
					<th>Fecha de cuota</th>
					<th>Total</th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${fees}" var="fee">
					<tr>
						<td>${fee}</td>
						<td>${fee.factoryFee}</td>
						<td>${fee.paymentDate.format("yyyy-MM-dd")}</td>
						<td style="width:1px;">${fee.total}</td>
					</tr>
				</g:each>
				<tr>
					<td><strong>TOTAL</strong></td>
					<td></td>
					<td></td>
					<td>${total}</td>
				</tr>
			</tbody>
		</table>
	</g:if>
	<g:else>
		<p><br>Nada que mostrar</p>
	</g:else>
</body>
</html>
