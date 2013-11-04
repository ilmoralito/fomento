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
			<div class="col-md-10">
				<g:layoutBody/>
			</div>
			<div class="col-md-2">
				<div class="well well-sm">
					<div class="btn-group">
						<fomento:periods/>
					</div>
				</div>

				<!--Aporte inicial-->
				<div class="panel panel-default">
					<div class="panel-heading">
						Detalle
					</div>
					<div class="panel-body">
						<table class="table">
							<tbody>
								<tr>
									<td style="width:1px;"><span class="glyphicon glyphicon-pushpin"></span></td>
									<td>${partner?.affiliation?.capitalization}</td>
								</tr>
								<tr>
									<td><span class="glyphicon glyphicon-time"></span></td>
									<td>${(dividend) ?: 'NAP'}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>

				<!--Totales de cuotas por socio y empresa-->
				<div class="panel panel-default">
					<div class="panel-heading">Total coutas</div>
					<div class="panel-body">
						<table class="table">
							<tbody>
								<tr>
									<td style="width:1px;"><span class="glyphicon glyphicon-user"></span></td>
									<td>${totalPartnerFee}</td>
								</tr>
								<tr>
									<td><span class="glyphicon glyphicon-briefcase"></span></td>
									<td>${totalPartnerFee}</td>
								</tr>
								<tr>
									<td><span class="glyphicon glyphicon-plus"></span></td>
									<td>${totalPartnerFee + totalPartnerFee}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<r:layoutResources/>
</body>
</html>