<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, jquery-ui, fees, datepicker"/>
</head>
<body>
	<g:if test="${partners}">
		<h4>${partners.size()} socios tipo de pago ${params?.typeOfPayment?.toUpperCase()}</h4>
		<g:form action="create">
			<g:hiddenField name="typeOfPayment" value="${params?.typeOfPayment}"/>
			<div class="row">
				<div class="col-md-10">
					<table class="table table-hover">
						<thead>
							<tr>
								<th width="1">
									<g:checkBox name="chkall" class="chkall toolchk" checked="false" title="Marcar/Desmarcar" data-placement="top"/>
								</th>
								<th><span id="all" class="chkall glyphicon glyphicon-th-list"></span> Socios</th>
							</tr>
						</thead>
						<tbody>
							<g:each in="${partners}" var="partner">
								<tr>
									<td>
										<g:checkBox name="partners" class="partners" value="${partner.id}" checked="false"/>
									</td>
									<td>${partner}</td>
								</tr>
							</g:each>
						</tbody>
					</table>
				</div>
				<div class="col-md-2">
					<label for="dateCreated" class="sr-only">Fecha de cuota</label>
					<g:textField name="dateCreated" class="form-control" placeholder="Fecha de cuota"/>
				</div>
			</div>
			<g:submitButton name="send" value="Confirmar" class="btn btn-default"/>
		</g:form>
	</g:if>
	<g:else>
		<h4>No hay aun socios registrados con el tipo de pago ${params.typeOfPayment}</h4>
	</g:else>
</body>
</html>
