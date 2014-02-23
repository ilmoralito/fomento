<h4 class="headers">Datos de renuncia</h4>
<p>
	<div class="row">
		<div class="col-md-2">Socio</div>
		<div class="col-md-10">${renounce.partner}</div>
	</div>
</p>

<p>
	<div class="row">
		<div class="col-md-2">Fecha de renuncia</div>
		<div class="col-md-10">${renounce.dateCreated.format("yyyy-MM-dd")}</div>
	</div>
</p>

<p>
	<div class="row">
		<div class="col-md-2">Fecha de afiliacion</div>
		<div class="col-md-10">${renounce.enrollmentDate.format("yyyy-MM-dd")}</div>
	</div>
</p>

<p>
	<div class="row">
		<div class="col-md-2">Saldo de socio</div>
		<div class="col-md-10">${renounce.residuePartner}</div>
	</div>
</p>

<p>
	<div class="row">
		<div class="col-md-2">Saldo de la empresa</div>
		<div class="col-md-10">${renounce.residueFactory}</div>
	</div>
</p>

<h4 class="headers">Datos de dividendos</h4>
<table class="table table-hover table-bordered">
	<thead>
		<tr>
			<th></th>
			<th colspan="5">Socio</th>
			<th colspan="3">Empresa</th>
			<th colspan="3">Periodo</th>
		</tr>
		<tr>
			<th width="1"></th>
			<!--Partner-->
			<th>Cuotas</th>
			<th>Dividendos</th>
			<th>Capitalizacion</th>
			<th>Retiro</th>
			<th>Capital</th>
			<!--Empresa-->
			<th>Cuotas</th>
			<th>Dividendos</th>
			<th>Capital</th>
			<!--Period data-->
			<th width="1">Cuotas</th>
			<th>UP</th>
			<th>Porcentaje</th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${renounce.histories}" var="history">
			<tr>
				<td>${history.period}</td>
				<td>${history.totalPartnerFee}</td>
				<td>${history.partnerDividend}</td>
				<td>${history.capitalization}</td>
				<td>${history.withdraw}</td>
				<td>${history.capital}</td>
				<td>${history.totalfactoryFee}</td>
				<td>${history.factoryDividend}</td>
				<td>${history.factoryCapital}</td>
				<td>${history.numberFees}</td>
				<td>${history.up}</td>
				<td>${history.percentage}</td>
			</tr>
		</g:each>
	</tbody>
</table>

<h4 class="headers">Datos de cuotas por periodo</h4>
<table class="table table-hover">
	<thead>
		<th width="1">Periodo</th>
		<th width="1">Cuota</th>
		<th>Numero</th>
	</thead>
	<tbody>
		<g:each in="${renounce.histories.feeHistory}" var="fh">
			<g:each in="${fh}" var="f">
				<tr>
					<td>${f.history.period}</td>
					<td>${f.fee}</td>
					<td>${f.numberFee}</td>
				</tr>
			</g:each>
		</g:each>
	</tbody>
</table>
