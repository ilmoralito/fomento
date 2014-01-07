<!doctype html>
<html lang="en">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<g:render template="tool-bar"/>
	<br>
	<div class="col-md-4">
		<legend>Actualizar usuario ${userInstance?.fullName}</legend>
		<g:form controller= "user" action="update">
			<g:hiddenField name="id" value="${userInstance.id}"/>
			<g:hiddenField name="userRole" value="${userRole}"/>
		   	<div class="form-group">
		   		<label for="username">Correo electronico</label>
		        <g:textField type="text" class="form-control" name="username" autofocus="true" value="${userInstance?.username}"/>
		    </div>
		    <div class="form-group">
		   		<label for="fullName">Nombres y Apellidos</label>
		        <g:textField type="text" class="form-control" name="fullName" value="${userInstance?.fullName}"/>
		    </div>
		     <div class="form-group">
		     	<label for="fullname">Contraseña</label>
		     	<g:passwordField class="form-control" name="password"/>
		     </div>

		    <g:submitButton name="btnregistration" value="${message(code:'org.fomento.btnupdate')}"class="btn btn-default"/>
		</g:form>
	</div>

	<div class="col-md-3">
		<legend>Actualizar roles y cuenta</legend>
	   	<div class="col-md-12">
		   	<g:form controller="user"  action="enabledaccount">
		   		<g:hiddenField name="userRole" value="${userRole}"/>
		   		<g:hiddenField name="id" value="${userInstance.id}"/>
			   		<g:if test="${userInstance.enabled==true}">
						<label class="checkbox disaccount">
					   		<g:checkBox name="disable"/><g:message code="org.fomento.disaccount"/>
					    </label>
					</g:if>
					<g:else>
						<label class="checkbox enableaccount">
					    	<g:checkBox name="enabled"/><g:message code="org.fomento.enableaccount"/>
					    </label>
					</g:else>

					<g:if test="${userInstance.enabled==true}">
						<g:submitButton name="btnregistration" value="${message(code:'org.fomento.btnexecuteaction')}"class="btn btn-danger btn-sm"/>
					</g:if>
					<g:else>
						<g:submitButton name="btnregistration" value="${message(code:'org.fomento.btnexecuteaction')}"class="btn btn-success btn-sm"/>
					</g:else>
				</g:form>
		</div>
		<br>
		<div class="col-md-12">
			<g:if test="${userRole=="NO_ROLE"}">
				<g:form controller="user" action="assignrole">
					<g:hiddenField name="userRole" value="${userRole}"/>
					<g:hiddenField name="id" value="${userInstance.id}"/>
					<h5><strong>Asignación de Rol de usuario</strong></h5>
						<div>
							<label class="radio">
								<g:radio name="role" value="user" checked="true"/>
								<g:message code="org.fomento.user"/>
							</label>
						</div>
						<div>
							<label class="radio">
								<g:radio name="role" value="admin"/>
								<g:message code="org.fomento.admin"/>
							</label>
						</div>
					<g:submitButton name="btnregistration" value="${message(code:'org.fomento.btnexecuteaction')}"class="btn btn-primary btn-sm"/>
				</g:form>
			</g:if>

			<g:if test="${userRole=="ROLE_ADMIN"}">
				<g:form controller="user" action="changerole">
					<g:hiddenField name="userRole" value="${userRole}"/>
					<g:hiddenField name="id" value="${userInstance.id}"/>
					<label class="checkbox">
						<g:checkBox name="roleuser"/>
						<g:message code="org.fomento.roleuser"/>
					</label>
					<g:submitButton name="btnregistration" value="${message(code:'org.fomento.btnexecuteaction')}"class="btn btn-primary btn-sm"/>
				</g:form>
			</g:if>
			<g:if test="${userRole=="ROLE_USER"}">
				<g:form controller="user" action="changerole">
					<g:hiddenField name="userRole" value="${userRole}"/>
					<g:hiddenField name="id" value="${userInstance.id}"/>
					<label class="checkbox">
						<g:checkBox name="roleadmin"/>
						<g:message code="org.fomento.roleadmin"/>
					</label>
					<g:submitButton name="btnregistration" value="${message(code:'org.fomento.btnexecuteaction')}"class="btn btn-primary btn-sm"/>
				</g:form>
			</g:if>



		</div>
	</div>
	<div class="col-md-5">
	<br>
		<g:if test="${er=="ok"}">
		     <g:render template="men-error"/>
		</g:if>
		<g:if test="${men=="ok"}">
		    <g:render template="men-success"/>
		</g:if>
	</div>
</body>
</html>

