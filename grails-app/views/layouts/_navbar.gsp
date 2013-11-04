<ul class="nav nav-tabs">
  	<li class="${(actionName == 'show') ? 'active' : ''}"><g:link controller="partner" action="show" id="${(params.id) ?: id}">Socio</g:link></li>
  	<li class="${(actionName == 'list') ? 'active' : ''}"><g:link controller="fee" id="${(params.id) ?: id}">Cuotas de ${partner}</g:link></li>
  	<li class="${(actionName == 'deductions') ? 'active' : ''}">
  		<g:link controller="deduction" action="deductions" params="[partner:partner.id, period:fomento.currentYear()]">
  			Deducciones de ${partner}
  		</g:link>
  	</li>
	<li class="${(actionName == 'report') ? 'active' : ''}">
		<g:link controller="partner" action="report" id="${(params.id) ?: id}">
			Reporte
		</g:link>
	</li>
	<li class="${(actionName == 'changeStatus') ? 'active' : ''}"><g:link controller="partner" action="changeStatus" id="${(params?.id) ?: id}">Estado</g:link></li>
</ul>
<br>