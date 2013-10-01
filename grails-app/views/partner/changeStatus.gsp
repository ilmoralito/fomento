<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<g:render template="toolbar"/>
	<g:render template="/layouts/navbar"/>

	<p>El estado actual del socio <strong>${partner}</strong> es</p>
	<g:form action="changeStatus">
		<g:hiddenField name="id" value="${partner.id}"/>
		<button type="submit" class="btn ${(partner.status) ? 'btn-default' : 'btn-warning'}">
			<fomento:partnerStatus status="${partner.status}"/>
		</button>
	</g:form>
</body>
</html>