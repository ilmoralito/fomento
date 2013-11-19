<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<g:if test="${partners}">
		<h4>${partners.size()} socios tipo de pago ${params?.typeOfPayment?.toUpperCase()}</h4>
		<g:form action="create">
			<g:hiddenField name="typeOfPayment" value="${params?.typeOfPayment}"/>
			<table class="table table-hover">
				<thead>
					<tr>
						<th></th>
						<th>Socio</th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${partners}" var="partner">
						<tr>
							<td style="width:1px;">
								<g:checkBox name="partners" value="${partner.id}" checked="false"/>
							</td>
							<td>${partner}</td>
						</tr>
					</g:each>
				</tbody>
			</table>
			<g:submitButton name="send" value="Confirmar" class="btn btn-default"/>
		</g:form>
	</g:if>
	<g:else>
		<h4>No hay aun socios registrados con el tipo de pago ${params.typeOfPayment} registrado...</h4>
	</g:else>
</body>
</html>
