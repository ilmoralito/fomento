<!doctype html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title><g:layoutTitle default="Reporte"/></title>
	<r:layoutResources/>
</head>
<body>
	<!--Navbar-->
	<g:render template="/layouts/main-navbar"/>

	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<g:render template="toolbar"/>
				<g:render template="/layouts/navbar"/>
			</div>
		</div>

		<div class="row">
			<div class="col-md-9">
				<g:layoutBody/>
			</div>
			<div class="col-md-3">
				<div class="well well-sm">
					<div class="btn-group">
						<fomento:periods/>
					</div>
				</div>

				<!--Aporte inicial-->
				<div class="panel panel-default">
					<div class="panel-heading">Detalle periodo ${params.period}</div>
					<div class="panel-body">
						<table class="table table-small">
							<tbody>
								<tr>
									<td>Aporte incial</td>
									<td>${partner?.affiliation?.capitalization}</td>
								</tr>
								<tr>
									<td>TAS</td>
									<td>${tas}</td>
								</tr>
								<tr>
									<td>TAE</td>
									<td>${tae}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>

				<!--Totales de cuotas por socio y empresa-->
				<g:if test="${fees}">
					<div class="panel panel-default">
						<div class="panel-heading">Cuotas</div>
						<div class="panel-body">
							<table class="table table-small">
								<tbody>
									<tr>
										<td>Aporte socio</td>
										<td>${totalPartnerFee}</td>
									</tr>
									<tr>
										<td>Aporte empresa</td>
										<td>${totalFactoryFee}</td>
									</tr>
									<tr>
										<td>TOTAL</td>
										<td>${totalPartnerFee + totalFactoryFee}</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</g:if>
			</div>
		</div>
	</div>

	<r:layoutResources/>
</body>
</html>