<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<g:render template="/partner/toolbar"/>
	<g:render template="/layouts/navbar"/>

	<g:if test="${fees}">
		<div class="row">
			<div class="col-md-6">
				<div class="row">
					<div class="col-md-6"><strong>Total aporte personal: ${total}</strong></div>
					<div class="col-md-6"><strong>Total aporte Empresa: ${totalFactoryFee}</strong></div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="row">
					<div class="col-md-6">
						<!---->
					</div>
					<div class="col-md-6">
						<div class="pull-right">
							<div class="btn-group btn-group-xs">
								<fomento:periods/>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<br>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Cuota de socio</th>
					<th>Cuota de empresa</th>
					<th>Fecha de cuota</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${fees}" var="fee">
					<tr>
						<td>${fee}</td>
						<td>${fee.factoryFee}</td>
						<td>${fee.paymentDate.format("yyyy-MM-dd")}</td>
						<td style="width:1px;"></td>
					</tr>
				</g:each>
			</tbody>
		</table>
	</g:if>
	<g:else>
		<p><br>Nada que mostrar</p>
	</g:else>
</body>
</html>
