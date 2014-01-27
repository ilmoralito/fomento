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
            <!--dividends-->
            <li class="${(controllerName == 'report' || controllerName == 'deduction') ? 'active' : ''}">
              <g:link controller="report" action="list">Dividendos</g:link>
            </li>
            <!--add fees manualy-->
            <li class="dropdown ${(controllerName == 'fee' && actionName != 'show') ? 'active' : ''}">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Cuotas <b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li role="presentation" class="dropdown-header">Catorcena</li>
                  <li><g:link controller="fee" action="create" params="[typeOfPayment:'Catorcena']">Catorcena</g:link></li>
                  <li><g:link controller="fee" action="create" params="[typeOfPayment:'Catorcena', filter:'divididos']">Pagos divididos</g:link></li>
                  <li><g:link controller="fee" action="create" params="[typeOfPayment:'Catorcena', filter:'completos']">Pagos completos</g:link></li>
                  <li role="presentation" class="dropdown-header">Otros tipos de pago</li>
                  <li>
                      <g:link controller="fee" action="create" params="[typeOfPayment:'Fin de mes']">Fin de mes</g:link>
                  </li>
                  <li>
                    <g:link controller="fee" action="create" params="[typeOfPayment:'Bono']">Bono</g:link>
                  </li>
                  <li role="presentation" class="divider"></li>
                  <li role="presentation">
                    <g:link role="menuitem" tabindex="-1" controller="fee" action="elist" params="[max:'2', offset:'0']">Historial</g:link>
                  </li>
                </ul>
            </li>
            <li class="${(controllerName == 'partner' || controllerName == 'renounce' || controllerName == 'fee' && actionName == 'show' && actionName != 'partnerToApplyFees' ) ? 'active' : ''}">
              <g:link controller="partner">Socios</g:link>
            </li>
  	  			<sec:ifAllGranted roles="ROLE_ADMIN">
  	  			  <li class="${(controllerName == 'user' && actionName != 'profile' ) ? 'active' : ''}">
                <g:link controller="user" action="list">Administrar</g:link>
              </li>
  	  			</sec:ifAllGranted>
  	    		<li class="dropdown ${(actionName == 'profile') ? 'active' : ''}">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <span class="glyphicon glyphicon-user"></span>
              <b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li>
                   <g:link controller="user" action="profile">
                     <span class="glyphicon glyphicon-wrench"></span>
                     Perfil
                   </g:link>
                  </li>
                  <li role="presentation" class="divider"></li>
                  <li role="presentation">
                      <g:link controller="logout">
                        <span class="glyphicon glyphicon-off"></span>
                        Salir
                      </g:link>
                  </li>
                </ul>
            </li>
  	  		</ul>
  		</nav>
</nav>
