<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap-css, bootstrap-dropdown, app"/>
</head>
<body>
	<g:render template="toolbar"/>
	<g:render template="/layouts/navbar"/>

	<div class="row">
		<div class="col-md-9">
			<g:if test="${!dontHasFeesOrDividends}">
				<g:if test="${status==false}">
					<g:link action="enablePartner" params="[id:params?.id]" class="btn btn-success">Activar al Socio</g:link>
				</g:if>
				<g:else>
					<g:link action="enablePartner" params="[id:params?.id]" class="btn btn-primary">Desactivar al Socio</g:link>
				</g:else>
			</g:if>
			<g:else>
				<div class="alert alert-danger">
					<p>Al iniciar el tramite de renunica del socio: <strong>${partner}</strong>, recuerde que una ves confirmado este proceso <br><strong>NO SE PODRAN DESHACER LOS CAMBIOS, DESEA CONTINUAR?</strong></p>
					<br>
					<g:link controller="renounce" params="[partnerId:params?.id]" class="btn btn-danger">Iniciar proceso de renunciar</g:link>
				</div> 
			</g:else>
		</div>
		<div class="col-md-3">
			<g:if test="${partner.renouncies}">
				<g:each in="${partner.renouncies}" var="renounce">
					<h4 style="margin:0 0 5px 0;">Renuncias</h4>
					<div class="renouncies">
						<g:link controller="renounce" action="show" params="[renounceId:renounce.id]">${renounce.dateCreated.format("yyyy-MM-dd")}</g:link>
					</div>
				</g:each>
			</g:if>
		</div>
	</div>

</body>
</html>