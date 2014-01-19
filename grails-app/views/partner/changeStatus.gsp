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

	<g:if test="${flag}">
		<g:form controller="renounce" autocomplete="off">
			<g:hiddenField name="partnerId" value="${params?.id}"/>
			<g:hiddenField name="period" value="${period}"/>

			<div class="form-group">
			   	<label for="up">Utilidad de este periodo</label>
			    <g:textField name="up" class="form-control" required autofocus="true"/>
			</div>
			<g:submitButton name="send" value="Calcular" class="btn btn-default pull-right"/>
		</g:form>
	</g:if>
	<g:else>
		<g:link controller="renounce" params="[partnerId:params?.id]">Renunciar</g:link>
	</g:else>
</body>
</html>