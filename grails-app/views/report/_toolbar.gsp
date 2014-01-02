<div class="row toolbar">
	<div class="col-md-12">
		<div class="pull-right">
			<g:link action="dividends" class="btn btn-default">Crear</g:link>
			<g:link action="list" class="btn btn-default">Lista</g:link>
			<g:if test="${controllerName == 'report' && actionName=='printReport'}">
				<g:link action="show" class="btn btn-default" params="[period:"${period}"]">Regresar</g:link>
			</g:if>
		</div>
	</div>
</div>