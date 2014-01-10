<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<g:render template="toolbar"/>
	<g:render template="/layouts/navbar"/>

	<p>Confirmar proceso de renunica del socio: <strong>${partner}</strong>, una ves finalizado este proceso <strong>NO SE PODRAN DESHACER LOS CAMBIOS, DESEA CONTINUAR?</strong></p>
	<g:link controller="renounce" params="[partnerId:params.id]">Renunciar</g:link>
</body>
</html>