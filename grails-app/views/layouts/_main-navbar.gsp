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
            <li class="${(controllerName == 'report') ? 'active' : ''}"><g:link controller="report" action="dividends" params="[period:fomento.currentYear()]">Reporte</g:link></li>
                <!--add fees manualy-->
                <li class="dropdown ${(actionName == 'partnerToApplyFees') ? 'active' : ''}">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Tipo de abono <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <g:link controller="partner" action="partnerToApplyFees" params="[typeOfPayment:'Catorcena']">Catorcena</g:link>
                        </li>
                        <li>
                            <g:link controller="partner" action="partnerToApplyFees" params="[typeOfPayment:'Fin de mes']">Fin de mes</g:link>
                        </li>
                        <li>
                            <g:link controller="partner" action="partnerToApplyFees" params="[typeOfPayment:'Bono']">Bono</g:link>
                        </li>
                    </ul>
                </li>
                <!--alert fees by date of month-->
                <g:if test="${session.alert}">
                    <li><g:link controller="partner" action="partnerToApplyFees" params="[typeOfPayment:session.typeOfPayment]"><span class="glyphicon glyphicon-exclamation-sign"></span></g:link></li>
                </g:if>
  	  			<sec:ifAllGranted roles="ROLE_ADMIN">
  	  			    <li class="${(controllerName == 'user' && actionName != 'profile' ) ? 'active' : ''}">
                        <g:link controller="user" action="list">Administrar</g:link>
                    </li>
  	  			</sec:ifAllGranted>
  	  			<li class="${((controllerName == 'partner' || controllerName == 'fee' || controllerName == 'deduction') && actionName != 'partnerToApplyFees') ? 'active' : ''}">
                    <g:link controller="partner">Socios</g:link>
                </li>
  	    		<li class="${((controllerName == 'user' && actionName == 'profile') ? 'active' : '')}">
                    <g:link controller="user" action="profile">Perfil</g:link>
                </li>
  	    		<li><g:link controller="logout">Salir</g:link></li>
  	  		</ul>
  		</nav>
</nav>
