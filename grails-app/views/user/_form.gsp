<div class="form-group">
	<label for="username">Correo electronico</label>
    <g:textField type="text" class="form-control" name="username" autofocus="true" value="${userInstance?.username}"/>
</div>
<div class="form-group">
    <label for="fullName">Nombres y Apellidos</label>
    <g:textField type="text" class="form-control" name="fullName" value="${userInstance?.fullName}"/>
</div>
<div class="form-group">
    <label for="authority">Rol</label>
    <g:select from="${org.fomento.Role.list()}" name="authority" value="${(actionName == 'edit') ? org.fomento.UserRole.findByUser(userInstance).role : 'ROLE_ADMIN'}" class="form-control"/>
</div>
<div class="form-group">
   	<label for="password">Contraseña</label>
  	<g:passwordField class="form-control" name="password"/>
</div>