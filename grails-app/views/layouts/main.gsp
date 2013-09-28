<!doctype html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title><g:layoutTitle default="Socios"/></title>
	<r:layoutResources/>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
		  	<div class="navbar-header">
		  	  <g:link class="navbar-brand" uri="/">Fomento</g:link>
		  	</div>
	  		<div class="pull-right">
	  	  		<ul class="nav navbar-nav">
	  	  			<sec:ifAllGranted roles="ROLE_ADMIN">
	  	  				<li class="${(controllerName == 'user' && actionName != 'profile') ? 'active' : ''}"><g:link controller="user">Usuarios</g:link></li>
	  	  			</sec:ifAllGranted>
	  	  			<li class="${(controllerName == 'partner') ? 'active' : ''}"><g:link controller="partner">Socios</g:link></li>
	  	    		<li  class="${((controllerName == 'user' && actionName == 'profile') ? 'active' : '')}"><g:link controller="user" action="profile">Perfil</g:link></li>
	  	    		<li><g:link controller="logout">Salir</g:link></li>
	  	  		</ul>
	  		</div>
	  	</div>
	</nav>

	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<g:layoutBody/>
				<g:if test="${flash.message}"><br><strong>${flash.message}</strong></g:if>
			</div>
		</div>
	</div>
	<r:layoutResources/>
</body>
</html>