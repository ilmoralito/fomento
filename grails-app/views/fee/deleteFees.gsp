<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<r:require modules="bootstrap-css, bootstrap-dropdown, jquery-ui, fees"/>
	<title>Eliminar cuotas por lote</title>
</head>
<body>
	<g:set var="typeOfPayment" value="${params.list('typeOfPayment')}"/>

	<div class="row">
		<div class="col-md-9">
			<g:if test="${fees}">
				<h4>Resultados</h4>
				<table class="table table-hover">
					<thead>
						<th>Socio</th>
						<th>Cuota de socio</th>
						<th>Cuota de empresa</th>
					</thead>
					<tbody>
						<g:each in="${fees}" var="fee">
							<tr>
								<td>
									${fee.partner}
									<g:if test="${typeOfPayment.size() > 1}">
										<small>${fee.partner.affiliation.typeOfPayment}</small>
									</g:if>
								</td>
								<td>${fee.fee}</td>
								<td>${fee.factoryFee}</td>
							</tr>
						</g:each>
					</tbody>
				</table>
				<g:form action="confirmDeleteFees">
					<g:hiddenField name="dateCreated" value="${params?.dateCreated}"/>
					<g:hiddenField name="typeOfPayment" value="${typeOfPayment.join(',')}"/>

					<g:submitButton name="confirm" value="Confirmar eliminar cuotas" class="btn btn-warning"/>
				</g:form>
			</g:if>
			<g:else>
				<h1>...</h1>
			</g:else>
		</div>
		<div class="col-md-3">
			<h4>Filtros</h4>
			<g:form action="deleteFees">
				<div class="form-group">
					<label>Fecha de cuota</label>
					<g:select from="${dates}" name="dateCreated" value="${params?.dateCreated}" class="form-control"/>
				</div>

				<div class="form-group">
					<label>Tipo de pago</label>
					<div class="checkbox">
						<label><g:checkBox name="typeOfPayment" value="Catorcena" checked="${typeOfPayment.contains('Catorcena')}"/>Catorcena</label>
					</div>
					<div class="checkbox">
						<label><g:checkBox name="typeOfPayment" value="Fin de mes" checked="${typeOfPayment.contains('Fin de mes')}"/>Fin de mes</label>
					</div>
					<div class="checkbox">
						<label><g:checkBox name="typeOfPayment" value="Bono" checked="${typeOfPayment.contains('Bono')}"/>Bono</label>
					</div>
				</div>

				<g:submitButton name="confirm" value="Listar cuotas afectadas" class="btn btn-default btn-block"/>
			</g:form>
		</div>
	</div>
</body>
</html>
