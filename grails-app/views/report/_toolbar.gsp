<div class="row">
	<div class="col-md-12">
		<div class="pull-right">
			<g:link action="dividends" class="btn btn-default">Crear</g:link>
			<g:link action="list" class="btn btn-default">Lista</g:link>
			<g:if test="${actionName == 'capitalize'}">
				<g:link action="show" params="[period:dividend.period]" class="btn btn-default">Regresar</g:link>
			</g:if>
		</div>
	</div>
</div>