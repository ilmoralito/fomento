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
							<th width="200">Socio</th>
							<th>FPS</th>
							<th>DS</th>
							<th>DNS</th>
							<th>FPE</th>
							<th>DE</th>
							<th>DNE</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${dividends}" var="dividend">
							<g:set var="dns" value="${dividend.partnerDividend - (dividend.partnerDividend * 0.1)}"/>
							<g:set var="dne" value="${dividend.factoryDividend - (dividend.factoryDividend * 0.1)}"/>

							<tr>
								<td><g:link action="capitalize" id="${dividend.id}">${dividend.partner}</g:link></td>
								<td>${dividend.fps}</td>
								<td>${dividend.partnerDividend}</td>
								<td>${dns}</td>
								<td>${dividend.fpe}</td>
								<td>${dividend.factoryDividend}</td>
								<td>${dne}</td>
							</tr>
						</g:each>
						<tr>
							<td>TOTAL</td>
							<td></td>
							<td><g:formatNumber number="${pds * up}" type="number" maxFractionDigits="3"/></td>
							<td></td>
							<td></td>
							<td><g:formatNumber number="${pde * up}" type="number" maxFractionDigits="3"/></td>
							<td></td>
						</tr>
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
				<g:render template="panel-heading"/>
				<div class="panel-body">
					<g:render template="panel-body"/>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
