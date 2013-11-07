<ul class="nav nav-tabs">
  	<li class="${(actionName == 'show') ? 'active' : ''}">
  		<g:link controller="partner" action="show" id="${params?.id}">Socio</g:link>
  	</li>
	<li class="${(actionName == 'report') ? 'active' : ''}">
		<g:link controller="partner" action="report" id="${params?.id}" params="[period:fomento.currentYear()]">
			Reporte
		</g:link>
	</li>
	<li class="${(actionName == 'changeStatus') ? 'active' : ''}">
		<g:link controller="partner" action="changeStatus" id="${params?.id}">Estado</g:link>
	</li>
</ul>
<br>