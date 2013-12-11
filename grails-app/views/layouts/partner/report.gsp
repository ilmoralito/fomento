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

				<div class="panel panel-default">
					<div class="panel-heading">Datos de socio</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-3">Info</div>
							<div class="col-md-9">Lorem ipsum dolor sit amet</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-3">Info</div>
							<div class="col-md-9">Lorem ipsum dolor sit amet</div>
						</div>
					</div>
				</div>

				<g:if test="${fees}">
					<div class="panel panel-default">
						<div class="panel-heading">Datos del periodo</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-7">Numero de cuotas</div>
								<div class="col-md-5">${fees.size()}</div>
							</div>
							<br>
							<div class="row">
								<div class="col-md-7">Aporte socio</div>
								<div class="col-md-5">${totalPartnerFee}</div>
							</div>
							<br>
							<div class="row">
								<div class="col-md-7">Aporte empresa</div>
								<div class="col-md-5">${totalFactoryFee}</div>
							</div>
						</div>
					</div>
				</g:if>
			</div>
		</div>
	</div>

	<r:layoutResources/>
</body>
</html>