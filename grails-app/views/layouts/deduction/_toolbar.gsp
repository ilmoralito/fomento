<div class="row">
	<div class="col-md-6">
		<h2>${partner} ${state}</h2>
	</div>
	<div class="col-md-6">
		<div class="btn-group-sm pull-right">
			<g:link controller="deduction" event="create" class="btn btn-default">Crear deduccion</g:link>
			<g:link controller="deduction" event="list" class="btn btn-default">Deducciones</g:link>
			<g:link action="deductions" event="cancel" class="btn btn-default">Cuotas de ${partner}</g:link>
		</div>
	</div>
</div>
