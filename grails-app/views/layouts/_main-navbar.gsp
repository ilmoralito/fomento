<nav class="navbar navbar-inverse navbar-default navbar-fixed-top" role="navigation">
	<div class="container">
	  	<div class="navbar-header">
        <button data-target=".bs-navbar-collapse" data-toggle="collapse" type="button" class="navbar-toggle collapsed">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
	  	  <g:link class="navbar-brand" uri="/">Fomento</g:link>
	  	</div>
  		  <nav role="navigation" class="navbar-collapse bs-navbar-collapse collapse" style="height: 1px;">
  	  		<ul class="nav navbar-nav navbar-right">
  	  			<sec:ifAllGranted roles="ROLE_ADMIN">
  	  			    <li class="${(controllerName == 'user' && actionName != 'profile') ? 'active' : ''}">
                        <g:link controller="user" action="list">Administrar</g:link>
                    </li>
  	  			</sec:ifAllGranted>
  	  			<li class="${(controllerName == 'partner') ? 'active' : ''}">
                    <g:link controller="partner">Socios</g:link>
                </li>
  	    		<li class="${((controllerName == 'user' && actionName == 'profile') ? 'active' : '')}">
                    <g:link controller="user" action="profile">Perfil</g:link>
                </li>
  	    		<li><g:link controller="logout">Salir</g:link></li>
  	  		</ul>
  		  </nav>
  	</div>
</nav>
