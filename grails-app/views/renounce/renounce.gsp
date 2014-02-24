<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<r:require modules="bootstrap, list, effect"/>
	<title>Document</title>
</head>
<body>
	<br>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover">
					<thead>
						<tr>
							<th colspan="12">Datos del Socio: ${partner}</th>
						</tr>
						<tr>
							<th>Periodo</th>
							<th>Cuotas</th>
							<th>A.Socio</th>
							<th>D.Socio</th>
							<th>Capitalizacion</th>
							<th>% Cap.</th>
							<th>Retiro</th>
							<th>A.Empresa</th>
							<th>D.Empresa</th>
							<th>UP</th>
							<th>F.Capital</th>
							<th>Capital</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${partnerData1}" var="pd" status="i">
							<g:if test="${i==0}">
								<g:set var="capital" value="${partner?.affiliation?.capitalization + partnerData2[i].totalPartnerFee + pd.capitalization}"/>
								<g:set var="fCapital" value="${partner?.affiliation?.factoryCapital + partnerData2[i].totalFactoryFee + pd.factoryDividend}"/>
							</g:if>
							<g:else>
								<g:set var="capital" value="${capital + partnerData2[i].totalPartnerFee + pd.capitalization}"/>
								<g:set var="fCapital" value="${fCapital + partnerData2[i].totalFactoryFee + pd.factoryDividend}"/>
							</g:else>
							<tr>
								<td>${pd.period}</td>
								<td>${partnerData2[i].numberFees}</td>
								<td><g:formatNumber number="${partnerData2[i].totalPartnerFee}" type="number" maxFractionDigits="3"/></td>
								<td><g:formatNumber number="${pd.partnerDividend}" type="number" maxFractionDigits="3"/></td>
								<td><g:formatNumber number="${pd.capitalization}" type="number" maxFractionDigits="3"/></td>
								<td>
									<g:if test="${pd.porcentaje==null}">
										100
									</g:if><else>
										${pd.porcentaje}
									</else>
								</td>
								<td><g:formatNumber number="${pd.withdraw}" type="number" maxFractionDigits="3"/></td>
								<td><g:formatNumber number="${partnerData2[i].totalFactoryFee}" type="number" maxFractionDigits="3"/></td>
								<td><g:formatNumber number="${pd.factoryDividend}" type="number" maxFractionDigits="3"/></td>
								<td><g:formatNumber number="${pd.up}" type="number" maxFractionDigits="3"/></td>
								<td><g:formatNumber number="${capital}" type="number" maxFractionDigits="3"/></td>
								<td><g:formatNumber number="${fCapital}" type="number" maxFractionDigits="3"/></td>
							</tr>
						</g:each>
					</tbody>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<table class="table table-hover">
					<thead>
						<tr>
							<th colspan="11">Datos de Cuotas por periodo</th>
						</tr>
						<tr>
							<th>Periodo</th>
							<th>Cuota</th>
							<th>Numero de Cuotas</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${feesData}" var="fd" status="i">
							<tr>
								<td>${fd.period}</td>
								<td><g:formatNumber number="${fd.fee}" type="number" maxFractionDigits="3"/></td>
								<td>${fd.count}</td>
							</tr>
						</g:each>
					</tbody>
				</table>
			</div>
			<div class="col-md-6">
				<div class="alert alert-danger">
					<p>
					<h4>IMPORTANTE!!!</h4>
					Recuerde que cuando de click a "Confirmar Renuncia", los datos del socio, <strong>${partner}</strong>, seran afectados y algunos de los cambios 
					seran irrebersibles, solo si esta seguro de dar este paso le recomendamos que lo haga, GRACIAS!! 
					</p><br>
					<g:link controller="renounce" action="confirmRenounce" class="btn btn-danger btn-large" params="[partnerId:"${partner.id}"]">Confirmar Renuncia</g:link>
				</div>
			</div>
		</div>
	</div>
</body>
</html>