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
			<form role="form" method="post">
				<div class="form-group">
					<label class="sr-only" for="up">Utilidad del periodo</label>
					<input type="text" class="form-control" id="up" name="up" placeholder="Utilidad del periodo" autofocus="true">
				</div>
				<button type="submit" class="btn btn-default">Enviar</button>
			</form>
			<br>

			<div class="well well-sm">
				<div class="btn-group">
					<fomento:periods ctrl="report"/>
				</div>
			</div>

			<g:if test="${tas}">
				<div class="panel panel-info">
					<div class="panel-heading">
						Resultados
					</div>
					<div class="panel-body">
						<p>TAS:${tas}</p>
						<p>UP:${up}</p>
						<p>Periodo: ${period}</p>
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
