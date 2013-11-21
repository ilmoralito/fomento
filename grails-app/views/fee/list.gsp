<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<div class="row">
		<div class="col-md-10">
			<g:if test="${partners}">
				<h3>Periodo ${params?.period}</h3>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Socio</th>
							<th>E</th>
							<th>F</th>
							<th>M</th>
							<th>A</th>
							<th>M</th>
							<th>J</th>
							<th>J</th>
							<th>A</th>
							<th>S</th>
							<th>O</th>
							<th>N</th>
							<th>D</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${partners}" var="partner">
							<tr>
								<td>${partner}</td>
								<td><fomento:feeDetail partner="${partner}" period="${params.period}" month="1"/></td>
								<td><fomento:feeDetail partner="${partner}" period="${params.period}" month="2"/></td>
								<td><fomento:feeDetail partner="${partner}" period="${params.period}" month="3"/></td>
								<td><fomento:feeDetail partner="${partner}" period="${params.period}" month="4"/></td>
								<td><fomento:feeDetail partner="${partner}" period="${params.period}" month="5"/></td>
								<td><fomento:feeDetail partner="${partner}" period="${params.period}" month="6"/></td>
								<td><fomento:feeDetail partner="${partner}" period="${params.period}" month="7"/></td>
								<td><fomento:feeDetail partner="${partner}" period="${params.period}" month="8"/></td>
								<td><fomento:feeDetail partner="${partner}" period="${params.period}" month="9"/></td>
								<td><fomento:feeDetail partner="${partner}" period="${params.period}" month="10"/></td>
								<td><fomento:feeDetail partner="${partner}" period="${params.period}" month="11"/></td>
								<td><fomento:feeDetail partner="${partner}" period="${params.period}" month="12"/></td>
							</tr>
						</g:each>
					</tbody>
					</table>
			</g:if>
			<g:else>
				<h2>...</h2>
			</g:else>
		</div>
		<div class="col-md-2">
			<g:form action="list">
				<div class="form-group">
					<label for="period">Periodo</label>
					<g:select from="${2013..new Date()[Calendar.YEAR]}" name="period" value="${(params?.period) ?: fomento.currentYear()}" class="form-control"/>
				</div>
				<g:submitButton name="name" value="Mostrar" class="btn btn-default"/>
			</g:form>
		</div>
	</div>
</body>
</html>