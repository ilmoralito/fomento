<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<g:render template="toolbar"/>

	<g:if test="${partners}">
		<g:form action="partnerToApplyFees">
			<g:hiddenField name="typeOfPayment" value="${params.typeOfPayment}"/>
			<table class="table">
				<thead>
					<tr>
						<th></th>
						<th>${partners.size()} Socio${partners.size() > 1 ? 's' : ''} en tipo de pago ${params.typeOfPayment}</th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${partners}" var="partner">
						<tr>
							<td style="width:1px;">
								<g:checkBox name="partners" value="${partner.numberOfEmployee}" checked="false"/>
							</td>
							<td><g:link action="show" id="${partner.id}">${partner.fullName}</g:link></td>
						</tr>
					</g:each>
				</tbody>
			</table>
			<g:submitButton name="send" value="Confirmar cuota" class="btn btn-default"/>
		</g:form>
	</g:if>
	<g:else>
		<p><br>Nada que mostrar</p>
	</g:else>
</body>
</html>
