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

					<g:if test="${fees}">
					<div class="panel panel-default  panelUp">
						<div class="panel-heading">Datos del periodo</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-7 cabValor">Numero de cuotas</div>
								<div class="col-md-5 valor">${fees.size()}</div>
							</div>
							<br>
							<div class="row">
								<div class="col-md-7 cabValor">Aporte socio</div>
								<div class="col-md-5 valor">${totalPartnerFee}</div>
							</div>
							<br>
							<div class="row">
								<div class="col-md-7 cabValor">Aporte empresa</div>
								<div class="col-md-5 valor">${totalFactoryFee}</div>
							</div>
						</div>
					</div>
				</g:if>

				<div class="panel panel-default">
					<div class="panel-heading">Datos de socio</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-7 cabValor">Saldo Socio</div>
							<div class="col-md-5 valor"><fomento:partnerSaldo partner="${partner}" flag="socio" /></div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-7 cabValor">Saldo Empresa</div>
							<div class="col-md-5 valor"><fomento:partnerSaldo partner="${partner}" flag="empresa" /></div>
						</div>
					</div>
				</div>
		
			</div>
		</div>
	</div>

	<r:layoutResources/>
</body>
</html>