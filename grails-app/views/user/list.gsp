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
	<div class="col-md-10 usercont container">
		<div class="defaultclss  cab row-fluid">
			<div class="col-md-4">
				<strong>Nombre de Usuario</strong>
			</div>
			<div class="col-md-4">
			    <strong>Nombres y Apellidos</strong>
			</div>
			<div class="col-md-2">
			    <strong>Habilitado</strong>
			</div>
			<div class="col-md-2">
			    <strong>Opciones</strong>
			</div>
		</div>
		<g:each in="${userInstance}" var="us" status="i">
			<div class="defaultclss row-fluid">
				<div class="col-md-4">
					<g:link action="show" id="${us.id}">${us.username}</g:link></td>
				</div>
		        <div class="col-md-4">
		        	${us.fullName}
		        </div>
		        <div class="col-md-2">
		        	${us.enabled}
		        </div>
		        <div class="col-md-2">
		        	<sec:ifAllGranted roles="ROLE_ADMIN">
						<g:link action="delete" id="${us.id}">
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
</body>
</html>