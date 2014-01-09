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
		<h4>Actualizar usuario</h4>
		<g:form controller= "user" action="update">
			<g:hiddenField name="id" value="${userInstance.id}"/>
		   	<g:render template="form"/>
		    <g:submitButton name="btnregistration" value="${message(code:'org.fomento.btnupdate')}"class="btn btn-default"/>
		</g:form>
	</div>
	<div class="col-md-3">
		<h4>Actualizar roles y cuenta</h4>
		<g:form controller="user" action="enabledaccount">
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

