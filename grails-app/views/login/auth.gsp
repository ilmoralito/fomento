<!doctype html>
<html lang="es">
<head>
	<title><g:message code="springSecurity.login.title"/></title>
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<form action='${postUrl}' method='POST' id='loginForm' class='cssform' autocomplete='off'>
		<div class="form-group">
			<label for='username' class="sr-only"><g:message code="springSecurity.login.username.label"/>:</label>
			<input type='text' class='text_ form-control' name='j_username' id='username' autofocus="true" placeholder="Email"/>
		</div>

		<div class="form-group">
			<label for='password' class="sr-only"><g:message code="springSecurity.login.password.label"/>:</label>
			<input type='password' class='text_ form-control' name='j_password' id='password' placeholder="Clave"/>
		</div>

		<input type='submit' id="submit" class="btn btn-default" value='${message(code:"springSecurity.login.button")}'/>
	</form>
</body>
</html>
