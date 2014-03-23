<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Renuncia</title>
	<r:require modules="bootstrap-css, bootstrap-dropdown, app"/>
</head>
<body>
	<div class="row">
		<g:link controller="partner" action="changeStatus" id="${renounce.partner.id}" class="pull-right btn btn-default">Regresar</g:link>
	</div>
	<br>
	<g:render template="info"/>
</body>
</html>