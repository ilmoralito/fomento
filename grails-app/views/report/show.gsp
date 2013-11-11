<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<g:render template="toolbar"/>
	<div class="row">
		<div class="col-md-9">
			<g:if test="${dividends}">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Socio</th>
							<th>FPS</th>
							<th>Dividendos a pagar Socio</th>
							<th>FPE</th>
							<th>Dividendos a pagar Empresa</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${dividends}" var="dividend">
						<tr>
							<td>
								<g:link controller="deduction" action="list" id="${dividend.id}">
									${dividend.partner}
								</g:link>
							</td>
							<td><fomento:fp partner="${dividend.partner}" period="${dividend.period}" fee="fee" capital="capitalization"/></td>
							<td>${dividend.partnerDividend}</td>
							<td><fomento:fp partner="${dividend.partner}" period="${dividend.period}" fee="factoryFee" capital="factoryCapital"/></td>
							<td>${dividend.factoryDividend}</td>
						</tr>
						</g:each>
					</tbody>
				</table>
			</g:if>
			<g:else>
				<h1>Nada que mostrar</h1>
			</g:else>
		</div>
		<div class="col-md-3">
			<br>
			<div class="panel panel-info">
				<div class="panel-heading">
					Resultados
				</div>
				<div class="panel-body">
					<g:render template="panel-body"/>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
