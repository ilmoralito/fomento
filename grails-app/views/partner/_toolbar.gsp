<div class="row">
	<div class="col-md-12">
		<div class="pull-right">
			<div class="btn-group">
				<g:if test="${actionName == 'list' && controllerName != 'fee'}">
					<g:link controller="partner" action="create" class="btn btn-default">Crear socio</g:link>
				</g:if>
				<g:else>
					<g:link controller="partner" action="list" class="btn btn-default">Listar socios</g:link>
				</g:else>
			</div>
		</div>
	</div>
</div>