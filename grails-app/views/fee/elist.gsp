<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<div class="row">
		<div class="col-md-10">
			<g:if test="${partners}">
				<h3>Periodo ${params?.period}</h3>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Socio</th>
							<th>Enero</th>
							
						</tr>
					</thead>
					<tbody>
						<g:each in="${partners}" var="partner">
							<tr>
								<td>${partner}</td>
								<td>${partner.fees.find{0}}</td>
							</tr>
						</g:each>
					</tbody>
					</table>
			</g:if>
			<g:else>
				<h2>...</h2>
			</g:else>
		</div>
	</div>
</body>
</html>