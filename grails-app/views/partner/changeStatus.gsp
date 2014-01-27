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
				<g:link action="enablePartner" params="[id:params?.id]" class="btn btn-primary">Desactivar</g:link>
			</g:if>
			<g:else>
				<p>Confirmar proceso de renunica del socio: <strong>${partner}</strong>, una ves finalizado este proceso <br><strong>NO SE PODRAN DESHACER LOS CAMBIOS, DESEA CONTINUAR?</strong></p>

				<g:if test="${flag}">
					<g:form controller="renounce" autocomplete="off">
						<g:hiddenField name="partnerId" value="${params?.id}"/>
						<g:hiddenField name="period" value="${period}"/>

						<div class="form-group">
						   	<label for="up">Utilidad de este periodo</label>
						    <g:textField name="up" class="form-control" required autofocus="true"/>
						</div>
						<g:submitButton name="send" value="Calcular" class="btn btn-default"/>
					</g:form>

				</g:if>
				<g:else>
					<g:link controller="renounce" params="[partnerId:params?.id]">Renunciar</g:link>
				</g:else>
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