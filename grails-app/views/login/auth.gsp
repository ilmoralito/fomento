<!doctype html>
<html lang="es">
<head>
	<title><g:message code="springSecurity.login.title"/></title>
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<g:form action='${postUrl}' autocomplete='off'>
		<div class="form-group">
			<label for='username' class="sr-only">Nombre de usuario | email</label>
			<g:textField name="j_username" class="form-control" placeholder="Nombre de usuario o email" autofocus="true"/>
		</div>

		<div class="form-group">
			<label for='password' class="sr-only">Clave</label>
			<g:passwordField name="password" class="form-control" placeholder="Clave"/>
		</div>

		<div class="row">
			<div class="col-md-3">
				<g:submitButton name="send" value="Iniciar sesion" class="btn btn-default"/>
			</div>
			<div class="col-md-3">
				<div class="checkbox">
					<label>
				    	<g:checkBox name="${rememberMeParameter}" checked="${hasCookie}"/> Recuerdame
				  	</label>
				</div>
			</div>
		</div>

	</g:form>
</body>
</html>
