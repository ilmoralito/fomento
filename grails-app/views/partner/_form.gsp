<g:set var="departments" value="${grailsApplication.config.org.fomento.departments}"/>

<div class="form-group">
	<label for="fullName">Nombre del socio</label>
	<g:textField name="fullName" class="form-control" value="${partner?.fullName}" autofocus="true"/>
</div>

<div class="form-group">
	<label for="numberOfEmployee">Numero de empleado</label>
	<g:textField name="numberOfEmployee" class="form-control" value="${partner?.numberOfEmployee}"/>
</div>

<div class="form-group">
	<label for="identificationCard">Cedula</label>
	<g:textField name="identificationCard" class="form-control" value="${partner?.identificationCard}"/>
</div>

<div class="form-group">
	<label for="department">Departamento</label>
	<g:select name="department" from="${departments}" class="form-control" value="${partner?.department}"/>
</div>

<div class="form-group">
	<label for="salary">Salario</label>
	<g:textField name="salary" class="form-control" value="${partner?.salary}"/>
</div>
