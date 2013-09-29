<!doctype html>
<html lang="en">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app"/>	
</head>
<body>
	<g:render template="tool-bar"/>
	<h4>Lista de Usuarios</h4>	
	<hr>
	<div class="col-md-10">
	<g:each in="${userInstance}" var="us" status="i">
		<div class="row defaultclss">
			<div class="col-md-3">
				<div>Nombre de Usuario</div>
				<div>Nombres y Apellidos</div>
				<div>Estado de la cuenta</div>
			</div>

			<div class="col-md-4">
				<div><g:link action="show" id="${us.id}">${us.username}</g:link></td></div>
				<div>z${us.fullName}</div>
				<div>${us.enabled}</div>
			</div>

			<div class="col-md-2">
		        <sec:ifAllGranted roles="ROLE_ADMIN">
					<g:link action="delete" id="${us.id}" class="tooldelete" title="jfskfsl" data-placement="bottom">
						<span class="glyphicon glyphicon-trash"></span>
					</g:link>
				</sec:ifAllGranted>
				<sec:ifAllGranted roles="ROLE_ADMIN">
					<g:link action="edit" id="${us.id}">
						<span class="glyphicon glyphicon-wrench"></span>
					</g:link>
				</sec:ifAllGranted>
		    </div>
		</div>
	</g:each>
    </div>
</html>

