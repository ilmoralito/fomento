<div class="row alert alert-success">
	<div class="col-md-7">
		<h4 class="headers">Datos de renuncia</h4>
	<p>
		<div class="row">
			<div class="col-md-4"><strong>Socio:</strong></div>
			<div class="col-md-8"><strong>${renounce.partner}</strong></div>
		</div>
	</p>

	<p>
		<div class="row">
			<div class="col-md-4"><strong>Fecha de afiliacion:</strong></div>
			<div class="col-md-8">${renounce.enrollmentDate.format("yyyy-MM-dd")}</div>
		</div>
	</p>

	<p>
		<div class="row">
			<div class="col-md-4"><strong>Fecha de renuncia:</strong></div>
			<div class="col-md-8">${renounce.dateCreated.format("yyyy-MM-dd")}</div>
		</div>
	</p>

	<p>
		<div class="row">
			<div class="col-md-4"><strong>Saldo Inicial del socio:</strong></div>
			<div class="col-md-8">${renounce.residuePartner}</div>
		</div>
	</p>

	<p>
		<div class="row">
			<div class="col-md-4"><strong>Saldo Inicial de la empresa:</strong></div>
			<div class="col-md-8">${renounce.residueFactory}</div>
		</div>
	</p>
	</div>
</div>

<h4 class="headers">Datos de dividendos</h4>
<table class="table table-hover table-bordered trenounce">
	<thead>
		<tr>
			<th colspan="6" class="cap">Datos del Socio</th>
			<th colspan="3" class="cap">Datos de la Empresa</th>
			<th colspan="3" class="cap">Datos del Periodo</th>
		</tr>
		<tr>
			<!--Partner-->
			<th>Periodo</th>
			<th>Cuotas</th>
			<th>Dividendo</th>
			<th>Capitalizacion</th>
			<th>Retiro</th>
			<th>Capital</th>
			<!--Empresa-->
			<th>Cuotas</th>
			<th>Dividendo</th>
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
