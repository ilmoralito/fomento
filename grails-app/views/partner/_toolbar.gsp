<div class="row">
	<div class="col-md-12">
		<div class="pull-right">
			<div class="btn-group">
				<g:if test="${actionName == 'list'}">
					<g:link action="create" class="btn btn-default">Crear socio</g:link>
				</g:if>
				<g:else>
					<g:link action="list" class="btn btn-default">Listar socios</g:link>
				</g:else>
			</div>
		</div>
	</div>
</div>