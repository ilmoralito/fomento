<div class="row">
	<div class="col-md-12">
		<div class="pull-right">
			<g:if test="${actionName == 'list' && controllerName != 'fee'}">
				<g:form action="list" class="form-inline" role="form">
					<div class="form-group">
						<label class="sr-only" for="query">Consulta</label>
				    	<input type="text" value="${params?.query}" class="form-control" name="query" id="query" placeholder="Nombre, numero empleado">
				  	</div>
					<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
					<button id="apply-effect" class="btn btn-default" data-effect="st-effect-1"><span class="glyphicon glyphicon-calendar"></span></button>
					<g:link controller="partner" action="create" class="btn btn-default">Crear socio</g:link>
				</g:form>
			</g:if>
			<g:else>
				<g:link controller="partner" action="list" class="btn btn-default">Listar socios</g:link>
			</g:else>
		</div>
	</div>
</div>