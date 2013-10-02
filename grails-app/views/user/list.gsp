<!doctype html>
<html lang="en">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app, datepicker"/>	
</head>
<body>
	<g:render template="tool-bar"/>
	<h4>Lista de Usuarios</h4>	
	<hr>
	<div class="col-md-12">
	<g:each in="${userInstance}" var="us" status="i">
		<div class="row defaultclss">
			<div class="col-md-1 usnum">
				<h2>${i+1}</h2>
			</div>
			<div class="col-md-3">
				<div><strong>email:</strong></div>
				<div><strong>Nombres y Apellidos:</strong></div>
				<div><strong>Estado de la cuenta:</strong></div>
			</div>

			<div class="col-md-4">
				<div><g:link action="edit" id="${us.id}">${us.username}</g:link></td></div>
				<div>
					<g:if test="${!us.fullName}">--</g:if>
					<else>${us.fullName}</else>
				</div>
				<div>
					<g:if test="${us.enabled==true}">Cuenta Habilitada</g:if>
					<g:else>Cuenta Deshabilitada</g:else>
				</div>
			</div>

			<div class="col-md-3">
			<div class="cab">
				<strong>Opciones</strong>
			</div>
			<div>
		        <sec:ifAllGranted roles="ROLE_ADMIN">
					<g:link controller="user" action="delete" id="${us.id}" title="${message(code:'org.fomento.delete')}" class="tooledit clsize" data-placement="top">
						<span class="glyphicon glyphicon-trash"></span>
					</g:link>
				</sec:ifAllGranted> 
				<sec:ifAllGranted roles="ROLE_ADMIN">
					<g:link controller="user" action="edit" id="${us.id}" title="${message(code:'org.fomento.edit')}" class="tooledit clsize" data-placement="top">
						<span class="glyphicon glyphicon-wrench"></span>
					</g:link>
				</sec:ifAllGranted>
			</div>
		    </div>
		</div>
	</g:each>
    </div>
</body>
</html>


