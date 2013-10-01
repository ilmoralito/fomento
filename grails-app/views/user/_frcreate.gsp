<g:form controller= "user" action="save">
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
<g:submitButton name="btnregistration" value="${message(code:'org.fomento.btnsave')}"class="btn btn-default pull-right"/>
</g:form>