<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<div class="row">
		<div class="col-md-10">
			<g:if test="${partners}">
				<table class="table table-hover">
					<thead>
						<th>Socio</th>
						<th>APS</th>
						<th>FPS</th>
						<th>DD</th>
						<th>DP</th>
					</thead>
					<tbody>
						<g:each in="${partners}" var="partner">
							<tr>
								<td>${partner}</td>
								<td><g:formatNumber number="${fomento.aps(partner:partner, period:period).toDouble()}" type="number" maxFractionDigits="2"/></td>
								<td><g:formatNumber number="${fomento.fps(tas:tas, partner:partner, period:period).toDouble()}" type="number" maxFractionDigits="2"/></td>
								<td><g:formatNumber number="${up.toDouble() * fomento.fps(tas:tas, partner:partner, period:period).toDouble()}" type="number" maxFractionDigits="2"/></td>
								<td><g:formatNumber number="${up.toDouble() * fomento.fps(tas:tas, partner:partner, period:period).toDouble() - (up.toDouble() * fomento.fps(tas:tas, partner:partner, period:period).toDouble()) * 0.1}" type="number" maxFractionDigits="2"/></td>
							</tr>
						</g:each>
					</tbody>
				</table>
			</g:if>
			<g:else>
				<h3>Nada que mostrar</h3>
			</g:else>
		</div>
		<div class="col-md-2">
			<g:form action="dividends">
				<div class="form-group">
					<label class="sr-only" for="up">Utilidad del periodo</label>
					<g:textField name="up" class="form-control" value="${params?.up}" placeholder="Uitilidad del periodo" autofocus="true"/>
				</div>
			</g:form>
			<div class="btn-group">
				<fomento:periods ctrl="report"/>
			</div>
			<br>
			<br>
			<g:if test="${tas}">
				<div class="panel panel-info">
					<div class="panel-heading">
						Resultados
					</div>
					<div class="panel-body">
						<table class="table">
							<tbody>
								<tr>
									<td>TAS</td>
									<td>${tas}</td>
								</tr>
								<tr>
									<td>UP</td>
									<td>${up}</td>
								</tr>
								<tr>
									<td>Perido</td>
									<td>${period}</td>
								</tr>
							</tbody>
						</table>
						<g:form action="applyDividends">
							<button type="submit" class="btn btn-default">
								<span class="glyphicon glyphicon-floppy-save"></span>
							</button>
						</g:form>
					</div>
				</div>
			</g:if>

			<g:hasErrors bean="${cmd}">
				<div class="alert">
					<g:renderErrors bean="${cmd}"/>
				</div>
			</g:hasErrors>
		</div>
	</div>
</body>
</html>
