<g:form controller= "user" action="profile">
    <g:hiddenField name="flag" value="generaldata"/>
   	<div class="form-group">
   		<label for="username">Correo electronico</label>
        <g:textField type="text" class="form-control" name="username" autofocus="true" value="${userInstance?.username}"/>
    </div>
     <div class="form-group">
     	<label for="fullname">Nombres y Apellidos</label>
     	<g:textField type="text" class="form-control" name="fullname" autofocus="true" value="${userInstance?.fullname}" placeholder="${message(code:'org.fomento.fullname')}"/>
     </div>
<g:submitButton name="btnregistration" value="${message(code:'org.fomento.confirmchange')}"class="btn btn-default pull-right"/>
</g:form>