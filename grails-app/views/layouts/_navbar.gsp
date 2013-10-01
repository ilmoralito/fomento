<ul class="nav nav-tabs">
  	<li class="${(actionName == 'show') ? 'active' : ''}"><g:link controller="partner" action="show" id="${params?.id}">Socio</g:link></li>
  	<li class="${(actionName == 'list') ? 'active' : ''}"><g:link controller="fee" id="${params?.id}">Cuotas</g:link></li>
	<li class="${(actionName == 'changeStatus') ? 'active' : ''}"><g:link controller="partner" action="changeStatus" id="${params?.id}">Estado</g:link></li>
</ul>
<br>