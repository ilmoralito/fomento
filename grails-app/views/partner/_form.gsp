<g:set var="departments" value="${grailsApplication.config.org.fomento.departments}"/>
<g:set var="typesOfPayment" value="${(partner?.affiliation?.constraints?.typeOfPayment?.inList) ?: ['Catorcena', 'Fin de mes', 'Bono']}"/>
<g:set var="fees" value="${org.fomento.Configuration.list().fee}"/>

<div class="row">
	<div class="col-md-6">
		<h4>Datos personales</h4>

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
	</div>

	<div class="col-md-6">
		<h4>Datos de afiliacion</h4>

		<div class="form-group">
			<label for="affiliation.fee">Cuota</label>
			<g:select name="affiliation.fee" from="${fees}" class="form-control" value="${partner?.affiliation?.fee}"/>
		</div>

		<div class="form-group">
			<label for="affiliation.typeOfPayment">Tipo de pago</label>
			<g:select name="affiliation.typeOfPayment" from="${typesOfPayment}" value="${partner?.affiliation?.typeOfPayment}" class="form-control"/>
		</div>

		<div class="form-group">
			<label for="affiliation.enrollmentDate">Fecha de afiliacion</label>
			<g:textField name="affiliation.enrollmentDate" id="enrollmentDate" value="${partner?.affiliation?.enrollmentDate?.format('yyyy-MM-dd')}" class="form-control"/>
		</div>

		<div class="form-group">
			<label for="affiliation.capitalization">Capitalizacion inicial</label>
			<g:textField name="affiliation.capitalization" id="affiliation.capitalization" value="${partner?.affiliation?.capitalization}" class="form-control"/>
		</div>

		<div class="form-group">
			<label for="affiliation.factoryCapital">Capitalizacion inicial de empresa</label>
			<g:textField name="affiliation.factoryCapital" id="affiliation.factoryCapital" value="${partner?.affiliation?.factoryCapital}" class="form-control"/>
		</div>
	</div>
</div>
