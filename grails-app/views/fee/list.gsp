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
					<th>Fecha de cuota</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${fees}" var="fee">
					<tr>
						<td>${fee}</td>
						<td>${fee.paymentDate.format("yyyy-MM-dd")}</td>
						<td style="width:1px;">
							<sec:ifAllGranted roles="ROLE_ADMIN">
								<g:link action="delete" id="${fee.id}">
									<span class="glyphicon glyphicon-trash"></span>
								</g:link>
							</sec:ifAllGranted>
						</td>
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
